import java.util.List;

/*
* A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

Write a function to count the total strobogrammatic numbers that exist in the range of low <= num <= high.

Example:
Input: low = "50", high = "100"
Output: 3

* Explanation: 69, 88, and 96 are three strobogrammatic numbers.
Note:
Because the range might be a large number, the low and high numbers are represented as string.
* */
public class StrobogrammaticNumberIII_248 {

    public static void main(String[] args) {
        StrobogrammaticNumberIII_248 test=new StrobogrammaticNumberIII_248();
        System.out.println(test.strobogrammaticInRange("0","0"));
    }

    public final char[][] mapp=new char[][]{{'0','0'},{'1','1'},{'6','9'},{'8','8'},{'9','6'}};
    public int num=0;
    public int strobogrammaticInRange(String low, String high) {
        int l=low.length();
        int h=high.length();
        for(int i=l;i<=h;i++){
            check(new char[i],0,i-1,low,high);
        }
        return num;

    }

    public void check(char[] chr,int left,int right,String low,String high){
        if(left>right){
            if(chr[0]!='0'||chr.length==1){
                String s=String.valueOf(chr);
                if(compareto(s,low)&&compareto(high,s)) num++;
            }
            return;
        }
        for(char[] map:mapp){
            if(left==right&&map[0]!=map[1]) continue;
            chr[left]=map[0];
            chr[right]=map[1];
            check(chr,left+1,right-1,low,high);
        }
    }

    public boolean compareto(String a,String b){
        if(a.length()==b.length()){
            if(a.compareTo(b)>=0) return true;
            else return false;
        }else return true;
    }



}
