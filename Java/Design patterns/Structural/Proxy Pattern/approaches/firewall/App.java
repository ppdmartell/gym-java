/*
The Proxy Pattern, with this approach, basically performs some actions either before or after. Allowing you
to control access to a particular object or resource. Manages the lifecycle of the service object, will work
even if the service object is not ready or not available because the proxy it's before it. The proxy must
implement the same interface as the service object, so they have the same method and when you call the proxy's
method, it will perform some actions and then call the service object's method. In this case, the proxy is
regulating access.
*/

import java.util.HashSet;
import java.util.Set;

public class App {
    public static void main(String[] args) {
        Internet regular = new RegularInternet();
        regular.connectTo("www.onlyfans.com");
        Internet proxy = new ProxyInternet();
        proxy.connectTo("www.onlyfans.com");
        proxy.connectTo("www.google.com");
    }
}

class RegularInternet implements Internet {
    @Override
    public void connectTo(String server) { System.out.println("CONNECTED to server: " + server); }
}

interface Internet {
    void connectTo(String server);
}

class ProxyInternet implements Internet {
    private static final Set<String> restrictedSites;
    private final Internet internet = new RegularInternet();

    static {
        restrictedSites = new HashSet<>();
        restrictedSites.add("www.onlyfans.com");
    }

    @Override
    public void connectTo(String server) {
        if(restrictedSites.contains(server)) {
            System.out.println("ACCESS DENIED to server: " + server);
            return;
        }
        System.out.println("CONNECTED to server: " + server);
    }
}


/*
Notes:
- I asked chatgpt (https://chat.openai.com/) about the static {} block of initialization.
Next the question and answer:

Q: Assistant, what's the purpose of the static {} portion of code in the next class and why
is it necessary to implement it this way? Is this the elegant way or are there other better
options for this static portion of code? (Is a reference to the static block of code in class ProxyInternet):

A: The static {} portion of code is called a static initialization block in Java.
It is used to initialize static variables or perform any static initialization tasks for the class.
In the given code snippet, the static initialization block is used to initialize the restrictedSites
set with the restricted websites. This block is executed once when the class is loaded, ensuring that
the restrictedSites set is initialized before any instance of the ProxyInternet class is created or any methods are called.

Using a static initialization block in this case is a suitable approach. It allows for the initialization
of the restrictedSites set in a centralized and consistent manner for all instances of the ProxyInternet
class. It ensures that the set is populated with the restricted websites before any methods are invoked.

Alternatives to using a static initialization block could include initializing the restrictedSites set
directly at its declaration or initializing it within a static method. However, using a static initialization
block keeps the initialization code logically grouped with the declaration of the variable, making the code
more readable and maintainable.

Overall, the use of a static initialization block in this context is a valid and efficient approach to
initialize static variables and perform static initialization tasks in a class.
*/
