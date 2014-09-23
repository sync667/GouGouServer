package com.gmail.sync667.gougouserver.log;

import java.io.BufferedWriter;
import java.io.Console;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;

import com.gmail.sync667.gougouserver.GouGouServer;

public class ConsoleLogger {

    private final Logger logger;
    private final Console console;
    private final File log;
    private final Path logPath;
    final static Charset ENCODING = StandardCharsets.UTF_8;

    public ConsoleLogger(Console console) {
        this.console = console;

        logger = Logger.getLogger("GouGouServer");

        logPath = Paths.get(GouGouServer.class.getProtectionDomain().getCodeSource().getLocation().getPath()
                + File.separator + "log.txt");

        log = logPath.toFile();
    }

    public void logFile(String message) {

        if (!log.exists()) {
            try {
                log.createNewFile();
            } catch (IOException e) {
                infoC("Failed to create log file! Stopping...");
                GouGouServer.getServer().stop();
                return;
            }
        }
        try (BufferedWriter writer = Files.newBufferedWriter(logPath, ENCODING)) {
            writer.write(message + "\n");
            // writer.newLine();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void info(String message) {
        logger.info(message + "\n");
        logFile(message);
    }

    public void warning(String message) {
        logger.warning(message + "\n");
        logFile(message);
    }

    public void severe(String message) {
        logger.severe(message + "\n");
        logFile(message);
    }

    public void infoC(String message) {
        if (console == null) {
            warning("Console class is null! Do not use infoC() for out.");
            info(message);
            return;
        }
        console.printf(message + "\n");
        logFile(message);
    }

}
