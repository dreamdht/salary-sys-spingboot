package com.kiljaeden.salarysys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kiljaeden.salarysys.pojo.Dept;
import com.kiljaeden.salarysys.service.DeptService;
import com.kiljaeden.salarysys.mapper.DeptMapper;
import org.springframework.stereotype.Service;

/**
* @author 会飞的鱼
* @description 针对表【dept】的数据库操作Service实现
* @createDate 2023-06-28 17:44:16
*/
@Service
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept>
    implements DeptService{

}




