package base.pdf;

import java.io.File;
import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

/**
 * @Package: base.pdf <br/>
 * @Description： TODO <br/>
 * @author: PengRong <br/>
 * @Date: Created in 2018/3/23 1:01 <br/>
 * @Company: PLCC <br/>
 * @Copyright: Copyright (c) 2018 <br/>
 * @Version: 1.0 <br/>
 * @Modified By: <br/>
 * @Created by PengRong on 2018/3/23. <br/>
 * 将PDF文档里面的图片截取出来。
 */

public class PdfToImg {
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
				int pageNum=0;
				SaveImagesInPdf imagesInPdf=new SaveImagesInPdf();
				for (PDPage page : doc.getPages()){
						pageNum++;
						System.out.println("开始处理第 "+ pageNum+ "页");
						imagesInPdf.processPage(page);
				}

		}
}
