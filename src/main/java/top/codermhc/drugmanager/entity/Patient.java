package top.codermhc.drugmanager.entity;

import javax.persistence.*;

/*
 * @author Ye Minghui
 */
@Table(name = "patient")
public class Patient {
    /**
     * 病人id
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
    @Column(name = "identify")
    private String identify;

    /**
     * 获取病人id
     *
     * @return id - 病人id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置病人id
     *
     * @param id 病人id
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
}