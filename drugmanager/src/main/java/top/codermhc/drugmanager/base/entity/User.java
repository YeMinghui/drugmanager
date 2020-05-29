package top.codermhc.drugmanager.base.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 职工
 *
 * @author Ye Minghui
 */
@Data
@TableName("user")
public class User implements Serializable {

    /**
     * 职工id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 身份证号
     */
    private String identity;

    /**
     * 工号
     */
    private String workId;

    /**
     * 职称
     */
    private String title;

    /**
     * 科室id
     */
    private Long departmentId;

    /**
     * 电子邮箱
     */
    private String email;

    /**
     * 电话号码
     */
    private String phone;

    /**
     * 用户状态
     */
    private Integer status;

    /**
     * 用户所属角色
     */
    private Integer roleId;

    /**
     * 上次登录时间
     */
    private LocalDateTime lastLoginTime;


}
