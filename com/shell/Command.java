package com.shell;

import com.compulsory.Catalog;
import lombok.Getter;

/**
 * Abstract class to describe a generic command
 *
 * @author Ioan Sava
 */
@Getter
public abstract class Command {
    private String command;
    private String arguments;

    public Command(String command) {
        this.command = command;
    }

    public Command(String command, String arguments) {
        this.command = command;
        this.arguments = arguments;
    }

    public abstract Catalog execute(Catalog catalog, Object... arguments);

    @Override
    public String toString() {
        return command + " " + arguments;
    }
}
