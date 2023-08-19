class Video {

    private String title;
    private int time;
    private int likes;
    private int views;

    public Double getNumberOfHoursPlayed() {
        return (time / 3600.0) * views;
    }

    public void persist() {
        System.out.println("Create the connection obbject.");
        System.out.println("Persist the object");
    }
}
