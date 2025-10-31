package word.latin;

import wordentry.latin.LatinAdverbEntry;

public class LatinAdverb extends LatinWord {
    public final LatinAdverbEntry entry;

    public LatinAdverb(LatinAdverbEntry entry) {
        this.entry = entry;
    }

    public String get() {
        return entry.adverb;
    }
}
