import components.NounCase;
import components.Number;
import components.PartOfSpeech;
import sentence.Sentence;
import word.WordPair;
import word.english.EnglishNoun;
import word.latin.LatinNoun;
import wordentry.WordEntryPair;
import wordentry.WordListRegistry;
import wordentry.english.EnglishNounEntry;
import wordentry.latin.LatinNounEntry;

public class Main {
    public static void main(String[] args) {
        WordListRegistry.init();

        //WordEntryPair word = WordListRegistry.randomWord(PartOfSpeech.NOUN);
        /*WordEntryPair word = WordListRegistry.get("marcus");
        WordPair girlWord = new WordPair(
                new EnglishNoun((EnglishNounEntry) word.getEnglish(), Number.SINGULAR),
                new LatinNoun((LatinNounEntry) word.getLatin(), NounCase.NOMINATIVE, Number.SINGULAR)
        );

        System.out.println(((LatinNoun) (girlWord.latin)).get());*/

        Sentence sentence = new Sentence();

        System.out.println(sentence.assembleLatin());
    }
}
