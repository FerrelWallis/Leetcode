import java.util.HashMap;

/*
* Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.
Symbol       Value
I             1
V             5
X             10
L             50
C             100
D             500
M             1000
For example, two is written as II in Roman numeral, just two one's added together.
Twelve is written as, XII, which is simply X + II.
The number twenty seven is written as XXVII, which is XX + V + II.

Roman numerals are usually written largest to smallest from left to right. However,
the numeral for four is not IIII. Instead, the number four is written as IV.
Because the one is before the five we subtract it making four.
The same principle applies to the number nine, which is written as IX.
There are six instances where subtraction is used:

I can be placed before V (5) and X (10) to make 4 and 9. 
X can be placed before L (50) and C (100) to make 40 and 90. 
C can be placed before D (500) and M (1000) to make 400 and 900.
Given a roman numeral, convert it to an integer.
Input is guaranteed to be within the range from 1 to 3999.

Example 1:
Input: "III"
Output: 3

Example 2:
Input: "IV"
Output: 4

Example 3:
Input: "IX"
Output: 9

Example 4:
Input: "LVIII"
Output: 58
Explanation: L = 50, V= 5, III = 3.

Example 5:
Input: "MCMXCIV"
Output: 1994
Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.
* */
public class RomanToInteger_13 {
    public static void main(String[] args) {
        RomanToInteger_13 test=new RomanToInteger_13();
        System.out.println(test.romanToInt("III"));
    }

    //小型数据hashmap体现不出快
    public int romanToInt(String s) {
//        HashMap<Character,Integer> numerals=new HashMap<>();
//        numerals.put('I',1);
//        numerals.put('V',5);
//        numerals.put('X',10);
//        numerals.put('L',50);
//        numerals.put('C',100);
//        numerals.put('D',500);
//        numerals.put('M',1000);

        int count=0;
        int fore=getValue(s.charAt(0));

        //如果需要判断前一个与后一个的关系，采用从第二位开始与前一个对比，这样不会有range超出问题
        for(int i=1;i<s.length();i++){
            int num=getValue(s.charAt(i));
            if(fore<num)
                count-=fore;
            else count+=fore;
            fore=num;
        }
        count+=fore;

        return count;
    }


    private int getValue(char ch) {
        switch(ch) {
            case 'I': return 1;
            case 'V': return 5;
            case 'X': return 10;
            case 'L': return 50;
            case 'C': return 100;
            case 'D': return 500;
            case 'M': return 1000;
            default: return 0;
        }
    }

}
