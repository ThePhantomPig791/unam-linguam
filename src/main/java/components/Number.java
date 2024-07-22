package components;

public enum Number {
    SINGULAR,
    PLURAL;

    public static Number random() {
        return values()[(int) (Math.random() * values().length)];
    }
}
