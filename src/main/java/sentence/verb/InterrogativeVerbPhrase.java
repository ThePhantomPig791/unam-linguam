package sentence.verb;

import word.latin.LatinInfinitiveVerb;
import word.latin.LatinVerb;

public class InterrogativeVerbPhrase extends VerbPhrase {
    public InterrogativeVerbPhrase() {
        super();
    }

    @Override
    public String assembleLatin() {
        String result = "";
        result += ((LatinVerb) verb.latin).get() + "ne";
        if (infinitive != null) {
            result += " " + ((LatinInfinitiveVerb) infinitive.latin).get();
        }
        return result;
    }
}
