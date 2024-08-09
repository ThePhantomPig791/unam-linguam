package sentence.noun;

import components.NounCase;
import components.Number;

public class CompoundNounPhrase extends NounPhrase {
    public SimpleNounPhrase one, two;

    // only called if number is plural
    public CompoundNounPhrase(Number number, NounCase c) {
        one = new SimpleNounPhrase(Number.SINGULAR, c);
        // TODO make sure the noun in the second one isn't the same as the noun in the first one
        // or maybe not it's pretty funny to see "Marcus and Marcus ought to sit" idk though
        two = new SimpleNounPhrase(Number.SINGULAR, c);
    }

    @Override
    public String getLatin() {
        return one.getLatin() + " et " + two.getLatin();
    }

    @Override
    public String getEnglish() {
        return one.getEnglish() + " and " + two.getEnglish();
    }
}
