package com.shell;

import com.compulsory.Catalog;
import com.compulsory.Document;
import com.exceptions.InvalidFormatException;
import com.exceptions.NotFoundException;
import com.optional.CatalogUtilPlainText;

/**
 * This command can be used to view a document
 * from the active catalog
 * Format: 'view documentId'
 *
 * @author Ioan Sava
 */
public class ViewCommand extends Command {
    public ViewCommand(String command, String arguments) {
        super(command, arguments);
    }

    @Override
    public Catalog execute(Catalog catalog, Object... arguments) {
        try {
            if (arguments.length == 0) {
                throw new InvalidFormatException("Invalid format");
            }

           boolean documentFound = false;
           for (Document document : catalog.getDocuments()) {
               if (document.getId().equals((String) arguments[0])) {
                   documentFound = true;
                   CatalogUtilPlainText.view(document);
               }
           }

           if (!documentFound) {
               throw new NotFoundException("Document not found");
           }
        } catch (NotFoundException exception) {
            System.out.println("Document not found in catalog");
        } catch (InvalidFormatException exception) {
            System.out.println("Missing argument. Format: 'view documentId'");
        }

        return catalog;
    }
}
