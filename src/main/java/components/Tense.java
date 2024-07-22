package components;

public enum Tense {
    PRESENT,
    IMPERFECT,
    FUTURE,
    PERFECT,
    PLUPERFECT,
    FUTURE_PERFECT;

    public static Tense random() {
        return values()[(int) (Math.random() * values().length)];
    }
    public static Tense randomIndependent() { // all the tenses except pluperfect and future perfect
        return values()[(int) (Math.random() * (values().length - 2))];
    }

    public boolean isPerfect() {
        return this == PERFECT || this == PLUPERFECT || this == FUTURE_PERFECT;
    }
}
