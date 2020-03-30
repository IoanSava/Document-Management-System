package com.helpers;

import com.models.Catalog;
import com.models.Document;
import com.exceptions.InvalidCatalogFileException;
import com.exceptions.NotFoundException;

import java.awt.*;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * A class responsible with external operations regarding a
 * plaintext stored Catalog
 *
 * @author: Ioan Sava
 */
public class CatalogUtilPlainText {
    public static void save(Catalog catalog) {
        try(FileWriter fileWriter = new FileWriter(catalog.getPath());
            PrintWriter printWriter = new PrintWriter(fileWriter)) {
            printWriter.print(catalog);

        } catch (IOException exception) {
            System.out.println("Save error");
        }
    }

    private static void checkNull(String str) throws InvalidCatalogFileException {
        if (str == null) {
            throw new InvalidCatalogFileException();
        }
    }

    private static String getSubString(String string, int index) {
        return string.split(":", 2)[index].trim();
    }

    /**
     * @return loaded Document from a plaintext saved Catalog
     */
    private static Document getCurrentDocument(BufferedReader bufferedReader) throws IOException, InvalidCatalogFileException {
        String line = bufferedReader.readLine();
        checkNull(line);

        line = bufferedReader.readLine();
        checkNull(line);
        String id = getSubString(line, 1);

        line = bufferedReader.readLine();
        checkNull(line);
        String name = getSubString(line, 1);

        line = bufferedReader.readLine();
        checkNull(line);
        String path = getSubString(line, 1);

        Document document = new Document(id, name, path);

        line = bufferedReader.readLine();
        checkNull(line);
        int numberOfTags = Integer.parseInt(getSubString(line, 1));
        for (int i = 0; i < numberOfTags; ++i) {
            line = bufferedReader.readLine();
            checkNull(line);
            String key = getSubString(line, 0);
            String value = getSubString(line, 1);
            document.addTag(key, value);
        }

        return document;
    }

    /**
     * @param path Catalog path
     * @return loaded Catalog
     */
    public static Catalog load(String path) throws InvalidCatalogFileException, IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
        String line = bufferedReader.readLine();
        checkNull(line);
        String catalogName = getSubString(line, 1);

        Catalog catalog = new Catalog(catalogName, path);

        line = bufferedReader.readLine();
        checkNull(line);
        int numberOfDocuments = Integer.parseInt(getSubString(line, 1));

        for (int i = 0; i < numberOfDocuments; ++i) {
            catalog.addDocument(getCurrentDocument(bufferedReader));
        }

        return catalog;
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
            e.printStackTrace();
        }
    }
}
