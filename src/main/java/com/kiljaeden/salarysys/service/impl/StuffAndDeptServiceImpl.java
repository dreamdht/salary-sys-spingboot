package com.kiljaeden.salarysys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kiljaeden.salarysys.pojo.StuffAndDept;
import com.kiljaeden.salarysys.service.StuffAndDeptService;
import com.kiljaeden.salarysys.mapper.StuffDeptMapper;
import org.springframework.stereotype.Service;

/**
* @author 会飞的鱼
* @description 针对表【stuff_dept】的数据库操作Service实现
* @createDate 2023-06-28 17:44:39
*/
@Service
public class StuffAndDeptServiceImpl extends ServiceImpl<StuffDeptMapper, StuffAndDept>
    implements StuffAndDeptService{

    @Override
    public boolean isHaveStuff(Integer id) {
        int temp = baseMapper.judgeStuff(id);
        return temp>0;
    }
}




