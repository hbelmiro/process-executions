package com.thegreatapi.processexecutions.api;

import com.thegreatapi.processexecutions.api.exceptions.InvalidCommandException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.awaitility.Awaitility.await;

class DefaultProcessRuntimeTest {

    @Test
    void start() {
        DefaultProcessRuntime processRuntime = new DefaultProcessRuntime();
        Process process = processRuntime.start(List.of("java"));

        await().atMost(2, SECONDS)
               .until(() -> !process.isAlive());

        assertThat(process.exitValue())
                .isEqualTo(1);
    }

    @Test
    void nullCommandThrowsException() {
        DefaultProcessRuntime processRuntime = new DefaultProcessRuntime();

        List<String> commands = new ArrayList<>();
        commands.add(null);

        assertThatCode(() -> processRuntime.start(commands))
                .isInstanceOf(InvalidCommandException.class);
    }

    @Test
    void emptyCommandListThrowsException() {
        DefaultProcessRuntime processRuntime = new DefaultProcessRuntime();

        List<String> commands = List.of();

        assertThatCode(() -> processRuntime.start(commands))
                .isInstanceOf(InvalidCommandException.class);
    }
}