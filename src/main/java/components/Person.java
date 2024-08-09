package components;

import util.Percentages;

public enum Person {
    FIRST,
    SECOND,
    THIRD;

    public static Person random() {
        return values()[(int) (Math.random() * values().length)];
    }

    public static Person randomWeighted() {
        if (Math.random() < Percentages.third_person_chance) return THIRD;
        if (Math.random() < Percentages.first_person_chance) return FIRST;
        return SECOND;
    }
}
