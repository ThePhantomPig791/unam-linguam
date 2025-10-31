package sentence.noun;

import components.NounCase;
import components.Number;
import components.PartOfSpeech;
import org.jetbrains.annotations.NotNull;
import util.Percentages;
import util.Util;
import word.WordPair;
import word.english.EnglishAdjective;
import word.english.EnglishNoun;
import word.latin.LatinAdjective;
import word.latin.LatinNoun;
import wordentry.WordEntryPair;
import wordentry.WordListRegistry;
import wordentry.english.EnglishAdjectiveEntry;
import wordentry.english.EnglishNounEntry;
import wordentry.latin.LatinAdjectiveEntry;
import wordentry.latin.LatinNounEntry;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class SimpleNounPhrase extends NounPhrase {
    @NotNull
    public final WordPair noun;
    public final WordPair[] adjectives;

    public final Number number;

    public SimpleNounPhrase(Number number, NounCase c) {
        this.number = number;

        WordEntryPair noun = WordListRegistry.randomWord(PartOfSpeech.NOUN, (number == Number.PLURAL) ? entry -> !entry.tags.contains("uncountable") : entry -> true);
        this.noun = new WordPair(
                new EnglishNoun((EnglishNounEntry) noun.getEnglish(), number),
                new LatinNoun((LatinNounEntry) noun.getLatin(), c, number)
        );

        int adjectiveCount = Math.max(Util.recursiveRandom(-1, Percentages.recursive_adjective_chance.value), 0);
        this.adjectives = new WordPair[adjectiveCount];
        for (int i = 0; i < adjectiveCount; i++) {
            WordEntryPair adjective = WordListRegistry.randomWord(PartOfSpeech.ADJECTIVE, entry -> !Util.arrayContains(this.adjectives, entry));
            this.adjectives[i] = new WordPair(
                    new EnglishAdjective((EnglishAdjectiveEntry) adjective.getEnglish(), number),
                    new LatinAdjective((LatinAdjectiveEntry) adjective.getLatin(), ((LatinNounEntry) noun.getLatin()).gender, c, number)
            );
        }
    }

    public String getLatin() {
        if (adjectives.length == 0) return ((LatinNoun) noun.latin).get();

        String result = "";

        /*int nounIndex = (int) (Math.random() * adjectives.length);
        for (int i = 0; i < adjectives.length; i++) {
            if (nounIndex == i) {
                result += ((LatinNoun) noun.latin).get();
                nounIndex = -1;
                i--;
            } else {
                WordPair adjective = adjectives[i];
                result += ((LatinAdjective) adjective.latin).get();
            }
            if (i != adjectives.length - 1) result += " ";
        }*/
        result += ((LatinNoun) noun.latin).get();
        for (WordPair adjective : adjectives) {
            result += " " + ((LatinAdjective) adjective.latin).get();
        }

        return result;
    }

    public String getEnglish() {
        String result = "";

        List<WordPair> sortedAdjectives = Stream.of(adjectives).sorted(Comparator.comparingInt(pair -> ((EnglishAdjective) pair.english).wordEntry.priority)).toList();
        for (WordPair adjective : adjectives) {
            result += ((EnglishAdjective) adjective.english).get() + " ";
        }
        result += ((EnglishNoun) noun.english).get();

        if (!((EnglishNoun) noun.english).wordEntry.tags.contains("proper")) {
            if (number == Number.PLURAL || Math.random() < 0.5) {
                result = "the " + result;
            } else {
                if (Util.isVowel(result.charAt(0))) {
                    result = "an " + result;
                } else {
                    result = "a " + result;
                }
            }
        }

        return result;
    }
}
