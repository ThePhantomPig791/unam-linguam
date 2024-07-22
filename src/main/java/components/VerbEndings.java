package components;

public class VerbEndings {
    // remember third_io is a conjugation in my implementation, thus 5 conjugations
    public static final String[] INFINITIVE_STEMS = {"are", "ere", "ere", "ere", "ire"};
    public static final String[][] IMPERATIVE_ENDINGS = {{"a", "ate"}, {"e", "ete"}, {"e", "ite"}, {"e", "ite"}, {"i", "ite"}};

    // tense (ordinal minus 3), number, person
    public static final String[][][] PERFECT_ENDINGS = {
            {{"i", "isti", "it"}, {"imus", "itis", "erunt"}},
            {{"eram", "eras", "erat"}, {"eramus", "eratus", "erant"}},
            {{"ero", "eris", "erit"}, {"erimus", "eritis", "erint"}}
    };

    // perfect endings are left off of this method since they're all exactly the same
    public static String[][][] getEndings(Conjugation conjugation) {
        // tense, number, person
        String[][][] endings = {
                {{"o", "s", "t"}, {"mus", "tis", "nt"}},
                {{"bam", "bas", "bat"}, {"bamus", "batis", "bant"}},
                null
        };
        if (conjugation == Conjugation.FIRST || conjugation == Conjugation.SECOND) {
            endings[2] = new String[][]{{"bo", "bis", "bit"}, {"bimus", "bitus", "bunt"}};
        } else {
            endings[2] = new String[][]{{"am", "es", "et"}, {"emus", "etis", "ent"}};
        }
        return endings;
    }

    public static String getStemVowel(Conjugation conjugation, Tense tense, Number number, Person person) {
        String stemVowel = "";
        switch (conjugation) {
            case FIRST -> {
                if (tense != Tense.PRESENT && number != Number.SINGULAR && person != Person.FIRST) {
                    stemVowel += "a";
                }
            }
            case SECOND -> {
                stemVowel += "e";
            }
            case THIRD -> {
                if (tense == Tense.PRESENT) {
                    if (number == Number.SINGULAR) {
                        if (person != Person.FIRST) stemVowel += "i";
                    } else {
                        if (person != Person.THIRD) stemVowel += "i";
                    }
                } else if (tense == Tense.IMPERFECT) {
                    stemVowel += "e";
                }
            }
            case THIRD_IO, FOURTH -> {
                stemVowel += "i";
                if (tense == Tense.PRESENT && number == Number.SINGULAR && person == Person.THIRD) stemVowel += "u";
                if (tense == Tense.IMPERFECT) stemVowel += "e";
            }
        }
        return stemVowel;
    }
}
