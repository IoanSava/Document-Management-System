package com.shell;

import com.models.Catalog;

/**
 * This command can be used to see the active catalog
 * Format: 'catalog'
 *
 * @author Ioan Sava
 */
public class CatalogCommand extends Command {

    public CatalogCommand(String command) {
        super(command);
    }

    @Override
    public Catalog execute(Catalog catalog, Object... arguments) {
        if (catalog == null) {
            System.out.println("A catalog has not been set yet");
        } else {
            System.out.println(catalog);
        }
        return catalog;
    }
}
