package com.thegreatapi.processexecutions.core;

import java.util.List;

@SuppressWarnings("InterfaceMayBeAnnotatedFunctional")
public interface ProcessRuntime {

    /**
     * Starts a new process using the attributes of this process builder.
     *
     * @param commands the list containing the program and its arguments
     * @return a new {@link Process} object for managing the subprocess
     * @throws ProcessExecutionException in case of any error
     */
    Process start(List<String> commands);
}
