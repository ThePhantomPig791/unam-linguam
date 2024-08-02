package sentence.noun;

import components.NounCase;
import components.Number;
import components.PartOfSpeech;
import org.jetbrains.annotations.NotNull;
import util.Util;
import word.WordPair;
import word.english.EnglishAdjective;
import word.english.EnglishNoun;
import word.latin.LatinAdjective;
import word.latin.LatinNoun;
import wordentry.WordEntryPair;
import wordentry.WordListRegistry;
import wordentry.english.EnglishAdjectiveEntry;
import wordentry.english.EnglishNounEntry;
import wordentry.latin.LatinAdjectiveEntry;
import wordentry.latin.LatinNounEntry;

public class NounPhrase {
    @NotNull
    public final WordPair noun;
    public final WordPair[] adjectives;

    public final Number number;

    public NounPhrase(Number number, NounCase c) {
        this.number = number;

        WordEntryPair noun = WordListRegistry.randomWord(PartOfSpeech.NOUN, (number == Number.PLURAL) ? entry -> !entry.tags.contains("uncountable") : entry -> true);
        this.noun = new WordPair(
                new EnglishNoun((EnglishNounEntry) noun.getEnglish(), number),
                new LatinNoun((LatinNounEntry) noun.getLatin(), c, number)
        );

        int adjectiveCount = 1; // Math.max(recursiveRandom(-1), 0);
        this.adjectives = new WordPair[adjectiveCount];
        for (int i = 0; i < adjectiveCount; i++) {
            WordEntryPair adjective = WordListRegistry.randomWord(PartOfSpeech.ADJECTIVE, entry -> !Util.arrayContains(this.adjectives, entry));
            this.adjectives[i] = new WordPair(
                new EnglishAdjective((EnglishAdjectiveEntry) adjective.getEnglish(), number),
                new LatinAdjective((LatinAdjectiveEntry) adjective.getLatin(), ((LatinNounEntry) noun.getLatin()).gender, c, number)
            );
        }
    }

    public String getLatin() {
        if (adjectives.length == 0) return ((LatinNoun) noun.latin).get();

        String result = "";

        int nounIndex = (int) (Math.random() * adjectives.length);
        for (int i = 0; i < adjectives.length; i++) {
            if (nounIndex == i) {
                result += ((LatinNoun) noun.latin).get();
                nounIndex = -1;
                i--;
            } else {
                WordPair adjective = adjectives[i];
                result += ((LatinAdjective) adjective.latin).get();
            }
            if (i != adjectives.length - 1) result += " ";
        }

        return result;
    }

    private static int recursiveRandom(int n) {
        if (Math.random() < 0.5) return recursiveRandom(n + 1);
        return n;
    }
}
