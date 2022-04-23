package ch03;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author Singh.Akash
 *
 */
public class Compare {
	public static void main(String[] args) {
		final List<Person> people = Arrays.asList(new Person("John", 20), new Person("Sara", 21),
				new Person("Jane", 21), new Person("Greg", 35));

//		List<Person> decreasingAge = people.stream().sorted((p1, p2) -> p2.ageDifference(p1))
//				.collect(Collectors.toList());
//		decreasingAge.forEach(System.out::println);
		// below the first arg is target and second is parameter, hence we can replace
		// above lambda with method reference.
		Comparator<Person> increasingAgeComparator = (p1, p2) -> p1.ageDifference(p2);
		Comparator<Person> decreasingAgeComparator = increasingAgeComparator.reversed();
		people.stream().sorted(decreasingAgeComparator).collect(Collectors.toList());

		List<Person> increasingAge = people.stream().sorted(Person::ageDifference).collect(Collectors.toList());
		printPeople(increasingAge, "Sorted based on increasing age");

		System.out.println("------------------------------------------");
		// FINDING SMALLEST/LARGEST IN A COLLECTION using min()/max()
		people.stream().min(increasingAgeComparator).ifPresent(person -> System.out.println("Youngest is: " + person));

		// MULTIPLE AND FLUENT COMPARISONS
		System.out.println("------------------------------------------");
		Function<Person, String> byName = person -> person.getName();
		Function<Person, Integer> byAge = person -> person.getAge();

		// sorting by name and then by age
		printPeople(
				people.stream().sorted(Comparator.comparing(byName).thenComparing(byAge)).collect(Collectors.toList()),
				"Sorting by name and age");
		// can just read above code like sorting people by name

		// Using collect method and Collectors class
		System.out.println("------------------------------------------");
		people.stream().filter(person -> person.getAge() > 20)
//				.collect(ArrayList::new, ArrayList::add, ArrayList::addAll)
				.collect(Collectors.toList()).forEach(System.out::println);
		// collect method took supplier as first argument and accumulator, combiner as
		// second and third arguments
		// collect method will perform parallel additions into different sublists and
		// then merge them in a thread safe manner into larger list

		// GROUPING PEOPLE BY THEIR AGE
		Map<Integer, List<String>> peopleByAge = people.stream().collect(
				Collectors.groupingBy(Person::getAge, Collectors.mapping(Person::getName, Collectors.toList())));
		// Collectors.mapping() method expects property on which to map and type of
		// object to collect into
		System.out.println("People grouped by age:");
		System.out.println(peopleByAge);

		// Sample: Group the names by their first character and then get oldest person
		// in each group
		Comparator<Person> byAgeComparison = Comparator.comparing(Person::getAge);
		Map<Character, Optional<Person>> oldestPersonOfEachLetter = people.stream().collect(Collectors.groupingBy(
				person -> person.getName().charAt(0), Collectors.reducing(BinaryOperator.maxBy(byAgeComparison))));
		// performed reduce operation because want to find only the oldest person for each letter
		System.out.println(oldestPersonOfEachLetter);
	}

	private static void printPeople(final List<Person> people, String message) {
		System.out.println(message);
		people.forEach(System.out::println);
	}
}
