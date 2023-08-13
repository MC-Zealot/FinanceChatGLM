package xfp.pdf.run;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import xfp.pdf.arrange.MarkPdf;
import xfp.pdf.core.PdfParser;
import xfp.pdf.pojo.ContentPojo;
import xfp.pdf.tools.FileTool;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;


public class Pdf2htmlMultiThread {
    static SimpleDateFormat sdf = new SimpleDateFormat();// 格式化时间
    public Pdf2htmlMultiThread() {
        sdf.applyPattern("yyyy-MM-dd HH:mm:ss");// a为am/pm的标记
    }

    public  int calLastedTime(Date startDate) {
        long a = new Date().getTime();
        long b = startDate.getTime();
        int c = (int)((a - b) / 1000);
        return c;
    }
    public static void extract_pdf_2_html(File f){
        PDDocument pdd = null;
        try {
            String file_name = f.getName().split("\\.")[0];
            String output_file_name = Path.outputAllHtmlPath+file_name+".html";
            File output_file = new File(output_file_name);
            if(output_file.exists()){
                System.out.println("file exists: "+output_file_name);
                return;
            }
            long a = new Date().getTime();
            String fileName = f.getName();
//            System.out.println("name: "+fileName);
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
                if(pdd!=null){
                    pdd.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static <T> void Thread(File[] list, int nThread) {
        if (list==null || nThread <= 0 || list.length==0) {
            return;
        }
        Semaphore semaphore = new Semaphore(nThread);//定义几个许可
        ExecutorService executorService = Executors.newFixedThreadPool(nThread);//创建一个固定的线程池
        for (File file : list) {
            try {
                semaphore.acquire();
                executorService.execute(() -> {
                    //此处可以放入待处理的业务
//                    System.out.println("number:" + file.getName());
                    extract_pdf_2_html(file);
                    semaphore.release();
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("wrong file: "+file.getName());
            }
        }
        executorService.shutdown();
    }
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        Pdf2htmlMultiThread p = new Pdf2htmlMultiThread();
        File file = new File(Path.inputAllPdfPath);
        File[] files = file.listFiles();
        Thread(files, 7);
//        for (File f : files) {
//            p.extract_pdf_2_html(f);
//        }
        long end = System.currentTimeMillis();
        System.out.println("总时间 = " + (int)(end - start)/1000);
    }
}