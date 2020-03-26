package com;

import com.compulsory.Catalog;
import com.compulsory.CatalogUtilBinary;
import com.compulsory.Document;
import com.exceptions.InvalidCatalogFileException;
import com.exceptions.NotFoundException;
import com.optional.CatalogUtilPlainText;
import com.optional.Util;
import com.shell.*;

import java.io.IOException;
import java.util.Scanner;

/**
 * Document Management System Application Shell
 *
 * @author: Ioan Sava
 */
public class Application {
    private static Catalog catalog = null;

    public static void main(String[] args) {
        Application application = new Application();
        //application.testMethods();

        Scanner scanner = new Scanner(System.in);
        Shell shell = getShell();
        System.out.println("Commands:");
        System.out.println(shell);

        boolean running = true;
        while (running) {
            System.out.print("shell>> ");
            String line = Util.readLine(scanner);
            String[] commandArguments = line.split(" ", 2);
            Command command = shell.getCommand(commandArguments[0]);
            running = executeCommand(shell, command, commandArguments);
        }
    }

    private static boolean executeCommand(Shell shell, Command command, String[] commandArguments) {
        if (command == null) {
            System.out.println("Invalid command. Type 'show-cmds' to see available commands");
        } else if (command.getCommand().equals("quit")) {
            command.execute(null, (Object) null);
            return false;
        } else if (command.getCommand().equals("show-cmds")) {
            command.execute(null, shell);
        } else {
            if (commandArguments.length == 1) {
                catalog = command.execute(catalog);
            } else {
                catalog = command.execute(catalog, commandArguments[1]);
            }
        }

        return true;
    }

    /**
     * Create a custom shell
     */
    private static Shell getShell() {
        Shell shell = new Shell();

        shell.addCommand(new CatalogCommand("catalog"));
        shell.addCommand(new CreateCatalogCommand("create", "catalogName"));
        shell.addCommand(new ViewCommand("view", "documentId"));
        shell.addCommand(new InfoCommand("info", "documentId"));
        shell.addCommand(new SaveCommand("save"));
        shell.addCommand(new SaveSerializedCommand("save-serialized"));
        shell.addCommand(new LoadCommand("load", "fileName"));
        shell.addCommand(new LoadSerializedCommand("load-serialized", "fileName"));
        shell.addCommand(new ShowCmdsCommand("show-cmds"));
        shell.addCommand(new ReportCommand("report", "html/pdf"));
        shell.addCommand(new QuitCommand("quit"));

        return shell;
    }

    private void testMethods() {
        //compulsory
        testCreateSaveBinary();
        testLoadViewBinary();

        //optional - first bullet
        testCreateSavePlainText();
        testLoadViewPlainText();
    }

    private void testCreateSavePlainText() {
        Catalog catalog = new Catalog("Java Resources", "catalog.txt");
        Document document = new Document("java1", "Java Course 1",
                "https://profs.info.uaic.ro/~acf/java/slides/en/intro_slide_en.pdf");
        document.addTag("type", "Slides");
        document.addTag("topic", "Java");

        Document document2 = new Document("java2", "Java Course 1",
                "https://profs.info.uaic.ro/~acf/java/slides/en/intro_slide_en.pdf");

        catalog.addDocument(document);
        catalog.addDocument(document2);
        CatalogUtilPlainText.save(catalog);
    }

    private void testLoadViewPlainText() {
        try {
            Catalog catalog = CatalogUtilPlainText.load("catalog.txt");
            Document document = catalog.findById("java1");
            CatalogUtilPlainText.view(document);
        } catch (NotFoundException exception) {
            System.out.println("Document not found");
        } catch (InvalidCatalogFileException | IOException e) {
            e.printStackTrace();
        }
    }

    private void testCreateSaveBinary() {
        Catalog catalog = new Catalog("Java Resources", "catalog.ser");
        Document document = new Document("java1", "Java Course 1",
                "https://profs.info.uaic.ro/~acf/java/slides/en/intro_slide_en.pdf");
        document.addTag("type", "Slides");
        catalog.addDocument(document);

        CatalogUtilBinary.save(catalog);
    }

    private void testLoadViewBinary() {
        try {
            Catalog catalog = CatalogUtilBinary.load("catalog.ser");
            Document document = catalog.findById("java1");
            CatalogUtilBinary.view(document);
        } catch (NotFoundException | IOException | ClassNotFoundException exception) {
            System.out.println("Document not found");
        }
    }
}
