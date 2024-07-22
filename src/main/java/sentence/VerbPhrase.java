package sentence;

import components.Number;
import components.PartOfSpeech;
import components.Person;
import components.Tense;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import word.WordPair;
import word.english.EnglishInfinitiveVerb;
import word.english.EnglishVerb;
import word.latin.LatinInfinitiveVerb;
import word.latin.LatinVerb;
import wordentry.WordEntryPair;
import wordentry.WordListRegistry;
import wordentry.english.EnglishVerbEntry;
import wordentry.latin.LatinVerbEntry;

import java.util.ArrayList;

public class VerbPhrase {
    public final Type type;

    @NotNull
    public final WordPair verb;
    @Nullable
    public final WordPair infinitive;

    public final Tense tense;
    public final Number number;
    public final Person person;

    public final ArrayList<String> tags;

    public VerbPhrase() {
        int random = (int) (Math.random() * 100);
        if (random >= 90) {
            this.type = Type.INTERROGATIVE;
        } else {
            this.type = Type.SIMPLE;
        }

        WordEntryPair verb = WordListRegistry.randomWord(PartOfSpeech.VERB);

        if (verb.tags.contains("auxiliary")) {
            Tense tense = Tense.randomIndependent();
            Number number = Number.random();
            Person person = Person.randomWeighted();
            this.verb = new WordPair(
                    new EnglishVerb((EnglishVerbEntry) verb.getEnglish(), tense, number, person),
                    new LatinVerb((LatinVerbEntry) verb.getLatin(), tense, number, person)
            );
            // tense, number, and person get set from the auxiliary verb; in "i want to bake a cake" the subject comes from want
            this.tense = tense;
            this.number = number;
            this.person = person;

            verb = WordListRegistry.randomWord(PartOfSpeech.VERB, entry -> !entry.tags.contains("auxiliary"));

            this.infinitive = new WordPair(
                    new EnglishInfinitiveVerb((EnglishVerbEntry) verb.getEnglish()),
                    new LatinInfinitiveVerb((LatinVerbEntry) verb.getLatin())
            );
            // tags get set from the infinitive; in "i want to bake a cake" the transitiveness of the verb phrase comes from to bake
            this.tags = verb.tags;
        } else {
            Tense tense = Tense.randomIndependent();
            Number number = Number.random();
            Person person = Person.randomWeighted();
            this.verb = new WordPair(
                    new EnglishVerb((EnglishVerbEntry) verb.getEnglish(), tense, number, person),
                    new LatinVerb((LatinVerbEntry) verb.getLatin(), tense, number, person)
            );

            this.tags = verb.tags;

            this.infinitive = null;
            this.tense = tense;
            this.number = number;
            this.person = person;
        }
    }

    public String assembleLatin() {
        String result = "";
        if (type == Type.SIMPLE) {
            if (infinitive != null) {
                result += ((LatinInfinitiveVerb) infinitive.latin).get() + " ";
            }
            result += ((LatinVerb) verb.latin).get();
        } else {
            result += ((LatinVerb) verb.latin).get() + "ne";
            if (infinitive != null) {
                result += " " + ((LatinInfinitiveVerb) infinitive.latin).get();
            }
        }
        return result;
    }

    public enum Type {
        SIMPLE, // one conjugated verb with or without an infinitive
        INTERROGATIVE // one conjugated verb with an enclitic -ne
    }
}
