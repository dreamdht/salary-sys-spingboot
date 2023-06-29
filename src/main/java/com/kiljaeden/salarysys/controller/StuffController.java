package com.kiljaeden.salarysys.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kiljaeden.salarysys.common.MD5Util;
import com.kiljaeden.salarysys.common.R;
import com.kiljaeden.salarysys.pojo.Stuff;
import com.kiljaeden.salarysys.service.StuffService;
import com.kiljaeden.salarysys.vo.StuffQueryVo;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.jcajce.provider.digest.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

/**
 * @Author: Kil'jaeden
 * @Email: pure3306@163.com
 * @Date: 2023/6/28 17:51
 * @Description:
 */
@RestController
@Slf4j
@RequestMapping("/stuff")
public class StuffController {
    @Autowired
    private StuffService stuffService;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public Object retrieveData(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public void storeData(String key, Object data) {
        redisTemplate.opsForValue().set(key, data);
    }

    /*计算实发工资*/
    private static Stuff soluteResultSalary(Stuff stuffParam) {
        BigDecimal basicSalary = stuffParam.getBasicSalary();
        BigDecimal allowance = stuffParam.getAllowance();
        BigDecimal fine = stuffParam.getFine();
        BigDecimal insurance = stuffParam.getInsurance();
        stuffParam.setResultSalary(basicSalary.add(allowance).subtract(fine).subtract(insurance));
        return stuffParam;
    }

    @PostMapping("/page/{current}/{limit}")
    public R pageInfo(@PathVariable Long current,
                      @PathVariable Long limit,
                      @RequestBody StuffQueryVo stuffQueryVo) {

        Page<Stuff> page = new Page<>(current, limit);
        Page<Stuff> stuffPage;

        if (stuffQueryVo == null) {
            stuffPage = stuffService.page(page, null);
            return R.ok(stuffPage);
        }

        LambdaQueryWrapper<Stuff> queryWrapper = new LambdaQueryWrapper<>();

        if (!StringUtils.isEmpty(stuffQueryVo.getMinResultSalary())) {
            queryWrapper.ge(Stuff::getResultSalary, stuffQueryVo.getMinResultSalary());
        }
        if (!StringUtils.isEmpty(stuffQueryVo.getMaxResultSalary())) {
            queryWrapper.le(Stuff::getResultSalary, stuffQueryVo.getMaxResultSalary());
        }


        if (!StringUtils.isEmpty(stuffQueryVo.getName())) {
            queryWrapper.like(Stuff::getName, stuffQueryVo.getName());
        }

        if (!StringUtils.isEmpty(stuffQueryVo.getDid())) {
            queryWrapper.eq(Stuff::getDeptId, stuffQueryVo.getDid());
        }

        stuffPage = stuffService.page(page, queryWrapper);

        return R.ok(stuffPage);

    }

    @DeleteMapping("/delete/{id}")
    public R deleteById(@PathVariable Integer id) {
        boolean result = stuffService.removeById(id);
        if (result) {
            redisTemplate.delete("redis-dataAnalyse-stuffCount");
            redisTemplate.delete("redis-dataAnalyse-avgSal");
            return R.ok();
        }
        return R.error("删除失败");
    }

    @PutMapping("/add")
    public R addOne(@RequestBody Stuff stuffParam) {
        soluteResultSalary(stuffParam);
        stuffParam.setGender(stuffParam.getGender()=="1"?"男":"女");
        stuffParam.setPassword(MD5Util.GetPasswordByMD5(stuffParam.getPassword()));

        boolean save = stuffService.save(stuffParam);
        if (save) {
            redisTemplate.delete("redis-dataAnalyse-stuffCount");
            redisTemplate.delete("redis-dataAnalyse-avgSal");
            return R.ok();
        }

        return R.error("员工录入信息不完整!");
    }

    @GetMapping("/getOne/{id}")
    public R getOneById(@PathVariable Integer id) {
        Stuff stuff = stuffService.getById(id);
        return R.ok(stuff);
    }

    @PostMapping("/update")
    public R update(@RequestBody Stuff stuffParam) {
        soluteResultSalary(stuffParam);
        stuffService.updateById(stuffParam);
        redisTemplate.delete("redis-dataAnalyse-stuffCount");
        redisTemplate.delete("redis-dataAnalyse-avgSal");
        return R.ok();
    }

    @DeleteMapping("/deleteMany")
    public R deleteMany(@RequestBody List<Integer> list) {
        boolean result = stuffService.removeBatchByIds(list);
        if (result) {
            redisTemplate.delete("redis-dataAnalyse-stuffCount");
            redisTemplate.delete("redis-dataAnalyse-avgSal");
            return R.ok();
        }
        return R.error();
    }

    @GetMapping("/findAll")
    public R findAll() {
        return R.ok(stuffService.list());
    }

    @PostMapping("/login")
    public R login(@RequestBody Stuff stuffParam){
        log.info("{}",stuffParam);

        if(Objects.isNull(stuffParam) || StringUtils.isEmpty(stuffParam.getUsername())
            || StringUtils.isEmpty(stuffParam.getPassword())) return R.error().data("请输入完整登录信息");

        LambdaQueryWrapper<Stuff> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Stuff::getUsername,stuffParam.getUsername());
        queryWrapper.eq(Stuff::getPassword, MD5Util.GetPasswordByMD5(stuffParam.getPassword()));

        Stuff stuff = stuffService.getOne(queryWrapper);
        if(Objects.isNull(stuff)) return R.error("用户名或密码错误");

        stuff.setPassword(null);
        return R.ok().data(stuff);
    }

    @GetMapping("/showSalary")
    public R getSalaryInfo(@RequestBody Stuff stuffParam){
        Stuff stuff = stuffService.getById(stuffParam);
        return R.ok(stuff);
    }
}
