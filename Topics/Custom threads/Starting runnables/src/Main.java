class Starter {

    public static void startRunnables(Runnable[] runnables) {
        // implement the method
        for(Runnable runnable : runnables) {
            Thread thr = new Thread(runnable, "run");
            thr.start();
        }
    }
}