package com.xiaoyu.lingdian.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * 文件下载接口
 *
 * @author zhangyu
 * @see
 */
@Controller
@RequestMapping("/download")
@Api(value = "download", description = "文件下载")
public class DownloadController extends BaseController {
	
    @RequestMapping(value = "/file", method = RequestMethod.GET)
    @ApiOperation(value = "文件下载", httpMethod = "GET", response = byte.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fileName", value = "文件名称", paramType = "query"),
            @ApiImplicitParam(name = "fileType", value = "文件类型", paramType = "query")
    })
    public void download(String fileName, String fileType, HttpServletRequest request, HttpServletResponse response) throws IOException {
        //将文件转换为二进制文件
        String filePath = request.getSession().getServletContext().getRealPath("") + "/WEB-INF/down/" + fileName + "." + fileType;
        fileName = URLEncoder.encode(fileName.trim(), "UTF-8") + "." + fileType;
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        InputStream is = new FileInputStream(filePath);
        int read = 0;
        byte[] bytes = new byte[2048];
        OutputStream os = response.getOutputStream();
        while ((read = is.read(bytes)) != -1) {//按字节逐个写入，避免内存占用过高
            os.write(bytes, 0, read);
        }
        os.flush();
        os.close();
        is.close();
    }

    @RequestMapping(value = "/url", method = RequestMethod.GET)
    @ApiOperation(value = "附件下载", httpMethod = "GET", response = byte.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fileName", value = "文件名称带后缀", paramType = "query"),
            @ApiImplicitParam(name = "filePath", value = "文件相对路径", paramType = "query")
    })
    public void downloadLic(String fileName, String filePath, HttpServletRequest request, HttpServletResponse response) throws IOException {
    	URL url = new URL(filePath);  
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();  
        //设置超时间为3秒
        conn.setConnectTimeout(3*1000);
        //防止屏蔽程序抓取而返回403错误
        conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
        fileName = URLEncoder.encode(fileName.trim(), "UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        //得到输入流
        InputStream is = conn.getInputStream();  
        int read = 0;
        byte[] bytes = new byte[2048];
        OutputStream os = response.getOutputStream();
        while ((read = is.read(bytes)) != -1) {//按字节逐个写入，避免内存占用过高
            os.write(bytes, 0, read);
        }
        os.flush();
        os.close();
        is.close();
    }
    
}
