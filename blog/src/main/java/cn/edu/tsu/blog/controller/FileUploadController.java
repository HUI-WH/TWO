package cn.edu.tsu.blog.controller;

import cn.edu.tsu.blog.commons.code.MsgCode;
import cn.edu.tsu.blog.commons.dto.FileInfo;
import cn.edu.tsu.blog.commons.dto.ResponseResult;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("")
public class FileUploadController {
    @Value("${aliyun.endpoint}")
    private String ENDPOINT ;
    @Value("${aliyun.accessKeyId}")
    private String ACCESS_KEY_ID ;
    @Value("${aliyun.accessKeySecret}")
    private String ACCESS_KEY_SECRET;
    @Value("${aliyun.bucketName}")
    private String BUCKET_NAME;

    @PostMapping(value = "upload")
    public ResponseResult<FileInfo> upload(MultipartFile multipartFile) {
        if(multipartFile==null){
            return new ResponseResult<FileInfo>(MsgCode.ERROR, "文件上传失败，请重试");
        }
        String fileName = multipartFile.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        String newName = UUID.randomUUID() + "." + suffix;
        OSS client = new OSSClientBuilder().build(ENDPOINT, ACCESS_KEY_ID, ACCESS_KEY_SECRET);
        try {
            client.putObject(new PutObjectRequest(BUCKET_NAME, "img/"+newName, new ByteArrayInputStream(multipartFile.getBytes())));
            // 上传文件路径 = http://BUCKET_NAME.ENDPOINT/自定义路径/fileName
            return new ResponseResult<FileInfo>(MsgCode.SUCCESS, "文件上传成功", new FileInfo("http://" + BUCKET_NAME + "." + ENDPOINT + "/img/" + newName));
        } catch (IOException e) {
            return new ResponseResult<FileInfo>(MsgCode.ERROR, "文件上传失败，请重试");
        } finally {
            client.shutdown();
        }
    }

}
