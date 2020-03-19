package com.compulsory;

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

    public void addDocument(Document document) {
        documents.add(document);
    }

    /**
     *
     * @param id identifier for a document
     * @return the Document with the corresponding {@param id}
     */
    public Document findById(String id) {
        return documents.stream()
                .filter(document -> document.getId().equals(id))
                .findFirst()
                .orElse(null);
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
