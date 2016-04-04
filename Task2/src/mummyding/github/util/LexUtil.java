package mummyding.github.util;

import java.util.regex.Pattern;

/**
 * Created by mummyding on 16-4-4.
 */
public class LexUtil {

    public static final String [] KEY = {"begin", "var", "real","int", "write", "read", "end"};

    public static final String [] OPER = {"+","-","\\*","//",":=",">","<",">=","<=","!="};

    public static final String [] DIVI = {",",":",";","{","}","(",")"};

    public static String build(String text){

        String [] strs = text.split(" ");
        StringBuilder resultContent = new StringBuilder();
        for (int i=0 ;i < strs.length ;i++){
            for (int j=0 ; j<KEY.length ;j++){
                String tmp = strs[i];
                int count = findKey(strs[i],KEY[j]);
                strs[i] = TextUtil.trim(strs[i]);
                if (count >0 ){
                    resultContent.append("\n"+2+" "+KEY[j]);
                    strs[i] = strs[i].replaceAll(Pattern.quote(KEY[j])," ");
                }
            }

            for (int j=0 ; j<OPER.length ;j++){
                int count = findKey(strs[i],OPER[j]);
                strs[i] = TextUtil.trim(strs[i]);
                if (count >0 ){
                    resultContent.append("\n"+4+" "+OPER[j]);
                    strs[i] = strs[i].replaceAll(Pattern.quote(OPER[j])," ");
                }
            }


            for (int j=0 ; j<DIVI.length ;j++){
                int count = findKey(strs[i],DIVI[j]);
                strs[i] = TextUtil.trim(strs[i]);
                if (count >0 ){
                    resultContent.append("\n"+5+" "+DIVI[j]);
                    strs[i] = strs[i].replaceAll(Pattern.quote(DIVI[j])," ");
                }
            }

            String [] tmpStrs = strs[i].split(" ");
            for (int j=0 ; j<tmpStrs.length ; j++){
                strs[j] = TextUtil.trim(strs[j]);
                if (TextUtil.isNull(tmpStrs[j].trim())) continue;
                if (TextUtil.containsLetter(tmpStrs[j])){
                    resultContent.append("\n1 "+tmpStrs[j]);
                }else{
                    resultContent.append("\n3 "+tmpStrs[j]);
                }
            }

        }

        return resultContent.toString();

    }


    private static int findKey(String text,String key){
        int count = TextUtil.findSubStringCount(text.trim(),key);
        return count;
    }
}
