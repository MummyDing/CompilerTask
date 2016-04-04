package mummyding.github;


import mummyding.github.util.FileUtil;
import mummyding.github.util.LexUtil;

/**
 * @author MummyDing
 * @date 04/04/2016
 * @GitHub https://github.com/MummyDing/CompilerTask
 * @Blog http://blog.csdn.net/mummyding
 */


public class Main {

    public static void main(String[] args) {
	// write your code here

        String text = FileUtil.readFromFile("test.txt");
        FileUtil.writeToFile("output.txt",LexUtil.build(text));
    }
}
