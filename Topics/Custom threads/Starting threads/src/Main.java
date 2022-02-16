public class Main {

    public static void main(String[] args) {

        // create threads and start them using the class RunnableWorker
        Thread th1 = new Thread(new RunnableWorker(), "worker-1");
        Thread th2 = new Thread(new RunnableWorker(), "worker-2");
        Thread th3 = new Thread(new RunnableWorker(), "worker-3");

        th1.start();
        th2.start();
        th3.start();
    }
}

// Don't change the code below       
class RunnableWorker implements Runnable {

    @Override
    public void run() {
        final String name = Thread.currentThread().getName();

        if (name.startsWith("worker-")) {
            System.out.println("too hard calculations...");
        } else {
            return;
        }
    }
}
