package ch03;

/**
 * @author Singh.Akash
 *
 */
public class IterateString {
	public static void main(String[] args) {
		String str = "w00t";
//		str.chars().forEach(System.out::println);
		// In above, since we already provided target for method reference println,
		// hence java used each int as argument instead of param

//		str.chars().forEach(IterateString::printChar);
		// can also do like this if we directly want stream of characters instead of int
		str.chars().mapToObj(ch -> Character.valueOf((char) ch)).forEach(System.out::print);

		System.out.println();
		str.chars().filter(Character::isDigit).forEach(IterateString::printChar);
	}

	private static void printChar(int ch) {
		System.out.println((char) ch);
	}
}
