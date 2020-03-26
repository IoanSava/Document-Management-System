package com.compulsory;

import com.exceptions.DuplicateDocumentException;
import com.exceptions.NotFoundException;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * This class manages a collection of Documents
 *
 * @author: Ioan Sava
 */
@Getter
@Setter
public class Catalog implements Serializable {
    private String name;
    private String path;
    private List<Document> documents = new ArrayList<>();

    public Catalog(String name) {
        this.name = name;
    }

    public Catalog(String name, String path) {
        this.name = name;
        this.path = path;
    }

    public void addDocument(Document document) {
        try {
            if (documents.contains(document)) {
                throw new DuplicateDocumentException(this.name);
            }
            documents.add(document);
        } catch (DuplicateDocumentException exception) {
            System.out.println("Can't add document");
        }
    }

    /**
     * @param id identifier for a document
     * @return the Document with the corresponding {@param id}
     */
    public Document findById(String id) throws NotFoundException {
        for (Document document : documents) {
            if (document.getId().equals(id)) {
                return document;
            }
        }

        throw new NotFoundException("Document with id " + id + " not found");
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("CATALOG: ").append(name).append("\n");
        stringBuilder.append("Number of documents: ").append(documents.size()).append("\n\n");
        for (Document document : documents) {
            stringBuilder.append(document);
        }

        return stringBuilder.toString();
    }
}
