package xfp.pdf.run;

public class Path {

//    public static String inputAllPdfPath = "../../存放pdf/";
    public static String inputAllPdfPath = "/Users/zealot/yizhou/data/allpdf/modelscope/chatglm_llm_fintech_raw_dataset/master/data_files/";
//    public static String inputAllPdfPath = "/Users/zealot/yizhou/data/allpdf_test/";
    //所有pdf转成的html的位置
//    public static String outputAllHtmlPath = "../../数据处理过程目录/html结果/";;
//    public static String outputAllHtmlPath = "/Users/zealot/yizhou/git/FinanceChatGLM/data_extract/data_extract_output/html_res/";
    public static String outputAllHtmlPath = "/Users/zealot/yizhou/data/html_res/";

    public static void main(String[] args) {
        String s = "/Users/zealot/yizhou/git/FinanceChatGLM/data_extract/存放pdf/2020-01-21__江苏安靠智能输电工程科技股份有限公司__300617__安靠智电__2019年__年度报告.pdf";
        String[] ss = s.split("/");
        String name = s.split("/")[s.split("/").length - 1].split("\\.")[0];
        for(String str: ss){
            System.out.println(str);
        }
        System.out.println(name);
    }

}
