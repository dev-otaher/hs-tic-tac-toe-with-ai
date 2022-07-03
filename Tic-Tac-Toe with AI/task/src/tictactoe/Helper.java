package tictactoe;

public class Helper {
    public static boolean isArrayIdentical(char[] array) {
        for (char ch : array) {
            if (ch != array[0]) {
                return false;
            }
        }
        return true;
    }

    public static int countChar(String str, char c) {
        int counter = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == c) {
                counter++;
            }
        }
        return counter;
    }
}
