package components;

public enum Person {
    FIRST,
    SECOND,
    THIRD;

    public static Person random() {
        return values()[(int) (Math.random() * values().length)];
    }

    public static Person randomWeighted() {
        int random = (int) (Math.random() * 100);
        if (random >= 50) return THIRD;
        else if (random >= 20) return FIRST;
        return SECOND;
    }
}
