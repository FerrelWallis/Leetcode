import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
* A strobogrammatic number is a number that looks the same when rotated 180 degrees
* (looked at upside down).

Find all strobogrammatic numbers that are of length = n.

Example:
Input:  n = 2
Output: ["11","69","88","96"]
* */
public class StrobogrammaticNumberII_247 {

    public static void main(String[] args) {
        StrobogrammaticNumberII_247 test = new StrobogrammaticNumberII_247();
        System.out.println(test.findStrobogrammatic(4));

    }

    char[][] mapp=new char[][]{{'0','0'},{'1','1'},{'6','9'},{'8','8'},{'9','6'}};
    public List<String> findStrobogrammatic(int n) {
        List<String> ans=new ArrayList<>();
        char[] c=new char[n];
        map(c,0,n-1,ans);
        return ans;
    }

    public void map(char[] c,int left,int right,List<String> ans){
        if(left>right){  //当左指针大于右指针，说明字符串已经完成，判断是否add
            if(c[0]!='0'||c.length==1)   //当chs不以0开头时加入res，除了长度为1的时候单个0需要加入
                ans.add(String.valueOf(c));
            return;
        }
        for(int i=0;i<mapp.length;i++){
            if(left==right && mapp[i][0]!=mapp[i][1]) continue;  //当位数为奇数个（才会出现lo==hi的情况），中间的数字不能为6或9
            c[left]=mapp[i][0];
            c[right]=mapp[i][1];
            map(c,left+1,right-1,ans);
        }
        return;

    }


}
