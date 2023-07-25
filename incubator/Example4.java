public class ThreadTest {

	public static void main(String[] args)
		throws InterruptedException
	{

		System.out.println("Begin - Thread");

		Thread.currentThread().join();

		System.out.println("End - Thread");
	}
}
