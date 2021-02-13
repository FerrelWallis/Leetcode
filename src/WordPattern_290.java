import java.util.HashMap;

public class WordPattern_290 {

    public boolean wordPattern(String pattern, String str) {
        HashMap<String,Integer> m_str=new HashMap<>();
        int[] m_pattern=new int[128];
        String[] strings=str.split(" ");
        if(strings.length!=pattern.length()) return false;
        for(int i=0;i<pattern.length();i++){
            char p=pattern.charAt(i);
            String s=strings[i];
            if(m_pattern[p]!=m_str.getOrDefault(s,0)){
                return false;
            }else {
                if(m_pattern[p]==0){
                    m_pattern[p]=i+1;
                    m_str.put(s,i+1);
                }

            }
        }
        return true;
    }
}
