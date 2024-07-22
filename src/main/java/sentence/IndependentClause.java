package sentence;

import components.*;
import components.Number;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import word.WordPair;
import word.english.EnglishNoun;
import word.english.EnglishVerb;
import word.latin.LatinNoun;
import word.latin.LatinVerb;
import wordentry.WordEntryPair;
import wordentry.WordListRegistry;
import wordentry.english.EnglishNounEntry;
import wordentry.english.EnglishVerbEntry;
import wordentry.latin.LatinNounEntry;
import wordentry.latin.LatinVerbEntry;

public class IndependentClause {
    @NotNull
    public final WordPair verb;
    @Nullable
    public final WordPair subject;
    @Nullable
    public final WordPair directObject;
    // TODO indirect objects

    public IndependentClause() {
        WordEntryPair verb = WordListRegistry.randomWord(PartOfSpeech.VERB);
        Tense tense = Tense.randomIndependent();
        Number number = Number.random();
        Person person = Person.randomWeighted();
        this.verb = new WordPair(
                new EnglishVerb((EnglishVerbEntry) verb.getEnglish(), tense, number, person),
                new LatinVerb((LatinVerbEntry) verb.getLatin(), tense, number, person)
        );

        if (verb.tags.contains("transitive")) {
            WordEntryPair directObject = WordListRegistry.randomWord(PartOfSpeech.NOUN);
            number = Number.random();
            this.directObject = new WordPair(
                    new EnglishNoun((EnglishNounEntry) directObject.getEnglish(), number),
                    new LatinNoun((LatinNounEntry) directObject.getLatin(), NounCase.ACCUSATIVE, number)
            );
        } else {
            directObject = null;
        }

        if (((LatinVerb)this.verb.latin).person == Person.THIRD) {
            WordEntryPair subject = WordListRegistry.randomWord(PartOfSpeech.NOUN, (((LatinVerb) this.verb.latin).number == Number.PLURAL) ? entry -> !entry.tags.contains("mass") : entry -> true);
            number = ((LatinVerb) this.verb.latin).number;
            this.subject = new WordPair(
                    new EnglishNoun((EnglishNounEntry) subject.getEnglish(), number),
                    new LatinNoun((LatinNounEntry) subject.getLatin(), NounCase.NOMINATIVE, number)
            );
        } else {
            // TODO first and second subject pronouns
            subject = null;
        }
    }

    public String assembleLatin() {
        String result = "";
        if (subject != null) {
            result += ((LatinNoun) subject.latin).get() + " ";
        }
        if (directObject != null) {
            result += ((LatinNoun) directObject.latin).get() + " ";
        }
        result += ((LatinVerb) verb.latin).get();
        return result;
    }
}
