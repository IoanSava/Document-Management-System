package com.shell;

import com.models.Catalog;

/**
 * This command can be used to see available commands
 * Format: 'show-cmds'
 *
 * @author Ioan Sava
 */
public class ShowCmdsCommand extends Command {
    public ShowCmdsCommand(String command) {
        super(command);
    }

    @Override
    public Catalog execute(Catalog catalog, Object... arguments) {
        System.out.println("Commands:");
        System.out.println(arguments[0]);
        return catalog;
    }
}
