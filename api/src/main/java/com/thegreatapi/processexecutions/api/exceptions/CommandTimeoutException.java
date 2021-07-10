package com.thegreatapi.processexecutions.api.exceptions;

/**
 * Exception that is thrown if a process does not complete in a specified time.
 */
public final class CommandTimeoutException extends RuntimeException {

    private static final long serialVersionUID = -6339592626825877989L;

    /**
     * Constructor.
     *
     * @param process the process that originated the exception
     */
    public CommandTimeoutException(Process process) {
        super(CommandLineAppender.append(process).to("Timeout while waiting for the command to exit."));
    }
}