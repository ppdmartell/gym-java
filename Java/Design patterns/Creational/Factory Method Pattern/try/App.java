//https://www.youtube.com/watch?v=EdFq_JIThqM
//This is another example of the Factory Method but using phones instead animals

public class App {
    public static void main(String[] args) {
        Store galaxyStore = new GalaxyStore();
        Phone galaxy = galaxyStore.deliverPhone();

        Store iphoneStore = new iPhoneStore();
        Phone iphone = iphoneStore.deliverPhone();

        galaxy.call("Messi");
        iphone.call("Newton");
    }
}

abstract class Store {
    public Phone deliverPhone() {
        Phone phone = createPhone();
        //Here you prepare the phone before being delivered (a.k.a execute other methods)
        return phone;
    }

    public abstract Phone createPhone();
}

class GalaxyStore extends Store {
    @Override
    public Phone createPhone() {
        return new Galaxy();
    }
}

class iPhoneStore extends Store {
    @Override
    public Phone createPhone() {
        return new iPhone();
    }
}

interface Phone {
    void call(String contact);
}

class Galaxy implements Phone {
    @Override
    public void call(String contact) { System.out.println("Calling " + contact + " from my Galaxy..."); }
}

class iPhone implements Phone {
    @Override
    public void call(String contact) { System.out.println("Calling " + contact + " from my iPhone..."); }
}
