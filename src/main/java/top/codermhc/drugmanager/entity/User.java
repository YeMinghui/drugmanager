package top.codermhc.drugmanager.entity;

import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * @author Ye Minghui
 */
@Table(name = "user")
public class User {
    /**
     * 职工id
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 姓名
     */
    @Column(name = "name")
    private String name;

    /**
     * 身份证号
     */
    @NotNull
    @Pattern(regexp = "^[1-9]\\d{5}(18|19|20)\\d{2}((0[1-9])|(1[0-2]))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$", message = "身份证号不合法")
    @Column(name = "identify")
    private String identify;

    /**
     * 工号
     */
    @Column(name = "work_id")
    private String workId;

    /**
     * 职称
     */
    @Column(name = "title")
    private String title;

    /**
     * 科室id
     */
    @Column(name = "department_id")
    private Long departmentId;

    /**
     * 电子邮箱
     */
    @Email
    @Column(name = "email")
    private String email;

    /**
     * 电话号码
     */
    @Column(name = "phone")
    private String phone;

    /**
     * 获取职工id
     *
     * @return id - 职工id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置职工id
     *
     * @param id 职工id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取姓名
     *
     * @return name - 姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置姓名
     *
     * @param name 姓名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取身份证号
     *
     * @return identify - 身份证号
     */
    public String getIdentify() {
        return identify;
    }

    /**
     * 设置身份证号
     *
     * @param identify 身份证号
     */
    public void setIdentify(String identify) {
        this.identify = identify;
    }

    /**
     * 获取工号
     *
     * @return work_id - 工号
     */
    public String getWorkId() {
        return workId;
    }

    /**
     * 设置工号
     *
     * @param workId 工号
     */
    public void setWorkId(String workId) {
        this.workId = workId;
    }

    /**
     * 获取职称
     *
     * @return title - 职称
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置职称
     *
     * @param title 职称
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取科室id
     *
     * @return department_id - 科室id
     */
    public Long getDepartmentId() {
        return departmentId;
    }

    /**
     * 设置科室id
     *
     * @param departmentId 科室id
     */
    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    /**
     * 获取电子邮箱
     *
     * @return email - 电子邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置电子邮箱
     *
     * @param email 电子邮箱
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取电话号码
     *
     * @return phone - 电话号码
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置电话号码
     *
     * @param phone 电话号码
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }
}