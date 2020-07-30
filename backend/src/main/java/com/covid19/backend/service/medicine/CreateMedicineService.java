package com.covid19.backend.service.medicine;

import com.covid19.backend.model.Medicine;
import com.covid19.backend.service.BaseService;
import com.covid19.backend.utils.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Service
public class CreateMedicineService extends BaseService{
    /**
     * 检查用户是否登陆
     * @param request
     * @return
     */
    public int checkCurrentUserInfo(HttpServletRequest request)
    {
        Long uid = Authentication.getCurrentUid(request);
        if (uid == null || uid != 0) return -1;
        return 0;
    }

    /**
     * 提供创建药品的服务
     * @param name
     * @param manufacturer
     * @param introduction
     * @param type
     * @return
     */
    public Medicine createMedicine(
            String name,
            String manufacturer,
            String introduction,
            String type
    )
    {
        Medicine medicine = new Medicine();
        medicine.setMedicine_name(name).setManufacturer(manufacturer).setIntroduction(introduction).setType(type);
        medicineMapper.insertMedicine(medicine);
        return medicine;
    }
}

