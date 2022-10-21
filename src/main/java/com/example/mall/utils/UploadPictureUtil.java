package com.example.mall.utils;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.*;
import java.util.Base64;
import java.util.Objects;

// 商品图片上传
public class UploadPictureUtil {

    public static byte[] enPic(File file) throws IOException {
        FileInputStream fis = new FileInputStream(file);
        byte[] arr = new byte[5242880];
        int read = fis.read(arr);
        byte[] arrByte = new byte[read];
        return arrByte;
    }

}
