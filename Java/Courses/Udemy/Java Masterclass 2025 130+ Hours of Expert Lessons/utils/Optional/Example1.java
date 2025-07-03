import java.util.Optional;

class Example1 {

    private static Optional<String> searchById(long id) {
        if (id == 1L) return Optional.of("John");
        return Optional.empty();
    }

    public static void main(String[] args) {
        Optional<String> username = searchById(2L); // If parameter is 1L, output is user: John.
        String name = username.orElse("User not found!");
        System.out.println("user: " + name);
    }

}
