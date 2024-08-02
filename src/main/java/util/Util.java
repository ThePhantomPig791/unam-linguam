package util;

public class Util {
    public static <T> boolean arrayContains(T[] arr, T c) {
        for (T o : arr) {
            if (o != null && o.equals(c)) return true;
        }
        return false;
    }
}
