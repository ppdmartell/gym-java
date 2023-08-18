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

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

class App2 {
    public static void main(String[] args) throws InterruptedException, ExecutionException {

        //Example about how to get a CompletableFuture of a CompletableFuture (nested). But this is kinda annoying. Method thenCompose might help to simplify things a bit.
        CompletableFuture<CompletableFuture<Double>> cFuture1 = getBankAccount("randomId")
                                                                    .thenApply(acc -> getAccountBalance(acc));
        System.out.printf("The current balance is: %.3f.%n", cFuture1.get().get());
        System.out.println("-------------------------------------------------------------------------------");

        //Using thenCompose() to get a top-level CompletableFuture (not nested, ergo less annoying to deal with).
        CompletableFuture<Double> cFuture2 = getBankAccount("randomId").thenCompose(acc -> getAccountBalance(acc));
        System.out.printf("The current balance is: %.3f.%n", cFuture2.get());   //Less code and less troublesome as well.
    }

    public static CompletableFuture<BankAccount> getBankAccount(String id) {
        return CompletableFuture.supplyAsync(() -> AccountsService.getBankAccount(id));
    }

    public static CompletableFuture<Double> getAccountBalance(BankAccount account) {
        return CompletableFuture.supplyAsync(() -> AccountsService.getAccountBalance(account));
    }
}

interface BankAccount {
    double checkAmount();
}

class RegularBankAccount implements BankAccount {

    private final double amount;

    public RegularBankAccount(double amount) {
        this.amount = amount;
    }

    @Override
    public double checkAmount() { return amount; }
}

class AccountsService {
    public static BankAccount getBankAccount(String id) {  //This is a mock method, just to comply-ish to the video example.
        return new RegularBankAccount(123.424);
    }

    public static Double getAccountBalance(BankAccount bankAccount) {
        return bankAccount.checkAmount();
    }
}
