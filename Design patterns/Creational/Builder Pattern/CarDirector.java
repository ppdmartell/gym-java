/*
Director classes are used in the Builder Pattern for constructing objects that are alike somehow (they
always have the same attribute values when being created).
*/

public class CarDirector {

	/*
	  Imagine all the Ferraris with year 2000 being built have the same values for some attributes, the Director will
	  help you to save some time and lines of code.
	*/
	public void buildFerrari(CarBuilder builder) {
		builder.brand("Ferrari")
               .fuelCapacity(1000)
               .year(2000);
	}

	//Idem for the Lamborghinis in this case.
	public void buildLambo(CarBuilder builder) {
		builder.brand("Lamborghini")
               .fuelCapacity(1100)
               .year(2005);
	}

}
