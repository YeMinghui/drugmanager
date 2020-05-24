package top.codermhc.drugmanager.base.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * 角色表
 *
 * @author Ye Minghui
 */
@Data
@TableName("role")
public class Role implements Serializable {

    /**
     * 角色id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 角色名
     */
    @NotEmpty
    private String name;

    /**
     * 当地用语名
     */
    @NotEmpty
    private String localeName;

    /**
     * 权限
     */
    @NotEmpty
    private String perms;

}
