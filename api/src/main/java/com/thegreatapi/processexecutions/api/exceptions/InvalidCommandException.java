package com.thegreatapi.processexecutions.api.exceptions;

import java.util.List;

/**
 * Exception that is thrown when an invalid command list is used to start a process.
 */
public final class InvalidCommandException extends RuntimeException {

    private static final long serialVersionUID = -8448902527591681039L;

    /**
     * Constructor.
     *
     * @param message  message
     * @param commands command list
     */
    public InvalidCommandException(String message, List<String> commands) {
        super(message + " Commands: " + commands);
    }
}