package word.latin;

import components.Number;
import components.Person;
import components.Tense;
import components.VerbEndings;
import wordentry.latin.LatinVerbEntry;

public class LatinVerb extends LatinWord {
    public final LatinVerbEntry wordEntry;

    public final Tense tense;
    public final Person person;
    public final Number number;

    public LatinVerb(LatinVerbEntry entry, Tense tense, Number number, Person person) {
        this.wordEntry = entry;
        this.tense = tense;
        this.person = person;
        this.number = number;
    }
    public LatinVerb(LatinVerbEntry entry) {
        this(entry, Tense.randomIndependent(), Number.random(), Person.randomWeighted());
    }

    public String get() {
        if (!tense.isPerfect()) {
            String stem = wordEntry.infinitive;
            stem = stem.substring(0, stem.lastIndexOf(VerbEndings.INFINITIVE_STEMS[wordEntry.conjugation.ordinal()]));

            String stemVowel = VerbEndings.getStemVowel(wordEntry.conjugation, tense, number, person);

            return stem + stemVowel + VerbEndings.getEndings(wordEntry.conjugation)[tense.ordinal()][number.ordinal()][person.ordinal()];
        } else {
            String stem = wordEntry.perfect;
            stem = stem.substring(0, stem.lastIndexOf(VerbEndings.PERFECT_ENDINGS[tense.ordinal() - 3][0][0]));

            return stem + VerbEndings.PERFECT_ENDINGS[tense.ordinal() - 3][number.ordinal()][person.ordinal()];
        }
    }
}
