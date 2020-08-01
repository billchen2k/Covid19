package com.covid19.backend.service.hospital;

import com.covid19.backend.dao.HospitalMapper;
import com.covid19.backend.model.Hospital;
import com.covid19.backend.service.BaseService;
import com.covid19.backend.utils.Authentication;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Array;
import java.util.ArrayList;

@Service
public class GetHospitalInfoService extends BaseService{


    public Hospital geHospitalInfoByID(long hospital_id)
    {
        return hospitalMapper.selectHospitalByID(hospital_id);
    }


    public ArrayList<Hospital> getHospitalInfo(Hospital hospital)
    {
        if(hospital.getHospital_name()== null) hospital.setHospital_name("%");
        if(hospital.getAddress() == null) hospital.setAddress("%");
        return hospitalMapper.selectHospital(hospital);
    }
}
