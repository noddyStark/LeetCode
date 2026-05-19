package CompanyWise.Snowflake;


/*
 * attributes (e.g., color, shape, shading, number)
 * each attribute can take on one of three distinct values
 *           (e.g., for color: red, green, purple; for shape: oval, squiggle, diamond)
 *
 * attribute : {
 *               color: red,
 *               shape: oval
 *
 *               }
 * */
public class ValidateASETCardGameHand {

    static void main() {
        int[][] cards = {
                {0, 1, 2, 0},
                {0, 0, 1, 1},
                {0, 2, 0, 2}
        };

        boolean result = isSet(cards);

        System.out.println(result);
    }

    private static boolean isSet(int[][] cards) {

        int attributes = cards[0].length;

        for (int i = 0; i < attributes; i++) {
            int a = cards[0][i];
            int b = cards[1][i];
            int c = cards[2][i];

            System.out.println("a = " + a + " b = " + b + " c = " + c);

            boolean allSame = (a == b && b == c);
            boolean allDifferent = (a != b && b != c && a != c);

            System.out.println("allSame = " + allSame + " allDifferent = " + allDifferent);

            boolean condition = !allSame && !allDifferent;
            System.out.println("condition = " + condition);

            if (!allSame && !allDifferent) {
                return false;
            }
        }
        return true;
    }
}
