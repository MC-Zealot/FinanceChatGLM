package xfp.pdf.run;

import org.apache.pdfbox.pdmodel.PDDocument;
import xfp.pdf.arrange.MarkPdf;
import xfp.pdf.core.PdfParser;
import xfp.pdf.pojo.ContentPojo;
import xfp.pdf.tools.FileTool;

import java.io.File;
import java.io.IOException;
import java.util.Date;


public class Pdf2html {
    public  int calLastedTime(Date startDate) {
        long a = new Date().getTime();
        long b = startDate.getTime();
        int c = (int)((a - b) / 1000);
        return c;
    }
    public static void main(String[] args) throws IOException {

        File file = new File(Path.inputAllPdfPath);
        File[] files = file.listFiles();
        for (File f : files) {
            PDDocument pdd = null;
            try {
                long a = new Date().getTime();
                pdd = PDDocument.load(f);
                ContentPojo contentPojo = PdfParser.parsingUnTaggedPdfWithTableDetection(pdd);
                MarkPdf.markTitleSep(contentPojo);
                FileTool.saveHTML(Path.outputAllHtmlPath, contentPojo, f.getAbsolutePath());
                long b = new Date().getTime();
                int c = (int)((a - b) / 1000);
                System.out.println("耗时时间差\t"+c +"\t" +Path.outputAllHtmlPath);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    pdd.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}