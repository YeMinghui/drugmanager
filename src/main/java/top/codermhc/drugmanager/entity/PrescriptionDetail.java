package top.codermhc.drugmanager.entity;

import javax.persistence.*;

/*
 * @author Ye Minghui
 */
@Table(name = "prescription_detail")
public class PrescriptionDetail {
    /**
     * 处方信息id
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 处方id
     */
    @Column(name = "prescription_id")
    private Long prescriptionId;

    /**
     * 药品id
     */
    @Column(name = "drug_id")
    private Long drugId;

    /**
     * 剂量
     */
    @Column(name = "dose")
    private String dose;

    /**
     * 获取处方信息id
     *
     * @return id - 处方信息id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置处方信息id
     *
     * @param id 处方信息id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取处方id
     *
     * @return prescription_id - 处方id
     */
    public Long getPrescriptionId() {
        return prescriptionId;
    }

    /**
     * 设置处方id
     *
     * @param prescriptionId 处方id
     */
    public void setPrescriptionId(Long prescriptionId) {
        this.prescriptionId = prescriptionId;
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
     * 获取剂量
     *
     * @return dose - 剂量
     */
    public String getDose() {
        return dose;
    }

    /**
     * 设置剂量
     *
     * @param dose 剂量
     */
    public void setDose(String dose) {
        this.dose = dose;
    }
}