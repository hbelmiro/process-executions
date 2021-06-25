package com.thegreatapi.processexecutions.api;

import com.thegreatapi.processexecutions.core.ProcessExecutionException;

public final class CommandTimeoutException extends ProcessExecutionException {

    private static final long serialVersionUID = -6339592626825877989L;

    CommandTimeoutException(Process process) {
        super("Timeout while waiting for the command to exit.", process);
    }
}