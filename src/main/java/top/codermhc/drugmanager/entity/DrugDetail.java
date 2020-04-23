package top.codermhc.drugmanager.entity;

import javax.persistence.*;

/*
 * @author Ye Minghui
 */
@Table(name = "drug_detail")
public class DrugDetail {
    /**
     * 药品id
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 药品名称
     */
    @Column(name = "drug_name")
    private String drugName;

    /**
     * 药品通用编码
     */
    @Column(name = "generic_id")
    private String genericId;

    /**
     * 成分
     */
    @Column(name = "ingredients")
    private String ingredients;

    /**
     * 性状
     */
    @Column(name = "description")
    private String description;

    /**
     * 适应症
     */
    @Column(name = "indications")
    private String indications;

    /**
     * 用法用量
     */
    @Column(name = "dosage_and_administration")
    private String dosageAndAdministration;

    /**
     * 禁忌
     */
    @Column(name = "contraindications")
    private String contraindications;

    /**
     * 注意事项
     */
    @Column(name = "precautions")
    private String precautions;

    /**
     * 不良反应
     */
    @Column(name = "adverse_reactions")
    private String adverseReactions;

    /**
     * 规格
     */
    @Column(name = "specifications")
    private String specifications;

    /**
     * 包装
     */
    @Column(name = "packages")
    private String packages;

    /**
     * 贮藏
     */
    @Column(name = "storage")
    private String storage;

    /**
     * 生产企业id
     */
    @Column(name = "manufacturer_id")
    private Long manufacturerId;

    /**
     * 批准文号
     */
    @Column(name = "approval_number")
    private String approvalNumber;

    /**
     * 医保类别
     */
    @Column(name = "medicare_classify")
    private String medicareClassify;

    /**
     * OTC类别
     */
    @Column(name = "otc_classify")
    private String otcClassify;

    /**
     * 说明书
     */
    @Column(name = "directions")
    private String directions;

    /**
     * 获取药品id
     *
     * @return id - 药品id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置药品id
     *
     * @param id 药品id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取药品名称
     *
     * @return drug_name - 药品名称
     */
    public String getDrugName() {
        return drugName;
    }

    /**
     * 设置药品名称
     *
     * @param drugName 药品名称
     */
    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    /**
     * 获取药品通用编码
     *
     * @return generic_id - 药品通用编码
     */
    public String getGenericId() {
        return genericId;
    }

    /**
     * 设置药品通用编码
     *
     * @param genericId 药品通用编码
     */
    public void setGenericId(String genericId) {
        this.genericId = genericId;
    }

    /**
     * 获取成分
     *
     * @return ingredients - 成分
     */
    public String getIngredients() {
        return ingredients;
    }

    /**
     * 设置成分
     *
     * @param ingredients 成分
     */
    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    /**
     * 获取性状
     *
     * @return description - 性状
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置性状
     *
     * @param description 性状
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 获取适应症
     *
     * @return indications - 适应症
     */
    public String getIndications() {
        return indications;
    }

    /**
     * 设置适应症
     *
     * @param indications 适应症
     */
    public void setIndications(String indications) {
        this.indications = indications;
    }

    /**
     * 获取用法用量
     *
     * @return dosage_and_administration - 用法用量
     */
    public String getDosageAndAdministration() {
        return dosageAndAdministration;
    }

    /**
     * 设置用法用量
     *
     * @param dosageAndAdministration 用法用量
     */
    public void setDosageAndAdministration(String dosageAndAdministration) {
        this.dosageAndAdministration = dosageAndAdministration;
    }

    /**
     * 获取禁忌
     *
     * @return contraindications - 禁忌
     */
    public String getContraindications() {
        return contraindications;
    }

    /**
     * 设置禁忌
     *
     * @param contraindications 禁忌
     */
    public void setContraindications(String contraindications) {
        this.contraindications = contraindications;
    }

    /**
     * 获取注意事项
     *
     * @return precautions - 注意事项
     */
    public String getPrecautions() {
        return precautions;
    }

    /**
     * 设置注意事项
     *
     * @param precautions 注意事项
     */
    public void setPrecautions(String precautions) {
        this.precautions = precautions;
    }

    /**
     * 获取不良反应
     *
     * @return adverse_reactions - 不良反应
     */
    public String getAdverseReactions() {
        return adverseReactions;
    }

    /**
     * 设置不良反应
     *
     * @param adverseReactions 不良反应
     */
    public void setAdverseReactions(String adverseReactions) {
        this.adverseReactions = adverseReactions;
    }

    /**
     * 获取规格
     *
     * @return specifications - 规格
     */
    public String getSpecifications() {
        return specifications;
    }

    /**
     * 设置规格
     *
     * @param specifications 规格
     */
    public void setSpecifications(String specifications) {
        this.specifications = specifications;
    }

    /**
     * 获取包装
     *
     * @return packages - 包装
     */
    public String getPackages() {
        return packages;
    }

    /**
     * 设置包装
     *
     * @param packages 包装
     */
    public void setPackages(String packages) {
        this.packages = packages;
    }

    /**
     * 获取贮藏
     *
     * @return storage - 贮藏
     */
    public String getStorage() {
        return storage;
    }

    /**
     * 设置贮藏
     *
     * @param storage 贮藏
     */
    public void setStorage(String storage) {
        this.storage = storage;
    }

    /**
     * 获取生产企业id
     *
     * @return manufacturer_id - 生产企业id
     */
    public Long getManufacturerId() {
        return manufacturerId;
    }

    /**
     * 设置生产企业id
     *
     * @param manufacturerId 生产企业id
     */
    public void setManufacturerId(Long manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    /**
     * 获取批准文号
     *
     * @return approval_number - 批准文号
     */
    public String getApprovalNumber() {
        return approvalNumber;
    }

    /**
     * 设置批准文号
     *
     * @param approvalNumber 批准文号
     */
    public void setApprovalNumber(String approvalNumber) {
        this.approvalNumber = approvalNumber;
    }

    /**
     * 获取医保类别
     *
     * @return medicare_classify - 医保类别
     */
    public String getMedicareClassify() {
        return medicareClassify;
    }

    /**
     * 设置医保类别
     *
     * @param medicareClassify 医保类别
     */
    public void setMedicareClassify(String medicareClassify) {
        this.medicareClassify = medicareClassify;
    }

    /**
     * 获取OTC类别
     *
     * @return otc_classify - OTC类别
     */
    public String getOtcClassify() {
        return otcClassify;
    }

    /**
     * 设置OTC类别
     *
     * @param otcClassify OTC类别
     */
    public void setOtcClassify(String otcClassify) {
        this.otcClassify = otcClassify;
    }

    /**
     * 获取说明书
     *
     * @return directions - 说明书
     */
    public String getDirections() {
        return directions;
    }

    /**
     * 设置说明书
     *
     * @param directions 说明书
     */
    public void setDirections(String directions) {
        this.directions = directions;
    }
}