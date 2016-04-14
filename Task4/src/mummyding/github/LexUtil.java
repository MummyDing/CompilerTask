package mummyding.github;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by mummyding on 16-4-4.
 */
public class LexUtil {

    public static final String [] KEY = {"begin", "var", "real","int", "write", "read", "end"};

    public static final String [] OPER = {"+","-","\\*","//",":=",">","<",">=","<=","!="};

    public static final String [] DIVI = {",",":",";","{","}","(",")"};


    public static final String htmlPre = "<html>\n" +
            "<body>\n" +
            "\n" +
            "<style>\n" +
            ".fontRed{\n" +
            "   color:red;\n" +
            "}\n" +
            ".fontBlue{\n" +
            "   color:blue;\n" +
            "}\n" +
            ".fontOrange{\n" +
            "   color:orange;\n" +
            "}\n" +
            ".fontYellow{\n" +
            "   color:yellow;\n" +
            "}\n" +
            "</style>";

    public static final String htmlEnd="</body>\n" +
            "</html>";
    public static final String styleEnd="</span>";

    public static final String styleRed="<span class=\"fontRed\">";

    public static final String styleYellow="<span class=\"fontYellow\">";

    public static final String styleOrange="<span class=\"fontOrange\">";

    public static final String styleBlue="<span class=\"fontBlue\">";

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


    public static String highLight(List<String> str){

        List<String> result = new ArrayList<>();

        for (String text:str){
            String [] strs = text.split(" ");
            for (int i=0 ;i<strs.length ;i++){
                for (int j=0 ; j<OPER.length ;j++){
                    if (!OPER[j].equals(">") && !OPER[j].equals("<"))
                    strs[i] = strs[i].replaceAll(Pattern.quote(OPER[j]),styleOrange+OPER[j]+styleEnd);
                }
            }

            for (int i=0 ;i<strs.length;i++){
                for (int j=0 ;j<DIVI.length ;j++){
                    strs[i] = strs[i].replaceAll(Pattern.quote(DIVI[j]),styleYellow+DIVI[j]+styleEnd);
                }
            }

            for (int i=0 ;i<strs.length ;i++){
                for (int j=0 ;j<KEY.length ;j++){
                    strs[i] = strs[i].replaceAll(Pattern.quote(KEY[j]),styleRed+KEY[j]+styleEnd);
                }
            }



            for (int j=0 ; j<strs.length ; j++){
                strs[j] = TextUtil.trim(strs[j]);

                String regex = ">\\d+(\\.\\d+)?<";
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(strs[j]);
                StringBuffer r = new StringBuffer();
                while (matcher.find()) {
                    StringBuffer replace = new StringBuffer();
                    replace.append(">"+styleBlue).append(matcher.group());
                    replace.append(styleEnd+"<");
                    matcher.appendReplacement(r, replace.toString());
                }
                matcher.appendTail(r);

                strs[j] = r.toString().replaceAll(">>",">");;
                strs[j] = strs[j].replaceAll("<<","<");

            }

            StringBuilder tmp = new StringBuilder("<p>");
            for (int i=0 ;i<strs.length ;i++){
                tmp.append("&nbsp;").append(strs[i]);
            }

            result.add(tmp.toString()+"<br>\n");

        }
        StringBuffer r = new StringBuffer();
        r.append(htmlPre);
        for (String s:result){
            r.append(s);
        }
        r.append(htmlEnd);
        return r.toString();
    }
    private static int findKey(String text,String key){
        int count = TextUtil.findSubStringCount(text.trim(),key);
        return count;
    }
}
