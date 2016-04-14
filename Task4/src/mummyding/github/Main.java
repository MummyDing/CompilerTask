package mummyding.github;

import java.util.List;

public class Main {

    public static void main(String[] args) {
	// write your code here
        List<String> text = FileUtil.readByLine("test.txt");
        FileUtil.writeToFile("output.html",LexUtil.highLight(text));
    }
}
