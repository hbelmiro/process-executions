package com.thegreatapi.processexecutions.testing;

import java.io.IOException;
import java.io.InputStream;

final class EmptyInputStream extends InputStream {

    @Override
    public int read() throws IOException {
        return -1;
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        return -1;
    }
}