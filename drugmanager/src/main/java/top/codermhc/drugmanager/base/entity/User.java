package top.codermhc.drugmanager.base.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
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
    @NotNull
    @Pattern(regexp = "^[1-9]\\d{5}(18|19|20)\\d{2}((0[1-9])|(1[0-2]))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$", message = "身份证号不合法")
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
    @Email
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
    @Min(1)
    private Integer roleId;

    /**
     * 上次登录时间
     */
    private LocalDateTime lastLoginTime;


}
