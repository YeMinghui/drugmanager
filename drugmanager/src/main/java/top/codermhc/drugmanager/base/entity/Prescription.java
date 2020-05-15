package top.codermhc.drugmanager.base.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;

/**
 * 处方
 *
 * @author Ye Minghui
 */
@Data
@TableName("prescription")
public class Prescription implements Serializable {

    /**
     * 处方id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 病例id
     */
    private Long medicalCaseId;

    /**
     * 病人id
     */
    private Long patientId;

}
