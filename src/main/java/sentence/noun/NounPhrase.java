package sentence.noun;

import components.NounCase;
import components.Number;
import util.Percentages;

public abstract class NounPhrase {
    public static NounPhrase getRandom(Number number, NounCase c) {
        double random = Math.random();
        if (random < Percentages.compound_noun_phrase_chance && number == Number.PLURAL) {
            return new CompoundNounPhrase(number, c);
        } else {
            return new SimpleNounPhrase(number, c);
        }
    }

    public abstract String getLatin();
    public abstract String getEnglish();
}
