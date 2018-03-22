package base.pdf;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

/**
 * batch-parent.base.pdf <br/>
 * Created by PengRong on 2018/3/19. <br/>
 *http://www.printmyfolders.com/Home/PDFBox-Tutorial
 * https://www.tutorialkart.com/apache-pdfbox-tutorial/
 * http://java.worldbestlearningcenter.com/2013/07/word-to-pdf-converter.html
 * @author PengRong <br/>
 * @Description 获取pdf文档的文档文字内容
 * @ClassName: ${CLASS}
 * @since 2018-03-19 13:34 <br/>
 */
public class PdfToText {
    public static void main(String[] args) throws IOException {
        //定义文件路径，路径是相对于classpath
        String filePath="/pdf/123.pdf";
        String outputfile="";
        //获取文件的全路径，CLASSPATH + filePath.
        filePath=PdfToText.class.getResource(filePath).getFile();
        File pdfFile= new File(filePath);
        //Load PDF Byte to PDDocument
        PDDocument doc=PDDocument.load(pdfFile);
        int pagenumber=doc.getNumberOfPages();
        System.out.print("pages"+pagenumber);

        if (pdfFile.isFile() && (!pdfFile.isDirectory())){
            outputfile = pdfFile.getParent();//获取到目录
        }
        String name= outputfile+"/"+"123.doc";
        FileOutputStream fos=new FileOutputStream(name);
        Writer writer=new OutputStreamWriter(fos,"UTF-8");
        //pdf文档文字读取器，忽略所有文字格式和文本位置
        PDFTextStripper stripper=new PDFTextStripper();
        stripper.setSortByPosition(true);//排序
        stripper.setStartPage(3);//设置转换的开始页
        stripper.setEndPage(7);//设置转换的结束页
        stripper.writeText(doc,writer);
        String text=stripper.getText(doc);
        System.out.println(text);
        writer.close();
        doc.close();
    }
}
