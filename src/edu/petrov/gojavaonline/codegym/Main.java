package edu.petrov.gojavaonline.codegym;

import java.util.*;

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
        if (m == 1) {
            output[0] = input[0][0];
            return output;
        }
        int f = n - 1;
        int w = m - 1;

        int c = 0;
        int i = 0;
        int j = 0;
        int p = 0;

        while (c != n * m - 1) {
            if (c >= n * m - 1) break;

            while (j < w) {
                output[c++] = input[i][j];
                j++;
            }
            while (i < f) {
                output[c++] = input[i][j];
                i++;
            }
            while (j > p) {
                output[c++] = input[i][j];
                j--;
            }
            f--;
            w--;
            p++;
            while (i > p) {
                output[c++] = input[i][j];
                i--;
            }
        }
        if (c == n * m - 1) {
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
        StringBuilder sum = new StringBuilder();
        int i = a.length() - 1;
        int j = b.length() - 1;
        int c = 0;
        while (i >= 0 || j >= 0 || c > 0) {
            int ai = i >= 0 ? (a.charAt(i) == '1' ? 1 : 0) : 0;
            int bj = j >= 0 ? (b.charAt(j) == '1' ? 1 : 0) : 0;
            int s = (ai + bj + c) % 2;
            sum.insert(0, s);
            c = (ai + bj + c) / 2;
            i--;
            j--;
        }
        return sum.toString();
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
        int currentPeriod;
        for (int start = 0; start < gdp.length; start++) {
            int end = gdp.length;
            for (int i = start; i < end; i++) {
                for (int j = i + 1; j < end; j++) {
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

class RectangleSquare {

    private int getArea(int x1, int y1, int x2, int y2) {
        return (x2 - x1) * (y2 - y1);
    }

    public int measure(int[] x, int[] h, int[] w) {

        Deque<Square> squares = new LinkedList<>();
        for (int i = 0; i < x.length; i++) {
            squares.add(new Square(x[i], h[i], w[i]));
        }
        int sum = 0;

        List<Integer> X = new LinkedList<>();
        List<Integer> Y = new LinkedList<>();
        for (Square s : squares) {
            X.add(s.x1);
            X.add(s.x2);
            Y.add(s.y1);
            Y.add(s.y2);
        }

        for (int i = 0; i < X.size() - 1; i++) {
            if (X.get(i) == X.get(i + 1)) {
                X.remove(i);
            }
        }
        for (int i = 0; i < Y.size() - 1; i++) {
            if (Y.get(i) == Y.get(i + 1)) {
                Y.remove(i);
            }
        }

        Comparator ascending = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 > o2 ? 1 : (o1 < o2 ? -1 : 0);
            }
        };

        X.sort(ascending);
        Y.sort(ascending);

        for (int i = 0; i < Y.size() - 1; i++) {
            for (int j = 0; j < X.size() - 1; j++) {
                for (Square s : squares) {
                    if (X.get(j) >= s.x1 && X.get(j + 1) <= s.x2 && Y.get(i) >= s.y1 && Y.get(i + 1) <= s.y2) {
                        sum += getArea(X.get(j), Y.get(i), X.get(j + 1), Y.get(i + 1));
                        break;
                    }
                }
            }
        }

        return sum;
    }

    class Square {
        public int x1;  // lower left point
        public int y1;
        public int x2;  // upper right point
        public int y2;

        public Square(int x, int h, int w) {
            this.x1 = x;
            this.y1 = 0;
            this.x2 = x + w;
            this.y2 = h;
        }

        @Override
        public String toString() {
            return "Square{" +
                    "x1=" + x1 +
                    ", y1=" + y1 +
                    ", x2=" + x2 +
                    ", y2=" + y2 +
                    '}';
        }
    }
}

class ReversePolishNotation {
    public static Double calc(String input) {
        Stack<Double> numbers = new Stack<>();
        for (String number : input.split(" ")) {
            Sign sign = Sign.find(number);
            if (sign != null) {
                calcSign(numbers, sign);
            } else {
                numbers.push(Double.parseDouble(number));
            }
        }
        return numbers.pop();
    }

    protected static Stack<Double> calcSign(Stack<Double> numbers, Sign sign) {
        numbers.push(sign.apply(numbers.pop(), numbers.pop()));
        return numbers;
    }

    public int evaluate(String expression) {
        return (int) Math.round(calc(expression));
    }

    public enum Sign {

        ADD("+") {
            public double apply(double num1, double num2) {
                return num2 + num1;
            }
        },
        REMOVE("-") {
            public double apply(double num1, double num2) {
                return num2 - num1;
            }
        },
        MULTIPLY("*") {
            public double apply(double num1, double num2) {
                return num2 * num1;
            }
        },
        DIVIDE("/") {
            public double apply(double num1, double num2) {
                return num2 / num1;
            }
        };

        private static final Map<String, Sign> map;

        static {
            map = new HashMap<>();
            for (Sign sign : Sign.values()) {
                map.put(sign.operatorText, sign);
            }
        }

        private final String operatorText;

        Sign(String operatorText) {
            this.operatorText = operatorText;
        }

        public static Sign find(String sign) {
            return map.get(sign);
        }

        public abstract double apply(double x1, double x2);

    }
}

class BinaryHeap2 {

    public BinaryTree tree = new BinaryTree();

    public BinaryHeap2(int size) {

    }

    public void insert(int val) {
        tree.insert(val);
    }

    public int poll() {
        int maxValue = tree.findMaximumValue();
        tree.remove(maxValue);
        return maxValue;
    }

    class BinaryTreeNode {
        public BinaryTreeNode left = null;
        public BinaryTreeNode right = null;
        public int value;

        public BinaryTreeNode() {

        }

        public BinaryTreeNode(int value) {
            this.value = value;
        }

        public int minValue() {
            if (left == null)
                return value;
            else
                return left.minValue();
        }

        private boolean remove(BinaryTreeNode parent, int value) {
            if (value < this.value) {
                if (left != null)
                    return left.remove(this, value);
                else
                    return false;
            } else if (value > this.value) {
                if (right != null)
                    return right.remove(this, value);
                else
                    return false;
            } else {
                if (left != null && right != null) {
                    this.value = right.minValue();
                    right.remove(this, right.minValue());
                } else if (parent.left == this) {
                    parent.left = (left != null) ? left : right;
                } else if (parent.right == this) {
                    parent.right = (left != null) ? left : right;
                }
                return true;
            }
        }

    }

    public class BinaryTree {
        private BinaryTreeNode root = null;

        public BinaryTree() {

        }

        private BinaryTreeNode createNode(int value) {
            BinaryTreeNode node = new BinaryTreeNode();
            node.value = value;
            return node;
        }

        private BinaryTreeNode createNode() {
            return createNode(0);
        }

        private void insert(BinaryTreeNode node, int value) {
            if (node == null) {
                throw new NullPointerException("Argument 'node' must not be null!");
            } else if (value > node.value) {
                if (node.right == null) {
                    node.right = createNode(value);
                } else {
                    insert(node.right, value);
                }

            } else if (value < node.value) {
                if (node.left == null) {
                    node.left = createNode(value);
                } else {
                    insert(node.left, value);
                }

            } else if (value == node.value) {
                throw new IllegalArgumentException("Argument '" + value + "' already exists in the tree!");
            }
        }

        public void insert(int value) {
            if (root == null) {
                root = createNode(value);
            } else {
                insert(root, value);
            }
        }

        private BinaryTreeNode find(BinaryTreeNode node, int value) {
            if (node == null) {
                return null;
            } else if (value > node.value) {
                return find(node.right, value);

            } else if (value < node.value) {
                return find(node.left, value);

            } else if (value == node.value) {
                return node;
            }
            return null;
        }

        public BinaryTreeNode find(int value) {
            return find(root, value);
        }

        public int findMaximumValue(BinaryTreeNode node) {
            int max = node.value;
            if (node.left != null) {
                max = Math.max(max, findMaximumValue(node.left));
            }
            if (node.right != null) {
                max = Math.max(max, findMaximumValue(node.right));
            }
            return max;
        }

        public int findMaximumValue() {
            if (root == null) {
                return 0;
            }
            return findMaximumValue(root);
        }

        public boolean remove(int value) {
            if (root == null)
                return false;
            else {
                if (root.value == value) {
                    BinaryTreeNode tmpRoot = new BinaryTreeNode();
                    tmpRoot.left = root;
                    boolean result = root.remove(tmpRoot, value);
                    root = tmpRoot.left;
                    return result;
                } else {
                    return root.remove(null, value);
                }
            }
        }

        public void print() {
            print(root, "   ", true);
        }

        private void print(BinaryTreeNode node, String prefix, boolean isTail) {
            System.out.println(prefix + (isTail ? "└── " : "├── ") + (node != null ? node.value : "null"));
            if (node == null)
                return;
            print(node.right, prefix + (isTail ? "    " : "│   "), false);
            print(node.left, prefix + (isTail ? "    " : "│   "), true);
        }
    }
}

class BinaryHeap {

    private TreeMap<Integer, Integer> map = new TreeMap<>();

    public BinaryHeap(int size) {

    }

    public void insert(int val) {
        map.put(val, val);
    }

    public int poll() {
        if (map.size() > 0) {
            int max = map.lastKey();
            map.remove(max);
            return max;
        }
        return 0;
    }
}

class Alphabet {
    public boolean check(String input) {
        if (input == null || input == "") {
            return false;
        }
        input = input.toLowerCase();
        Set<String> alphabet = new HashSet<>();
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) >= 'a' && input.charAt(i) <= 'z') {
                alphabet.add(Character.toString(input.charAt(i)));
            }
        }
        return alphabet.size() == 26;
    }
}

