
public class CourseTester {

	public static void main(String[] args) {
		Course course = new Course(); // Create a Course object
		// Access the public instance variables
		course.courseCode = "COMP2396A"; // Set the course code of the course
		course.courseName = "Object-Oriented Programming and Java"; // Set the course name of the course
		course.courseInstructor = "Dr. Kenneth Wong"; // Set the name of course instructor of the course
		course.quota = 150; // Set the enrollment quota of the course

		course.courseIntroduction(); // Access the public method of the object
	}

}
