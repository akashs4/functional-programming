package ch02;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @author Singh.Akash
 *
 */
public class UsingCollections {
	public static void main(String[] args) {
		final List<String> friends = Arrays.asList("Akash", "Shobhit", "Yash", "Kshitij");

		// transforming list
//		friends.stream().map(String::toUpperCase).forEach(System.out::println);

		// reusing lambda expressions
//		final List<String> editors = Arrays.asList("Brian", "Jackie", "John", "Mike");
		final List<String> comrades = Arrays.asList("Kate", "Ken", "Nick", "Paula");

//		final long countFriendsStartA = friends.stream().filter(friend -> friend.startsWith("A")).count();
//		final long countEditorsStartN = editors.stream().filter(editor -> editor.startsWith("N")).count();
//		final long countComradesStartN = comrades.stream().filter(comrade -> comrade.startsWith("N")).count();
//
//		System.out.println(countFriendsStartA);
//		System.out.println(countEditorsStartN);
//		System.out.println(countComradesStartN);

		// alternative 1
//		System.out.println(friends.stream().filter(checkIfStartsWith("A")).count());
//		System.out.println(editors.stream().filter(checkIfStartsWith("B")).count());
//		System.out.println(comrades.stream().filter(checkIfStartsWith("K")).count());

		// best way, since we will now reduce the scope to wherever it's needed only
		// for given letter constructing inner lambda expression, where name string
		// checks if it starts with letter string
		final Function<String, Predicate<String>> startsWithLetter = letter -> name -> name.startsWith(letter);

		final long countStartsWithK = comrades.stream().filter(startsWithLetter.apply("K")).count();
		System.out.println(countStartsWithK);

		// PICK AN ELEMENT
		pickName(friends, "N");
		pickName(friends, "A");

		// REDUCING COLLECTION TO SINGLE VALUE

		System.out.println(
				"Total number of characters in all names: " + friends.stream().mapToInt(name -> name.length()).sum());
	}

	private static void pickName(final List<String> names, final String startingLetter) {
		final Optional<String> foundName = names.stream().filter(name -> name.startsWith(startingLetter)).findFirst();

		System.out.println(
				String.format("A name starting with %s: %s", startingLetter, foundName.orElse("No name found")));

		// can also use below optional way to execute a block of code only if value is
		// present in optional
		foundName.ifPresent(name -> System.out.println("Hello " + name));
	}

//	private static Predicate<String> checkIfStartsWith(String letter) {
//		return name -> name.startsWith(letter);
	// in above lambda expression, name is clear, param to lambda expression
	// but what's variable letter bound to, java doesn't find it in scope of this
	// lambda expression
	// so java reaches over to scope of definition of this lambda expression and
	// find letter in that scope (LEXICAL SCOPING).
//	}
}
