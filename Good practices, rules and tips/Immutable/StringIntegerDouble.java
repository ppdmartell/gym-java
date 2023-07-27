/*
String, Integer and Double are immutable in Java. There is a pool where they are
stored, some sort of a cache, so in case the same object is needed, it can be reused
without creating a new one. The String pool is the most famous, but there is a pool
also for Integer and Double (for values only between [-128;127). See [1] for more
info.

Important concepts:
- Value equality
- Reference equality

Resources:
[1] https://chat.openai.com/c/d77e13c2-40b0-42ff-8310-eb84fd399ba4 [search phrase: "cache or pool for certain values of"]
*/

class StringIntegerDouble {
	public static void main(String[] args) {
		String a = "example";
		String b = new String("example");
		System.out.println(a.equals(b)); //value equality
		System.out.println(a == b);      //reference equality

		System.out.println("----------------");
		Integer c = 100;
		Integer d = 100;
		System.out.println(c == d);  //true because both c and d references the same memory space (pool reference)
		Integer e = 200;
		Integer f = 200;
		System.out.println(e == f);  //false because 200 doesn't fall in [-128,127] range, ergo they reference two difference spaces in memory

		System.out.println("----------------");
		Double g = 1d;
		Double h = 1d;
		System.out.println(g == h);
		Double x = 177d;
		Double y = 177d;
		System.out.println(x == y);  //!!!!!!!Something is wrong here, should be true and false.
	}
}
