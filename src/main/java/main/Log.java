package main;

public class Log {
    public static final int TRACE = 0;
    public static final int INFO = 1;
    /* How much logging should be performed. */
    public static int logLevel;

    public static void trace(String format, Object... args) {
        trace(String.format(format, args));
    }

    public static void trace(String s) {
        if (logLevel <= TRACE) {
            System.out.print(s);
        }
    }

    public static void info(String format, Object... args) {
        info(String.format(format, args));
    }

    public static void info(String s) {
        if (logLevel <= INFO) {
            System.out.print(s);
        }
    }
}
