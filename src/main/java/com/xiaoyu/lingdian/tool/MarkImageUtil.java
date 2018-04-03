package com.xiaoyu.lingdian.tool;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

@SuppressWarnings("restriction")
public class MarkImageUtil {

	protected final Logger logger = LoggerFactory.getLogger("BASE_LOGGER");

	public static void main(String[] args) throws IOException {
//		String codePath = "C:/Users/Administrator/Desktop/codePath.png";//二维码
//        String headPath = "C:/Users/Administrator/Desktop/headPath.png";//头像
//        String productPath = "C:/Users/Administrator/Desktop/productPath.png";//商品图片
//        new MarkImageUtil().outPic(headPath, codePath, productPath, "我发现这个宝贝很好，非常适合你哦", "Dickies休闲运动裤 男 毛圈布叶子图案大D...");
        
        BufferedImage bi1 = ImageIO.read(new File("C:/Users/Administrator/Desktop/head1.png"));
        
        // 根据需要是否使用 BufferedImage.TYPE_INT_ARGB
        BufferedImage bi2 = new BufferedImage(bi1.getWidth(), bi1.getHeight(),
                BufferedImage.TYPE_INT_RGB);
 
        Ellipse2D.Double shape = new Ellipse2D.Double(0, 0, bi1.getWidth(), bi1
                .getHeight());
 
        Graphics2D g2 = bi2.createGraphics();
        g2.setBackground(Color.WHITE);
        g2.fill(new Rectangle(bi2.getWidth(), bi2.getHeight()));
        g2.setClip(shape);
        // 使用 setRenderingHint 设置抗锯齿
        g2.drawImage(bi1, 0, 0, null);
        g2.dispose();
 
        try {
            ImageIO.write(bi2, "jpg", new File("C:/Users/Administrator/Desktop/head2.png"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}
	
	public byte[] outPic(String headPath, String codePath, String productPath, String shareCopy, String productDesc) {
		byte[] bytes = null;
		long start = new Date().getTime();
		String bgImgPath = Thread.currentThread().getContextClassLoader()
				.getResource("\\basic\\product_back.png").getPath();
		try {
			Image bgImgSrc = ImageIO.read(new File(bgImgPath));
			BufferedImage buffImg = new BufferedImage(bgImgSrc.getWidth(null),
					bgImgSrc.getHeight(null), BufferedImage.TYPE_3BYTE_BGR);
			Graphics2D g = buffImg.createGraphics();
			g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
					RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			g.drawImage(bgImgSrc, 0, 0, null);
			g.setColor(new Color(51, 51, 51));

			// 分享文案
			Font f = new Font("微软雅黑", Font.PLAIN, 30);// 字体，样式,和字号
			g.setFont(f);
			if (shareCopy.length() > 28) {
				logger.info("分享文案超过28个字,生成图片有瑕疵");
			}
			if (shareCopy.length() > 14) {
				g.drawString(shareCopy.substring(0, 14), 150, 90);
				g.drawString(shareCopy.substring(14, shareCopy.length()), 150,
						147);
			}
			if (shareCopy.length() <= 14) {
				g.drawString(shareCopy, 150, 90);
			}

			// 商品描述
			f = new Font("微软雅黑", Font.PLAIN, 28);// 字体，样式,和字号
			g.setFont(f);
			if (productDesc.length() > 36) {
				logger.info("商品描述超过36个字,生成图片有瑕疵");
			}
			if (productDesc.length() > 18) {
				g.drawString(productDesc.substring(0, 18), 36, 626);
				g.drawString(productDesc.substring(18, productDesc.length()), 36, 670);
			}
			if (productDesc.length() <= 18) {
				g.drawString(productDesc, 150, 626);
			}

			float alpha = 1f; // 透明度

			if (!StringUtil.isEmpty(headPath)) {
				// 头像
				BufferedImage bi1 = ImageIO.read(new File(headPath));
		        // 根据需要是否使用 BufferedImage.TYPE_INT_ARGB
		        BufferedImage bi2 = new BufferedImage(bi1.getWidth(), bi1.getHeight(), BufferedImage.TYPE_INT_RGB);
		        Ellipse2D.Double shape = new Ellipse2D.Double(0, 0, bi1.getWidth(), bi1.getHeight());
		        Graphics2D g2 = bi2.createGraphics();
		        g2.setBackground(Color.WHITE);
		        g2.fill(new Rectangle(bi2.getWidth(), bi2.getHeight()));
		        g2.setClip(shape);
		        // 使用 setRenderingHint 设置抗锯齿
		        g2.drawImage(bi1, 0, 0, null);
		        g2.dispose();
				// 得到Image对象。
				Image headimg = (Image)bi2;
				g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
				// 表示水印图片的位置
				g.drawImage(headimg, 24, 36, 90, 90, null);
			}

			if (!StringUtil.isEmpty(productPath)) {
				// 商品图
				ImageIcon productimgIcon = new ImageIcon(productPath);
				// 得到Image对象。
				Image productimg = productimgIcon.getImage();
				g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,
						alpha));
				// 表示水印图片的位置
				g.drawImage(productimg, 36, 184, 582, 372, null);
			}	

			if (!StringUtil.isEmpty(codePath)) {
				// 二维码
				ImageIcon codeimgIcon = new ImageIcon(codePath);
				// 得到Image对象。
				Image codeimg = codeimgIcon.getImage();
				g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,
						alpha));
				// 表示水印图片的位置
				g.drawImage(codeimg, 178, 780, 130, 130, null);
			}

			// g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
			// g.dispose();
			// 生成图片
			// saveImage(buffImg, "PNG", outPath);
			ByteOutputStream out = new ByteOutputStream();
			ImageIO.write(buffImg, "PNG", out);
			bytes = out.getBytes();
			try {
				if (out != null) {
					out.close();
				}
			} catch (Exception e) {
			}
			long end = new Date().getTime();
			logger.info("文件图片成功,耗时" + ((end - start) / 1000));
		} catch (Exception e) {
			logger.info("生成图片失败");
		}

		return bytes;
	}
	
	/** 
     * simple save use ImageIO.write 
     */  
    public static void saveImage(BufferedImage image, String format,  
            String filePath) {  
        try {  
            ImageIO.write(image, format, new File(filePath));  
        } catch (Exception e) {  
            throw new RuntimeException(e);  
        }  
    }  
    
	// 高度   System.out.println(fm.getHeight());  
    // 单个字符宽度  System.out.println(fm.charWidth('A'));  
    // 整个字符串的宽度  System.out.println(fm.stringWidth("宋A"));
	
}