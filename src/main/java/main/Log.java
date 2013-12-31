package main;

/*
 * Author: Josh DeWitt
 * Written for Program 2 during CSCI4500 in 2013 Summer session.
 *
 * Class used for easily switching between verbose mode and normal mode.
 * Output should be done using Log.info or Log.trace instead of
 * System.out.println.
 */

public class Log {
    public static final int TRACE = 0;
    public static final int INFO = 1;
    /* How much logging should be performed. */
    public static int logLevel;
    /* Temporary storage for trace logic. */
    private static StringBuffer trace = new StringBuffer();

    public static void trace(String format, Object... args) {
        trace(String.format(format, args));
    }

    /* Method for verbose tracing. */
    public static void trace(String s) {
        if(logLevel <= TRACE) {
            trace.append(s);
        }
    }

    /* Method to actually trigger the trace being printed.                   */
    /* Prints the passed information before the previous traced information. */
    public static void printTrace(String format, Object... args) {
        if (logLevel <= TRACE) {
            System.out.printf(format, args);
            System.out.print(trace);
            trace = new StringBuffer();
        }
    }

    /* Print at the normal level using a format string. */
    public static void info(String format, Object... args) {
        info(String.format(format, args));
    }

    /* Print a string at the normal level. */
    public static void info(String s) {
        if (logLevel <= INFO) {
            System.out.print(s);
        }
    }
}
