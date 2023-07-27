package scheduling;

public enum Days {
    SUNDAY("Sunday"),
    MONDAY("Monday"),
    TUESDAY("Tuesday"),
    WEDNESDAY("Wednesday"),
    THURSDAY("Thursday"),
    FRIDAY("Friday"),
    SATURDAY("Saturday");
    
    private final String string;
    
    Days(String string) {
        this.string = string;
    }
    
    public String getString() { return string; }
}
