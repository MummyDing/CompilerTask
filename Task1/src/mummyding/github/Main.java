package mummyding.github;


/**
 * @author MummyDing
 * @date 02/04/2016
 * @GitHub https://github.com/MummyDing/CompilerTask
 * @Blog http://blog.csdn.net/mummyding
 */
public class Main {

    public static void main(String[] args) {
        // function 1: trim
        System.out.println(TextUtil.trim("   12  2 3 23 23  4 34   43 4 34  "));
        // function 2: reverse string
        System.out.println(TextUtil.reverse("12345678",3,5));
        // function 3: count sub string
        System.out.println(TextUtil.findSubStringCount("12323232111212121","1212"));
        // function 4: find common sub string (with max length)
        System.out.println(TextUtil.findMaxCommonString("12121,12121,21211122122","32121111221"));
        // function 5: reverse words
        System.out.println(TextUtil.reverseWords("I am come from China!"));
    }
}
