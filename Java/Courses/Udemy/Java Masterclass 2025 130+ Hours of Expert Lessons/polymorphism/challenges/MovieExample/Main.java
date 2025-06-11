class Main {

    public static void main(String[] args) {
       Movie movie = new Movie("Sin");
       movie.watchMovie();

       Adventure adventure = new Adventure("Indiana Jones");
       adventure.watchMovie();

       Comedy comedy = new Comedy("Scary movie");
       comedy.watchMovie();

       Fiction fiction = new Fiction("Ender's game");
       fiction.watchMovie();
    }

}
