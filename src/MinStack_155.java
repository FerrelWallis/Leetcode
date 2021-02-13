import java.util.Stack;

public class MinStack_155 {
    /** initialize your data structure here. */
    private Stack<Integer> data;
    private Stack<Integer> min;

    //双栈、辅助栈维护最小元素
//    public MinStack_155() {
//        data=new Stack<>();
//        min=new Stack<>();
//    }

    public void push(int x) {
        data.push(x);
        if(min.empty() || min.peek()>=x) min.push(x);
    }

    public void pop() {
        if(data.pop().equals(min.peek())) min.pop();
    }

    public int top() {
        return data.peek();
    }

    public int getMin() {
        return min.peek();
    }


    private int min2;


    //单栈+变量，维护最小元素
    //问题1：新的更小元素入栈之后，min2赋值会丢失之前的最小值
    //解决：再将新的更小值赋值给min2前，将原最小值压入栈中，再将新的值压入栈中，最后赋值min2
    public MinStack_155() {
        data=new Stack<>();
        min2=Integer.MAX_VALUE;
    }

    public void push2(int x) {
        if(x<=min2){
            data.push(min2);
            min2=x;
        }
        data.push(x);
    }

    public void pop2() {
        if(data.pop()==min2) min2=data.pop();
    }

    public int top2() {
        return data.peek();
    }

    public int getMin2() {
        return min2;
    }


}
/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */