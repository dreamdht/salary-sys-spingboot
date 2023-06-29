package com.kiljaeden.salarysys.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.kiljaeden.salarysys.common.MD5Util;
import com.kiljaeden.salarysys.common.R;
import com.kiljaeden.salarysys.pojo.Administrators;
import com.kiljaeden.salarysys.pojo.Stuff;
import com.kiljaeden.salarysys.service.AdministratorsService;
import com.kiljaeden.salarysys.service.StuffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @Author: Kil'jaeden
 * @Email: pure3306@163.com
 * @Date: 2023/6/28 17:50
 * @Description:
 */
@RestController
@RequestMapping("/admin")
public class AdministratorsController {
    @Autowired
    private AdministratorsService administratorsService;
    private Administrators administratorsMain;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public Object retrieveData(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public void storeData(String key, Object data) {
        redisTemplate.opsForValue().set(key, data);
    }

    @PostMapping("/login")
    public R stuffLogin(@RequestBody Administrators administratorsParam) {
        if (StringUtils.isEmpty(administratorsParam)
                || StringUtils.isEmpty(administratorsParam.getUsername())
                || StringUtils.isEmpty(administratorsParam.getPassword()))
            return R.error("请输入完整登录信息");

        LambdaQueryWrapper<Administrators> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Administrators::getUsername,administratorsParam.getUsername());
        lambdaQueryWrapper.eq(Administrators::getPassword, MD5Util.GetPasswordByMD5(administratorsParam.getPassword()));
        Administrators admin = administratorsService.getOne(lambdaQueryWrapper);

        if(Objects.isNull(admin)) return R.error("用户名或密码错误!");

        Map<String,Object> map = new HashMap<>();
        map.put("token","admin-token");
        administratorsMain = admin;
        return R.ok().data(map);
    }

    @GetMapping("/info")
    public R userInfo(){
        Map<String,Object> map = new HashMap<>();
        map.put("roles","administrators");
        map.put("introduction","小城故事真不错");
        map.put("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        map.put("name",administratorsMain.getName());
        return R.ok(map);
    }

    @PostMapping("/logout")
    public R logout(){
        administratorsMain = null;
        return R.ok();
    }

}
