package com.compulsory;

import com.exceptions.DuplicateDocumentException;
import com.exceptions.NotFoundException;
import lombok.Getter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * This class manages a collection of Documents
 *
 * @author: Ioan Sava
 */
@Getter
public class Catalog implements Serializable {
    private String name;
    private String path;
    private List<Document> documents = new ArrayList<>();

    public Catalog(String path) {
        this.path = path;
    }

    public Catalog(String name, String path) {
        this.name = name;
        this.path = path;
    }

    public void addDocument(Document document) throws DuplicateDocumentException {
        if (documents.contains(document)) {
            throw new DuplicateDocumentException(this.name);
        }
        documents.add(document);
    }

    /**
     *
     * @param id identifier for a document
     * @return the Document with the corresponding {@param id}
     */
    public Document findById(String id) {
        for (Document document : documents) {
            if (document.getId().equals(id)) {
                return document;
            }
        }

        throw new NotFoundException("Document with id " + id + " not found");
    }

    @Override
    public String toString() {
        return "Catalog{" +
                "name='" + name + '\'' +
                ", path='" + path + '\'' +
                ", documents=" + documents +
                '}';
    }
}
