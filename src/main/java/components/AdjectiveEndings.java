package components;

public class AdjectiveEndings {
    // declension, gender, number, case
    public static final String[][][][] ENDINGS = {
            {{{"a", "ae", "ae", "am", "a", "a"}, {"ae", "arum", "is", "as", "is", "ae"}}, {{"us", "i", "o", "um", "o", "e"}, {"i", "orum", "is", "os", "is", "i"}}, {{"um", "i", "o", "um", "o", "um"}, {"a", "orum", "is", "a", "is"}}},
            null,
            {{{null, "is", "i", "em", "i", null}, {"es", "ium", "ibus", "es", "ibus", "es"}}, {{null, "is", "i", "em", "i", null}, {"es", "ium", "ibus", "es", "ibus", "es"}}, {{null, "is", "i", null, "i", null}, {"ia", "ium", "ibus", "ia", "ibus"}}},
            null,
            null
    };
    // a null ending means use the WordEntry nominative value FOR THAT GENDER
}