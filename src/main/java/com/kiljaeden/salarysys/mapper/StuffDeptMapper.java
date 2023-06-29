package com.kiljaeden.salarysys.mapper;

import com.kiljaeden.salarysys.pojo.StuffAndDept;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 会飞的鱼
* @description 针对表【stuff_dept】的数据库操作Mapper
* @createDate 2023-06-28 17:44:39
* @Entity com.kiljaeden.salarysys.pojo.StuffAndDept
*/
@Mapper
public interface StuffDeptMapper extends BaseMapper<StuffAndDept> {

    int judgeStuff(Integer id);
}




