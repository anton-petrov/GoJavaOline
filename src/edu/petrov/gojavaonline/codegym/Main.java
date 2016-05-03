package edu.petrov.gojavaonline.codegym;


import java.util.Deque;
import java.util.LinkedList;


class JoinCharacters {
    public int join(char[] input) {
        int output = 0;
        for (int i = input.length - 1, r = 1; i >= 0; i--, r *= 10) {
            output += Character.getNumericValue(input[i]) * r;
        }
        return output;
    }
}

class SumDigits {
    public int sum(int number) {
        int sum = 0;
        while (number != 0) {
            sum += number % 10;
            number /= 10;
        }
        return Math.abs(sum);
    }
}

class FirstOddNumber {
    public int find(int[] input) {
        for (int i = 0; i < input.length; i++) {
            if (input[i] % 2 != 0) {
                return i;
            }
        }
        return -1;
    }
}

class MatrixSnakeTraversal {
    public int[] print(int[][] input) {

        int length = 0;
        for (int i = 0; i < input.length; i++) {
            length += input[i].length;
        }
        int result[] = new int[length];
        int p = 1;
        int i = 0;
        int k = 0;
        for (int j = 0; j < input[0].length; j++) {
            while (i >= 0 && i < input.length) {
                result[k++] = input[i][j];
                i += p;
            }
            p = -p;
            i += p;
        }
        return result;
    }
}

class MatrixTraversal {
    public int[] print(int[][] input) {
        if (input == null || input.length == 0 || input[0].length == 0) return new int[0];
        int n = input.length;
        int m = input[0].length;
        int[] output = new int[n * m];
        if ( m == 1) {
            output[0] = input[0][0];
            return output;
        }
        int f = n-1;
        int w = m-1;

        int c = 0;
        int i = 0;
        int j = 0;
        int p = 0;

        while (c != n*m-1)
        {
            if (c >= n*m-1) break;

            while (j < w)
            {
                output[c++] = input[i][j];
                j++;
            }
            while (i < f)
            {
                output[c++] = input[i][j];
                i++;
            }
            while (j > p)
            {
                output[c++] = input[i][j];
                j--;
            }
            f--;
            w--;
            p++;
            while (i > p)
            {
                output[c++] = input[i][j];
                i--;
            }
        }
        if(c == n*m-1)
        {
            output[c++] = input[i][j];
        }
        for (int e : output) {
            System.out.print(e + " ");
        }
        return output;
    }
}

class FindMaxNumber {
    public int max(int[] input) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < input.length; i++) {
            if (max < input[i]) {
                max = input[i];
            }
        }
        return max;
    }
}

class AddNumberBase36 {

    public static String add(String a, String b) {

        a = a.toLowerCase();
        b = b.toLowerCase();

        String result = "";
        int i = a.length() - 1;
        int j = b.length() - 1;
        int c = 0;
        while (i >= 0 || j >= 0 || c > 0) {

            byte ar = i >= 0 ? Byte.parseByte(String.valueOf(a.charAt(i)), 36) : 0;
            byte br = j >= 0 ? Byte.parseByte(String.valueOf(b.charAt(j)), 36) : 0;
            result = Integer.toString((ar + br + c) % 36, 36) + result;
            c = (ar + br + c) / 36;
            i--;
            j--;
        }

        return result;
    }
}

class AddBinary {
    String add(String a, String b) {
        String sum = "";
        int i = a.length()-1;
        int j = b.length()-1;
        int c = 0;
        while (i >= 0 || j >= 0 || c > 0) {
            int ai = i >= 0 ? (a.charAt(i) == '1' ? 1 : 0) : 0;
            int bj = j >= 0 ? (b.charAt(j) == '1' ? 1 : 0) : 0;
            int s = (ai + bj + c) % 2;
            sum = s + sum;
            c = (ai + bj + c) / 2;
            i--;
            j--;
        }
        return sum;
    }
}

// =====================================================================================================================

class GnomeFood {

    public static int getRank(int[] objects, int elementIndex) {
        int rank = 0;
        for (int current = 0; current < objects.length; current++) {
            if (objects[elementIndex] < objects[current]) {
                rank++;
            }
        }
        return rank;
    }

    public static int[] find(int[] gnames, int[] portions) {
        int[] result = new int[gnames.length]; // result
        int[] granks = new int[gnames.length]; // gnome ranks
        int[] pranks = new int[gnames.length]; // portions ranks

        for (int i = 0; i < gnames.length; i++) {
            granks[i] = getRank(gnames, i);
        }

        for (int i = 0; i < portions.length; i++) {
            pranks[i] = getRank(portions, i);
        }

        for (int i = 0; i < granks.length; i++) {
            for (int j = 0; j < pranks.length; j++) {
                if (granks[i] == pranks[j]) {
                    result[i] = j;
                    break;
                }
            }
        }

        return result;
    }

    /*
        int[] gnames = new int[] { 5, 7, 6, 9, 4 };
        int[] portions = new int[] { 8, 5, 6, 2, 3 };

        int[] result = GnomeFood.find(gnames, portions);
        for (int e : result)
            System.out.println(e);
     */
}

class UnixPath {
    public final static String test = "/home/../var/./lib//file.txt";

    public String simplify(String input) {
        Deque<String> stringDeque = new LinkedList<>();
        String[] path = input.split("/");

        for (String current : path) {
            if (current.equals("..")) {
                if (!stringDeque.isEmpty()) {
                    stringDeque.pop();
                }
            } else if (current.equals("") || current.equals(".")) {
                // do nothing
            } else {
                stringDeque.push(current);
            }
        }

        StringBuilder result = new StringBuilder();
        while (true) {
            if (!(result.length() > 1 && stringDeque.isEmpty())) {
                result.append("/");
            }
            if (!stringDeque.isEmpty()) {
                result.append(stringDeque.removeLast());
            } else {
                break;
            }
        }
        return result.toString();
    }
}

class LongestStabilityPeriod {
    public int count(int[] gdp) {
        int maxStablePeriod = gdp.length > 1 ? 0 : gdp.length;
        int currentPeriod = 0;
        for (int start = 0; start < gdp.length; start++) {
            int end = gdp.length;
            for (int i = start; i < end; i++) {
                for (int j = start; j < end; j++) {
                    if (Math.abs(gdp[i] - gdp[j]) > 1) {
                        end = j;
                        break;
                    }
                }
            }
            currentPeriod = end - start;
            maxStablePeriod = Math.max(currentPeriod, maxStablePeriod);
        }
        return maxStablePeriod;
    }
}

public class Main {

    public static int setBit(int value, int n) {
        return value = value | (1 << n);
    }

    public static int unsetBit(int value, int n) {
        return value = value & ~(1 << n);
    }


    public static void main(String[] args) {

        System.out.println(new LongestStabilityPeriod().count(new int[]{900, 900, 900, 900, 900, 900, 900, 900, 900, 900, 900, 900, 900, 900, 900}));
        System.out.println(new LongestStabilityPeriod().count(new int[]{901, 901, 901, 902, 902, 903, 903, 902, 902, 901}));
        System.out.println(new LongestStabilityPeriod().count(new int[]{1, 2, 2, 90, 91}));
    }
}