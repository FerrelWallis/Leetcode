import java.util.HashMap;

public class IsomorphicString_205 {
    public static void main(String[] args) {
        int[] a=new int[5];
        System.out.println(a[1]);
    }

    public boolean ThirdJudge(String s, String t) {
        if(s.length()!=t.length()) return false;
        int n = s.length();
        //设置两个映射，分别对照s,t  int[128]是ASKⅡ码值, map['a']会强转成map[97]
        //int数组未赋值前对应为0 ，mapS[2]=0
        int[] mapS = new int[128];
        int[] mapT = new int[128];

        for (int i = 0; i < n; i++) {
            char c1 = s.charAt(i);
            char c2 = t.charAt(i);
            //当前的映射值是否相同,不同直接false
            if (mapS[c1] != mapT[c2]) {
                return false;
            } else {
                //映射值相同有两种情况：1.未修改过 2.已修改过
                //未修改过需要进行赋值，对它赋的具体值不重要，但是必须大于0且mapS[c1]与mapT[c2]相同，才能建立对应关系
                if (mapS[c1] == 0) {
                    mapS[c1] = i + 1;
                    mapT[c2] = i + 1;
                }
            }
        }
        return true;
    }


    public boolean DoubleSide(String s, String t) {
        if(s.length()!=t.length()) return false;
        HashMap<Character,Character> map=new HashMap<>();
        HashMap<Character,Character> map2=new HashMap<>();
        for(int i=0;i<s.length();i++){
            if(map.get(s.charAt(i))==null){
                map.put(s.charAt(i),t.charAt(i));
            }else{
                if(map.get(s.charAt(i))!=t.charAt(i)) return false;
            }
            if(map2.get(t.charAt(i))==null){
                map2.put(t.charAt(i),s.charAt(i));
            }else {
                if(map2.get(t.charAt(i))!=s.charAt(i)) return false;
            }
        }
        return true;
    }






}
