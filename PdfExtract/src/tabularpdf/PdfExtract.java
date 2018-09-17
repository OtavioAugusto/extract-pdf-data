package tabularpdf;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;

import java.io.IOException;
import java.util.List;

import technology.tabula.Page;
import technology.tabula.PageIterator;
import technology.tabula.Table;
import technology.tabula.Ruling;
import technology.tabula.extractors.BasicExtractionAlgorithm;
import technology.tabula.extractors.SpreadsheetExtractionAlgorithm;
import technology.tabula.writers.TSVWriter;

import org.apache.pdfbox.pdmodel.PDDocument;

public class PdfExtract {

	Table table;
	List<Integer> pages = new ArrayList<>();
	List<Table> tables = new ArrayList<Table>();

	public static void main(String[] args) throws IOException {
		System.out.println("started");
		String pdfFile = "test.pdf";
		String result = testTSVWriter(pdfFile);
		System.out.println(result);
	}
	
	private List<Table> getTable(String pdfFile) throws IOException {
		PDDocument document = PDDocument.load(new File(pdfFile));
		byte[] fileContent = Files.readAllBytes(new File(pdfFile).toPath());

		Integer numPages = document.getNumberOfPages();
		pages = new ArrayList<>(numPages);

		for(int i = 1; i <= numPages; i++){
			Page page = UtilsForTesting.getPage(fileContent, i);
			BasicExtractionAlgorithm bea = new BasicExtractionAlgorithm();
			table = bea.extract(page).get(0);
			/*SpreadsheetExtractionAlgorithm ssea = new SpreadsheetExtractionAlgorithm();
			table = ssea.extract(page).get(0);*/
			tables.add(table);
		}

		return tables;
	}

	public static String testTSVWriter(String path) throws IOException {
		PdfExtract table = new PdfExtract();
		StringBuilder sb = new StringBuilder();
		(new TSVWriter()).write(sb, table.getTable(path));
		String s = sb.toString();
		return s;
	}

}
