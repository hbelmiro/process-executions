package com.thegreatapi.processexecutions.samples.basic;

import com.thegreatapi.processexecutions.api.ProcessExecutor;
import com.thegreatapi.processexecutions.api.ProcessExecutorBuilder;
import com.thegreatapi.processexecutions.api.ProcessRuntime;
import com.thegreatapi.processexecutions.testing.FakeProcessBuilder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OperatingSystemNamePrinterTest {

    @Test
    void getOperatingSystemNameSuccess() {
        ProcessRuntime processRuntime = commands -> FakeProcessBuilder.builder()
                                                                      .inputStream("The Great OS".getBytes())
                                                                      .build();

        ProcessExecutor processExecutor = ProcessExecutorBuilder.newBuilder()
                                                                .processRuntime(processRuntime)
                                                                .build();

        OperatingSystemNamePrinter printer = new OperatingSystemNamePrinter(processExecutor);

        assertEquals("The Great OS", printer.getOperatingSystemName());
    }

    @Test
    void getOperatingSystemNameNonZeroExit() {
        ProcessRuntime processRuntime = commands -> FakeProcessBuilder.builder()
                                                                      .exitValue(42)
                                                                      .errorStream("Something went wrong".getBytes())
                                                                      .build();

        ProcessExecutor processExecutor = ProcessExecutorBuilder.newBuilder()
                                                                .processRuntime(processRuntime)
                                                                .build();

        OperatingSystemNamePrinter printer = new OperatingSystemNamePrinter(processExecutor);

        assertEquals("Unknown", printer.getOperatingSystemName());
    }
}