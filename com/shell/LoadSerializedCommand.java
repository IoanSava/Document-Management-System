package com.shell;

import com.compulsory.Catalog;
import com.compulsory.CatalogUtilBinary;
import com.exceptions.InvalidFormatException;

import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;

/**
 * This command can be used to load a binary catalog
 * Format: 'load-serialized fileName'
 *
 * @author Ioan Sava
 */
public class LoadSerializedCommand extends Command {
    public LoadSerializedCommand(String command, String arguments) {
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
            catalog = CatalogUtilBinary.load(path);
            catalog.setPath(path);
            System.out.println("Catalog loaded!");
        } catch (InvalidPathException | IOException | ClassNotFoundException exception) {
            System.out.println("Load error");
        } catch (InvalidFormatException exception) {
            System.out.println("Missing argument. Format: 'load-serialized fileName'");
        }

        return catalog;
    }
}
