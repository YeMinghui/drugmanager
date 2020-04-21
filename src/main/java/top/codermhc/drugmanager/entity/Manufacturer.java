package top.codermhc.drugmanager.entity;

import javax.persistence.*;

@Table(name = "manufacturer")
public class Manufacturer {
    /**
     * 生产企业id
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 企业名称
     */
    @Column(name = "name")
    private String name;

    /**
     * 生产地址
     */
    @Column(name = "address")
    private String address;

    /**
     * 邮编
     */
    @Column(name = "postcode")
    private String postcode;

    /**
     * 电话号码
     */
    @Column(name = "phone")
    private String phone;

    /**
     * 获取生产企业id
     *
     * @return id - 生产企业id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置生产企业id
     *
     * @param id 生产企业id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取企业名称
     *
     * @return name - 企业名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置企业名称
     *
     * @param name 企业名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取生产地址
     *
     * @return address - 生产地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置生产地址
     *
     * @param address 生产地址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取邮编
     *
     * @return postcode - 邮编
     */
    public String getPostcode() {
        return postcode;
    }

    /**
     * 设置邮编
     *
     * @param postcode 邮编
     */
    public void setPostcode(String postcode) {
        this.postcode = postcode;
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