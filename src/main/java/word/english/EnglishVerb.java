package word.english;

import components.Number;
import components.Person;
import components.Tense;
import wordentry.english.EnglishVerbEntry;

public class EnglishVerb extends EnglishWord {
    public final EnglishVerbEntry wordEntry;

    public final Tense tense;
    public final Person person;
    public final Number number;

    public EnglishVerb(EnglishVerbEntry entry, Tense tense, Number number, Person person) {
        this.wordEntry = entry;
        this.tense = tense;
        this.person = person;
        this.number = number;
    }
    public EnglishVerb(EnglishVerbEntry entry) {
        this(entry, Tense.randomIndependent(), Number.random(), Person.randomWeighted());
    }
}
