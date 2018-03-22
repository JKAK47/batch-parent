package base.pdf;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.imageio.ImageIO;
import org.apache.pdfbox.contentstream.PDFStreamEngine;
import org.apache.pdfbox.contentstream.operator.Operator;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.graphics.PDXObject;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

/**
 * @Package: base.pdf <br/>
 * @Description： TODO <br/>
 * @author: PengRong <br/>
 * @Date: Created in 2018/3/23 1:02 <br/>
 * @Company: PLCC <br/>
 * @Copyright: Copyright (c) 2018 <br/>
 * @Version: 1.0 <br/>
 * @Modified By: <br/>
 * @Created by PengRong on 2018/3/23. <br/>
 * 将pdf 里面的图片下载下来
 */

public class SaveImagesInPdf extends PDFStreamEngine{
		public  SaveImagesInPdf(){

		}
		public int imageNumber = 1;
		@Override
		protected void processOperator(Operator operator, List<COSBase> operands) throws IOException {
				String operation=operator.getName();
				if ("Do".equals(operation)){
						COSName cosName= (COSName) operands.get(0);
						PDXObject object=getResources().getXObject(cosName);
						if (object instanceof PDImageXObject){
								PDImageXObject imageObject = (PDImageXObject) object;
								int imageWidth=imageObject.getWidth();
								int imageHeight=imageObject.getHeight();
								//保存图片
								BufferedImage image= new BufferedImage(imageWidth,imageHeight,BufferedImage.TYPE_INT_ARGB);
								image=imageObject.getImage();
								ImageIO.write(image,"PNG",new File("image_"+imageNumber++ +".png"));
								System.out.println("save img end ");
								imageNumber++;
						}
				}else {
						super.processOperator(operator,operands);
				}
		}
}
