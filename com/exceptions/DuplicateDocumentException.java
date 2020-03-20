package com.exceptions;

public class DuplicateDocumentException extends Exception {
    public DuplicateDocumentException(String nameOfCatalog) {
        super("This document was already added in the catalog " + nameOfCatalog);
    }
}
