class NumbersThread extends Thread {
    int from;
    int to;
    public NumbersThread(int from, int to) {
        // implement the constructor
        this.from = from;
        this.to = to;
    }

    // you should override some method here

    @Override
    public void run() {
        for (int i = this.from; i <= this.to; i++) {
            System.out.println(i);
        }
    }
}