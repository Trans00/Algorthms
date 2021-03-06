import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class KnuthMorrisPratt {
    class FastScanner {
        StringTokenizer tok = new StringTokenizer("");
        BufferedReader in;

        FastScanner() {
            in = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() throws IOException {
            while (!tok.hasMoreElements())
                tok = new StringTokenizer(in.readLine());
            return tok.nextToken();
        }

        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }

    // Find all the occurrences of the pattern in the text and return
    // a list of all positions in the text (starting from 0) where
    // the pattern starts in the text.
    public List<Integer> findPattern(String pattern, String text) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        String kmp = pattern + "$" + text;
        int[] prefixFunc = calcPrefix(kmp);
        for (int i = 0; i < prefixFunc.length; i++) {
            if (prefixFunc[i] == pattern.length()) {
                result.add(i - 2 * pattern.length());
            }
        }
        return result;
    }

    private int[] calcPrefix(String kmp) {
        int[] prefixFunc = new int[kmp.length()];
        int current = 0;
        for (int i = 0; i < kmp.length(); i++) {
            while (current != 0 && kmp.charAt(current) != kmp.charAt(i)) {
                current--;
            }
            if (kmp.charAt(current) == kmp.charAt(i) && current != i) {
                current++;
            }
            prefixFunc[i] = current;
        }
        return prefixFunc;

    }

    static public void main(String[] args) throws IOException {
        new KnuthMorrisPratt().run();
    }

    public void print(List<Integer> x) {
        for (int a : x) {
            System.out.print(a + " ");
        }
        System.out.println();
    }

    public void run() throws IOException {
        FastScanner scanner = new FastScanner();
        String pattern = scanner.next();
        String text = scanner.next();
        List<Integer> positions = findPattern(pattern, text);
        print(positions);
    }
}
