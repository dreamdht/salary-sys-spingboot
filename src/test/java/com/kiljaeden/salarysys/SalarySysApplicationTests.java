package com.kiljaeden.salarysys;

import com.kiljaeden.salarysys.common.MD5Util;
import com.kiljaeden.salarysys.mapper.StuffMapper;
import com.kiljaeden.salarysys.pojo.Administrators;
import com.kiljaeden.salarysys.pojo.Stuff;
import com.kiljaeden.salarysys.service.AdministratorsService;
import com.kiljaeden.salarysys.service.StuffService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

@SpringBootTest
class SalarySysApplicationTests {
    @Autowired
    private AdministratorsService administratorsService;
    @Autowired
    private StuffMapper stuffMapper;

    @Test
    void contextLoads() {
    }

    @Test
    public void demo01(){
        Administrators administrators = new Administrators();
        administrators.setUsername("admin");
        administrators.setPassword(MD5Util.GetPasswordByMD5("admin1227"));
        administrators.setName("超级管理员1227");
        administratorsService.save(administrators);
    }

    @Test
    public void demo02(){
        System.out.println(MD5Util.GetPasswordByMD5("gaojunbo123"));
    }

    @Test
    public void demo03(){
        List<Map<String, Object>> stuffCountByDeptId = stuffMapper.getStuffCountByDeptId();
        stuffCountByDeptId.forEach(System.out::println);
    }

    @Test
    public void demo04(){
        stuffMapper.getAvgSalaryByDept().forEach(System.out::println);
    }

    @Test
    public void demo05(){
        System.out.println(MD5Util.GetPasswordByMD5("demo08"));
        System.out.println(MD5Util.GetPasswordByMD5("demo03"));
        System.out.println(MD5Util.GetPasswordByMD5("zhangchangpeng123"));
        System.out.println(MD5Util.GetPasswordByMD5("machunsen123"));
        System.out.println(MD5Util.GetPasswordByMD5("xiadongqi123"));
        System.out.println(MD5Util.GetPasswordByMD5("zhanghuanmin123"));
        System.out.println(MD5Util.GetPasswordByMD5("lifan123"));
        System.out.println(MD5Util.GetPasswordByMD5("zhouyang123"));
        System.out.println(MD5Util.GetPasswordByMD5("wanghan123"));
        System.out.println(MD5Util.GetPasswordByMD5("songhongkang123"));
        System.out.println(MD5Util.GetPasswordByMD5("xujingbo123"));
    }
}
