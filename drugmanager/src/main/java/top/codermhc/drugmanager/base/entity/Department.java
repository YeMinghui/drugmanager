package top.codermhc.drugmanager.base.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;

/**
 * 科室
 *
 * @author Ye Minghui
 */
@Data
@TableName("department")
public class Department implements Serializable {

    /**
     * 科室id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 科室名
     */
    private String name;

    /**
     * 科室地址
     */
    private String address;

    /**
     * 科室电话
     */
    private String phone;

    /**
     * 常用药类型
     */
    private String drugs;

}
