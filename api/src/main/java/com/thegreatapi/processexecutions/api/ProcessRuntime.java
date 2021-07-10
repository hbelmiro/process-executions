package com.thegreatapi.processexecutions.api;

import com.thegreatapi.processexecutions.api.exceptions.InvalidCommandException;

import java.util.List;

@SuppressWarnings("InterfaceMayBeAnnotatedFunctional")
public interface ProcessRuntime {

    /**
     * Starts a new process using the attributes of this process builder.
     *
     * @param commands the list containing the program and its arguments
     * @return a new {@link Process} object for managing the subprocess
     * @throws InvalidCommandException if the command is an empty list (has size 0) or if an element of the command list
     *                                 is null
     */
    Process start(List<String> commands);
}
