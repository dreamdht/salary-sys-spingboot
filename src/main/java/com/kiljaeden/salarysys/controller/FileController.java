package com.kiljaeden.salarysys.controller;

import com.kiljaeden.salarysys.common.R;
import com.kiljaeden.salarysys.service.CosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: Kil'jaeden
 * @Email: pure3306@163.com
 * @Date: 2023/6/29 12:16
 * @Description:
 */
@RestController
@RequestMapping("/file")
public class FileController {
    @Autowired
    private CosService cosService;

    @PostMapping("/upload")
    public R addHeadImg(MultipartFile file){
        String url = cosService.upload(file);
        return R.ok().data(url).message("上传头像成功");
    }
}
