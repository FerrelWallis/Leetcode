import java.util.HashSet;

/*
* Write a function that takes a string as input and reverse only the vowels of a string.

Example 1:
Input: "hello"
Output: "holle"

Example 2:
Input: "leetcode"
Output: "leotcede"

Note:
The vowels does not include the letter "y".
* */
public class ReverseVowelsOfAString_345 {
    public static void main(String[] args) {
        ReverseVowelsOfAString_345 test=new ReverseVowelsOfAString_345();
        System.out.println(test.reverseVowels(".,"));
    }

    //双指针左右找元音字母，交换
    public String reverseVowels(String s) {
        char[] strings=s.toCharArray();
        int left=0;
        int right=s.length()-1;
        while(left<right){
            while(left<s.length()&&!check(strings[left]))
                left++;
            while (right>0&&!check(strings[right]))
                right--;
            if(left<right){
                char temp=strings[left];
                strings[left++]=strings[right];
                strings[right--]=temp;
            }
        }
        return String.valueOf(strings);
    }

    public Boolean check(char c){
        switch (c){
            case 'a': return true;
            case 'e': return true;
            case 'o': return true;
            case 'i': return true;
            case 'u': return true;
            case 'A': return true;
            case 'E': return true;
            case 'I': return true;
            case 'O': return true;
            case 'U': return true;
            default:return false;
        }
    }
}
