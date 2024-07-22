package wordentry;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import json.GsonHelper;
import components.PartOfSpeech;
import wordentry.english.EnglishAdjectiveEntry;
import wordentry.english.EnglishNounEntry;
import wordentry.english.EnglishVerbEntry;
import wordentry.english.EnglishWordEntry;
import wordentry.latin.LatinAdjectiveEntry;
import wordentry.latin.LatinNounEntry;
import wordentry.latin.LatinVerbEntry;
import wordentry.latin.LatinWordEntry;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class WordListRegistry {
    public static final Path WORD_LIST_DIRECTORY = Paths.get(System.getProperty("user.dir") + "/src/main/resources/wordlists/");

    public static final ArrayList<WordEntryPair> ALL_WORD_ENTRIES = new ArrayList<>();

    public static final String NAME_FIELD = "name";

    public static void init() {
        Gson gson = new Gson();

        try (Stream<Path> paths = Files.walk(WORD_LIST_DIRECTORY)) {
            paths.filter(Files::isRegularFile)
                .forEach(path -> {
                    String filename = path.getFileName().toString().substring(0, path.getFileName().toString().indexOf("."));
                    try {
                        JsonObject json = gson.fromJson(new JsonReader(new FileReader(path.toFile())), JsonObject.class);
                        String name = json.get(NAME_FIELD).getAsJsonPrimitive().getAsString();
                        WordList list = new WordList(name, filename);


                        System.out.println("Parsing words for list " + name + " {" + filename + "}...");

                        parseEntries(json, "nouns", PartOfSpeech.NOUN, list, EnglishNounEntry::fromJson, LatinNounEntry::fromJson);
                        parseEntries(json, "verbs", PartOfSpeech.VERB, list, EnglishVerbEntry::fromJson, LatinVerbEntry::fromJson);
                        parseEntries(json, "adjectives", PartOfSpeech.ADJECTIVE, list, EnglishAdjectiveEntry::fromJson, LatinAdjectiveEntry::fromJson);

                        System.out.println("Finished parsing words for list " + list.name + " {" + list.id + "}\n");
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void parseEntries(JsonObject json, String type, PartOfSpeech pos, WordList list, Function<JsonObject, EnglishWordEntry> englishParser, Function<JsonObject, LatinWordEntry> latinParser) {
        if (json.get(type) == null) {
            System.out.println(" | Found 0 " + type);
            return;
        }
        JsonObject entries = json.get(type).getAsJsonObject();
        System.out.println(" | Parsing " + type + "...");

        AtomicInteger count = new AtomicInteger();
        entries.asMap().forEach((key, jsonElement) -> {
            WordEntryPair entry = new WordEntryPair(key, pos);

            JsonObject jsonObject = jsonElement.getAsJsonObject();
            JsonElement english = jsonObject.get("english"), latin = jsonObject.get("latin");
            if (english == null || latin == null) {
                throw new JsonParseException("Expected fields \"english\" and \"latin\" for entry " + key + " in WordList " + list.id);
            }

            if (english.isJsonObject()) {
                entry.add(englishParser.apply(english.getAsJsonObject()));
            }
            if (english.isJsonArray()) {
                english.getAsJsonArray().asList().forEach(element -> {
                    entry.add(englishParser.apply(element.getAsJsonObject()));
                });
            }

            if (latin.isJsonObject()) {
                entry.add(latinParser.apply(latin.getAsJsonObject()));
            }
            if (latin.isJsonArray()) {
                latin.getAsJsonArray().asList().forEach(element -> {
                    entry.add(latinParser.apply(element.getAsJsonObject()));
                });
            }

            if (jsonObject.has("tags")) {
                entry.addTags(List.of(GsonHelper.getStringArray(jsonObject.get("tags"))));
            }

            list.entries.add(entry);
            ALL_WORD_ENTRIES.add(entry);
            count.incrementAndGet();
            System.out.println(" || " + key);
        });
        System.out.println(" | Parsed " + count + " " + type);
    }

    public static WordEntryPair randomWord(Predicate<WordEntryPair> predicate) {
        List<WordEntryPair> filtered = ALL_WORD_ENTRIES.stream().filter(predicate).toList();
        return filtered.get((int) (Math.random() * filtered.size()));
    }
    public static WordEntryPair randomWord(PartOfSpeech pos) {
        return randomWord(wordEntryPair -> wordEntryPair.partOfSpeech == pos);
    }
    public static WordEntryPair randomWord(PartOfSpeech pos, Predicate<WordEntryPair> predicate) {
        return randomWord(wordEntryPair -> (wordEntryPair.partOfSpeech == pos && predicate.test(wordEntryPair)));
    }
    public static WordEntryPair randomWord() {
        return randomWord(wordEntryPair -> true);
    }

    public static WordEntryPair get(String id) {
        for (WordEntryPair wordEntryPair : ALL_WORD_ENTRIES) {
            if (wordEntryPair.key.equals(id)) return wordEntryPair;
        }
        return null;
    }
}
