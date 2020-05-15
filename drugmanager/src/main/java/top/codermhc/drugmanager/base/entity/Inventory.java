package top.codermhc.drugmanager.base.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;

/**
 * 药品库存表
 *
 * @author Ye Minghui
 */
@Data
@TableName("inventory")
public class Inventory implements Serializable {

    /**
     * 药品库存id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 药品id
     */
    private Long drugId;

    /**
     * 产品批号
     */
    private String serialNumber;

    /**
     * 生产日期
     */
    private LocalDate productionDate;

    /**
     * 有效期至
     */
    private LocalDate expDate;

    /**
     * 药品单价
     */
    private Float price;

    /**
     * 存量
     */
    private Integer amount;

}
