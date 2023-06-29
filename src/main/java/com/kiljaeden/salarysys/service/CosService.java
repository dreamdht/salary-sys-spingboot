package com.kiljaeden.salarysys.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: Kil'jaeden
 * @Email: pure3306@163.com
 * @Date: 2023/6/29 12:17
 * @Description:
 */
public interface CosService {
    String upload(MultipartFile file);

}
