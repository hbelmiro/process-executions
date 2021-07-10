package com.thegreatapi.processexecutions.api;

import com.thegreatapi.processexecutions.api.exceptions.CommandTimeoutException;
import com.thegreatapi.processexecutions.api.exceptions.InvalidCommandException;
import com.thegreatapi.processexecutions.api.exceptions.NonZeroExitException;

import java.time.Duration;
import java.util.List;

/**
 * Executor for running operating system processes.
 */
@SuppressWarnings("InterfaceMayBeAnnotatedFunctional")
public interface ProcessExecutor {

    /**
     * Runs an operating system process using the specified commands and a timeout.
     *
     * @param commands the program and its arguments. Ex.: {@code List.of("ls", "-l")}
     * @param timeout  the maximum amout of time for the process to complete
     * @return a {@link List} containing the stdout lines
     * @throws NonZeroExitException    if the process finishes with a non-zero exit value
     * @throws CommandTimeoutException if the process does not finish in the specified time
     * @throws InvalidCommandException if the command is an empty list (has size 0) or if an element of the command list
     *                                 is null
     */
    List<String> run(List<String> commands, Duration timeout);
}