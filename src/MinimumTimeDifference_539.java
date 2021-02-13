import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/*
* Given a list of 24-hour clock time points in "Hour:Minutes" format, find the minimum minutes difference between any two time points in the list.
Example 1:
Input: ["23:59","00:00"]
Output: 1
Note:
The number of time points in the given list is at least 2 and won't exceed 20000.
The input time is legal and ranges from 00:00 to 23:59.
* */
public class MinimumTimeDifference_539 {
    public static void main(String[] args) {
        MinimumTimeDifference_539 test=new MinimumTimeDifference_539();
        List<String> time=new ArrayList<>();
        time.add("23:59");
        time.add("00:00");
//        System.out.println(test.findMinDifference(time));
        HashSet<Integer> s=new HashSet<>();
        s.add(1);
        System.out.println(s.add(1));
    }



    //排序求解
    public int findMinDifference(List<String> timePoints) {
        int[] check=new int[timePoints.size()];
        HashSet<String> set=new HashSet<>(); //速度提升点1:利用hashset判断是否有相同时间点，有直接返回0
        for(int i=0;i<timePoints.size();i++){
            if(!set.add(timePoints.get(i))) return 0; //hashset的add函数特性，如果有重复的，返回false，否则返回true
            int h=Integer.parseInt(timePoints.get(i).substring(0,2));
            int m=Integer.parseInt(timePoints.get(i).substring(3));
            check[i]=h*60+m; //将其全部转为minutes
        }
        Arrays.sort(check); //排序是为了使得两两之间距离为最小，因此后面只需判断两两之间距离哪个最小即可
        int min=(check[0]-check[check.length-1]+1440)%1440; //优化2：先将首尾的进行比较，结果放入min，就不用再全部对比完在进行一次首尾比较
        for(int i=1;i<check.length;i++){
            int temp=(check[i]-check[i-1]+1440)%1440; //00:00与23:59只差1,（用大的-小的+最大总和）%总和，即可实现循环
            if(min>temp) min=temp;
        }
        return min;
    }




}
