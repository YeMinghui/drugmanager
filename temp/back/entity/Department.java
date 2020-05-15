package top.codermhc.drugmanager.back.entity;

import javax.persistence.*;

/**
 * @author Ye Minghui
 */
@Table(name = "department")
public class Department {
    /**
     * 科室id
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 科室名
     */
    @Column(name = "name")
    private String name;

    /**
     * 科室地址
     */
    @Column(name = "address")
    private String address;

    /**
     * 科室电话
     */
    @Column(name = "phone")
    private String phone;

    /**
     * 常用药类型
     */
    @Column(name = "drugs")
    private String drugs;

    /**
     * 获取科室id
     *
     * @return id - 科室id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置科室id
     *
     * @param id 科室id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取科室名
     *
     * @return name - 科室名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置科室名
     *
     * @param name 科室名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取科室地址
     *
     * @return address - 科室地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置科室地址
     *
     * @param address 科室地址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取科室电话
     *
     * @return phone - 科室电话
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置科室电话
     *
     * @param phone 科室电话
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取常用药类型
     *
     * @return drugs - 常用药类型
     */
    public String getDrugs() {
        return drugs;
    }

    /**
     * 设置常用药类型
     *
     * @param drugs 常用药类型
     */
    public void setDrugs(String drugs) {
        this.drugs = drugs;
    }
}