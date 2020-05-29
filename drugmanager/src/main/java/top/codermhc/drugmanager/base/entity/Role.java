package top.codermhc.drugmanager.base.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
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
    private String name;

    /**
     * 当地用语名
     */
    private String localeName;

    /**
     * 权限
     */
    private String perms;

}
