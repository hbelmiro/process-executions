package com.thegreatapi.processexecutions.api;

import com.thegreatapi.processexecutions.api.exceptions.CommandTimeoutException;
import com.thegreatapi.processexecutions.api.exceptions.NonZeroExitException;
import com.thegreatapi.processexecutions.testing.FakeProcessBuilder;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

class ProcessExecutorImplTest {

    @Test
    void runSuccess() {
        var expectedOutput = "any output";

        ProcessRuntime processRuntime = commands -> FakeProcessBuilder.builder()
                                                                      .inputStream(expectedOutput.getBytes())
                                                                      .build();

        ProcessExecutionBuilder builder = ProcessExecutionBuilder.newBuilder()
                                                                 .processRuntime(processRuntime);

        ProcessExecutorImpl processExecutor = new ProcessExecutorImpl(builder);

        List<String> stdOut = processExecutor.run(List.of("anycommand"), Duration.ofSeconds(1));

        assertThat(stdOut)
                .containsExactly(expectedOutput);
    }

    @Test
    void runFailure() {
        var expectedError = "any error";
        int expectedExitValue = 42;

        ProcessRuntime processRuntime = commands -> FakeProcessBuilder.builder()
                                                                      .exitValue(expectedExitValue)
                                                                      .errorStream(expectedError.getBytes())
                                                                      .build();

        ProcessExecutionBuilder builder = ProcessExecutionBuilder.newBuilder()
                                                                 .processRuntime(processRuntime);

        ProcessExecutorImpl processExecutor = new ProcessExecutorImpl(builder);
        List<String> commands = List.of("anycommand");
        Duration timeout = Duration.ofSeconds(1);

        assertThatCode(() -> processExecutor.run(commands, timeout))
                .isInstanceOf(NonZeroExitException.class)
                .matches(e -> ((NonZeroExitException) e).getExitValue() == expectedExitValue)
                .matches(e -> ((NonZeroExitException) e).getStdErr().equals(List.of(expectedError)));
    }

    @Test
    void timeoutExpected() {
        ProcessRuntime processRuntime = commands -> createARuntimeThatNeverFinishTheProcess();

        ProcessExecutionBuilder builder = ProcessExecutionBuilder.newBuilder()
                                                                 .processRuntime(processRuntime);

        ProcessExecutorImpl processExecutor = new ProcessExecutorImpl(builder);
        List<String> commands = List.of("anycommand");
        Duration timeout = Duration.ofSeconds(1);

        assertThatCode(() -> processExecutor.run(commands, timeout))
                .isInstanceOf(CommandTimeoutException.class);
    }

    private static Process createARuntimeThatNeverFinishTheProcess() {
        return FakeProcessBuilder.builder()
                                 .waitFor(false)
                                 .build();
    }
}