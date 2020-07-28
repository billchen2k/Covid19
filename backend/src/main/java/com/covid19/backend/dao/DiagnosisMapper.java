package com.covid19.backend.dao;

import com.covid19.backend.model.Diagnosis;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import java.util.ArrayList;

// MyBatis
@Mapper
@Component
public interface DiagnosisMapper {
    @Select("SELECT * from diagnosis where diagnosis_id=#{diagnosis_id}")
    public Diagnosis selectDiagnosisByID(@Param("diagnosis_id") long diagnosis_id);

    @Select("SELECT * from diagnosis where " +
            "patient_id like concat('%',#{patient_id},'%') " +
            "and doctor_id like concat('%',#{doctor_id},'%') " +
            "and time like concat('%',#{time},'%') " +
            "and symptom like concat('%',#{symptom},'%') " +
            "and temperature like concat('%',#{temperature},'%') " +
            "and nucleic_acid = concat('%',#{nucleic_acid},'%')")
    public ArrayList<Diagnosis> selectDiagnosis(Diagnosis diagnosis);

    @Insert("insert into Diagnosis(" +
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

    @Update("Update Diagnosis set " +
            "patient_id=#{patient_id}, " +
            "doctor_id=#{doctor_id}, " +
            "time=#{time}, " +
            "symptom=#{symptom}, " +
            "temperature=#{temperature}, " +
            "nucleic_acid=#{nucleid_acid} " +
            "where diagnosis_id=#{diagnosis_id}")
    void updateDiagnosis(Diagnosis diagnosis);

    @Delete("delete from Diagnosis where diagnosis_id = #{diagnosis_id}")
    void deleteDiagnosisByID(@Param("diagnosis_id") long diagnosis_id);
}