class ReverseBits {
    public int reverse(int input) {
        return Integer.reverse(input);
    }
}

class Doubles {

    public Double parse(String s) {
        try {
            return Double.parseDouble(s);
        } catch(Exception e) {
            return null;
        }
    }
}

class BinarySearch {
    public int binarySearch(int[] array, int target, int left, int right) {
        if (left >= right) {
            return -(1 + left);
        }
        int mid = left + (right - left) / 2;
        if (target == array[mid]) {
            return mid;
        } else if (target > array[mid]) {
            return binarySearch(array, target, mid + 1, right);
        } else if (target < array[mid]) {
            return binarySearch(array, target, left, mid);
        }
        return 0;
    }
    public int find(int[] array, int target) {
        return binarySearch(array, target, 0, array.length);
    }
}

class BreakLine {
    public String slice(String word, int width) {
        StringBuilder result = new StringBuilder();
        StringBuilder buffer = new StringBuilder();
        for (int i = 0, counter = 0; i <= word.length(); i++, counter++) {
            if (counter == width || i == word.length()) {
                result.append(buffer);
                if (i == word.length())
                    break;
                result.append("\n");
                buffer = new StringBuilder();
                counter = 0;
            }
            buffer.append(word.charAt(i));
        }
        return result.toString();
    }

