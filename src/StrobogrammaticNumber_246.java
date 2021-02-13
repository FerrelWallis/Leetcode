
/*
* A strobogrammatic number is a number that looks the same when rotated 180 degrees
(looked at upside down).

Write a function to determine if a number is strobogrammatic.
The number is represented as a string.

Example 1:
Input:  "69"
Output: true

Example 2:
Input:  "88"
Output: true

Example 3:
Input:  "962"
Output: false

* */

public class StrobogrammaticNumber_246 {
    public static void main(String[] args) {
        StrobogrammaticNumber_246 test=new StrobogrammaticNumber_246();
        System.out.println(test.isStrobogrammatic("6"));
    }

    public boolean isStrobogrammatic(String num) {
        int n;
        if(num.length()%2==0) n=num.length()/2;
        else n=num.length()/2+1;
        for(int i=0;i<n;i++){
            if(!check(num.charAt(i),num.charAt(num.length()-1-i)))
                return false;
        }
        return true;
    }

    public boolean check(char left,char right){
        if((left=='0'||left=='1'||left=='8')&&left==right) return true;
        if((left=='6'&&right=='9')||(left=='9'&&right=='6')) return true;
        return false;
    }

}
