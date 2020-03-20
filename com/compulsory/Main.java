package com.compulsory;

import com.exceptions.DuplicateDocumentException;
import com.exceptions.DuplicateTagException;

/**
 * Document Management System tests
 *
 * @author: Ioan Sava
 */
public class Main {

    public static void main(String[] args) throws DuplicateTagException, DuplicateDocumentException {
        Main application = new Main();
        application.testCreateSave();
        application.testLoadView();
    }

    private void testCreateSave() throws DuplicateTagException, DuplicateDocumentException {
        Catalog catalog = new Catalog("Java Resources", "catalog.ser");
        Document document = new Document("java1", "Java Course 1",
                "https://profs.info.uaic.ro/~acf/java/slides/en/intro_slide_en.pdf");
        document.addTag("type", "Slides");
        catalog.addDocument(document);

        CatalogUtilBinary.save(catalog);
    }

    private void testLoadView() {
        Catalog catalog = CatalogUtilBinary.load("catalog.ser");
        Document document = catalog.findById("java1");
        CatalogUtilBinary.view(document);
    }
}
