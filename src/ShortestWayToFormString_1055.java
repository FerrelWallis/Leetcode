public class ShortestWayToFormString_1055 {
    public static void main(String[] args) {
        ShortestWayToFormString_1055 test=new ShortestWayToFormString_1055();
        System.out.println(test.shortestWay("abc","abcab"));
    }


    //双指针,判断使用source的次数
    public int shortestWay(String source, String target) {

        char[] tar=target.toCharArray();
        char[] sor=source.toCharArray();
        int count=0;

        //若target中有source中没有的字符，直接返回
        for(int i=0;i<tar.length;){
            if(!source.contains(""+tar[i])) return -1;
            for(int j=0;j<sor.length;j++){
                if(i>=tar.length) break;
                if(tar[i]==sor[j]) i++;
            }
            count++;
        }
        return count;
    }

    public int shortestWay2(String source, String target) {
        boolean[] need = new boolean[26];
        for(char c : source.toCharArray()) {
            need[c-'a'] = true;
        }
        int i = 0;
        for(char c : target.toCharArray()) {
            if(!need[c-'a']) return -1;
            while(source.charAt(i % source.length()) != c) i++;
            i++;
        }
        return (i-1+source.length()) / source.length();
    }


}
