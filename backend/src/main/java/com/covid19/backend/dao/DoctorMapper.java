package com.covid19.backend.dao;

import com.covid19.backend.model.Doctor;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import java.util.ArrayList;

// MyBatis
@Mapper
@Component
public interface DoctorMapper {
    @Select("SELECT * from doctor where doctor_id=#{doctor_id}")
    public Doctor selectDoctorByID(@Param("doctor_id") long doctor_id);

    @Select("SELECT * from doctor where " +
            "name like #{name} " +
            "and gender like #{gender} " +
            "and birthday like #{birthday} " +
            "and department like #{department} " +
            "and hospital_id like #{hospital_id}")
    public ArrayList<Doctor> selectDoctor(
            @Param("name") String name,
            @Param("gender") String gender,
            @Param("birthday") String birthday,
            @Param("department") String department,
            @Param("hospital_id") String hospital_id
    );

    @Insert("insert into doctor(" +
            "name, " +
            "gender, " +
            "birthday, " +
            "department, " +
            "hospital_id) " +
            "values (" +
            "#{name}, " +
            "#{gender}, " +
            "#{birthday}, " +
            "#{department}, " +
            "#{hospital_id})"
    )
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "doctor_id", before = false, resultType = long.class)
    long insertDoctor(Doctor doctor);

    @Update("Update doctor set " +
            "name=#{name}, " +
            "gender=#{gender}, " +
            "birthday=#{birthday}, " +
            "department=#{department}, " +
            "hospital_id=#{hospital_id} " +
            "where doctor_id=#{doctor_id}")
    void updateDoctorInfo(Doctor doctor);

    @Delete("delete from doctor where doctor_id = #{doctor_id}")
    void deleteDoctorByID(@Param("doctor_id") long doctor_id);
}
