package wordentry;

import components.PartOfSpeech;
import wordentry.english.EnglishWordEntry;
import wordentry.latin.LatinWordEntry;

import java.util.ArrayList;
import java.util.List;

public class WordEntryPair {
    public final String key;
    public final PartOfSpeech partOfSpeech;

    public final ArrayList<EnglishWordEntry> english = new ArrayList<>();
    public final ArrayList<LatinWordEntry> latin = new ArrayList<>();

    public final ArrayList<String> tags = new ArrayList<>();

    public WordEntryPair(String key, PartOfSpeech pos) {
        this.key = key;
        this.partOfSpeech = pos;
    }
    public WordEntryPair(String key, PartOfSpeech pos, EnglishWordEntry e, LatinWordEntry l) {
        this.key = key;
        this.partOfSpeech = pos;
        english.add(e);
        latin.add(l);
    }
    public WordEntryPair(String key, PartOfSpeech pos, ArrayList<EnglishWordEntry> e, ArrayList<LatinWordEntry> l) {
        this.key = key;
        this.partOfSpeech = pos;
        english.addAll(e);
        latin.addAll(l);
    }

    public void add(EnglishWordEntry e) {
        english.add(e);
    }
    public void add(LatinWordEntry l) {
        latin.add(l);
    }
    public void add(EnglishWordEntry e, LatinWordEntry l) {
        add(e);
        add(l);
    }

    public void addTag(String tag) {
        tags.add(tag);
    }
    public void addTags(List<String> tags) {
        this.tags.addAll(tags);
    }

    public EnglishWordEntry getEnglish() {
        return english.get((int) (Math.random() * english.size()));
    }
    public LatinWordEntry getLatin() {
        return latin.get((int) (Math.random() * latin.size()));
    }

    public boolean matches(EnglishWordEntry e) {
        return english.contains(e);
    }
    public boolean matches(LatinWordEntry l) {
        return latin.contains(l);
    }


    @Override
    public String toString() {
        return "WordEntryPair[key=" + key + "partOfSpeech=" + partOfSpeech + ", english=" + english + ", latin=" + latin + "]";
    }
}