package com.kiljaeden.salarysys.service;

import com.kiljaeden.salarysys.pojo.StuffAndDept;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 会飞的鱼
* @description 针对表【stuff_dept】的数据库操作Service
* @createDate 2023-06-28 17:44:39
*/
public interface StuffAndDeptService extends IService<StuffAndDept> {

    boolean isHaveStuff(Integer id);
}
