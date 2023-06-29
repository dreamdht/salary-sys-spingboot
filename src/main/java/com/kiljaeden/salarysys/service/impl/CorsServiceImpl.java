package com.kiljaeden.salarysys.service.impl;


import com.kiljaeden.salarysys.service.CosService;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.http.HttpProtocol;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

/**
 * @Author: Kil'jaeden
 * @Email: pure3306@163.com
 * @Date: 2023/6/29 12:17
 * @Description:
 */
@Service
public class CorsServiceImpl implements CosService {
    @Override
    public String upload(MultipartFile file) {
        COSCredentials cred = new BasicCOSCredentials
                (
                        "AKID1weEesX1PccdZTURCnEbmjW5Tfdz96qM",
                        "Z0ITceVDsOmz2vfEdu00oX6WlsphOyQj"
                );
        Region region = new Region("ap-chengdu");
        ClientConfig clientConfig = new ClientConfig(region);
        clientConfig.setHttpProtocol(HttpProtocol.https);
        COSClient cosClient = new COSClient(cred, clientConfig);
        try {
            InputStream inputStream = file.getInputStream();
            String bucketName = "stuff-salary-sys-1317112389";
            String key = UUID.randomUUID().toString().replace("-","")+
                    file.getOriginalFilename();
            String deteUrl = new DateTime().toString("yyyy/MM/dd");
            key = deteUrl+"/"+key;
            ObjectMetadata objectMetadata = new ObjectMetadata();
            PutObjectRequest putObjectRequest =
                    new PutObjectRequest(bucketName, key, inputStream, objectMetadata);
            PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest);
            String url =
                    "https://stuff-salary-sys-1317112389.cos.ap-chengdu.myqcloud.com/"+key;
            return url;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
