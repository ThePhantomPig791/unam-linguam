package word;

import word.english.EnglishWord;
import word.latin.LatinWord;

public class WordPair {
    public final EnglishWord english;
    public final LatinWord latin;

    public WordPair(EnglishWord english, LatinWord latin) {
        this.english = english;
        this.latin = latin;
    }
}
