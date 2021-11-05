package com.techelevator;

import org.w3c.dom.ls.LSOutput;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {
    private File logFile;
    private PrintWriter writer;
    private VendingMachine vendingMachine;

    public Logger(String path) {
        try {
            logFile = new File(path);
            if (logFile.isDirectory()) {
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException e) {
            System.out.println("The path you entered is invalid.");
            System.exit(1);
        }


        try {
            if (!logFile.exists()) {
                logFile.createNewFile();
            }
            writer = new PrintWriter(new FileWriter(logFile, true));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void logWithDateStamp(String input) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss a");
        String formattedDateTime = LocalDateTime.now().format(formatter);

        writer.println(formattedDateTime + " " + input);
        writer.flush();
    }

    public void log(String input) {
        writer.println(input);
    }

    public void flush() {
        writer.flush();
    }

    public void close() throws IOException {
        writer.close();
    }
}
