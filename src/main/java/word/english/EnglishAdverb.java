package word.english;

import wordentry.english.EnglishAdverbEntry;

public class EnglishAdverb extends EnglishWord {
    public final EnglishAdverbEntry entry;

    public EnglishAdverb(EnglishAdverbEntry entry) {
        this.entry = entry;
    }

    public String get() {
        return entry.adverb;
    }
}
