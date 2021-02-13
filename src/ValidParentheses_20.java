import java.util.Stack;

public class ValidParentheses_20 {

    public static void main(String[] args) {
        ValidParentheses_20 test=new ValidParentheses_20();
        System.out.println(test.isValid("[]"));
    }

    //优化stack 1ms
    public boolean isValid2(String s) {
        if(s.length()%2>0) return false;
        if(s.isEmpty())
            return true;
        Stack<Character> stack=new Stack<Character>();
        for(char c:s.toCharArray()){
            if(c=='(')
                stack.push(')');
            else if(c=='{')
                stack.push('}');
            else if(c=='[')
                stack.push(']');
            else if(stack.empty()||c!=stack.pop())
                return false;
        }
        return stack.isEmpty();
    }


    //stack 2ms
    public boolean isValid(String s) {
        if(s.length()%2>0) return false;
        Stack<Integer> stack=new Stack<>();
        for(int i=0;i<s.length();i++){
            int temp=s.charAt(i);
            if(temp=='('||temp=='['||temp=='{') stack.push(temp);
            else {
                if(stack.isEmpty()) return false;
                else {
                    if(temp==')') {
                        if(stack.peek()=='(') stack.pop();
                        else return false;
                    }else if(temp==']') {
                        if(stack.peek()=='[') stack.pop();
                        else return false;
                    }else if (temp=='}') {
                        if(stack.peek()=='{') stack.pop();
                        else return false;
                    }
                }
            }
        }
        return stack.isEmpty();
    }
}
