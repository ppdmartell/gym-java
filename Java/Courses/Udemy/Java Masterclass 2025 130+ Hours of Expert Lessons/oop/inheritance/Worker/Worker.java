class Worker {

    private String name;
    private String birthDate;
    private String endDate;

    public String getName() { return this.name; }
    public String getBirthDate() { return this.birthDate; }
    public String getEndDate() { return this.endDate; }

    public Worker() {}

    public Worker (String name, String birthDate) {
        this.name = name;
        this.birthDate = birthDate;
    }

    public int getAge() {
       int currentYear = 2025;
       int birthYear = Integer.parseInt(birthDate.substring(6));

       return currentYear - birthYear;
    }

    public double collectPay() {
        return 0.0;
    }

    public void terminate(String terminateDate) {
       this.endDate = terminateDate; 
    }

    @Override
    public String toString() {
        return "Worker[" +
               "name='" + name + '\'' +
               ", birthDate='" + birthDate + '\'' +
               ", endDate='" + endDate + '\'' +
               '}';
    }

}
