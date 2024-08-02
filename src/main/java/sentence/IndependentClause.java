package sentence;

import components.*;
import components.Number;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import sentence.verb.InterrogativeVerbPhrase;
import sentence.verb.VerbPhrase;
import word.WordPair;
import word.english.EnglishNoun;
import word.latin.LatinNoun;
import wordentry.WordEntryPair;
import wordentry.WordListRegistry;
import wordentry.english.EnglishNounEntry;
import wordentry.latin.LatinNounEntry;

public class IndependentClause {
    @NotNull
    public final VerbPhrase verb;
    @Nullable
    public final WordPair subject;
    @Nullable
    public final WordPair directObject;
    // TODO indirect objects

    public IndependentClause() {
        double random = Math.random();
        if (random <= 0.9) {
            this.verb = new VerbPhrase();
        } else {
            this.verb = new InterrogativeVerbPhrase();
        }

        if (verb.tags.contains("transitive")) {
            WordEntryPair directObject = WordListRegistry.randomWord(PartOfSpeech.NOUN);
            Number number = Number.random();
            this.directObject = new WordPair(
                    new EnglishNoun((EnglishNounEntry) directObject.getEnglish(), number),
                    new LatinNoun((LatinNounEntry) directObject.getLatin(), NounCase.ACCUSATIVE, number)
            );
        } else {
            directObject = null;
        }

        if (verb.person == Person.THIRD) {
            WordEntryPair subject = WordListRegistry.randomWord(PartOfSpeech.NOUN, (verb.number == Number.PLURAL) ? entry -> !entry.tags.contains("uncountable") : entry -> true);
            Number number = verb.number;
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

        if (verb instanceof InterrogativeVerbPhrase) {
            result += verb.assembleLatin();
            if (subject != null) {
                result += " " + ((LatinNoun) subject.latin).get();
            }
            if (directObject != null) {
                result += " " + ((LatinNoun) directObject.latin).get();
            }
        } else {
            if (subject != null) {
                result += ((LatinNoun) subject.latin).get() + " ";
            }
            if (directObject != null) {
                result += ((LatinNoun) directObject.latin).get() + " ";
            }
            result += verb.assembleLatin();
        }

        if (!result.isEmpty()) {
            result = result.substring(0, 1).toUpperCase() + result.substring(1);
        }
        result += (verb instanceof InterrogativeVerbPhrase) ? "?" : ".";
        return result;
    }
}
