/*
If you need to store enum in a Set, you shoul always consider using EnumSet, see [1].
And enum types doesn't allow to override the equals() - hashCode() contract. This
means you shouldn't use them in a Set, maybe that's why there is a special type named
EnumSet for this.

Resources:
[1] https://www.baeldung.com/java-enumset
*/

package employees;

import scheduling.Days;
import java.util.Arrays;
import java.util.EnumSet;

public final class Hairdresser {
    
    private final String name;
    private volatile EnumSet<Days> daysAvailable;
    
    public Hairdresser(String name) {
        this.name = name;
        daysAvailable = EnumSet.noneOf(Days.class);  //This means create it emtpy. If you use allOf(), will add all entries of the Days enum.
    }
    
    public synchronized void setDaysAvailable(Days... days) {
        Arrays.stream(days).forEach(daysAvailable::add);
    }
    
    public synchronized void removeDay(Days day) {
        daysAvailable.remove(day);
    }
    
    public EnumSet<Days> getDaysAvailable() {
        return daysAvailable;
    }
    
    public String getName() { return name; }
}
