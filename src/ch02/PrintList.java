package ch02;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PrintList {

	public static void main(String[] args) {
		final List<String> friends = Arrays.asList("Akash", "Yash", "Shobhit", "Kshitij");
		System.out.println(String.join(", ", friends));// StringJoiner class performs the join operation internally

		final String allFriends = friends.stream().map(String::toUpperCase).collect(Collectors.joining(", "));
		System.out.println("All friends are: " + allFriends);
//		final List<String> friendNames = friends.stream().map(String::toUpperCase).collect(Collectors.toList());
		
		//StringJoiner provides more control over format of concatenation, can specify prefix, suffix and infix character sequences.
	}

}
