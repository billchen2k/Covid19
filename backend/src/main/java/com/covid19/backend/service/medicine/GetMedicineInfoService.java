package com.covid19.backend.service.medicine;

import com.covid19.backend.model.Medicine;
import com.covid19.backend.service.BaseService;
import com.covid19.backend.utils.Authentication;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Array;
import java.util.ArrayList;

@Service
public class GetMedicineInfoService extends BaseService{
    /**
     * 根据药品ID获取药品信息
     */
    public Medicine getMedicineInfoByID(long medicine_id){
        return medicineMapper.selectMedicineByID(medicine_id);
    }

    /**
     * 根据药品其他属性获取药品信息
     */
    public ArrayList<Medicine> getMedicineInfo(
            String name,
            String manufacturer,
            String introduction,
            String type
    )
    {
        if(name == null) name = "%";
        if(manufacturer == null) manufacturer = "%";
        if(introduction == null) introduction = "%";
        if(type == null) type = "%";

        return medicineMapper.selectMedicine(
                name,
                manufacturer,
                introduction,
                type);
    }
}
