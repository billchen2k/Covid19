package com.covid19.backend.dao;

import com.covid19.backend.model.Diagnosis;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

// MyBatis
@Mapper
@Component
public interface DiagnosisMapper {
    @Select("SELECT * from diagnosis where diagnosis_id=#{diagnosis_id}")
    public Diagnosis selectDiagnosisByID(@Param("diagnosis_id") long diagnosis_id);

    @Select("SELECT count(*) " +
            "from diagnosis")
    public Integer getNumber();

//
//    @Select("SELECT * from diagnosis where patient_id=#{pati_id}")
//    public ArrayList<Diagnosis> selectDiagnosisByPatientID(@Param("patinet_id") long patient_id);
//
    @Select("SELECT * from diagnosis where " +
            "patient_id = #{patient_id}" +
            "and doctor_id like #{doctor_id}" +
            "and time like concat('%',#{time},'%') " +
            "and temperature like concat('%',#{temperature},'%') " +
            "and symptom like concat('%',#{symptom},'%') " +
            "and nucleic_acid LIKE #{nucleic_acid}")
    public ArrayList<Diagnosis> selectDiagnosis(Diagnosis params);

    @Insert("insert into diagnosis(" +
            "patient_id, " +
            "doctor_id, " +
            "time, " +
            "symptom, " +
            "temperature, " +
            "nucleic_acid) " +
            "values (" +
            "#{patient_id}, " +
            "#{doctor_id}, " +
            "#{time}, " +
            "#{symptom}, " +
            "#{temperature}, " +
            "#{nucleic_acid})"
    )
    @SelectKey(statement = "SELECT LAST_INSERT_ID() as diagnosis_id", keyProperty = "diagnosis_id", before = false, resultType = long.class)
    long insertDiagnosis(Diagnosis diagnosis);

    @Update("Update diagnosis set " +
            "patient_id=#{patient_id}, " +
            "doctor_id=#{doctor_id}, " +
            "time=#{time}, " +
            "symptom=#{symptom}, " +
            "temperature=#{temperature}, " +
            "nucleic_acid=#{nucleid_acid} " +
            "where diagnosis_id=#{diagnosis_id}")
    void updateDiagnosis(Diagnosis diagnosis);

    @Delete("delete from diagnosis where diagnosis_id = #{diagnosis_id}")
    void deleteDiagnosisByID(@Param("diagnosis_id") long diagnosis_id);
}
