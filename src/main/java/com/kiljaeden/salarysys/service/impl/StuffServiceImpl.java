package com.kiljaeden.salarysys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kiljaeden.salarysys.pojo.Stuff;
import com.kiljaeden.salarysys.service.StuffService;
import com.kiljaeden.salarysys.mapper.StuffMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
* @author 会飞的鱼
* @description 针对表【stuff】的数据库操作Service实现
* @createDate 2023-06-28 17:41:08
*/
@Service
public class StuffServiceImpl extends ServiceImpl<StuffMapper, Stuff>
    implements StuffService{

    @Override
    public List<Map<String, Object>> getStuffCountByDeptId() {
        return baseMapper.getStuffCountByDeptId();
    }

    @Override
    public List<Map<String, Object>> getAvgSalaryByDept() {
        return baseMapper.getAvgSalaryByDept();
    }
}




