package top.codermhc.drugmanager.base.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;

/**
 * 病例表
 *
 * @author Ye Minghui
 */
@Data
@TableName("medical_case")
public class MedicalCase implements Serializable {

    /**
     * 病例id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 病人id
     */
    private Long patientId;

    /**
     * 病人年龄
     */
    private Integer age;

    /**
     * 病情描述
     */
    private String describe;

}
