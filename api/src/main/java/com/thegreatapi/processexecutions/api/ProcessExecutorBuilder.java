package com.thegreatapi.processexecutions.api;

/**
 * Builder for {@link ProcessExecutor}.
 */
public final class ProcessExecutorBuilder {

    private ProcessRuntime processRuntime = new DefaultProcessRuntime();

    private ProcessExecutorBuilder() {
    }

    /**
     * Builds the {@link ProcessExecutor} instance
     *
     * @return the instance
     */
    public ProcessExecutor build() {
        return new ProcessExecutorImpl(this);
    }

    /**
     * Sets a runtime where the process should be executed.
     *
     * @param processRuntime the runtime where the process should be executed
     * @return this builder
     */
    public ProcessExecutorBuilder processRuntime(ProcessRuntime processRuntime) {
        this.processRuntime = processRuntime;
        return this;
    }

    ProcessRuntime getProcessRuntime() {
        return processRuntime;
    }

    /**
     * Creates a new instance of this builder.
     *
     * @return the new instance
     */
    public static ProcessExecutorBuilder newBuilder() {
        return new ProcessExecutorBuilder();
    }
}