package top.codermhc.drugmanager.back.entity;

import java.util.Date;
import javax.persistence.*;

/**
 * @author Ye Minghui
 */
@Table(name = "inventory_change")
public class InventoryChange {
    /**
     * 库存变动id
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 药品库存id
     */
    @Column(name = "inventory_id")
    private Long inventoryId;

    /**
     * 操作员id
     */
    @Column(name = "operator_id")
    private Long operatorId;

    /**
     * 操作时间
     */
    @Column(name = "operate_time")
    private Date operateTime;

    /**
     * 操作方式
     */
    @Column(name = "operate_method")
    private Integer operateMethod;

    /**
     * 操作明细id
     */
    @Column(name = "operate_detail_id")
    private Long operateDetailId;

    /**
     * 存量变动值
     */
    @Column(name = "amount_delta")
    private Integer amountDelta;

    /**
     * 获取库存变动id
     *
     * @return id - 库存变动id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置库存变动id
     *
     * @param id 库存变动id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取药品库存id
     *
     * @return inventory_id - 药品库存id
     */
    public Long getInventoryId() {
        return inventoryId;
    }

    /**
     * 设置药品库存id
     *
     * @param inventoryId 药品库存id
     */
    public void setInventoryId(Long inventoryId) {
        this.inventoryId = inventoryId;
    }

    /**
     * 获取操作员id
     *
     * @return operator_id - 操作员id
     */
    public Long getOperatorId() {
        return operatorId;
    }

    /**
     * 设置操作员id
     *
     * @param operatorId 操作员id
     */
    public void setOperatorId(Long operatorId) {
        this.operatorId = operatorId;
    }

    /**
     * 获取操作时间
     *
     * @return operate_time - 操作时间
     */
    public Date getOperateTime() {
        return operateTime;
    }

    /**
     * 设置操作时间
     *
     * @param operateTime 操作时间
     */
    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }

    /**
     * 获取操作方式
     *
     * @return operate_method - 操作方式
     */
    public Integer getOperateMethod() {
        return operateMethod;
    }

    /**
     * 设置操作方式
     *
     * @param operateMethod 操作方式
     */
    public void setOperateMethod(Integer operateMethod) {
        this.operateMethod = operateMethod;
    }

    /**
     * 获取操作明细id
     *
     * @return operate_detail_id - 操作明细id
     */
    public Long getOperateDetailId() {
        return operateDetailId;
    }

    /**
     * 设置操作明细id
     *
     * @param operateDetailId 操作明细id
     */
    public void setOperateDetailId(Long operateDetailId) {
        this.operateDetailId = operateDetailId;
    }

    /**
     * 获取存量变动值
     *
     * @return amount_delta - 存量变动值
     */
    public Integer getAmountDelta() {
        return amountDelta;
    }

    /**
     * 设置存量变动值
     *
     * @param amountDelta 存量变动值
     */
    public void setAmountDelta(Integer amountDelta) {
        this.amountDelta = amountDelta;
    }
}