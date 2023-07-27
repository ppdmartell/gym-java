/*
According to [1], the order of the keywords in fields declaration can vary, although there
are rules to follow. For more info see [1]. The next program has two fields and they are both
static and final,and yet the keywords doesn't follow the same order; but still this program
will execute without any issues.
However, it is recommended "private static final".

Resources:
[1] https://stackoverflow.com/a/11219598
*/

class App {

	private static final int field1 = 1;
	private final static int field2 = 1;

	public static void main(String[] args) {
		System.out.println("All good here!");
	}
}
