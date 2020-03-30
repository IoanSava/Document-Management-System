package com.shell;

import com.models.Catalog;
import com.exceptions.InvalidFormatException;

/**
 * This command can be used to create a new catalog
 * Format: 'create catalogName'
 *
 * @author Ioan Sava
 */
public class CreateCatalogCommand extends Command {
    public CreateCatalogCommand(String command, String arguments) {
        super(command, arguments);
    }

    @Override
    public Catalog execute(Catalog catalog, Object... arguments) {
        try {
            if (arguments.length == 0) {
                throw new InvalidFormatException("Invalid format");
            }

            catalog = new Catalog((String) arguments[0]);
            System.out.println("Catalog created!");
        } catch (InvalidFormatException exception) {
            System.out.println("Missing argument. Format: 'create catalogName'");
        }

        return catalog;
    }
}
