/*
This example is based on [1] and we will create a custom exception named MinorThanTenException.

Resources:
[1] https://www.youtube.com/watch?v=EaNlSWOnOjE&list=PLsyeobzWxl7rS9B2K1l--VDpCn41gijnV&index=8
*/

class App3 {
	public static void main(String[] args) {
		try {
			calculate(4);
		} catch (MinorThanTenException e) {
			System.out.println(e);
		}
	}

	private static int calculate(int value) throws MinorThanTenException {
		if (value >= 10) return 99;
		else throw new MinorThanTenException("The value can't be minor than 10.");
	}
}


//Here is where the custom exception is created.
class MinorThanTenException extends Exception {

	public MinorThanTenException() {}

	public MinorThanTenException(String msg) {
		super(msg);
	}
}
