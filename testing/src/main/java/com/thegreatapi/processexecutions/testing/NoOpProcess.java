package com.thegreatapi.processexecutions.testing;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.TimeUnit;

class NoOpProcess extends Process {

    private final InputStream inputStream = new EmptyInputStream();

    private final InputStream errorStream = new EmptyInputStream();

    private final OutputStream outputStream = new NoOpOutputStream();

    private final ProcessHandle.Info info = new NoOpInfo();

    protected NoOpProcess() {
    }

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
    public int waitFor(){
        return 0;
    }

    @Override
    public boolean waitFor(long timeout, TimeUnit unit) {
        return true;
    }

    @Override
    public int exitValue() {
        return 0;
    }

    @Override
    public void destroy() {
        // No op
    }

    @Override
    public ProcessHandle.Info info() {
        return info;
    }
}