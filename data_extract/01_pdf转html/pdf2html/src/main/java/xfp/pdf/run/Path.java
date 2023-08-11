package xfp.pdf.run;

public class Path {

//    public static String inputAllPdfPath = "../../存放pdf/";
    public static String inputAllPdfPath = "/Users/zealot/yizhou/git/FinanceChatGLM/data_extract/存放pdf/";;
    //所有pdf转成的html的位置
//    public static String outputAllHtmlPath = "../../数据处理过程目录/html结果/";;
    public static String outputAllHtmlPath = "/Users/zealot/yizhou/git/FinanceChatGLM/data_extract/数据处理过程目录/html结果/";

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
