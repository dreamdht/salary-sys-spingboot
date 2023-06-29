package com.kiljaeden.salarysys.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName stuff_dept
 */
@TableName(value ="stuff_dept")
@Data
public class StuffAndDept implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    private Integer did;

    /**
     * 
     */
    private Integer sid;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}