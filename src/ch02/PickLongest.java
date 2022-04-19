package ch02;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author Singh.Akash
 */
public class PickLongest {

	public static void main(String[] args) {
		final List<String> friends = Arrays.asList("Akash", "Shobhit", "Yash", "Kshitij");
		final Optional<String> longName = friends.stream()
				.reduce((name1, name2) -> (name1.length() > name2.length()) ? name1 : name2);
		// what reduce method has to do is separated from it into lambda expression that
		// we pass in it
		longName.ifPresent(name -> System.out.println("Longest name is: " + longName.get()));

		// can also use reduce like below, so that we get a default value in worst case
		final String anotherLongName = friends.stream().reduce("Steverrrrrr",
				(name1, name2) -> (name1.length() > name2.length()) ? name1 : name2);

		System.out.println("Another long name: " + anotherLongName);
	}
}
