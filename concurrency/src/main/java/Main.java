import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) {

        // callicoder.com/java-8-completablefuture-tutorial

        //
        // first Future
        //
        CompletableFuture<String> completableFuture = new CompletableFuture<>();
        completableFuture.complete("Hello World, my first Future");
        try {
            String result = completableFuture.get();
            System.out.println("Result of execution ==> " + result);
        } catch (InterruptedException | ExecutionException e) {
            System.out.println("Future's error");
        }

        //
        // lets run a Runnable task here (which always return Void)
        // runAsync is good for tasks that do not return anything.
        //
        CompletableFuture<Void> future = CompletableFuture.runAsync(
                () -> {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        throw new IllegalStateException(e);
                    }
                    System.out.println("Running my first thread separate from the main thread");
                }
        );

        try {
            future.get();
        } catch (InterruptedException | ExecutionException e) {
            System.out.println("Future's error");
        }

        //
        // lets run a Runnable task here (which always return Void)
        // supplyAsync will return the value of said computation
        //
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(
                () -> {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch(InterruptedException e) {
                        throw new IllegalStateException(e);
                    }
                    return "Returning the result of an async thread computation";
                }
        );

        try {
            String result = future1.get();
            System.out.println(result);
        } catch (InterruptedException | ExecutionException e) {
            System.out.println("Future's error");
        }


        //
        // lets create our own thread pool and pass it to the AsyncMethods
        //

//        Executor executor = Executors.newFixedThreadPool(10);
//        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(
//                () -> {
//                    try {
//                        TimeUnit.SECONDS.sleep(1);
//                    } catch(InterruptedException e) {
//                        throw new IllegalStateException(e);
//                    }
//                    return "Returning the result of an async thread computation";
//                }, executor // we will now pass in our own thread pool
//        ).whenComplete( (i, t) -> {
//                    System.out.println("threads have finished, via a chained call");
//                }
//                );
//
//        try {
//            String result = future2.get();
//            System.out.println(result);
//            System.out.println("worrying about thread cleanup");
//
//        } catch (InterruptedException | ExecutionException e) {
//            System.out.println("Future's error");
//        }


        // lets do some chaining
        CompletableFuture<String> welcomeText = CompletableFuture.supplyAsync(
                () -> {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch(InterruptedException e) {
                        throw new IllegalStateException(e);
                    }
                    return "Matthew";
                }
        ).thenApply(name -> {
            return "Hello " + name;
        }).thenApply(greeting ->
                {
                    return "My first CompletableFuture Chaining -> " + greeting;
                });

        try {
            System.out.println(welcomeText.get());

        } catch (InterruptedException | ExecutionException e) {
            System.out.println("Future's error");
        }

        // thenAccept()
            // has access to the results of the CompletableFuture on which it is attached to.

        // thenRun()
            // does not have access to the future's result. This is good for when we want to complete some
            // thing after our chains have finished

        // thenApply() v thenCompose()
            // thenApply() would return a nested completable future.
            // thenCompose() takes one future, then applies a second future. then the result is a top level
            // unnested future. use this when two futures are dependant on one another

        // thenCombine()
            // use this when you want two futures to run independently and then do something after.
    }
}
