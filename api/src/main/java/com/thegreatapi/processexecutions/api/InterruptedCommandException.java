package com.thegreatapi.processexecutions.api;

import com.thegreatapi.processexecutions.core.ProcessExecutionException;

public final class InterruptedCommandException extends ProcessExecutionException {

    private static final long serialVersionUID = -6595166866910761350L;

    public InterruptedCommandException(String message, Process process) {
        super(message, process);
    }
}