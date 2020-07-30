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
            "doctor_name like #{doctor_name} " +
            "and doctor_gender like concat('%',#{doctor_gender},'%') " +
            "and doctor_birthday like concat('%',#{doctor_birthday},'%') " +
            "and department like concat('%',#{department},'%') " +
            "and hospital_id like concat('%',#{hospital_id},'%')")
    public ArrayList<Doctor> selectDoctor(
            @Param("doctor_name") String name,
            @Param("doctor_gender") String gender,
            @Param("doctor_birthday") String birthday,
            @Param("department") String department,
            @Param("hospital_id") String hospital_id
    );

    @Insert("insert into doctor(" +
            "doctor_name, " +
            "doctor_gender, " +
            "doctor_birthday, " +
            "department, " +
            "hospital_id) " +
            "values (" +
            "#{doctor_name}, " +
            "#{doctor_gender}, " +
            "#{doctor_birthday}, " +
            "#{department}, " +
            "#{hospital_id})"
    )
    @SelectKey(statement = "SELECT LAST_INSERT_ID() as doctor_id", keyProperty = "doctor_id", before = false, resultType = long.class)
    long insertDoctor(Doctor doctor);

    @Update("Update doctor set " +
            "doctor_name=#{doctor_name}, " +
            "doctor_gender=#{doctor_gender}, " +
            "doctor_birthday=#{doctor_birthday}, " +
            "department=#{department}, " +
            "hospital_id=#{hospital_id} " +
            "where doctor_id=#{doctor_id}")
    void updateDoctorInfo(Doctor doctor);

    @Delete("delete from doctor where doctor_id = #{doctor_id}")
    void deleteDoctorByID(@Param("doctor_id") long doctor_id);
}
