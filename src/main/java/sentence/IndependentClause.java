package sentence;

import components.*;
import components.Number;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import sentence.noun.NounPhrase;
import sentence.verb.InterrogativeVerbPhrase;
import sentence.verb.VerbPhrase;

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
        if (random <= 0.9) {
            this.verb = new VerbPhrase();
        } else {
            this.verb = new InterrogativeVerbPhrase();
        }

        if (verb.tags.contains("transitive")) {
            this.directObject = new NounPhrase(Number.random(), NounCase.ACCUSATIVE);
        } else {
            directObject = null;
        }

        if (verb.person == Person.THIRD && Math.random() < 0.9) {
            this.subject = new NounPhrase(verb.number, NounCase.NOMINATIVE);
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
}
