package CompanyWise.Snowflake;

public class ValidWordAbbreviation {

    static void main() {
        String word = "internationalization";
        String abbr = "i12iz4n";

        System.out.println("validWordAbbreviation = " + validWordAbbreviation(word, abbr));
    }

    private static boolean validWordAbbreviation(String word, String abbr) {

        int i = 0; // pointer for word
        int j = 0; // pointer for abbr

        while (i < word.length() && j < abbr.length()) {

            char ch = abbr.charAt(j);

            if (Character.isLetter(ch)) {

                if (word.charAt(i) != ch) {
                    return false;
                }
                i++;
                j++;

            } else {
                // Character is Digit

                if (ch == '0') {
                    return false;
                }

                int num = 0;

                while (j < abbr.length() && Character.isDigit(abbr.charAt(j))) {
                    num = num * 10 + (abbr.charAt(j) - '0');
                    j++;
                }

                i = i + num; // skip characters in word
            }
        }

        return i == word.length() && j == abbr.length();
    }
}
