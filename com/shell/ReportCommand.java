package com.shell;

import com.compulsory.Catalog;
import com.compulsory.Document;
import com.exceptions.InvalidFormatException;
import com.exceptions.InvalidReportFormatException;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * This command can be used to create
 * a report in various formats
 * (html, pdf) representing the content of the catalog
 * Format: 'report html/pdf'
 *
 * @author Ioan Sava
 */
public class ReportCommand extends Command {
    public ReportCommand(String command, String arguments) {
        super(command, arguments);
    }

    private void writeDocumentHTML(Document document, PrintWriter printWriter) {
        printWriter.println("<li>" + document.getName());
        printWriter.println("<ul>");
        printWriter.println("<li> id:" + document.getId() + "</li>");
        printWriter.println("<li> path:" + document.getPath() + "</li>");
        int numberOfTags = document.getTags().size();
        if (numberOfTags > 0) {
            printWriter.println("<li> Tags <ul>");
            for (Map.Entry<String, String> tag : document.getTags().entrySet()) {
                printWriter.println("<li>" + tag.getKey() + ":" + tag.getValue() + "</li>");
            }
            printWriter.println("</ul> </li>");
        } else {
            printWriter.println("<li>No tags</li>");
        }
        printWriter.println("</ul>");
        printWriter.println("</li>");
    }

    private void createHTMLReport(Catalog catalog) {
        String filename = "report.html";
        try(FileWriter fileWriter = new FileWriter(filename);
            PrintWriter printWriter = new PrintWriter(fileWriter)) {

            printWriter.println("<html>");

            printWriter.println("<head>");
            printWriter.println("<title>Catalog</title>");
            printWriter.println("</head>");

            printWriter.println("<body>");
            printWriter.println("<h1>" + catalog.getName() + "</h1>");
            int numberOfDocuments = catalog.getDocuments().size();
            if (numberOfDocuments > 0) {
                printWriter.println("<ul> Documents");
                for (int i = 0; i < numberOfDocuments; ++i) {
                    writeDocumentHTML(catalog.getDocuments().get(i), printWriter);
                }
                printWriter.println("</ul>");
            } else {
                printWriter.println("<p>No documents</p>");
            }
            printWriter.println("</body>");

            printWriter.println("</html>");

        } catch (IOException exception) {
            System.out.println("Report error");
        }
    }

    @Override
    public Catalog execute(Catalog catalog, Object... arguments) {
        try {
            if (arguments.length == 0) {
                throw new InvalidFormatException("Invalid format");
            }

            String format = (String) arguments[0];

            if (format.equals("html")) {
                createHTMLReport(catalog);
                System.out.println("report.html created");
            } else if (format.equals("pdf")) {

                System.out.println("report.pdf created");
            } else {
                throw new InvalidReportFormatException("Invalid report format");
            }
        } catch (InvalidReportFormatException exception) {
            System.out.println("Invalid report format. You can choose between html and pdf");
        } catch (InvalidFormatException exception) {
            System.out.println("Missing argument. Format: 'report html/pdf'");
        }


        return catalog;
    }
}
