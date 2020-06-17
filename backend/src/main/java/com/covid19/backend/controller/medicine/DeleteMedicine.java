package com.covid19.backend.controller.medicine;

import com.covid19.backend.controller.BaseController;
import com.covid19.backend.model.Result;
import com.covid19.backend.model.Medicine;
import com.covid19.backend.service.medicine.DeleteMedicineService;
import com.covid19.backend.service.medicine.GetMedicineInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;


@RestController
@Api(tags = "药品控制器", value = "和药品有关的控制器")
public class DeleteMedicine {
    @Autowired
    public DeleteMedicineService deleteMedicineService;
    @Autowired
    public GetMedicineInfoService getMedicineInfoService;

    @PostMapping("/medicine/deleteMedicineByID")
    @ApiOperation(value = "删除药品信息",notes = "可以删除药品的信息")
    @ApiImplicitParam(name = "medicine_id",value = "药品ID")
    public Result deleteDoctorByID(
            @RequestParam(value = "medicine_id") long medicine_id,
            HttpServletRequest request)
    {
        if(deleteMedicineService.checkCurrentUserInfo(request) == -1) return Result.error(Result.CODE_UNAUTHORIZED, "账号信息错误。");
        Medicine medicine = getMedicineInfoService.getMedicineInfoByID(medicine_id);
        if(medicine == null) return Result.error(2012,"不存在该药品");
        deleteMedicineService.deleteMedicineByID(medicine_id);
        return Result.ok();
    }
}
