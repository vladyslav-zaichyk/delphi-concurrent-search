package vz.concurrent_search;

public class Main {
    static {
        System.setProperty("java.util.logging.SimpleFormatter.format",
                "[%1$tT.%1$tL] [%4$-7s] %5$s %n");
    }

    public static void main(String[] args) {
        new ConcurrentSearch().run();
    }
}
