package com.thegreatapi.processexecutions.api;

import java.time.Duration;
import java.util.List;

@SuppressWarnings("InterfaceMayBeAnnotatedFunctional")
public interface ProcessExecutor {

    List<String> run(List<String> commands, Duration timeout);
}