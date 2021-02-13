
/*
* Given a paragraph and a list of banned words, return the most frequent word
* that is not in the list of banned words. 
* It is guaranteed there is at least one word that isn't banned,
* and that the answer is unique.
* Words in the list of banned words are given in lowercase, and free of punctuation. 
* Words in the paragraph are not case sensitive.  The answer is in lowercase.
*
Example:
Input:
paragraph = "Bob hit a ball, the hit BALL flew far after it was hit."
banned = ["hit"]
Output: "ball"
* */

import java.util.HashMap;
import java.util.HashSet;

public class MostCommonWord_819 {

    public static void main(String[] args) {
        String s="Bob";
        String[] b={""};


        MostCommonWord_819 test=new MostCommonWord_819();
        System.out.println(test.mostCommonWord(s,b));
    }

    public String mostCommonWord(String paragraph, String[] banned) {
        String p=(paragraph+".").toLowerCase();
        HashSet<String> bann=new HashSet<>();
        for(String b:banned)
            bann.add(b);

        HashMap<String,Integer> map=new HashMap<>();
        String answer="";
        int ansfrequency=0;
        StringBuilder word=new StringBuilder();
        for(char c:p.toCharArray()){
            if(Character.isLetter(c)){
                word.append(c);
            }else if(word.length()>0){
                String finalword=word.toString();
                //如果这个word存在于banned中，直接跳过，不用进行以下操作节省时间
                if(!bann.contains(finalword)){
                    int wordnum=map.getOrDefault(finalword,0)+1;
                    if(ansfrequency<wordnum){
                        answer=finalword;
                        ansfrequency=wordnum;
                    }
                    map.put(finalword,wordnum);
                }
                word=new StringBuilder();
            }

        }

        return answer;
    }
}
