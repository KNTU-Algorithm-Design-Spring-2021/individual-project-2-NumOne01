import java.util.Scanner;

public class WordWrapping {
    final static private int INFINITY = Integer.MAX_VALUE;

    /**
     * calculates input's words length
     *
     * @param input input string
     * @return array consists of lengths of input words
     */
    private int[] calcLength(String input) {
        String[] words = input.split(" ");
        int[] lengths = new int[words.length];
        for (int i = 0; i < lengths.length; i++) {
            lengths[i] = words[i].length();
        }
        return lengths;
    }

    private int costFunction(int length, int i, int j, int k) {
        return (int) Math.pow(k - length - (i - j), 3);
    }

    /**
     * find minimized cost of wrapping word
     *
     * @param input              input string
     * @param maxCharacterInLine maximum character in a line
     */
    private void wordWrap(String input, int maxCharacterInLine) {
        int[] lengths = calcLength(input);

        // words size
        int size = lengths.length;

        // Array in which dp[i] represents
        // cost of line starting with word i
        int[] dp = new int[size];

        // Array in which ans[i] stores
        // index of last word in line starting
        // with word i
        int[] ans = new int[size];

        // Hence cost of this line is zero.
        // Ending point is also n-1 as
        // single word is present.
        dp[size - 1] = 0;
        ans[size - 1] = size - 1;

        // Variable to store
        // number of characters
        // in given line.
        int currentLength;

        // Variable to store possible
        // minimum cost of line.
        int cost;

        for (int i = size - 2; i >= 0; i--) {
            dp[i] = INFINITY;
            currentLength = -1;

            for (int j = i; j < size; j++) {
                //1 represents space character
                // between two words.
                currentLength += (lengths[j] + 1);

                if (currentLength > maxCharacterInLine) break;

                if (j == size - 1) {
                    cost = 0;
                } else {
                    cost = costFunction(currentLength, i, j, maxCharacterInLine) + dp[j + 1];
                }

                if (cost < dp[i]) {
                    dp[i] = cost;
                    ans[i] = j;
                }
            }
        }

        String[] words = input.split(" ");
        printWrappedWords(words, ans);
    }

    private void printWrappedWords(String[] words, int[] indices) {
        int i = 0;
        while (i < indices.length) {
            int to = indices[i] + 1;
            while (i < to) {
                System.out.print(words[i] + " ");
                i++;
            }
            System.out.println();
            i = to;
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int maxCharacterInLine = in.nextInt();
        String input = in.nextLine();
        WordWrapping wordWrapping = new WordWrapping();
        wordWrapping.wordWrap(input, maxCharacterInLine);
    }
}
