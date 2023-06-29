package com.kiljaeden.salarysys.mapper;

import com.kiljaeden.salarysys.pojo.Dept;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 会飞的鱼
* @description 针对表【dept】的数据库操作Mapper
* @createDate 2023-06-28 17:44:16
* @Entity com.kiljaeden.salarysys.pojo.Dept
*/
@Mapper
public interface DeptMapper extends BaseMapper<Dept> {

}




