package mummyding.github;

/**
 * @author MummyDing
 * @date 02/04/2016
 * @GitHub https://github.com/MummyDing/CompilerTask
 * @Blog http://blog.csdn.net/mummyding
 */
public class TextUtil {

    public static String UpperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static String LowerCase = "abcdefghijklmnopqrstuvwxyz";

    public static boolean isNull(String str){
        if (str == null || str.length() == 0) return true;
        return false;
    }

    public static boolean isNull(char ch){
        if (ch == ' ') return true;
        return false;
    }

    public static boolean isLetter(char ch) {
        for (int i = 0; i < UpperCase.length(); i++)
            if (UpperCase.charAt(i) == ch) return true;
        for (int i=0 ;i<LowerCase.length();i++)
            if (LowerCase.charAt(i) == ch) return true;
        return false;
    }
    public static String trim(String str){
        int len = str.length();
        int start = 0 , end = len-1 ;
        for (;start<= end && str.charAt(start)==' ';)start++;
        for (;end>=start &&str.charAt(end) == ' ';) end--;
        return str.substring(start,end+1);
    }
    public static String reverse(String str,int start,int end){
        char[] tmpStr = str.substring(start,end).toCharArray();
        StringBuilder midStr = new StringBuilder(String.valueOf(tmpStr));
        return str.substring(0,start)+midStr.reverse()+str.substring(end,str.length());
    }

    public static int findSubStringCount(String str,String subStr){
        int count = 0 , endPos = str.length() - subStr.length();

        for (int i=0 ; i<= endPos ;i++)
            if ((str.substring(i,subStr.length()+i)).equals(subStr)) count++;
        return count;
    }

    public static String findMaxCommonString(String strA,String strB){
        if (strA.length() < strB.length()){
            String tmp = strA;
            strA = strB;
            strB = tmp;
        }
        int len = strB.length();
        for (int i=len; i>0 ;i--)
            for (int j=0 ;j<=len-i;j++)
                if (strA.contains(strB.subSequence(j,i+j))) return strB.substring(j,i+j);
        return "";
    }


    public static String reverseWords(String str){
        StringBuilder result = new StringBuilder(str.length());
        int end = str.length();
        for (int i=str.length() -1 ;i>=0 ;i--){
            if (str.charAt(i) == ' ' || i==0){
                result.append(str.substring(i+1,end));
                result.append(str.charAt(i));
            }else if (isLetter(str.charAt(i)) == false){
                result.append(str.charAt(i));
            }else continue;
            end = i;
        }
        return result.toString();
    }
}
