package com.thegreatapi.processexecutions.testing;

import java.time.Duration;
import java.time.Instant;
import java.util.Optional;

class NoOpInfo implements ProcessHandle.Info {
    @Override
    public Optional<String> command() {
        return Optional.empty();
    }

    @Override
    public Optional<String> commandLine() {
        return Optional.empty();
    }

    @Override
    public Optional<String[]> arguments() {
        return Optional.empty();
    }

    @Override
    public Optional<Instant> startInstant() {
        return Optional.empty();
    }

    @Override
    public Optional<Duration> totalCpuDuration() {
        return Optional.empty();
    }

    @Override
    public Optional<String> user() {
        return Optional.empty();
    }
}