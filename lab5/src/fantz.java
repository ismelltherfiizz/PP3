import java.util.*;

public class fantz {
    private static Scanner input = new Scanner(System.in, "UTF-8");


    public static void main(String[] args) {
        String keyMenu = input.nextLine().toUpperCase();
        switch (keyMenu) {
            case "1": {
                String in = "101101101";
                int result = getMin(in);
                System.out.println(result);
                break;
            }
            case "2": {
                String in = "1111101";
                int result = getMin(in);
                System.out.println(result);
                break;
            }
            case "3": {
                String in = "110011011";
                int result = getMin(in);
                System.out.println(result);
                break;
            }
        }
    }

    public static int getMin(String S) {
        int[] pieces = new int[S.length()];
        for (int i = 0; i < pieces.length; i++) {
            pieces[i] = -1;
            if (isPowerOf5(S.substring(0, i + 1))) {
                pieces[i] = 1;
            } else {
                for (int j = 0; j < i; j++) {
                    if (isPowerOf5(S.substring(j + 1, i + 1))) {
                        if (pieces[j] != -1
                                && (pieces[i] == -1 || pieces[j] + 1 <= pieces[i])) {
                            pieces[i] = pieces[j] + 1;
                        }
                    }
                }
            }
        }
        return pieces[pieces.length-1];
    }

    static boolean isPowerOf5(String str) {
        if (str.startsWith("0")) {
            return false;
        }
        long number = Long.parseLong(str, 2);
        while (number % 5 == 0) {
            number /= 5;
        }
        return number == 1;
    }
}