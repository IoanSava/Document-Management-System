package com.optional;

import java.util.Scanner;

/**
 * Utility class for shell operations
 *
 * @author Ioan Sava
 */
public final class Util {

    /**
     * Read a line from stdin
     */
    public static String readLine(Scanner scanner) {
        String line;
        while (true) {
            line = scanner.nextLine();
            if (line.length() == 0) {
                continue;
            }
            break;
        }

        return line.trim();
    }
}
