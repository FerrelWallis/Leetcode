import java.util.ArrayList;
import java.util.List;

/*
* You are playing the following Flip Game with your friend:
* Given a string that contains only these two characters: + and -,
* you and your friend take turns to flip two consecutive "++" into "--".
* The game ends when a person can no longer make a move and therefore the other person will be the winner.
* Write a function to compute all possible states of the string after one valid move.
Example:
Input: s = "++++"
Output:
[
  "--++",
  "+--+",
  "++--"
]
* 请你写出一个函数，来计算出每个第一次有效操作后，字符串所有的可能状态。
* */


public class FlipGame_293 {

    public List<String> generatePossibleNextMoves(String s) {
        List<String> string=new ArrayList<>();
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)=='+'&&s.charAt(i+1)=='+'){
                StringBuilder ss=new StringBuilder(s);
                ss.setCharAt(i,'-');
                ss.setCharAt(i+1,'-');
                string.add(ss.toString());
            }
        }
        return string;
    }
}
