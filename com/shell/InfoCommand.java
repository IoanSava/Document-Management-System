package com.shell;

import com.compulsory.Catalog;
import com.compulsory.Document;
import com.exceptions.InvalidFormatException;
import com.exceptions.NotFoundException;

/**
 * This command can be used to display the metadata of a specific
 * document
 * Format: 'info documentId'
 *
 * @author Ioan Sava
 */
public class InfoCommand extends Command {
    public InfoCommand(String command, String arguments) {
        super(command, arguments);
    }

    @Override
    public Catalog execute(Catalog catalog, Object... arguments) {
        try {
            if (arguments.length == 0) {
                throw new InvalidFormatException("Invalid format");
            }

            for (Document document : catalog.getDocuments()) {
                if (document.getId().equals((String) arguments[0])) {
                    document.printMetadata();
                    return catalog;
                }
            }

            throw new NotFoundException("Document not found");
        } catch (NotFoundException exception) {
            System.out.println("Document not found in catalog");
        } catch (InvalidFormatException exception) {
            System.out.println("Missing argument. Format: 'info documentId'");
        }

        return catalog;
    }
}
