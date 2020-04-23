package top.codermhc.drugmanager.entity;

import javax.persistence.*;

/*
 * @author Ye Minghui
 */
@Table(name = "prescription")
public class Prescription {
    /**
     * 处方id
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 病例id
     */
    @Column(name = "medical_case_id")
    private Long medicalCaseId;

    /**
     * 病人id
     */
    @Column(name = "patient_id")
    private Long patientId;

    /**
     * 获取处方id
     *
     * @return id - 处方id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置处方id
     *
     * @param id 处方id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取病例id
     *
     * @return medical_case_id - 病例id
     */
    public Long getMedicalCaseId() {
        return medicalCaseId;
    }

    /**
     * 设置病例id
     *
     * @param medicalCaseId 病例id
     */
    public void setMedicalCaseId(Long medicalCaseId) {
        this.medicalCaseId = medicalCaseId;
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
}