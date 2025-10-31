package sentence.verb;

import components.Number;
import components.PartOfSpeech;
import components.Person;
import components.Tense;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import util.Percentages;
import util.Util;
import word.WordPair;
import word.english.EnglishAdverb;
import word.english.EnglishInfinitiveVerb;
import word.english.EnglishVerb;
import word.latin.LatinAdjective;
import word.latin.LatinAdverb;
import word.latin.LatinInfinitiveVerb;
import word.latin.LatinVerb;
import wordentry.WordEntryPair;
import wordentry.WordListRegistry;
import wordentry.english.EnglishAdverbEntry;
import wordentry.english.EnglishVerbEntry;
import wordentry.latin.LatinAdverbEntry;
import wordentry.latin.LatinVerbEntry;

import java.util.ArrayList;

public class VerbPhrase {
    @NotNull
    public final WordPair verb;
    @Nullable
    public final WordPair infinitive;

    public final Tense tense;
    public final Number number;
    public final Person person;

    public final WordPair[] adverbs;

    public final ArrayList<String> tags;

    public VerbPhrase() {
        WordEntryPair verb = WordListRegistry.randomWord(PartOfSpeech.VERB);

        if (verb.tags.contains("auxiliary")) {
            Tense tense = verb.randomTense();
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

        int adverbCount = Util.recursiveRandom(-2, Percentages.recursive_adverb_chance.value);
        if (adverbCount < 0) adverbCount = 0;
        adverbs = new WordPair[adverbCount];
        for (int i = 0; i < adverbCount; i++) {
            ArrayList<WordEntryPair> used = new ArrayList<>();
            var adverb = WordListRegistry.randomWord(PartOfSpeech.ADVERB, word -> !used.contains(word));
            used.add(adverb);
            adverbs[i] = new WordPair(
                    new EnglishAdverb((EnglishAdverbEntry) adverb.getEnglish()),
                    new LatinAdverb((LatinAdverbEntry) adverb.getLatin())
            );
        }
    }

    public String assembleLatin() {
        String result = "";
        if (infinitive != null) {
            result += ((LatinInfinitiveVerb) infinitive.latin).get() + " ";
        }

        for (WordPair adverb : adverbs) {
            result += ((LatinAdverb) adverb.latin).get() + " ";
        }

        result += ((LatinVerb) verb.latin).get();
        return result;
    }
    public String assembleEnglish() {
        String result = "";

        if (adverbs.length == 1) result += ((EnglishAdverb) adverbs[0].english).get() + " ";
        else if (adverbs.length == 2) result += ((EnglishAdverb) adverbs[0].english).get() + " and " + ((EnglishAdverb) adverbs[1].english).get() + " ";
        else for (int i = 0; i < adverbs.length; i++) {
            result += ((EnglishAdverb) adverbs[i].english).get();
            if (i < adverbs.length - 1) result += ",";
            result += " ";
        }

        result += ((EnglishVerb) verb.english).get();
        if (infinitive != null) {
            result += " to " + ((EnglishInfinitiveVerb) infinitive.english).get();
        }
        return result;
    }
}
