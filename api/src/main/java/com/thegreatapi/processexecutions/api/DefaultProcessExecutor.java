package com.thegreatapi.processexecutions.api;

import java.time.Duration;
import java.util.List;

/**
 * Default implementation of {@link ProcessExecutor}.
 */
public final class DefaultProcessExecutor implements ProcessExecutor {

    private final ProcessExecutor delegate;

    public DefaultProcessExecutor() {
        delegate = ProcessExecutorBuilder.newBuilder().build();
    }

    @Override
    public List<String> run(List<String> commands, Duration timeout) {
        return delegate.run(commands, timeout);
    }
}