package com.kiljaeden.salarysys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kiljaeden.salarysys.pojo.Administrators;
import com.kiljaeden.salarysys.service.AdministratorsService;
import com.kiljaeden.salarysys.mapper.AdministratorsMapper;
import org.springframework.stereotype.Service;

/**
* @author 会飞的鱼
* @description 针对表【administrators】的数据库操作Service实现
* @createDate 2023-06-28 17:44:34
*/
@Service
public class AdministratorsServiceImpl extends ServiceImpl<AdministratorsMapper, Administrators>
    implements AdministratorsService{

}




