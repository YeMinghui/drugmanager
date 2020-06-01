package top.codermhc.drugmanager.base.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;

/**
 * 处方信息表
 *
 * @author Ye Minghui
 */
@Data
@TableName("prescription_detail")
public class PrescriptionDetail implements Serializable {

    /**
     * 处方信息id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 处方id
     */
    private Long prescriptionId;

    /**
     * 药品id
     */
    private Long drugId;

    /**
     * 剂量
     */
    private Integer dose;

    /**
     * 次数
     */
    private Integer times;

}
