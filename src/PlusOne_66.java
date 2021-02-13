public class PlusOne_66 {


    public int[] plusOne(int[] digits) {
        int cur=digits.length-1;
        while(cur>=0){
            digits[cur]=(digits[cur]+1)%10; //加一之后取余，如果为0，则前一位也加一取余
            if(digits[cur]!=0) return digits; //不为0 直接返回
            cur--;
        }
        //循环玩整个数组还没return，说明最大一位加一之后取余还是为0
        // 直接新建数组，长度是原来长度+1,最大位为1，其余都为0
        digits=new int[digits.length+1]; //这一步直接在原始数组上扩容
        digits[0]=1;
        return digits;
    }
}
