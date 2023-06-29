package com.kiljaeden.salarysys.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kiljaeden.salarysys.common.R;
import com.kiljaeden.salarysys.pojo.Dept;
import com.kiljaeden.salarysys.pojo.Stuff;
import com.kiljaeden.salarysys.pojo.StuffAndDept;
import com.kiljaeden.salarysys.service.DeptService;
import com.kiljaeden.salarysys.service.StuffAndDeptService;
import com.kiljaeden.salarysys.service.StuffService;
import com.kiljaeden.salarysys.vo.StuffQueryVo;
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
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @Author: Kil'jaeden
 * @Email: pure3306@163.com
 * @Date: 2023/6/28 17:51
 * @Description:
 */
@RestController
@RequestMapping("/dept")
public class DeptController {
    @Autowired
    private DeptService deptService;
    @Autowired
    private StuffService stuffService;
    @Autowired
    private StuffAndDeptService stuffAndDeptService;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public Object retrieveData(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public void storeData(String key, Object data) {
        redisTemplate.opsForValue().set(key, data);
    }

    @PostMapping("/page/{current}/{limit}")
    public R pageInfo(@PathVariable Long current,
                      @PathVariable Long limit) {

        Page<Dept> page = new Page<>(current, limit);
        Page<Dept> deptPage = deptService.page(page);

        return R.ok().data(deptPage);
    }

    @DeleteMapping("/delete/{id}")
    public R deleteById(@PathVariable Integer id) {
        LambdaQueryWrapper<Stuff> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Stuff::getDeptId, id);
        List<Stuff> list = stuffService.list(queryWrapper);

        if (list.size() > 0) return R.error("该部门还有员工，不能删除！");

        boolean result = deptService.removeById(id);
        if (result) return R.ok();

        redisTemplate.delete("redis-dataAnalyse-stuffCount");
        redisTemplate.delete("redis-dataAnalyse-avgSal");

        return R.error("删除失败");
    }

    @PutMapping("/add")
    public R addOne(@RequestBody Dept deptParam) {
        if (Objects.isNull(deptParam)) return R.error("提交表单不能为空");
        deptService.save(deptParam);
        return R.ok();
    }

    @GetMapping("/getOne/{id}")
    public R getOneById(@PathVariable Integer id) {
        Dept dept = deptService.getById(id);
        return R.ok(dept);
    }

    @PostMapping("/update")
    public R update(@RequestBody Dept deptParam) {
        deptParam.setUpdateTime(new Date());
        deptService.updateById(deptParam);
        redisTemplate.delete("redis-dataAnalyse-stuffCount");
        redisTemplate.delete("redis-dataAnalyse-avgSal");
        return R.ok();
    }

    @DeleteMapping("/deleteMany")
    public R deleteMany(@RequestBody List<Integer> list) {
        boolean result = deptService.removeBatchByIds(list);
        if (result) {
            redisTemplate.delete("redis-dataAnalyse-stuffCount");
            redisTemplate.delete("redis-dataAnalyse-avgSal");
            return R.ok();
        }
        return R.error();
    }

    @GetMapping("/findAll")
    public R findAll() {
        return R.ok(deptService.list());
    }

}
