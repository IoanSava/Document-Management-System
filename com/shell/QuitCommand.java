package com.shell;

import com.compulsory.Catalog;

/**
 * This command can be used to exit the shell
 * Format: 'quit'
 *
 * @author Ioan Sava
 */
public class QuitCommand extends Command {
    public QuitCommand(String command) {
        super(command);
    }

    @Override
    public Catalog execute(Catalog catalog, Object... arguments) {
        System.out.println("Exit");
        return catalog;
    }
}
