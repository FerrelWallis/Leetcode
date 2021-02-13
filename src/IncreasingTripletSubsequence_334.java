
/*
Given an unsorted array return whether an increasing subsequence of length 3 exists or not
in the array.
Formally the function should:
Return true if there exists i, j, k
such that arr[i] < arr[j] < arr[k] given 0 ≤ i < j < k ≤ n-1 else return false.

Note: Your algorithm should run in O(n) time complexity and O(1) space complexity.

Example 1:
Input: [1,2,3,4,5]
Output: true

Example 2:
Input: [5,4,3,2,1]
Output: false
* */
public class IncreasingTripletSubsequence_334 {
    public static void main(String[] args) {
        IncreasingTripletSubsequence_334 test=new IncreasingTripletSubsequence_334();
        int[] num=new int[]{1,1,1,1,1};
        System.out.println(test.increasingTriplet(num));
    }

    public boolean increasingTriplet(int[] nums) {
        int n1=Integer.MAX_VALUE,n2=Integer.MAX_VALUE;
        for(int n:nums){
            if(n<n1) n1=n;   //n1最小值
            else if(n>n1&&n<n2) n2=n;  //大于n1的最小值
            else if(n>n2) return true;  //大于n1,n2的值
        }
        return false;
    }
}
