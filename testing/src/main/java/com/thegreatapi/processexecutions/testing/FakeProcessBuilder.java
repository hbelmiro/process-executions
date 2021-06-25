package com.thegreatapi.processexecutions.testing;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.TimeUnit;

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

    public FakeProcessBuilder errorStream(InputStream errorStream) {
        this.errorStream = errorStream;
        return this;
    }

    public FakeProcessBuilder errorStream(byte[] byteArray) {
        errorStream = new ByteArrayInputStream(byteArray);
        return this;
    }

    public FakeProcessBuilder inputStream(InputStream inputStream) {
        this.inputStream = inputStream;
        return this;
    }

    public FakeProcessBuilder inputStream(byte[] byteArray) {
        inputStream = new ByteArrayInputStream(byteArray);
        return this;
    }

    public FakeProcessBuilder exitValue(int exitValue) {
        this.exitValue = exitValue;
        return this;
    }

    public FakeProcessBuilder outputStream(OutputStream outputStream) {
        this.outputStream = outputStream;
        return this;
    }

    public FakeProcessBuilder waitFor(boolean waitFor) {
        this.waitFor = waitFor;
        return this;
    }

    public FakeProcessBuilder destroy(Runnable destroy) {
        this.destroy = destroy;
        return this;
    }

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

    public static FakeProcessBuilder builder() {
        return new FakeProcessBuilder();
    }
}
