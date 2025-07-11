class Horse extends Mammal {

    public Horse(String type, String size, double weight) {
        super(type, size, weight);
    }

    @Override
    public void makeNoise() {
        
    }

    @Override
    public void shedHair() {
        System.out.println(getExplicitType() + " shed in the spring");
    }
}



// Interesting, method move(String speed) was overriden in Mammal (super Animal), so due to this,
// I am not forced to override it in concrete Horse class. Only need to override makeNoise(), the
// other abstract method from Animal that was not overriden in Mammal.
// Method shedHair() is abstract in Mammal, so I must override it here in Horse concrete class.
