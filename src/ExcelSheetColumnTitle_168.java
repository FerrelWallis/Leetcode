/*
* Given a positive integer, return its corresponding column title as appear in an Excel sheet.
For example:
    1 -> A
    2 -> B
    3 -> C
    ...
    26 -> Z
    27 -> AA
    28 -> AB

Example 1:
Input: 1
Output: "A"

Example 2:
Input: 28
Output: "AB"

Example 3:
Input: 701
Output: "ZY"
* */
public class ExcelSheetColumnTitle_168 {
    public static void main(String[] args) {
        ExcelSheetColumnTitle_168 test=new ExcelSheetColumnTitle_168();
        System.out.println(test.convertToTitle(703));
    }

    public String convertToTitle(int n) {
        StringBuilder sb=new StringBuilder();
        int cur=n;
        while (cur!=0) {
            //n从1开始,直接%26当碰到26的倍数时会变为0，转成Z很麻烦，因此cur-1变为0开始
            //位数从个位先append
            sb.append((char) ((cur-1) % 26 + 'A'));
            //获取更大位数的值，因为获取位数时是0-25->A-Z 在获取更大位数值时，需要减去1-26的余数，除以26，再取余26
            cur = (cur - ((cur-1) % 26+1)) / 26;
        }
        //由于拼接时是从个位开始，因此要reverse
        return sb.reverse().toString();
    }
}
