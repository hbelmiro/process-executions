package com.thegreatapi.processexecutions.api;

import com.thegreatapi.processexecutions.api.exceptions.CommandTimeoutException;
import com.thegreatapi.processexecutions.api.exceptions.InterruptedCommandException;
import com.thegreatapi.processexecutions.api.exceptions.NonZeroExitException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

final class ProcessExecutorImpl implements ProcessExecutor {

    private final ProcessRuntime processRuntime;

    ProcessExecutorImpl(ProcessExecutorBuilder builder) {
        processRuntime = builder.getProcessRuntime();
    }

    @Override
    public List<String> run(List<String> commands, Duration timeout) {
        var process = processRuntime.start(commands);
        blockUntilProcessToExit(process, timeout);
        if (process.exitValue() == 0) {
            return getStdOut(process);
        } else {
            throw new NonZeroExitException(process);
        }
    }

    private static List<String> getStdOut(Process process) {
        try (var stdOutReader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            return stdOutReader.lines().collect(Collectors.toUnmodifiableList());
        } catch (IOException e) {
            throw new UncheckedIOException("An IOException occurred while reading the stdout.", e);
        }
    }

    private static void blockUntilProcessToExit(Process process, Duration timeout) {
        try {
            if (!process.waitFor(timeout.toMillis(), TimeUnit.MILLISECONDS)) {
                throw new CommandTimeoutException(process);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new InterruptedCommandException("InterruptedException while waiting for the command to respond.",
                    process);
        }
    }
}