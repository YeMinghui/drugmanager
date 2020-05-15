package top.codermhc.drugmanager.base.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;

/**
 * 供应商
 *
 * @author Ye Minghui
 */
@Data
@TableName("supplier")
public class Supplier implements Serializable {

    /**
     * 供应商id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 供应商名称
     */
    private String name;

    /**
     * 供应商地址
     */
    private String address;

    /**
     * 供应商电话
     */
    private String phone;

}
