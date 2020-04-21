package top.codermhc.drugmanager.entity;

import javax.persistence.*;

@Table(name = "medical_case")
public class MedicalCase {
    /**
     * 病例id
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 病人id
     */
    @Column(name = "patient_id")
    private Long patientId;

    /**
     * 病人年龄
     */
    @Column(name = "age")
    private Integer age;

    /**
     * 病情描述
     */
    @Column(name = "describe")
    private String describe;

    /**
     * 获取病例id
     *
     * @return id - 病例id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置病例id
     *
     * @param id 病例id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取病人id
     *
     * @return patient_id - 病人id
     */
    public Long getPatientId() {
        return patientId;
    }

    /**
     * 设置病人id
     *
     * @param patientId 病人id
     */
    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    /**
     * 获取病人年龄
     *
     * @return age - 病人年龄
     */
    public Integer getAge() {
        return age;
    }

    /**
     * 设置病人年龄
     *
     * @param age 病人年龄
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * 获取病情描述
     *
     * @return describe - 病情描述
     */
    public String getDescribe() {
        return describe;
    }

    /**
     * 设置病情描述
     *
     * @param describe 病情描述
     */
    public void setDescribe(String describe) {
        this.describe = describe;
    }
}