package CompanyWise.Snowflake.ValidateASETCardGameHand;


import java.util.Arrays;

/**
 * Follow-up 2: Card has 4 attributes, and you have N cards. Find a valid SET.
 * <p>
 * Now instead of checking only 3 cards, we are given many cards.
 *
 * Note: Attributes value can only be 0,1 & 2
 */
public class ValidateASETCardGameHand_NCards {
    static void main() {
        int[][] cards = {
                {0, 1, 2, 0}, // card 1
                {0, 0, 1, 1}, // card 2
                {0, 2, 0, 2}, // card 3
                {1, 1, 1, 1}  // card 4
        };

        int[][] result = findValidSet(cards);

        if (result.length == 0) {
            System.out.println("No valid SET found");
        } else {
            System.out.println("Valid SET found:");
            for (int[] card : result) {
                System.out.println(Arrays.toString(card));
            }
        }
    }

    // O(N^3 * M)
    // N = number of cards
    // M = number of attributes
    // Space complexity: O(1)
    private static int[][] findValidSet(int[][] cards) {

        int n = cards.length;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {

                    int[][] threeCards = {
                            cards[i],
                            cards[j],
                            cards[k]
                    };

                    if (isSet(threeCards)) {
                        return threeCards;
                    }
                }
            }
        }

        return new int[0][0];
    }

    private static boolean isSet(int[][] cards) {

        for (int i = 0; i < cards.length; i++) {

            int a = cards[0][i];
            int b = cards[1][i];
            int c = cards[2][i];

            boolean allSame = (a == b) && (b == c);
            boolean allDifferent = (a != b) && (b != c) && (c != a);

            if (!allSame && !allDifferent) {
                return false;
            }
        }

        return true;
    }
}
