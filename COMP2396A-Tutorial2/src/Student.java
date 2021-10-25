
public class Student {
	public String name;
	public int id;
	public String email;
	public char status;

	public void introduceMyself() {
		System.out.println("I am " + name + ".");
		System.out.println("My student ID is " + id + ".");

		if (status != 'O') {
			System.out.println("Feel free to contact me via Email (" + email + ") :D");
		} else {
			System.out.println("I AM OCCUPIED! Please DO NOT contact me!");
		}
	}

}
