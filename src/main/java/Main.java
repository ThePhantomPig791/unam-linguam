import application.MainApplication;
import sentence.Sentence;
import wordentry.WordListRegistry;

public class Main {
    public static void main(String[] args) {
        WordListRegistry.init();

        //WordEntryPair word = WordListRegistry.randomWord(PartOfSpeech.NOUN);
        /*WordEntryPair word = WordListRegistry.get("marcus");
        WordPair girlWord = new WordPair(
                new EnglishNoun((EnglishNounEntry) word.getEnglish(), Number.SINGULAR),
                new LatinNoun((LatinNounEntry) word.getLatin(), NounCase.NOMINATIVE, Number.SINGULAR)
        );

        System.out.println(((LatinNoun) (girlWord.latin)).get());

        Sentence sentence = new Sentence();

        System.out.println(sentence.assembleLatin());*/

        MainApplication.initialize();
    }
}
