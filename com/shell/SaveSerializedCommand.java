package com.shell;

import com.models.Catalog;
import com.helpers.CatalogUtilBinary;

/**
 * This command can be used to save a catalog
 * in a binary format
 * Format: 'save-serialized'
 *
 * @author Ioan Sava
 */
public class SaveSerializedCommand extends Command {
    public SaveSerializedCommand(String command) {
        super(command);
    }

    @Override
    public Catalog execute(Catalog catalog, Object... arguments) {
        CatalogUtilBinary.save(catalog);
        return catalog;
    }
}
