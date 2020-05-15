package top.codermhc.drugmanager.base.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;

/**
 * 库存变动表
 *
 * @author Ye Minghui
 */
@Data
@TableName("inventory_change")
public class InventoryChange implements Serializable {

    /**
     * 库存变动id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 药品库存id
     */
    private Long inventoryId;

    /**
     * 操作员id
     */
    private Long operatorId;

    /**
     * 操作时间
     */
    private LocalDateTime operateTime;

    /**
     * 操作方式
     */
    private Integer operateMethod;

    /**
     * 操作明细id
     */
    private Long operateDetailId;

    /**
     * 存量变动值
     */
    private Integer amountDelta;

}
