package com.exceptions;

public class DuplicateTagException extends Exception {
    public DuplicateTagException(String nameOfDocument) {
        super("This tag was already defined in document " + nameOfDocument);
    }
}
