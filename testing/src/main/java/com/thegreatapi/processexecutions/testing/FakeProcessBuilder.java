package com.thegreatapi.processexecutions.testing;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.TimeUnit;

/**
 * Utility class to create fake {@link Process} instances.
 */
public final class FakeProcessBuilder {

    private final NoOpProcess noopProcess = new NoOpProcess();

    private int exitValue = noopProcess.exitValue();

    private OutputStream outputStream = noopProcess.getOutputStream();

    private InputStream inputStream = noopProcess.getInputStream();

    private InputStream errorStream = noopProcess.getErrorStream();

    private boolean waitFor = noopProcess.waitFor(0, TimeUnit.SECONDS);

    private Runnable destroy = noopProcess::destroy;

    private FakeProcessBuilder() {
    }

    /**
     * Sets what should be returned by {@link Process#getErrorStream()}.
     *
     * @param errorStream the return of {@link Process#getErrorStream()}
     * @return this builder
     */
    @SuppressWarnings("unused")
    public FakeProcessBuilder errorStream(InputStream errorStream) {
        this.errorStream = errorStream;
        return this;
    }

    /**
     * Sets what should be returned by {@link Process#getErrorStream()}.
     *
     * @param byteArray an array of bytes to be returned on {@link Process#getErrorStream()}
     * @return this builder
     */
    public FakeProcessBuilder errorStream(byte[] byteArray) {
        errorStream = new ByteArrayInputStream(byteArray);
        return this;
    }

    /**
     * Sets what should be returned by {@link Process#getInputStream()}.
     *
     * @param inputStream the return of {@link Process#getInputStream()}
     * @return this builder
     */
    @SuppressWarnings("unused")
    public FakeProcessBuilder inputStream(InputStream inputStream) {
        this.inputStream = inputStream;
        return this;
    }

    /**
     * Sets what should be returned by {@link Process#getInputStream()}.
     *
     * @param byteArray an array of bytes to be returned on {@link Process#getInputStream()}
     * @return this builder
     */
    public FakeProcessBuilder inputStream(byte[] byteArray) {
        inputStream = new ByteArrayInputStream(byteArray);
        return this;
    }

    /**
     * Sets the exit value that should be returned by {@link Process#exitValue()}.
     *
     * @param exitValue the exit value
     * @return this builder
     */
    public FakeProcessBuilder exitValue(int exitValue) {
        this.exitValue = exitValue;
        return this;
    }

    /**
     * Sets what should be returned by {@link Process#getOutputStream()}.
     *
     * @param outputStream the return of {@link Process#getOutputStream()}
     * @return this builder
     */
    @SuppressWarnings("unused")
    public FakeProcessBuilder outputStream(OutputStream outputStream) {
        this.outputStream = outputStream;
        return this;
    }

    /**
     * Sets what should be returned by {@link Process#waitFor()}.
     *
     * @param waitFor the value to be returned
     * @return this builder
     */
    public FakeProcessBuilder waitFor(boolean waitFor) {
        this.waitFor = waitFor;
        return this;
    }

    /**
     * Sets what should be run on {@link Process#destroy()}.
     *
     * @param destroy the code that should be run
     * @return this builder
     */
    @SuppressWarnings("unused")
    public FakeProcessBuilder destroy(Runnable destroy) {
        this.destroy = destroy;
        return this;
    }

    /**
     * Builds a {@link Process} with the attributes specified in this builder.
     *
     * @return the new {@link Process}.
     */
    public Process build() {
        return new NoOpProcess() {
            @Override
            public OutputStream getOutputStream() {
                return outputStream;
            }

            @Override
            public InputStream getInputStream() {
                return inputStream;
            }

            @Override
            public InputStream getErrorStream() {
                return errorStream;
            }

            @Override
            public boolean waitFor(long timeout, TimeUnit unit) {
                return waitFor;
            }

            @Override
            public int exitValue() {
                return exitValue;
            }

            @Override
            public void destroy() {
                destroy.run();
            }
        };
    }

    /**
     * Creates a new instance of this builder.
     *
     * @return the created instance
     */
    public static FakeProcessBuilder builder() {
        return new FakeProcessBuilder();
    }
}