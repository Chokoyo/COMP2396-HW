// Solution
public class Course {
	public String courseCode;
	public String courseName;
	public String courseInstructor;
	public int quota;

	public void courseIntroduction() {
		System.out.println("This course is " + courseCode + ":" + courseName + " with a quota of " + quota + ".");
		System.out.println("The course instructor is " + courseInstructor + ".");
	}

}

// Exercises to students
// 1. Inconsistent class name with the filename of Java Source Code File (Course.java)
// 2. Missing the return type of the method courseIntroduction (void)
// 3. Missing the semicolon (;) in the programming statements of the method courseIntroduction
// Remarks: We can omit the access modifier public. We will discuss this later.
//
//public class course {
//	public String courseCode;
//	public String courseName;
//	public String courseInstructor;
//	public int quota;
//
//	public courseIntroduction() {
//		System.out.println("This course is " + courseCode + ":" + courseName + " with a quota of " + quota + ".")
//		System.out.println("The course instructor is " + courseInstructor + ".")
//	}
//
//}