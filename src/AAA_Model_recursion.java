public class AAA_Model_recursion {

    public static void main(String[] args) {
        BloomFilter test = new BloomFilter(3,10);
        test.add("a");
//        test.add("b");
    }


    //1.典型递归模板
    int Max_level=Integer.MAX_VALUE;
    public void recur(int level,int param){
        //1. terminator 循环终结条件
        if(level>Max_level){
            //返回结果
            return;
        }

        //2.process current logic 当前层里所要处理的业务逻辑
        //.....

        //3.drill down进入下一层递归
        recur(level+1,param);

        //4.restore current status 恢复或清理数据，有些全局变量的数据之类的，看情况而定
    }


    //1.分治思想模板（特殊递归）
    public void subdivide(int level,int param){
        //1. terminator 循环终结条件
        if(level>Max_level){
            //返回结果
            return;
        }

        //2.process current logic 当前层里细分要处理的业务逻辑，

        //3.drill down进入下一层递归
        //.....int a=subdivide(level,param)
        //.....int b=subdivide(level,param)
        //.....int c=subdivide(level,param)

        //4.将当前层所有数据整合（典型递归没有的）
        //int sum=help(a,b,c,....)


        //5.restore current status 恢复或清理数据，有些全局变量的数据之类的，看情况而定
    }
}
