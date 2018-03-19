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
 *
 * @author PengRong <br/>
 * @Description TODO(${END})
 * @ClassName: ${CLASS}
 * @since 2018-03-19 13:34 <br/>
 */
public class PdfToWord {
    public static void main(String[] args) throws IOException {
        //PDFBox box=null;
        File pdfFile= new File("D:\\Dev\\WorkStation\\IdeaProjects\\batch-parent\\batch-base\\src\\main\\resources\\pdf/冠珠卫浴小画册-第二部份(浴室柜淋浴房浴缸龙头花洒11-30)转曲.pdf");
        PDDocument document=null;
        PDDocument doc=PDDocument.load(pdfFile);
        int pagenumber=doc.getNumberOfPages();
        System.out.print("pages"+pagenumber);
        FileOutputStream fos=new FileOutputStream("D:\\Dev\\WorkStation\\IdeaProjects\\batch-parent\\batch-base\\src\\main\\resources\\pdf/123.doc");
        Writer writer=new OutputStreamWriter(fos,"UTF-8");
        PDFTextStripper stripper=new PDFTextStripper();
        stripper.setSortByPosition(true);//排序
        stripper.setStartPage(3);//设置转换的开始页
        stripper.setEndPage(7);//设置转换的结束页
        stripper.writeText(doc,writer);
        writer.close();
        doc.close();
    }
}
