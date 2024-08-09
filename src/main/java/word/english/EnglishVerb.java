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

    public String get() {
        if (tense == Tense.PRESENT) {
            if (person == Person.THIRD && number == Number.SINGULAR) return wordEntry.thirdPersonSingular;
            else return wordEntry.infinitive;
        } else if (tense == Tense.FUTURE) {
            return "will " + wordEntry.infinitive;
        } else if (tense == Tense.PERFECT) {
            return wordEntry.past;
        } else if (tense == Tense.IMPERFECT) {
            return ((number == Number.PLURAL || person == Person.SECOND) ? "were " : "was ") + wordEntry.presentParticiple;
        } else if (tense == Tense.FUTURE_PERFECT) {
            return "will have " + wordEntry.past;
        } else { // pluperfect
            return "had " + wordEntry.pastParticiple;
        }
    }
}
