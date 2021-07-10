package com.thegreatapi.processexecutions.api.exceptions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Exception that is thrown when a process exits with a non-zero value.
 */
public final class NonZeroExitException extends RuntimeException {

    private static final long serialVersionUID = 4486063852635931827L;

    private final int exitValue;

    private final List<String> stdErr;

    /**
     * Constructor.
     *
     * @param process the process that exited with a non-zero exit value
     */
    public NonZeroExitException(Process process) {
        super(CommandLineAppender.append(process).to("The process returned a non-zero exit value."));
        exitValue = process.exitValue();
        stdErr = readStdErr(process);
    }

    private static List<String> readStdErr(Process process) {
        try (var stdErrReader = new BufferedReader(new InputStreamReader(process.getErrorStream()))) {
            return stdErrReader.lines().collect(Collectors.toList());
        } catch (IOException e) {
            throw new UncheckedIOException("IOException while reading the stderr.", e);
        }
    }

    /**
     * @return a list with the stderr lines.
     */
    public List<String> getStdErr() {
        return Collections.unmodifiableList(stdErr);
    }

    /**
     * @return the exit value
     */
    public int getExitValue() {
        return exitValue;
    }
}