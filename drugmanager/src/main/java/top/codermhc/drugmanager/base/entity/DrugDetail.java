package top.codermhc.drugmanager.base.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;

/**
 * 药品信息表
 *
 * @author Ye Minghui
 */
@Data
@TableName("drug_detail")
public class DrugDetail implements Serializable {

    /**
     * 药品id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 药品名称
     */
    private String drugName;

    /**
     * 药品通用编码
     */
    private String genericId;

    /**
     * 成分
     */
    private String ingredients;

    /**
     * 性状
     */
    private String description;

    /**
     * 适应症
     */
    private String indications;

    /**
     * 用法用量
     */
    private String dosageAndAdministration;

    /**
     * 禁忌
     */
    private String contraindications;

    /**
     * 注意事项
     */
    private String precautions;

    /**
     * 不良反应
     */
    private String adverseReactions;

    /**
     * 规格
     */
    private String specifications;

    /**
     * 包装
     */
    private String packages;

    /**
     * 贮藏
     */
    private String storage;

    /**
     * 说明书
     */
    private String directions;

    /**
     * 生产企业id
     */
    private Long manufacturerId;

    /**
     * 批准文号
     */
    private String approvalNumber;

    /**
     * 医保类别
     */
    private String medicareClassify;

    /**
     * OTC类别
     */
    private String otcClassify;

}
