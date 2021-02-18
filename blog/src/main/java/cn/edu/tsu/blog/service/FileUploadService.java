package cn.edu.tsu.blog.service;

import cn.edu.tsu.blog.commons.dto.FileUploadResult;
import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {
    FileUploadResult upload(MultipartFile uploadFile);
}
