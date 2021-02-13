import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
/*
* Given a string, we can "shift" each of its letter to its successive letter,
* for example: "abc" -> "bcd". We can keep "shifting" which forms the sequence:
* "abc" -> "bcd" -> ... -> "xyz"
* Given a list of strings which contains only lowercase alphabets,
* group all strings that belong to the same shifting sequence.
* Example:
Input: ["abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"],
Output:
[
  ["abc","bcd","xyz"],
  ["az","ba"],
  ["acef"],
  ["a","z"]
]
*
* */
public class GroupShiftedStrings_249 {

    public List<List<String>> groupStrings(String[] strings) {
        List<List<String>> group=new ArrayList<>();
        HashMap<String,List<String>> check=new HashMap<>();

        for(int i=0;i<strings.length;i++){
            char[] s=strings[i].toCharArray();

            StringBuilder sb=new StringBuilder();
            sb.append(1);
            for(int j=1;j<s.length;j++){
                //重点在于如何将判断a和z或类似的距离为1，其实就是将第一个数字为基准
                sb.append(((s[j]-s[0]+26)%26));
            }
            if(check.get(sb.toString())==null){
                List<String> temp=new ArrayList<>();
                temp.add(strings[i]);
                check.put(sb.toString(),temp);
            }else{
                check.get(sb.toString()).add(strings[i]);
            }
        }

        Iterator<List<String>> get=check.values().iterator();
        while (get.hasNext()){
            group.add(get.next());
        }

        return group;
    }

}
