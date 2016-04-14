package mummyding.github;

import java.util.*;

/**
 * Created by mummyding on 16-4-14.
 */
public class GrammarUtil {

    public static void printResult(String text){
        text = text.replaceAll(" ","");
        List<String> list = Arrays.asList(text.split(";"));
        System.out.println("该文法是"+getType(list)+"型文法");
        System.out.println("开始符是: "+getStartSymbol(list));
        System.out.println("终结符是: "+getEndType(list));
        System.out.println("非终结符是: "+getNotEndType(list));
        System.out.println("产生式: "+getExpression(list));
    }

    private static int getType(List<String> str){
        boolean flag = true;
        // 0型文法
        for (String s:str){
            String [] tmp = s.split("->");
            for (int i=0 ; i<tmp[0].length() ; i++){
                if (TextUtil.isUpperLetter(tmp[0].charAt(i))){
                    flag = false;
                    break;
                }
            }
        }
        if (flag){
            return -1;
        }

        // 1型文法
        for (String s:str){
            String [] tmp = s.split("->");
            if (tmp[0].length() > tmp[1].length()){
                flag = true;
                break;
            }
        }

        if (flag){
            return 0;
        }

        // 2型文法

        for (String s:str){
            String [] tmp = s.split("->");
            for (int i=0 ; i<tmp[0].length() ; i++){
                if (TextUtil.isLowerLetter(tmp[0].charAt(i))){
                    flag = true;
                    break;
                }
            }
        }

        if (flag){
            return 1;
        }

        // 3型文法
        for (String s:str){
            String [] tmp = s.split("->");
            for (int i=0 ; i<tmp[1].length() ; i++){
                if (TextUtil.isUpperLetter(tmp[1].charAt(i))){
                    if (i+1<tmp[1].length() && TextUtil.isLowerLetter(tmp[1].charAt(i+1))){
                        flag = true;
                        break;
                    }
                }
            }
        }
        if (flag){
            return 2;
        }
        return 3;
    }

    private static Set<Character> getStartSymbol(List<String> str){
        Set<Character> resultSet = new HashSet<>();
        Set<Character> set = getNotEndType(str);
        StringBuffer endStr = new StringBuffer();
        for (String s: str){
            String[] tmp = s.split("->");
            endStr.append(tmp[1].charAt(tmp[1].length()-1));
        }
        for (Character ch : set){
            boolean flag = true;
            for (int i=0 ; i<endStr.length() ;i++){
                if (ch == endStr.charAt(i)){
                    flag = false;
                }
            }
            if (flag){
                resultSet.add(ch);
            }
        }
        return resultSet;
    }

    private static Set<Character> getEndType(List<String> str){
        Set<Character> set = new HashSet<>();
        for (String s:str){
            for (int i=0 ; i<s.length() ;i++){
                if (TextUtil.isLowerLetter(s.charAt(i))){
                    set.add(s.charAt(i));
                }
            }
        }

        return set;
    }

    private static Set<Character> getNotEndType(List<String> str){
        Set<Character> set = new HashSet<>();
        for (String s:str){
            for (int i=0 ; i<s.length() ;i++){
                if (TextUtil.isUpperLetter(s.charAt(i))){
                    set.add(s.charAt(i));
                }
            }
        }
        return set;
    }

    private static List<String> getExpression(List<String> str){

        List<String> result = new ArrayList<>();
        List<String> firstExpressList = new LinkedList<>();
        List<String> secondExpressList = new LinkedList<>();
        for (String s:str){
            String [] tmp = s.split("->");
            firstExpressList.add(tmp[0]);
            secondExpressList.add(tmp[1]);
        }
        while (true){
            if (firstExpressList.isEmpty()){
                break;
            }
            String s = firstExpressList.get(0);
            List<String> endS = new ArrayList<>();
            List<Integer> deletePos = new ArrayList<>();
            for (int i=0 ; i<firstExpressList.size() ;i++){
                if (firstExpressList.get(i).equals(s)){
                    endS.add(secondExpressList.get(i));
                    deletePos.add(i);
                }
            }

            for (int i=0 ;i<deletePos.size() ;i++){
                firstExpressList.remove(deletePos.get(i)-i);
                secondExpressList.remove(deletePos.get(i)-i);
            }

            StringBuffer express = new StringBuffer(s+"->");
            for (int i=0 ;i<endS.size() ;i++){
                if (i==0){
                    express.append(endS.get(i));
                }else{
                    express.append("|"+endS.get(i));
                }
            }
            result.add(express.toString());

        }
        return result;
    }

}
