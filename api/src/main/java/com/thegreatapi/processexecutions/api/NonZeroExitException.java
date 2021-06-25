package com.thegreatapi.processexecutions.api;

import com.thegreatapi.processexecutions.core.ProcessExecutionException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public final class NonZeroExitException extends ProcessExecutionException {

    private static final long serialVersionUID = 4486063852635931827L;

    private final int exitValue;

    private final List<String> stdErr;

    public NonZeroExitException(Process process) {
        super("The process returned a non zero exit code.", process);
        exitValue = process.exitValue();
        try {
            stdErr = readStdErr(process);
        } catch (IOException e) {
            throw new ProcessExecutionException("An IOException occurred while reading the stderr of the process.", process);
        }
    }

    private static List<String> readStdErr(Process process) throws IOException {
        try (var stdErrReader = new BufferedReader(new InputStreamReader(process.getErrorStream()))) {
            return stdErrReader.lines().collect(Collectors.toList());
        }
    }

    public List<String> getStdErr() {
        return Collections.unmodifiableList(stdErr);
    }

    public int getExitValue() {
        return exitValue;
    }
}