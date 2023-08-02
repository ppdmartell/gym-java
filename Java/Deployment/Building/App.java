class App {
	public static void main(String[] args) {
		Person p = new Person("Lionel", "Messi");
		System.out.printf("Name: %s, Lastname: %s.%n", p.getName(), p.getLastname());
	}
}
