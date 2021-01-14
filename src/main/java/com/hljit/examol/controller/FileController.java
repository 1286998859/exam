package com.hljit.examol.controller;



import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
@Controller
public class FileController {

    /**
     * 图片上传(一张)
     * @param pic 需要上传的图片
     * @return 图片url
     * @author YSL
     * 2019-03-01 17:14
     */
    @RequestMapping("/img_upload")
    @ResponseBody
    public String imgUpload(@RequestParam(value = "pic", required = false) MultipartFile pic, HttpServletRequest request){

        List<String> urlList = upload(new MultipartFile[]{pic}, "pictures", request);

        return urlList != null ? urlList.get(0) : "";
    }

    /**
     * 图片上传(多张)
     * @param pics 需要上传的图片
     * @return 图片下标和url
     * @author YSL
     * 2019-03-01 17:14
     */
    @RequestMapping("/mul_img_upload")
    @ResponseBody
    public List<String[]> imgUpload(@RequestParam(value = "pics", required = false) MultipartFile[] pics, HttpServletRequest request){

        List<String> urlList = upload(pics, "pictures", request);

        List<String[]>  list = new ArrayList<>();
        for (int i = 0; i < urlList.size() ; i++) {
            String[] idx_url = new String[2];

            // 图片下标
            idx_url[0]=i+"";
            // 拼接url
            idx_url[1] = urlList.get(i);

            list.add(idx_url);
        }

        return list;
    }

    /**
     * 文件上传
     * @param files 需要上传的文件
     * @return 文件url
     * @author YSL
     * 2019-03-01 17:14
     */
    @RequestMapping("/file_upload")
    @ResponseBody
    public List<String> fileUpload(@RequestParam(value = "files", required = false) MultipartFile[] files, HttpServletRequest request){
        List<String> urlList = upload(files, "pictures", request);
        return urlList;
    }

    /**
     * 文件/图片上传。并做备份<br/>
     * 路径不能有反斜线和空格 <br/>
     * 上传路径：.../webapps/blog_files/pictures/20190301/图片 <br/>
     * 上传路径：.../webapps/blog_files/files/20190301/文件 <br/>
     * 备份路径：.../webserver_bak/blog/pictures/20190301/图片 <br/>
     * 备份路径：.../webserver_bak/blog/files/20190301/文件
     * @param files 需要上传的文件
     * @param categoryPath 类别路径，pictures/files
     * @return 上传成功，返回文件url
     * @author YSL
     * 2019-03-01 16:45
     */
    public List<String> upload(MultipartFile[] files, String categoryPath, HttpServletRequest request){

        // 非空判定
        if(files == null || files.length == 0){
            return new ArrayList<>();
        }

        // 专门存放文件工程名称（是一个javaweb工程，方便图片直接通过http访问）
        String fileProject = "blog_files";
        // 备份路径
        String bakPath = "D:/webserver_bak/blog/";
        //http://localhost:7989/
        String ipPort = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort() + "/";

        /**
         * 获取项目绝对路径，格式，D:\tomcats\apache-tomcat-8.0.52\webapps\boot\。
         * markdown编辑器图片路径不能有\，所以替换为/
         * 注意：.replace("//", "/"); 与 replaceAll("\\\\", "/");
         */
        String rootPath = request.getSession().getServletContext().getRealPath("").replaceAll("\\\\", "/");

        // 项目路径。/boot
        String contextPath = request.getContextPath();
        rootPath = rootPath.substring(0, rootPath.lastIndexOf(contextPath.replace("/","")));

        StringBuilder fileRoot = new StringBuilder("");
        // 工程名称
        fileRoot.append(fileProject);
        fileRoot.append("/");
        // 类别目录
        fileRoot.append(categoryPath);
        fileRoot.append("/");
        // 文件目录，图片上传失败时使用
        String picRootPath = fileRoot.toString();
        String day = new SimpleDateFormat("yyyyMMdd").format(new Date());
        // 日期目录
        fileRoot.append(day);
        fileRoot.append("/");

        // 文件最终保存目录
        String fileDir = fileRoot.toString();

        List<String>  list = new ArrayList<>();
        for (MultipartFile  multipartFile : files) {

            // 文件名称。markdown编辑器图片路径不能有空格
            String upFileName = multipartFile.getOriginalFilename().replaceAll("\\s+", "");
            String filename = new SimpleDateFormat("HHmmss").format(new Date()) + "_" + UUID.randomUUID().toString() + "_" + upFileName;

            String filePathName = rootPath + fileDir + filename;
            File destFile = new File(filePathName);
            try {
                // 复制临时文件到指定目录下, 会创建没有的目录
                FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), destFile);

                // 拼接url
                list.add(ipPort + fileDir + filename);

                // 备份
                File bakFile = new File(bakPath + fileDir + filename);
                FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), bakFile);
            } catch (UnsupportedEncodingException e2) {
                e2.printStackTrace();
                if("pictures".equals(categoryPath)){
                    // 默认图片
                    list.add(picRootPath+"default.jpg");
                }else{
                    list.add("");
                }
            } catch (IOException e) {
                e.printStackTrace();
                if("pictures".equals(categoryPath)){
                    // 默认图片
                    list.add(picRootPath+"default.jpg");
                }else{
                    list.add("");
                }
            }
        }

        return list;
    }
}
