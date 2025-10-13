package java8.lambda.refactor.to.lambda;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Exercise2Concurrency_Before {

  public static void main(String[] args) throws Exception {
    AuditService auditService = new AuditService();

    System.out.println("=== BEFORE: Anonymous Runnable & Callable ===");

    // TODO #2: Refactor this anonymous Runnable to a lambda or method reference.
    Thread worker = new Thread(new Runnable() {
      @Override
      public void run() {
        auditService.flush();
      }
    });
    worker.start();
    worker.join();

    // TODO #3: Refactor this anonymous Callable to a lambda.
    ExecutorService exec = Executors.newSingleThreadExecutor();
    Future<String> result = exec.submit(new Callable<String>() {
      @Override
      public String call() {
        return auditService.computeGreeting("Lara");
      }
    });
    System.out.println("Callable result: " + result.get());
    exec.shutdown();
  }

  // Simple service used by the tasks
  static class AuditService {
    void flush() {
      System.out.println("Flushing audit buffer on thread: " + Thread.currentThread().getName());
    }
    String computeGreeting(String name) {
      return "Hello, " + name + "!";
    }
  }
}
