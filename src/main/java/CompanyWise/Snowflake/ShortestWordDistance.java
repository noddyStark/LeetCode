package CompanyWise.Snowflake;


/*
* Given an array of strings wordsDict and two different strings that already exist in the array word1 and word2,
* return the shortest distance between these two words in the list.
Example 1:

Input: wordsDict = ["practice", "makes", "perfect", "coding", "makes"], word1 = "coding", word2 = "practice"
Output: 3
Example 2:

Input: wordsDict = ["practice", "makes", "perfect", "coding", "makes"], word1 = "makes", word2 = "coding"
Output: 1

* Constraints:

2 <= wordsDict.length <= 3 * 104
1 <= wordsDict[i].length <= 10
wordsDict[i] consists of lowercase English letters.
word1 and word2 are in wordsDict.
word1 != word2
**/
public class ShortestWordDistance {

    static void main() {

        String[] wordsDict = {"practice", "makes", "perfect", "coding", "makes"};
        String word1 = "coding";
        String word2 = "practice";

        System.out.println(shortestDistance(wordsDict, word1, word2));
    }

    private static int shortestDistance(String[] wordsDict, String word1, String word2) {

        int index1 = -1;
        int index2 = -1;

        int minDistance = Integer.MAX_VALUE;

        for (int i = 0; i < wordsDict.length; i++) {

            if (word1.equals(wordsDict[i])) {
                index1 = i;
            }

            if(word2.equals(wordsDict[i])) {
                index2 = i;
            }

            if (index1 != -1 && index2 != -1) {
                minDistance = Math.min(Math.abs(index1 - index2), minDistance);
            }
        }

        return minDistance;
    }
}
