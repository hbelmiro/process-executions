package com.thegreatapi.processexecutions.api;

import com.thegreatapi.processexecutions.core.ProcessExecutionException;
import com.thegreatapi.processexecutions.core.ProcessRuntime;

import java.util.List;
import java.util.Objects;

final class DefaultProcessRuntime implements ProcessRuntime {

    /**
     * @throws InvalidCommandException   if the command is an empty list (has size 0) or if an element of the command list
     *                                   is null
     * @throws ProcessExecutionException in case of an unexpected error occurs
     * @see ProcessBuilder#start()
     */
    @Override
    public Process start(List<String> commands) {
        validateCommands(commands);
        try {
            return new ProcessBuilder(commands).start();
        } catch (Exception e) {
            throw new ProcessExecutionException("Unexpected error when running a process. Commands: " + commands, e);
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