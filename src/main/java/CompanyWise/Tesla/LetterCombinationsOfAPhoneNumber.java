package CompanyWise.Tesla;

import java.util.*;

/*
17. Letter Combinations of a Phone Number
Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number
could represent. Return the answer in any order.

A mapping of digits to letters (just like on the telephone buttons) is given below. Note that 1 does not map to
any letters.

Example 1:
Input: digits = "23"
Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]

Example 2:
Input: digits = "2"
Output: ["a","b","c"]

Constraints:

1 <= digits.length <= 4
digits[i] is a digit in the range ['2', '9'].
* */
public class LetterCombinationsOfAPhoneNumber {

    static void main() {
        String digits = "23";
        List<String> result = letterCombinations(digits);
        System.out.println("Result = " + result);
    }

    public static List<String> letterCombinations(String digits) {

        HashMap<Integer, String> mapOfNumbersToChars = new HashMap<>();
        mapOfNumbersToChars.put(0, "");
        mapOfNumbersToChars.put(1, "");
        mapOfNumbersToChars.put(2, "abc");
        mapOfNumbersToChars.put(3, "def");
        mapOfNumbersToChars.put(4, "ghi");
        mapOfNumbersToChars.put(5, "jkl");
        mapOfNumbersToChars.put(6, "mno");
        mapOfNumbersToChars.put(7, "pqrs");
        mapOfNumbersToChars.put(8, "tuv");
        mapOfNumbersToChars.put(9, "wxyz");

        List<String> result = new ArrayList<>();
        backTrack(0, new StringBuilder(), mapOfNumbersToChars, result, digits);

        return result;
    }

    public static void backTrack(int index,
                                 StringBuilder current,
                                 HashMap<Integer, String> mapOfNumbersToChars,
                                 List<String> result,
                                 String digits) {

        if (index == digits.length()) {
            result.add(current.toString());
            return;
        }

        int digit = digits.charAt(index) - '0'; // digits = 23, digit = 2 (when index is 0) | digit = 3 (when index is 1)
        String chars = mapOfNumbersToChars.get(digit); // abc (digit = 2) | def (digit = 3)

        for (int i = 0; i < chars.length(); i++) {
            current.append(chars.charAt(i)); // current = a
            backTrack(index + 1, current, mapOfNumbersToChars, result, digits);
            current.deleteCharAt(current.length() - 1);
        }

    }
}
