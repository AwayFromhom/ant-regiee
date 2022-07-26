package com.itheima.reggie.controller;

import com.itheima.reggie.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping(value = "/api/upload",produces = "application/json;charset=utf-8")
public class fileController {

    @Value("${regiee.photo.path}")
    private String basePath;

    /**
     * 上传菜品图片
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping("/adddish")
    public R<String> adddish(MultipartFile file) throws IOException {

        //获取原始文件名
        String originalFilename = file.getOriginalFilename();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));

        //使用uuid重新生成文件名，防止文件名重复造成文件覆盖
        String filename = UUID.randomUUID().toString() + suffix;

        //判断目录是否存在，不存在则创建目录。
        File dir = new File(basePath);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        file.transferTo(new File(basePath+filename));
           return R.success(filename);
    }

    @GetMapping("/downloaddish")
    public R<String> downloadDish(String filename, HttpServletResponse response) throws IOException {
        //输入流通过输入流读取文件内容
        FileInputStream fileInputStream = new FileInputStream(new File(basePath+filename));

        //输出流通过输出流将文件写会浏览器
        ServletOutputStream outputStream = response.getOutputStream();

        response.setContentType("image/jpeg");

        int len = 0;
        byte[] bytes = new byte[1024];
        while ((len= fileInputStream.read(bytes)) != -1) {
            outputStream.write(bytes, 0, len);
            outputStream.flush();
        }

        //关闭资源
        outputStream.close();
        fileInputStream.close();

        return R.success(filename);

    }

}


