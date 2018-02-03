import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

//http://java.worldbestlearningcenter.com/2013/06/pdf-to-text-converter.html : 참고 자료
public class PDFToTextConverter {
	public static void main(String[] args) {
		selectPDFFiles();
	}
	
	public static void selectPDFFiles() {
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("PDF","pdf");
		chooser.setFileFilter(filter);
		chooser.setMultiSelectionEnabled(true);
		int returnVal = chooser.showOpenDialog(null);
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			File[] Files = chooser.getSelectedFiles();
			System.out.println(Files[0].getName().substring(0, Files[0].getName().length() -4));
			System.out.println("Plz wait..");
			for(int i=0; i<Files.length;i++) {
				convertPDFToText(Files[i].toString(),Files[i].getName().substring(0, Files[i].getName().length() -4)+".txt");
				//convertPDFToText(Files[i].toString(),"textfrompdf"+i+".txt");
			}
			System.out.println("Conversion complete");
		}
		
	}
	
	public static void convertPDFToText(String src, String desc) {
		try {
			FileWriter fw = new FileWriter(desc);
			BufferedWriter bw = new BufferedWriter(fw);
			PdfReader pr = new PdfReader(src);
			int pNum = pr.getNumberOfPages();
			for(int page=1; page<=pNum; page++) {
				String text = PdfTextExtractor.getTextFromPage(pr, page);
				bw.write(text);
				bw.newLine();
			}
			bw.flush();
			bw.close();
		}catch(Exception e) {e.printStackTrace();}
	}
}

