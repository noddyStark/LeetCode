package CompanyWise.Rippling.DesignATextEditor;

public class DesignATextEditor {


    // ["TextEditor", "addText",    "deleteText",     "addText",    "cursorRight",  "cursorLeft", "deleteText", "cursorLeft", "cursorRight"]
    // [    [],     ["leetcode"],       [4],          ["practice"],       [3],             [8],         [10],       [2],        [6]]

    // Output
    //  [  null, null, 4, null, "etpractice", "leet", 4, "", "practi"]

    // TextEditor           ->      null
    // addText("leetcode")  ->      "leetcode|"
    // deleteText(4)        ->      4, The current text is "leet|".
    // addText("practice")  ->      The current text is "leetpractice|".
    // cursorRight(3)       ->      return "etpractice", The current text is "leetpractice|". The cursor cannot be moved beyond the actual text and thus did not move.
    // cursorLeft(8)        ->      return "leet", The current text is "leet|practice". "leet" is the last min(10, 4) = 4 characters to the
    // deleteText(10)       ->      returns 4, The current text is "|practice", Only 4 characters were deleted.
    // cursorLeft(2)        ->      return "", The current text is "|practice". The cursor cannot be moved beyond the actual text and thus did not move.
    // cursorRight(6)       ->      return practi, The current text is "practi|ce". "practi" is the last min(10, 6) = 6 characters to the left of the cursor.


    static void main() {
        TextEditor_BruteForce textEditor = new TextEditor_BruteForce();

        textEditor.addText("leetcode"); // leetcode|
        textEditor.deleteText(4); // leet|
        textEditor.addText("practice"); // leetpractice|
        textEditor.cursorRight(3);
        textEditor.cursorLeft(8);
        textEditor.deleteText(10);
        textEditor.cursorLeft(2);
        textEditor.cursorRight(6);

        System.out.println(textEditor.left);
        System.out.println(textEditor.right);

    }

}
