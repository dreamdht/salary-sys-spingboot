package com.kiljaeden.salarysys.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName stuff
 */
@TableName(value ="stuff")
@Data
public class Stuff implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    private String username;

    /**
     * 
     */
    private String password;

    /**
     * 
     */
    private String name;

    /**
     * 
     */
    private String gender;

    /**
     * 
     */
    private String phone;

    /**
     * 
     */
    private String location;

    /**
     * 
     */
    private String headImg;

    /**
     * 基础工资
     */
    private BigDecimal basicSalary;

    /**
     * 补助费
     */
    private BigDecimal allowance;

    /**
     * 罚款
     */
    private BigDecimal fine;

    /**
     * 六险一金
     */
    private BigDecimal insurance;

    /**
     * 实际工资
     */
    private BigDecimal resultSalary;

    /**
     * 实际工资
     */
    private Integer deptId;

    /**
     * 
     */
    private Date createTime;

    /**
     * 
     */
    private Date updateTime;

    /**
     * 
     */
    @TableLogic
    private Integer isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}