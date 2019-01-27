import java.util.TreeSet;

public class RemoveDuplicates {

	public static void main(String[] args) {
		RemoveDuplicates.test();
	}

	public static void test() {
		String input = "Mississipi";
		char[] characters = input.toCharArray();

		TreeSet<String> ts = new TreeSet<String>();
		ts.add("M");
		ts.add("i");
		ts.add("s");
		ts.add("s");
		ts.add("i");
		ts.add("s");
		ts.add("s");
		ts.add("i");
		ts.add("p");
		ts.add("i");
		System.out.println(ts);

	}

}