    public String format(String input, int width) {
        StringBuilder result = new StringBuilder();
        Deque<String> words = new ArrayDeque<>();
        for (String word : input.split(" ")) {
            words.add(word);
        }
        Deque<String> slicedWords = new ArrayDeque<>();
        for (String word : words) {
            if (word.length() > width) {
                for (String w : slice(word, width).split("\n")) {
                    slicedWords.add(w);
                }
            }
            else {
                slicedWords.add(word);
            }
        }
        words = slicedWords;
        while (!words.isEmpty()) {
            int numChars = 0;
            StringBuffer buffer = new StringBuffer();
            while (!words.isEmpty()) {
                String word = words.removeFirst();
                buffer.append(word);
                numChars += word.length();
                if (words.isEmpty()) break;
                if (numChars + 1 + words.peekFirst().length() > width) {
                    result.append(buffer);
                    result.append("\n");
                    break;
                } else {
                    numChars++;
                    buffer.append(" ");
                }
            }
            if (words.isEmpty()) {
                result.append(buffer);
            }
        }
        return result.toString();
    }

    class WordNumber {
        public int count(String input) {
            if (input == "") return 0;
            return input.toLowerCase().split("[^a-z]+").length;
        }
    }

}

class CSVParser2 {
    public List<List<String>> parse(String input) {
        List<List<String>> lines = new LinkedList<>();
        String[] inputLines = input.split("\n");
        for (int i = 0; i < inputLines.length; i++) {
            List<String> buffer = new LinkedList<>();
            lines.add(buffer);
            for (String token : inputLines[i].split(",")) {
                token = token.trim();
                buffer.add(token);
                System.out.print(token + " ");
            }
            System.out.println("\n");
        }
        return lines;
    }
}

