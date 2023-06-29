package com.kiljaeden.salarysys.mapper;

import com.kiljaeden.salarysys.pojo.Stuff;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
* @author 会飞的鱼
* @description 针对表【stuff】的数据库操作Mapper
* @createDate 2023-06-28 17:41:08
* @Entity com.kiljaeden.salarysys.pojo.Stuff
*/
@Mapper
public interface StuffMapper extends BaseMapper<Stuff> {
    List<Map<String,Object>> getStuffCountByDeptId();

    List<Map<String,Object>> getAvgSalaryByDept();
}




