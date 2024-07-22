package word.latin;

import wordentry.latin.LatinVerbEntry;

public class LatinInfinitiveVerb extends LatinWord {
    public final LatinVerbEntry wordEntry;

    public LatinInfinitiveVerb(LatinVerbEntry entry) {
        this.wordEntry = entry;
    }

    public String get() {
        return wordEntry.infinitive;
    }
}
