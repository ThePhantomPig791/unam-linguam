package components;

public enum NounCase {
    NOMINATIVE,
    GENITIVE,
    DATIVE,
    ACCUSATIVE,
    ABLATIVE,
    VOCATIVE;

    public static NounCase random() {
        return values()[(int) (Math.random() * values().length)];
    }
}
