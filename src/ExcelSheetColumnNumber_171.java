
/**
 * Given a column title as appear in an Excel sheet, return its corresponding column number.
 * For example:
 *
 *     A -> 1
 *     B -> 2
 *     C -> 3
 *     ...
 *     Z -> 26
 *     AA -> 27
 *     AB -> 28
 *     ...
 * Example 1:
 * Input: "A"
 * Output: 1
 *
 * Example 2:
 * Input: "AB"
 * Output: 28
 *
 * Example 3:
 * Input: "ZY"
 * Output: 701
 */
public class ExcelSheetColumnNumber_171 {
    public static void main(String[] args) {
        ExcelSheetColumnNumber_171 test=new ExcelSheetColumnNumber_171();
        System.out.println(test.titleToNumber("A"));
    }
    public int titleToNumber(String s) {
        int count=0;
        for(int i=0;i<s.length();i++){
            int c=s.charAt(i)-'A'+1;
            System.out.println(c);
            count=count+(int)Math.pow(26,s.length()-i-1)*c;
        }
        return count;
    }
}

