package CompanyWise.Snowflake.ValidateASETCardGameHand;


import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/*
If both values are same:
    third value should be same

If both values are different:
    third value should be the remaining value from {0,1,2}

Example:
    card1 attr = 0
    card2 attr = 1
third attr must be 2

Because 0,1,2 are all different.

Formula:
    thirdValue = (3 - a - b) % 3;

But easier to understand:
    if (a == b) {
        needed = a;
    } else {
        needed = 3 - a - b;
    }

Because values are only 0, 1, 2.
* */
public class ValidateASETCardGameHand_NCards_Optimized {


    static void main() {
        int[][] cards = {
                {0, 1, 2, 0},
                {0, 0, 1, 1},
                {0, 2, 0, 2},
                {1, 1, 1, 1}
        };

        int[][] result = findValidSetOptimized(cards);

        if (result.length == 0) {
            System.out.println("No valid SET found");
        } else {
            System.out.println("Valid SET found:");
            for (int[] card : result) {
                System.out.println(Arrays.toString(card));
            }
        }
    }

    /*
     * a = 0, b = 1
     * needed = 3 - 0 - 1 = 2
     * a = 0, b = 2
     * needed = 3 - 0 - 2 = 1
     * a = 1, b = 2
     * needed = 3 - 1 - 2 = 0
     */
    private static int[][] findValidSetOptimized(int[][] cards) {

        Set<String> seen = new HashSet<>();

        for (int[] card : cards) {
            seen.add(Arrays.toString(card));
        }

        System.out.println("seen = " + seen);

        int n = cards.length;
        int attributes = cards[0].length;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {

                int[] needed = new int[attributes];

                for (int attr = 0; attr < attributes; attr++) {
                    int a = cards[i][attr];
                    int b = cards[j][attr];

                    if (a == b) {
                        needed[attr] = a;
                    } else {
                        needed[attr] = 3 - a - b;
                    }
                }

                System.out.println("needed = " + Arrays.toString(needed));

                if (seen.contains(Arrays.toString(needed))) {
                    return new int[][]{
                            cards[i],
                            cards[j],
                            needed
                    };
                }
            }
        }

        return new int[0][0];
    }
}
