package top.codermhc.drugmanager.base.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;

/**
 * 用户认证表
 *
 * @author Ye Minghui
 */
@Data
@TableName("user_authentication")
public class UserAuthentication implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 工号
     */
    private String workId;

    /**
     * 密码
     */
    private String password;

    /**
     * 盐
     */
    private String salt;

    /**
     * 用户状态
     */
    private Integer status;

    /**
     * 用户所属角色
     */
    private Integer roleId;

}
