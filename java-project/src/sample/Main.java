package sample;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

class JavaApplication {

    public static void main(String[] args) {
        System.out.println("[Java] main start");

        List<Integer> result = doAll();
        System.out.println(result.get(0));
        System.out.println(result.get(1));

        System.out.println("[Java] main end");
    }

    /**
     * JavaScriptPromise.all処理結果と似た様な実装
     * KotlinのcoroutineScopeでasyncした結果と似た様な実装
     */
    private static List<Integer> doAll() {
        List<Future<Integer>> futures = new ArrayList<>();
        List<Integer> result = new ArrayList<>();
        ExecutorService executor = Executors.newFixedThreadPool(2);
        try {
            // この２行がjsやkotlinでいうasyncキーワードとかPromiseと大体動きは同じ思ってください
            futures.add(executor.submit(new Text1Class()));
            futures.add(executor.submit(new Text2Class()));

            // Promise.all風
            // Callableを実装したクラスの実行結果をArrayListにいれて返してみると、promise.allっぽくなる
            for (Future<Integer> future : futures) {
                result.add(future.get()); // future.get() が jsやkotlinでいうawaitキーワードみたいなものです
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executor.shutdownNow(); // Javaは明示的にスレッド止めないと、プロセスが落ちてくれません。
        }
        // このループで全ての実行結果が買ってくるまで、awaitしてる感じです。
        return result;
    }
}

class Text1Class implements Callable<Integer> {

    @Override
    public Integer call() throws InterruptedException {
        System.out.println("test1 method");
        Thread.sleep(2000); // APIとかを呼び出していると仮定
        return 123;
    }
}

class Text2Class implements Callable<Integer> {

    @Override
    public Integer call() throws InterruptedException {
        System.out.println("test2 method"); // APIとかを呼び出していると仮定
        Thread.sleep(1000);
        return 456;
    }
}
