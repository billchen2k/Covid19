package com.covid19.backend.service.doctor;

import com.covid19.backend.model.Doctor;
import com.covid19.backend.service.BaseService;
import com.covid19.backend.utils.Authentication;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

@Service
public class GetDoctorInfoService extends BaseService{
    /**
     * 根据医生ID获取医生信息
     */
    public Doctor getDoctorInfoByID(long doctor_id){
        return doctorMapper.selectDoctorByID(doctor_id);
    }

    public HashMap<Object, Object> getDetailedDoctorInfoByID(long doctor_id){
        return doctorMapper.selectDoctorDetailByID(doctor_id);
    }


    /**
     * 根据医生其他属性获取医生信息
     */
    public ArrayList<HashMap<String, String>> getDoctorInfo(
            String name,
            String gender,
            String birthday,
            String department,
            String hospital_id
    )
    {
        if(name == null) name = "%";
        if(gender == null) gender = "%";
        if(birthday == null) birthday = "%";
        if(department == null) department = "%";
        if(hospital_id == null) hospital_id = "%";

        return doctorMapper.selectMultipleDetailedDoctor(
                name,
                gender,
                birthday,
                department,
                hospital_id);
    }
}
