package com.shell;

import com.compulsory.Catalog;
import com.optional.CatalogUtilPlainText;

/**
 * This command can be used to save a catalog
 * in a plaintext format
 * Format: 'save'
 *
 * @author Ioan Sava
 */
public class SaveCommand extends Command {
    public SaveCommand(String command) {
        super(command);
    }

    @Override
    public Catalog execute(Catalog catalog, Object... arguments) {
        CatalogUtilPlainText.save(catalog);
        return catalog;
    }
}
