package com.thegreatapi.processexecutions.core;

public class ProcessExecutionException extends RuntimeException {

    private static final long serialVersionUID = -5397841696842499505L;

    protected ProcessExecutionException(String message) {
        super(message);
    }

    public ProcessExecutionException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProcessExecutionException(String message, Process process) {
        this(appendCommandLineToMessage(message, process));
    }

    private static String appendCommandLineToMessage(String message, Process process) {
        return process.info()
                      .commandLine()
                      .map(commandLine -> message + " Commandline: " + commandLine)
                      .orElse(message);
    }
}