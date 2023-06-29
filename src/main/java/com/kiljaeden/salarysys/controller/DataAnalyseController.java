package com.kiljaeden.salarysys.controller;

import com.kiljaeden.salarysys.common.R;
import com.kiljaeden.salarysys.config.RedisConfig;
import com.kiljaeden.salarysys.pojo.Dept;
import com.kiljaeden.salarysys.service.DeptService;
import com.kiljaeden.salarysys.service.StuffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Author: Kil'jaeden
 * @Email: pure3306@163.com
 * @Date: 2023/6/29 14:56
 * @Description:
 */
@RestController
@RequestMapping("/dataAnalyse")
public class DataAnalyseController {
    @Autowired
    private DeptService deptService;
    @Autowired
    private StuffService stuffService;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public Object retrieveData(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public void storeData(String key, Object data) {
        redisTemplate.opsForValue().set(key, data);
    }

    @GetMapping("/deptList")
    public R getDeptList(){

        return R.ok().data(deptService.list());
    }

    @GetMapping("/stuffCount")
    public R getStuffCountFromByDept(){
        Map<String, Object> redisData = (Map<String, Object>) retrieveData("redis-dataAnalyse-stuffCount");
        if(redisData != null){
            return R.ok().data(redisData);
        }

        List<Map<String, Object>> stuffCountList = stuffService.getStuffCountByDeptId();
        List<Dept> deptList = deptService.list();

        List<Map<String, Object>> resultList = new ArrayList<>();

        for (Map<String, Object> stuffCountMap : stuffCountList) {
            Integer deptId = (Integer) stuffCountMap.get("deptId");
            Long employeeCount = (Long) stuffCountMap.get("count");

            String deptName = getDeptNameById(deptId, deptList);

            Map<String, Object> result = new HashMap<>();
            result.put("deptName", deptName);
            result.put("employeeCount", employeeCount);
            resultList.add(result);
        }

        storeData("redis-dataAnalyse-stuffCount",resultList);

        return R.ok().data(resultList);
    }

    private String getDeptNameById(Integer deptId, List<Dept> deptList) {
        for (Dept dept : deptList) {
            if (dept.getId().equals(deptId)) {
                return dept.getName();
            }
        }
        return null; // or throw an exception if necessary
    }

    @GetMapping("/avgSal")
    public R getDeptAvgSalary(){
        Map<String, Object> redisData = (Map<String, Object>) retrieveData("redis-dataAnalyse-avgSal");
        if(redisData!=null){
            return R.ok().data(redisData);
        }

        List<Map<String, Object>> list = stuffService.getAvgSalaryByDept();
        List<Dept> deptList = deptService.list();

        List<Map<String, Object>> resultList = new ArrayList<>();

        for (Map<String, Object> stuffCountMap : list) {
            Integer deptId = (Integer) stuffCountMap.get("deptId");
            BigDecimal employeeCount = (BigDecimal) stuffCountMap.get("avgSalary");

            String deptName = getDeptNameById(deptId, deptList);

            Map<String, Object> result = new HashMap<>();
            result.put("deptName", deptName);
            result.put("avgSalary", employeeCount);
            resultList.add(result);
        }

        storeData("redis-dataAnalyse-avgSal",resultList);

        return R.ok().data(resultList);
    }

}
