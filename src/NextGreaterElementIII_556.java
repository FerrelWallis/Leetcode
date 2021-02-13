import java.util.Arrays;

/*
Given a positive 32-bit integer n, you need to find the smallest 32-bit integer
which has exactly the same digits existing in the integer n and is greater in value than n.
If no such positive 32-bit integer exists, you need to return -1.

Example 1:
Input: 12
Output: 21
 
Example 2:
Input: 21
Output: -1
* */
public class NextGreaterElementIII_556 {

    public static void main(String[] args) {
        NextGreaterElementIII_556 test=new NextGreaterElementIII_556();
        System.out.println(test.nextGreaterElement(1234));
    }



    //找到最小的比当前大的数，且不超过integer_max,否则返回-1
        public int nextGreaterElement(int n) {
            char[] chars = String.valueOf(n).toCharArray();
            //从右往左找第一个比右边小的节点，说明节点右边是升序
            for(int i = chars.length - 1; i > 0; i--){
                //比右边大就继续找
                if(chars[i - 1] >= chars[i]) continue;
                //找到了就 进行下面操作
                i--;
                int j = i;
                //因为是升序，从节点后面一位开始往后找，找到最小比节点大的数，也就是找到比节点小的数的前一位
                while(j != chars.length - 1 && chars[j + 1] > chars[i]) j++;
                //将两者交换位置
                swap(chars, i , j);
                //此时节点右边依然是升序，将右边升序改为降序，即最小比当前大的数
                for(int k = i + 1; k <= i + (chars.length - 1 - i) / 2; k++){
                    swap(chars, k, chars.length + i - k);
                }
                try{
                    //将这个数转为integer，如果超过integer_max会抛出异常，直接返回-1
                    return Integer.parseInt(new String(chars));
                }
                catch (Exception e){
                    return -1;
                }
            }
            return -1;
        }

        private void swap(char[] chars, int i, int j){
            char t = chars[i];
            chars[i] = chars[j];
            chars[j] = t;
        }




    public int nextGreaterElement2(int n) {
        char[] number=Integer.toString(n).toCharArray();
        for(int i=number.length-1;i>0;i--){
            if(number[i-1]>=number[i]) continue;
            int key=i-1;
            int j=i-1;
            while(j!=number.length-1&&number[j+1]>number[key]) j++;
            char temp=number[j];
            number[j]=number[key];
            number[key]=temp;
            String s=new StringBuilder(String.valueOf(number).substring(key+1)).reverse().toString();
            try {
                return Integer.parseInt(String.valueOf(number).substring(0,key+1)+s);
            }catch (Exception e){
                return -1;
            }
        }
        return -1;
    }




}