class CSVParser {
    public String[] splitByLines(String input) {
        List<String> lines = new LinkedList<>();
        StringBuilder stringBuffer = new StringBuilder();
        boolean quoteMode = false;
        for (int i = 0; i < input.length(); i++) {
            if (quoteMode) {
                if (input.charAt(i) == '"') {
                    quoteMode = false;
                }
            } else {
                if (input.charAt(i) == '\n') {
                    lines.add(stringBuffer.toString());
                    stringBuffer = new StringBuilder();
                    continue;
                } else if (input.charAt(i) == '"') {
                    quoteMode = true;
                }
            }
            stringBuffer.append(input.charAt(i));
        }
        if (stringBuffer.length() > 0) {
            lines.add(stringBuffer.toString());
        }
        return lines.toArray(new String[0]);
    }

    public List<List<String>> parse(String input) {
        List<List<String>> lines = new LinkedList<>();
        String[] inputLines = splitByLines(input);
        for (int i = 0; i < inputLines.length; i++) {
            List<String> buffer = new LinkedList<>();
            lines.add(buffer);
            for (int k = 0; k < inputLines[i].length(); ) {
                String token = "";
                // skip whitespace
                while (k < inputLines[i].length() && inputLines[i].charAt(k) == ' ') {
                    k++;
                }
                // double-quote
                if (k < inputLines[i].length() && inputLines[i].charAt(k) == '\"') {
                    k++;
                    while (k < inputLines[i].length() && inputLines[i].charAt(k) != '\"') {
                        if (k + 2 < inputLines[i].length() &&
                                inputLines[i].charAt(k + 1) == '\"' && inputLines[i].charAt(k + 2) == '\"') {
                            token += inputLines[i].charAt(k);
                            k += 3;
                            token += '\"';
                        } else {
                            token += inputLines[i].charAt(k);
                            k++;
                        }
                    }
                    // search next comma
                    while (k < inputLines[i].length() && inputLines[i].charAt(k) != ',') {
                        k++;
                    }
                    // read text until comma
                } else {
                    int t = k;
                    while (k < inputLines[i].length() && inputLines[i].charAt(k) != ',') {
                        k++;
                    }
                    if (k > t) {
                        token = inputLines[i].substring(t, k);
                    }
                }
                buffer.add(token);
                if (k < inputLines[i].length() && inputLines[i].charAt(k) == ',' && k + 1 >= inputLines[i].length()) {
                    buffer.add("");
                }
                k++;
            }
        }
        return lines;
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
        System.out.println(new CSVParser().parse("one,\"tw\"\"o\"\nthree,"));
        System.out.println(new CSVParser().parse("one,\"tw\"\"o\"\n\"thr,ee\",\""));
        System.out.println(new CSVParser().parse("\"thr,ee\",\""));

        System.out.println(new CSVParser().parse(",,,"));

        System.out.println(new CSVParser().splitByLines("\"a\nb\"\nc \"123\"\"567\""));

    }
}