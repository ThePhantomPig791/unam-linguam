package components;

public class NounEndings {
    // declension, gender, number, case
    public static final String[][][][] ENDINGS = {
            {{{"a", "ae", "ae", "am", "a", "a"}, {"ae", "arum", "is", "as", "is", "ae"}}, null, null},
            {null, {{null, "i", "o", "um", "o", "e"}, {"i", "orum", "is", "os", "is", "i"}}, {{"um", "i", "o", "um", "o", "um"}, {"a", "orum", "is", "as", "is"}}},
            {{{null, "is", "i", "em", "e", null}, {"es", "um", "ibus", "es", "ibus", "es"}}, {{null, "is", "i", "em", "e", null}, {"es", "um", "ibus", "es", "ibus", "es"}}, {{null, "is", "i", null, "e", null}, {"a", "um", "ibus", "a", "ibus", "a"}}},
            {null, {{"us", "us", "ui", "um", "u", "us"}, {"us", "uum", "ibus", "us", "ibus", "us"}}, {{"u", "us", "u", "u", "u", "u"}, {"ua", "uum", "ibus", "ua", "ibus", "ua"}}},
            {{{"es", "ei", "ei", "em", "e", "es"}, {"es", "erum", "ebus", "es", "ebus", "es"}}, {{"es", "ei", "ei", "em", "e", "es"}, {"es", "erum", "ebus", "es", "ebus", "es"}}, null}
    };
    // a null ending means use the WordEntry nominative value
}