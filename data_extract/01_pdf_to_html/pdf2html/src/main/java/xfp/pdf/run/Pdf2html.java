package xfp.pdf.run;

import org.apache.pdfbox.pdmodel.PDDocument;
import xfp.pdf.arrange.MarkPdf;
import xfp.pdf.core.PdfParser;
import xfp.pdf.pojo.ContentPojo;
import xfp.pdf.tools.FileTool;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.text.SimpleDateFormat;


public class Pdf2html {
    SimpleDateFormat sdf = new SimpleDateFormat();// 格式化时间
    public Pdf2html() {
        sdf.applyPattern("yyyy-MM-dd HH:mm:ss");// a为am/pm的标记
    }

    public  int calLastedTime(Date startDate) {
        long a = new Date().getTime();
        long b = startDate.getTime();
        int c = (int)((a - b) / 1000);
        return c;
    }
    public void extract_pdf_2_html(File f){
        PDDocument pdd = null;
        try {

            long a = new Date().getTime();
            String fileName = f.getName();
            System.out.println("name: "+fileName);
            pdd = PDDocument.load(f);
            ContentPojo contentPojo = PdfParser.parsingUnTaggedPdfWithTableDetection(pdd);
            MarkPdf.markTitleSep(contentPojo);
            FileTool.saveHTML(Path.outputAllHtmlPath, contentPojo, f.getAbsolutePath());
            long b = new Date().getTime();
            int c = (int)((a - b) / 1000);
//            SimpleDateFormat sdf = new SimpleDateFormat();// 格式化时间
//            sdf.applyPattern("yyyy-MM-dd HH:mm:ss");// a为am/pm的标记
//            Date date = ;// 获取当前时间
            System.out.println(sdf.format(new Date())+"\t"+"耗时时间差\t"+c +"\t" +Path.outputAllHtmlPath+fileName);
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
    public static void main(String[] args) {
        Pdf2html p = new Pdf2html();
        File file = new File(Path.inputAllPdfPath);
        File[] files = file.listFiles();
        for (File f : files) {
            p.extract_pdf_2_html(f);
        }
    }
}