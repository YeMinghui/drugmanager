package top.codermhc.drugmanager.entity;

import javax.persistence.*;

/*
 * @author Ye Minghui
 */
@Table(name = "supplier")
public class Supplier {
    /**
     * 供应商id
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 供应商名称
     */
    @Column(name = "name")
    private String name;

    /**
     * 供应商地址
     */
    @Column(name = "address")
    private String address;

    /**
     * 供应商电话
     */
    @Column(name = "phone")
    private String phone;

    /**
     * 获取供应商id
     *
     * @return id - 供应商id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置供应商id
     *
     * @param id 供应商id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取供应商名称
     *
     * @return name - 供应商名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置供应商名称
     *
     * @param name 供应商名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取供应商地址
     *
     * @return address - 供应商地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置供应商地址
     *
     * @param address 供应商地址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取供应商电话
     *
     * @return phone - 供应商电话
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置供应商电话
     *
     * @param phone 供应商电话
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }
}