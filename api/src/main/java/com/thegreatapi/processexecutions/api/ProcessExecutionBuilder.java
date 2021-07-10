package com.thegreatapi.processexecutions.api;

public final class ProcessExecutionBuilder {

    private ProcessRuntime processRuntime = new DefaultProcessRuntime();

    private ProcessExecutionBuilder() {
    }

    public ProcessExecutor build() {
        return new ProcessExecutorImpl(this);
    }

    public ProcessExecutionBuilder processRuntime(ProcessRuntime processRuntime) {
        this.processRuntime = processRuntime;
        return this;
    }

    ProcessRuntime getProcessRuntime() {
        return processRuntime;
    }

    public static ProcessExecutionBuilder newBuilder() {
        return new ProcessExecutionBuilder();
    }
}