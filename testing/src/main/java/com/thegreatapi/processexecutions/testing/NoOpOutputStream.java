package com.thegreatapi.processexecutions.testing;

import java.io.OutputStream;

final class NoOpOutputStream extends OutputStream {

    @Override
    public void write(int b) {
        // No op
    }

    @Override
    public void write(byte[] b, int off, int len) {
        // No op
    }
}