class Example1 {

    public static void main(String[] args) {
        Thread t = new Thread(() -> System.out.println("Running inside the thread."));
        t.start();
        System.out.println("Execution continued besides the thread.");
    }

}
