package com.shell;

import com.compulsory.Catalog;
import com.exceptions.InvalidCatalogFileException;
import com.exceptions.InvalidFormatException;
import com.optional.CatalogUtilPlainText;

import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;

/**
 * This command can be used to load a plaintext catalog
 * Format: 'load fileName'
 *
 * @author Ioan Sava
 */
public class LoadCommand extends Command {
    public LoadCommand(String command, String arguments) {
        super(command, arguments);
    }

    @Override
    public Catalog execute(Catalog catalog, Object... arguments) {
        try {
            if (arguments.length == 0) {
                throw new InvalidFormatException("Invalid format");
            }

            String path = (String) arguments[0];
            Paths.get(path);
            catalog = CatalogUtilPlainText.load(path);
            catalog.setPath(path);
            System.out.println("Catalog loaded!");
        } catch (InvalidPathException | InvalidCatalogFileException | IOException exception) {
            System.out.println("Load error");
        } catch (InvalidFormatException exception) {
            System.out.println("Missing argument. Format: 'load fileName'");
        }

        return catalog;
    }
}
