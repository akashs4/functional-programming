package ch03;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Singh.Akash
 *
 */
public class PartitioningByExample {
	private static final Integer PASSING_THRESHOLD = 33;

	public static void main(String[] args) {
		List<Student> students = Arrays.asList(new Student("Nutan", "Singh", 99, 1),
				new Student("Akash", "Singh", 100, 2), new Student("Shobhit", "Singh", 60, 3),
				new Student("Chinki", "Singh", 30, 4));

		Map<Boolean, List<Student>> passingFailing = students.stream()
				.collect(Collectors.partitioningBy(s -> s.getGrade() >= PASSING_THRESHOLD));
		
		System.out.println(passingFailing);

	}
}
