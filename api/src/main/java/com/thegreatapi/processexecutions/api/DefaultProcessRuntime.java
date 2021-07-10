package com.thegreatapi.processexecutions.api;

import com.thegreatapi.processexecutions.api.exceptions.InvalidCommandException;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.List;
import java.util.Objects;

final class DefaultProcessRuntime implements ProcessRuntime {

    @Override
    public Process start(List<String> commands) {
        validateCommands(commands);
        try {
            return new ProcessBuilder(commands).start();
        } catch (IOException e) {
            throw new UncheckedIOException("IOException while running " + commands, e);
        }
    }

    private static void validateCommands(List<String> commands) {
        checkNullCommands(commands);
        checkEmptyCommandList(commands);
    }

    private static void checkEmptyCommandList(List<String> commands) {
        if (commands.isEmpty()) {
            throw new InvalidCommandException("Empty command list.", commands);
        }
    }

    private static void checkNullCommands(List<String> commands) {
        if (commands.stream().anyMatch(Objects::isNull)) {
            throw new InvalidCommandException("Null commands are not allowed.", commands);
        }
    }
}