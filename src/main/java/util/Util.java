package util;

public class Util {
    public static <T> boolean arrayContains(T[] arr, T c) {
        for (T o : arr) {
            if (o != null && o.equals(c)) return true;
        }
        return false;
    }

    public static boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
}
