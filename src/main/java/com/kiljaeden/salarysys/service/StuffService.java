package com.kiljaeden.salarysys.service;

import com.kiljaeden.salarysys.pojo.Stuff;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
* @author 会飞的鱼
* @description 针对表【stuff】的数据库操作Service
* @createDate 2023-06-28 17:41:08
*/
public interface StuffService extends IService<Stuff> {
    List<Map<String,Object>> getStuffCountByDeptId();

    List<Map<String,Object>> getAvgSalaryByDept();

}
