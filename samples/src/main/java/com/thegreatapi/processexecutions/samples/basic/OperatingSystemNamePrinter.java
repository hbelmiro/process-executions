package com.thegreatapi.processexecutions.samples.basic;

import com.thegreatapi.processexecutions.api.DefaultProcessExecutor;
import com.thegreatapi.processexecutions.api.ProcessExecutor;
import com.thegreatapi.processexecutions.api.exceptions.NonZeroExitException;

import java.time.Duration;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public final class OperatingSystemNamePrinter {

    private static final Logger LOGGER = Logger.getLogger(OperatingSystemNamePrinter.class.getName());

    private final ProcessExecutor processExecutor;

    public OperatingSystemNamePrinter(ProcessExecutor processExecutor) {
        this.processExecutor = processExecutor;
    }

    public static void main(String[] args) {
        var printer = new OperatingSystemNamePrinter(new DefaultProcessExecutor());
        System.out.println(printer.getOperatingSystemName());
    }

    public String getOperatingSystemName() {
        List<String> stdout;
        try {
            stdout = processExecutor.run(List.of("uname", "-a"), Duration.ofSeconds(1));
        } catch (NonZeroExitException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            return "Unknown";
        }
        return stdout.stream().collect(Collectors.joining(System.lineSeparator()));
    }
}