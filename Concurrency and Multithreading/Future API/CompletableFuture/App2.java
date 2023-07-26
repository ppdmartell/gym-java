/*
This is part two of the CompletableFuture part (file named App.java in this same folder is part
one), which will be focused on thenCompose() and its difference to thenApply()[3]. Also an example
about thenCombine().

One thing to note is no presence of the ExecutorService concurrent mechanism. But turns out
most of the CompletableFuture's Async methods are overloaded with an Executor parameter[1] for the
developer to use with a custom ExecutorService[2]. If no ExecutorService is supplied (simple Async)
then the execution of the method will beused the ForkJoinPool provided by the JVM.

Resources:
[1] https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/CompletableFuture.html
[2] https://chat.openai.com/c/86acd6d2-b3de-493c-8acb-065c9ac93f31 [search phrase: "customize the thread execution behavior"]
[3] https://www.youtube.com/watch?v=xpjvY45Hbyg
*/

class App2 {
	public static void main(String[] args) {
		
	}
}
