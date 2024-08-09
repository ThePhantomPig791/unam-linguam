package sentence;

import components.*;
import components.Number;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import sentence.noun.NounPhrase;
import sentence.verb.InterrogativeVerbPhrase;
import sentence.verb.VerbPhrase;
import util.Percentages;

public class IndependentClause {
    @NotNull
    public final VerbPhrase verb;
    @Nullable
    public final NounPhrase subject;
    @Nullable
    public final NounPhrase directObject;
    // TODO indirect objects

    public IndependentClause() {
        double random = Math.random();
        if (random < Percentages.interrogative_chance.value) {
            this.verb = new InterrogativeVerbPhrase();
        } else {
            this.verb = new VerbPhrase();
        }

        if (verb.tags.contains("transitive")) {
            this.directObject = NounPhrase.getRandom(Number.random(), NounCase.ACCUSATIVE);
        } else {
            directObject = null;
        }

        if (verb.person == Person.THIRD && Math.random() < Percentages.explicit_subject_chance.value) {
            this.subject = NounPhrase.getRandom(verb.number, NounCase.NOMINATIVE);
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
                result += " " + subject.getLatin();
            }
            if (directObject != null) {
                result += " " + directObject.getLatin();
            }
        } else {
            if (subject != null) {
                result += subject.getLatin() + " ";
            }
            if (directObject != null) {
                result += directObject.getLatin() + " ";
            }
            result += verb.assembleLatin();
        }

        if (!result.isEmpty()) {
            result = result.substring(0, 1).toUpperCase() + result.substring(1);
        }
        result += (verb instanceof InterrogativeVerbPhrase) ? "?" : ".";
        return result;
    }

    public String assembleEnglish() {
        String result = "";

        if (verb instanceof InterrogativeVerbPhrase) {
            result += "i don't know how to do interrogatives yet";
        } else {
            if (subject != null) {
                result += subject.getEnglish();
            } else {
                if (verb.person == Person.FIRST) {
                    if (verb.number == Number.SINGULAR) {
                        result += "I";
                    } else {
                        result += "we";
                    }
                } else if (verb.person == Person.SECOND) {
                    if (verb.number == Number.SINGULAR) {
                        result += "you";
                    } else {
                        result += "you all";
                    }
                } else { // third
                    if (verb.number == Number.SINGULAR) {
                        if (Math.random() < 0.5) result += "she";
                        else result += "he";
                    } else {
                        result += "they";
                    }
                }
            }

            result += " " + verb.assembleEnglish();
        }

        if (!result.isEmpty()) {
            result = result.substring(0, 1).toUpperCase() + result.substring(1);
        }
        result += (verb instanceof InterrogativeVerbPhrase) ? "?" : ".";
        return result;
    }
}
