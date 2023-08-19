/*
Imagine you use a third-party library in your application and in several parts throughout your codebase. Then that
library changes somehow and you have to change every place you used this library. This violates the Open/Closed Design Principle
and the Facade Pattern helps a bit with this. The code is based on resource [1], both idea and most part of the implementation.
The Factory Method Pattern was introduced in this version.

Resources:
[1] https://www.youtube.com/watch?v=xWk6jvqyhAQ

*/

public class App {
    public static void main(String[] args) {
        /*
            The operations should be the next ones:
            - Create the database service
            - Create the user using the database service
            - Buy the desired amount of cryptocurrency

            But putting this code directly here calling the third-party library would be coupling it to our codebase more than recommended.
            Then enter the Facade Pattern, which is code that belongs to our codebase and serves as a gateway to the third-party library
            and helps us to lower the coupling level.
        */

        CryptoServiceFacade cryptoServiceFacade = new CryptoServiceFacade();
        cryptoServiceFacade.buyCurrency("ETH", 4.6d);

        /*
            As we can see, only two lines of code are in our main method and they have nothing to do with the third-party library
            and only two parameters were used: the type of crypto currency and the amount.
        */
    }
}

abstract class CryptoServiceFactory {
    public CryptoService getCryptoService() {
        CryptoService service = createCryptoService();
        //Perform some business logic here if needed with the crypto service and then return it.
        return service;
    }

    abstract public CryptoService createCryptoService();
}

class BitcoinServiceFactory extends CryptoServiceFactory {
    @Override
    public CryptoService createCryptoService() { return new BitcoinService(); }
}

class EthereumServiceFactory extends CryptoServiceFactory {
    @Override
    public CryptoService createCryptoService() { return new EthereumService(); }
}

class BitcoinService extends CryptoService {
    @Override
    public void buyCurrency(User user, double amount) {
        System.out.println("Buying " + amount + " BTC...");
    }
}

class EthereumService extends CryptoService {
    @Override
    public void buyCurrency(User user, double amount) {
        System.out.println("Buying " + amount + " ETH...");
    }
}

abstract class CryptoService {
    //You can put attributes in common here
    abstract public void buyCurrency(User user, double amount);
}

class User {
    private String username;
    private String password;
    private double amount;

    public User() {}
    public User(String username, double amount) {
        this.username = username;
        this.amount = amount;
    }

    public double getAmount() { return amount; }
}

//Here we should think about using an abstract DatabaseService class to depend on abstractions and not concrete implementations, but let's make it this way for simplicity
class DatabaseService {
    public User getUserById(String id) {
        //Imagine there is a real database operation here returning the desired user by its id.
        return new User("ppdmartell", 10d);
    }
}

class CryptoServiceFacade {
    public void buyCurrency(String currency, double amount) { //The least possible amount of parameters to avoid high coupling.
        DatabaseService db = new DatabaseService();
        User user = db.getUserById(UIService.getLoggedInUserId());
        if(user.getAmount() < amount) {
            System.out.println("Not enough money to buy that " + currency + " currency.");
            return;
        }
        CryptoServiceFactory fac = null;
        if(currency.equals("BTC")) fac = new BitcoinServiceFactory();
        if(currency.equals("ETH")) fac = new EthereumServiceFactory();
        CryptoService service = fac.getCryptoService();
        service.buyCurrency(user, amount);
    }
}

class UIService {
    public static String getLoggedInUserId() {
        //Perform some action and obtains the id for the logged in user.
        return "random_user";
    }

    //Other system methods
}
