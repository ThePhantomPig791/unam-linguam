package word.english;

import components.Number;
import wordentry.english.EnglishAdjectiveEntry;
import wordentry.english.EnglishNounEntry;

public class EnglishAdjective extends EnglishWord {
    public final EnglishAdjectiveEntry wordEntry;

    public final Number number;

    public EnglishAdjective(EnglishAdjectiveEntry entry, Number number) {
        this.wordEntry = entry;
        this.number = number;
    }
    public EnglishAdjective(EnglishAdjectiveEntry entry) {
        this(entry, Number.random());
    }

    public String get() {
        return wordEntry.adjective;
    }
}
