
public class StudentTester {

	public static void main(String[] args) {
		Student student1 = new Student();
		student1.name = "Justin";
		student1.id = 12345678;
		student1.email = "jcfyum@cs.hku.hk";
		student1.status = 'O';

		student1.introduceMyself();

		// ==================================================

		Student student2 = new Student();
		student2.name = "Marco";
		student2.id = 87654321;
		student2.email = "marco@cs.hku.hk";
		student2.status = 'U';

		student2.introduceMyself();
	}

}
