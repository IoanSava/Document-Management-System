package com.helpers;

import com.exceptions.NotFoundException;
import com.models.Catalog;
import com.models.Document;

import java.awt.*;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * A class responsible with external operations regarding a
 * binary stored Catalog
 *
 * @author: Ioan Sava
 */
public class CatalogUtilBinary {
    /**
     * Save the {@param catalog} at the path specified by {@method catalog.getPath}
     */
    public static void save(Catalog catalog) {
        try (FileOutputStream fos = new FileOutputStream(catalog.getPath());
             ObjectOutputStream out = new ObjectOutputStream(fos)) {
            out.writeObject(catalog);
        } catch (FileNotFoundException exception) {
            System.out.println("The file " + catalog.getPath() + " is missing!");
        } catch (IOException exception) {
            System.out.println("Unexpected error writing the file!");
        }
    }

    /**
     * @param path the path where the catalog to be loaded should be found
     * @return the loaded catalog
     */
    public static Catalog load(String path) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(path);
        ObjectInputStream ois = new ObjectInputStream(fis);
        return (Catalog) ois.readObject();
    }

    /**
     * @return true if {@param url} represents a
     * valid web adress
     */
    public static boolean isValidUrl(String url) {
        // Try creating a valid URL
        try {
            new URL(url).toURI();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    /**
     * Open a document using the native operating system application
     *
     * @param document a document that is wanted to be viewed
     */
    public static void view(Document document) {
        try {
            if (document == null) {
                throw new NotFoundException("Document not found");
            }

            Desktop desktop = Desktop.getDesktop();

            if (isValidUrl(document.getPath())) {
                URI uri = new URI(document.getPath());
                desktop.browse(uri);
            } else {
                desktop.open(new File(document.getPath()));
            }
        } catch (IOException | URISyntaxException | NotFoundException e) {
            System.out.println("View error");
        }
    }
}
