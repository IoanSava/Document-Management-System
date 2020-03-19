package com.compulsory;

import lombok.Getter;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * A Documents represents an entry in a Catalog.
 * A Document may be located using a path in the
 * local file system or a link to an external URL.
 *
 * @author: Ioan Sava
 */
@Getter
public class Document implements Serializable {
    private String id;
    private String name;
    private String path;
    private Map<String, Object> tags = new HashMap<>();

    public Document(String id, String name, String path) {
        this.id = id;
        this.name = name;
        this.path = path;
    }

    public void addTag(String key, Object value) {
        tags.put(key, value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Document document = (Document) o;
        return Objects.equals(id, document.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Document{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", path='" + path + '\'' +
                ", tags=" + tags +
                '}';
    }
}
