import java.util.Arrays;

public class MergeSortedArray_88 {
    public static void main(String[] args) {

    }


    //1. 先合并，再排序
    public void merge2(int[] nums1, int m, int[] nums2, int n) {
        //arraycopy参数（需要被复制的数组，开始复制的起点，黏贴到目标数组，目标数组开始黏贴的起点，黏贴的长度）
        System.arraycopy(nums2,0,nums1,m,n);
        Arrays.sort(nums1);
    }

    //2.双指针 / 从后往前
    public void merge3(int[] nums1, int m, int[] nums2, int n) {
        int l1=m-1, l2=n-1, cur=m+n-1;
        while (l1>=0 && l2>=0) {
            nums1[cur--] = nums1[l1]>=nums2[l2]? nums1[l1--]:nums2[l2--];
        }
        //如果是nums1有剩余，原本就是有序的所以无需操作，如果num2有剩余，则剩余的也是有序的，直接贴到nums1前面
        //如果num2没有剩余，l2=-1，l2+1=0，所以无影响
        System.arraycopy(nums2,0,nums1,0,l2+1);
    }


    //3. 新建array
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] ans=new int[nums1.length];
        int l1=0,l2=0;
        for(int i=0;i<m+n;i++){
            if(l1<m&&l2<n){
                if(nums1[l1]<=nums2[l2]) ans[i]=nums1[l1++];
                else ans[i]=nums2[l2++];
            }else if(l1>=m){
                ans[i]=nums2[l2++];
            }else ans[i]=nums1[l1++];
        }
        nums1=ans;
    }
}
