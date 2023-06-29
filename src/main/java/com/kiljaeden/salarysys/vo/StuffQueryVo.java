package com.kiljaeden.salarysys.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author: Kil'jaeden
 * @Email: pure3306@163.com
 * @Date: 2023/6/28 20:52
 * @Description:
 */
@Data
public class StuffQueryVo {
    private String name;

    /*最低工资*/
    private BigDecimal minResultSalary;

    /*最高工资*/
    private BigDecimal maxResultSalary;

    /*部门*/
    private Integer did;
}
