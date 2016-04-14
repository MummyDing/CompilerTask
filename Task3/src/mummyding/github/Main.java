package mummyding.github;

public class Main {

    public static void main(String[] args) {
	// write your code here
        String text = FileUtil.readFromFile("g.txt");
        GrammarUtil.printResult(text);
    }
}
