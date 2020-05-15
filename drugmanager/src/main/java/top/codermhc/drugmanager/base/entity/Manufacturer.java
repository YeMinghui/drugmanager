package top.codermhc.drugmanager.base.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;

/**
 * 生产企业
 *
 * @author Ye Minghui
 */
@Data
@TableName("manufacturer")
public class Manufacturer implements Serializable {

    /**
     * 生产企业id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 企业名称
     */
    private String name;

    /**
     * 生产地址
     */
    private String address;

    /**
     * 邮编
     */
    private String postcode;

    /**
     * 电话号码
     */
    private String phone;

}
