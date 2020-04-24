package top.codermhc.drugmanager.entity;

import java.util.Date;
import javax.persistence.*;

/**
 * @author Ye Minghui
 */
@Table(name = "inventory")
public class Inventory {
    /**
     * 药品库存id
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 药品id
     */
    @Column(name = "drug_id")
    private Long drugId;

    /**
     * 产品批号
     */
    @Column(name = "serial_number")
    private String serialNumber;

    /**
     * 生产日期
     */
    @Column(name = "production_date")
    private Date productionDate;

    /**
     * 有效期至
     */
    @Column(name = "exp_date")
    private Date expDate;

    /**
     * 药品单价
     */
    @Column(name = "price")
    private Float price;

    /**
     * 存量
     */
    @Column(name = "amount")
    private Integer amount;

    /**
     * 获取药品库存id
     *
     * @return id - 药品库存id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置药品库存id
     *
     * @param id 药品库存id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取药品id
     *
     * @return drug_id - 药品id
     */
    public Long getDrugId() {
        return drugId;
    }

    /**
     * 设置药品id
     *
     * @param drugId 药品id
     */
    public void setDrugId(Long drugId) {
        this.drugId = drugId;
    }

    /**
     * 获取产品批号
     *
     * @return serial_number - 产品批号
     */
    public String getSerialNumber() {
        return serialNumber;
    }

    /**
     * 设置产品批号
     *
     * @param serialNumber 产品批号
     */
    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    /**
     * 获取生产日期
     *
     * @return production_date - 生产日期
     */
    public Date getProductionDate() {
        return productionDate;
    }

    /**
     * 设置生产日期
     *
     * @param productionDate 生产日期
     */
    public void setProductionDate(Date productionDate) {
        this.productionDate = productionDate;
    }

    /**
     * 获取有效期至
     *
     * @return exp_date - 有效期至
     */
    public Date getExpDate() {
        return expDate;
    }

    /**
     * 设置有效期至
     *
     * @param expDate 有效期至
     */
    public void setExpDate(Date expDate) {
        this.expDate = expDate;
    }

    /**
     * 获取药品单价
     *
     * @return price - 药品单价
     */
    public Float getPrice() {
        return price;
    }

    /**
     * 设置药品单价
     *
     * @param price 药品单价
     */
    public void setPrice(Float price) {
        this.price = price;
    }

    /**
     * 获取存量
     *
     * @return amount - 存量
     */
    public Integer getAmount() {
        return amount;
    }

    /**
     * 设置存量
     *
     * @param amount 存量
     */
    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}