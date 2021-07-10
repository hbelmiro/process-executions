package com.thegreatapi.processexecutions.api.exceptions;

final class CommandLineAppender {

    private CommandLineAppender() {
    }

    static AppendableCommandLine append(Process process) {
        return new AppendableCommandLine(process);
    }

    static class AppendableCommandLine {

        private final Process process;

        AppendableCommandLine(Process process) {
            this.process = process;
        }

        String to(String message) {
            return process.info()
                          .commandLine()
                          .map(commandLine -> message + " Commandline: " + commandLine)
                          .orElse(message);
        }
    }
}