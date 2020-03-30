package com.models;

import com.exceptions.DuplicateTagException;
import com.exceptions.NotFoundException;
import lombok.Getter;
import lombok.Setter;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.helpers.CatalogUtilPlainText.isValidUrl;

/**
 * A Documents represents an entry in a Catalog.
 * A Document may be located using a path in the
 * local file system or a link to an external URL.
 *
 * @author: Ioan Sava
 */
@Getter
@Setter
public class Document implements Serializable {
    private String id;
    private String name;
    private String path;
    private Map<String, String> tags = new HashMap<>();

    public Document(String id, String name, String path) {
        this.id = id;
        this.name = name;
        this.path = path;
    }

    public String getTag(String key) throws NotFoundException {
        if (tags.containsKey(key)) {
            return tags.get(key);
        }

        throw new NotFoundException("Tag " + key + " not found");
    }

    public void addTag(String key, String value) {
        try {
            if (tags.containsKey(key)) {
                throw new DuplicateTagException(this.name);
            }
            tags.put(key, value);
        } catch (DuplicateTagException exception) {
            System.out.println("Tag won't be added");
        }
    }

    public void updateTag(String key, String value) {
        try {
            if (!tags.containsKey(key)) {
                throw new NotFoundException("Tag " + key + " not found");
            }
            tags.put(key, value);
        } catch (NotFoundException exception) {
            System.out.println("Can't update tag");
        }

    }

    public void removeTag(String key) {
        try {
            if (!tags.containsKey(key)) {
                throw new NotFoundException("Tag " + key + " not found");
            }
            tags.remove(key);
        } catch (NotFoundException exception) {
            System.out.println("Can't remove tag");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Document document = (Document) o;
        return Objects.equals(id, document.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    private String getTagsAsString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("Number of tags: ").append(tags.size()).append("\n");
        for (Map.Entry<String, String> tag : tags.entrySet()) {
            stringBuilder.append(tag.getKey()).append(" : ").append(tag.getValue()).append("\n");
        }

        return stringBuilder.toString();
    }

    @Override
    public String toString() {

        return "#" + "id: " + id + "\n" +
                "#" + "name: " + name + "\n" +
                "#" + "path: " + path + "\n" +
                getTagsAsString() +
                "\n";
    }

    /**
     * Print metadata using Apache Tika
     */
    public void printMetadata() {
        if (isValidUrl(path)) {
            System.out.println("Metadata can only be provided for file system documents!");
        } else {
            try (InputStream inputStream = new FileInputStream(path)) {
                Parser parser = new AutoDetectParser();
                BodyContentHandler handler = new BodyContentHandler();
                Metadata metadata = new Metadata();
                ParseContext context = new ParseContext();
                parser.parse(inputStream, handler, metadata, context);

                String[] names = metadata.names();
                System.out.println("Document metadata:");
                for (String name : names) {
                    System.out.println(name + " : " + metadata.get(name));
                }
            } catch (IOException | TikaException | org.xml.sax.SAXException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }
}
