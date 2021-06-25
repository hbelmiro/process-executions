package com.thegreatapi.processexecutions.api;

import com.thegreatapi.processexecutions.core.ProcessExecutionException;

import java.util.List;

public final class InvalidCommandException extends ProcessExecutionException {

    private static final long serialVersionUID = -8448902527591681039L;

    public InvalidCommandException(String message, List<String> commands) {
        super(message + " Commands: " + commands);
    }
}