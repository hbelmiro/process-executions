package com.thegreatapi.processexecutions.api.exceptions;

/**
 * Exception thrown if an {@link InterruptedException} occurs while waiting for the process to execute.
 */
public final class InterruptedCommandException extends RuntimeException {

    private static final long serialVersionUID = -6595166866910761350L;

    /**
     * Constructor.
     *
     * @param message message
     * @param process process that was running while the exception occurred
     */
    public InterruptedCommandException(String message, Process process) {
        super(CommandLineAppender.append(process).to(message));
    }
}