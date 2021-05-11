package com.ashokit.pdf;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.stream.Stream;



import com.ashokit.entity.InsurancePlans;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class PDFGenerator {

	// private static Logger logger = LoggerFactory.getLogger(PDFGenerator.class);

	public static ByteArrayInputStream insurancePDFReport(List<InsurancePlans> plans) {
		Document document = new Document();
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		try {

			PdfWriter.getInstance(document, out);
			document.open();

			// Add Text to PDF file ->
			Font font = FontFactory.getFont(FontFactory.COURIER, 14, BaseColor.BLACK);
			Paragraph para = new Paragraph("InsurancePlans Table", font);
			para.setAlignment(Element.ALIGN_CENTER);
			document.add(para);
			document.add(Chunk.NEWLINE);

			PdfPTable table = new PdfPTable(5);
			// Add PDF Table Header ->
			Stream.of("ID", "Plane Name", "plane status", "HolderName", "Holder_SSN").forEach(headerTitle -> {
				PdfPCell header = new PdfPCell();
				Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
				header.setBackgroundColor(BaseColor.LIGHT_GRAY);
				header.setHorizontalAlignment(Element.ALIGN_CENTER);
				header.setBorderWidth(2);
				header.setPhrase(new Phrase(headerTitle, headFont));
				table.addCell(header);
			});

			for (InsurancePlans plan : plans) {
				PdfPCell idCell = new PdfPCell(new Phrase(plan.getPlanId().toString()));
				idCell.setPaddingLeft(4);
				idCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				idCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(idCell);

				PdfPCell planNameCell = new PdfPCell(new Phrase(plan.getPlanName()));
				planNameCell.setPaddingLeft(4);
				planNameCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				planNameCell.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(planNameCell);

				PdfPCell planStatusCell = new PdfPCell(new Phrase(String.valueOf(plan.getPlanStatus())));
				planStatusCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				planStatusCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				planStatusCell.setPaddingRight(4);
				table.addCell(planStatusCell);

				PdfPCell holderNameCell = new PdfPCell(new Phrase(String.valueOf(plan.getPlanName())));
				holderNameCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				holderNameCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				holderNameCell.setPaddingRight(4);
				table.addCell(holderNameCell);
				
				PdfPCell holderSsnCell = new PdfPCell(new Phrase(String.valueOf(plan.getHolderSsn())));
				holderSsnCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				holderSsnCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				holderSsnCell.setPaddingRight(4);
				table.addCell(holderSsnCell);
			}
			document.add(table);

			document.close();
		} catch (DocumentException e) {
			e.printStackTrace();
		//	logger.error(e.toString());
		}

		return new ByteArrayInputStream(out.toByteArray());
	}
}