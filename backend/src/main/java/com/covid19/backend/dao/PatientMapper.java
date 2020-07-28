package com.covid19.backend.dao;

import com.covid19.backend.model.Patient;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;

// MyBatis
@Mapper
@Component
public interface PatientMapper {

    @Select("SELECT * from patient where patient_id=#{patient_id}")
    public Patient selectPatientByID(@Param("patient_id") long patient_id);

    @Select("SELECT * from patient NATURAL JOIN (SELECT `name` AS hospital_name, hospital_id FROM hospital) AS T where patient_id=#{patient_id}")
    public HashMap<String, String> selectDetailedPatientByID(@Param("patient_id") long patient_id);

    @Select("SELECT * from patient where " +
            "name like concat('%',#{name},'%') " +
            "and gender like concat('%',#{gender},'%') " +
            "and birthday like concat('%',#{birthday},'%') " +
            "and onset_place like concat('%',#{onset_place},'%') " +
            "and onset_date like concat('%',#{onset_date},'%') " +
            "and confirm_date like concat('%',#{confirm_date},'%') " +
            "and status like concat('%',#{status},'%') " +
            "and doctor_id like concat('%',#{doctor_id},'%') " +
            "and hospital_id like concat('%',#{hospital_id},'%') " +
            "and is_doctor like concat('%',#{is_doctor},'%')"
    )
    public ArrayList<Patient> selectPatient(
            @Param("name") String name,
            @Param("gender") String gender,
            @Param("birthday") String birthday,
            @Param("onset_place") String onset_place,
            @Param("onset_date") String onset_date,
            @Param("confirm_date") String confirm_date,
            @Param("status") String status,
            @Param("doctor_id") String doctor_id,
            @Param("hospital_id") String hospital_id,
            @Param("is_doctor") String is_doctor
    );


    @Insert("insert into patient(" +
            "name, " +
            "gender, " +
            "birthday, " +
            "onset_place, " +
            "onset_date, " +
            "confirm_date, " +
            "status, " +
            "doctor_id, " +
            "hospital_id, " +
            "is_doctor)" +
            " values (" +
            "#{name}, " +
            "#{gender}, " +
            "#{birthday}, " +
            "#{onset_place}, " +
            "#{onset_date}, " +
            "#{confirm_date}, " +
            "#{status}, " +
            "#{doctor_id}, " +
            "#{hospital_id}, " +
            "#{is_doctor})"
    )
    @SelectKey(statement = "SELECT LAST_INSERT_ID() as patient_id", keyProperty = "patient_id", before = false, resultType = long.class)
    long insertPatient(Patient patient);

    @Update("Update patient set " +
            "name=#{name}, " +
            "gender=#{gender}, " +
            "birthday=#{birthday}, " +
            "onset_place=#{onset_place}, " +
            "onset_date=#{onset_date}, " +
            "confirm_date=#{confirm_date}, " +
            "status=#{status}, " +
            "doctor_id=#{doctor_id}, " +
            "hospital_id=#{hospital_id}, " +
            "is_doctor=#{is_doctor}" +
            " where patient_id=#{patient_id}")
    void updatePatientInfo(Patient patient);

    @Delete("delete from patient where patient_id = #{patient_id}")
    void deletePatientByID(@Param("patient_id") long patient_id);
}
