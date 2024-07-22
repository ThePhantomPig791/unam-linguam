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
    public final VerbPhrase verb;
    @Nullable
    public final WordPair subject;
    @Nullable
    public final WordPair directObject;
    // TODO indirect objects

    public IndependentClause() {
        this.verb = new VerbPhrase();

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

        if (verb.type == VerbPhrase.Type.INTERROGATIVE) {
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
        result += (verb.type == VerbPhrase.Type.INTERROGATIVE) ? "?" : ".";
        return result;
    }
}
