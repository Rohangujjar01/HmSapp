package com.hmsapp.hmsapp.service;
import com.hmsapp.hmsapp.Entity.Booking;
import com.hmsapp.hmsapp.repository.BookingRepository;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Cell;
import org.springframework.stereotype.Service;

import java.io.IOException;
@Service
public class PDFGenerator {
    public void generatePDF(String path, Booking booking) {

        // Create the PDF document
        try {
            // Initialize PdfWriter and PdfDocument
            PdfWriter writer = new PdfWriter(path);
            PdfDocument pdfDocument = new PdfDocument(writer);

            // Initialize Document (using PdfDocument)
            Document document = new Document(pdfDocument);

            // Add a simple paragraph
            document.add(new Paragraph("This is a PDF document with a 2-column table."));

            // Create a table with 2 columns
            float[] columnWidths = {2, 1}; // Relative widths for two columns (2:1 ratio)
            Table table = new Table(columnWidths);

            // Add headers to the table
            table.addHeaderCell("Name");
            table.addHeaderCell("Mobile");

            // Add rows to the table
            table.addCell(booking.getGuestName());
            table.addCell(booking.getMobile());


            // Add the table to the document
            document.add(table);

            // Close the document
            document.close();

            System.out.println("PDF with 2-column table created successfully at: " + path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
