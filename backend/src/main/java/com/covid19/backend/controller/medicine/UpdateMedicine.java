package com.covid19.backend.controller.medicine;

import com.covid19.backend.controller.BaseController;
import com.covid19.backend.model.Result;
import com.covid19.backend.model.Medicine;
import com.covid19.backend.service.medicine.GetMedicineInfoService;
import com.covid19.backend.service.medicine.UpdateMedicineService;
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

/**
 * 更新药品控制器
 */
@RestController
@Api(tags = "药品控制器", value = "和药品有关的控制器")
public class UpdateMedicine extends BaseController{
    @Autowired
    public UpdateMedicineService updateMedicineService;
    @Autowired
    public GetMedicineInfoService getMedicineInfoService;

    @PostMapping("/medicine/updateMedicine")
    @ApiOperation(value = "更新药品信息", notes = "可以更新药品信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name",value = "药品名称"),
            @ApiImplicitParam(name = "manufacturer",value = "生产厂家"),
            @ApiImplicitParam(name = "introduction",value = "详细介绍"),
            @ApiImplicitParam(name = "type",value = "类型")
    })
    public Result updateMedicine(
            @RequestParam(value = "medicine_id") long medicine_id,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "manufacturer",required = false)String manufacturer,
            @RequestParam(value = "introduction",required = false)String introduction,
            @RequestParam(value = "type",required = false)String type,
            HttpServletRequest request)
    {
        if(updateMedicineService.checkCurrentUserInfo(request) == -1)
            return Result.error(Result.CODE_UNAUTHORIZED, "账号信息错误。");
        Medicine medicine = getMedicineInfoService.getMedicineInfoByID(medicine_id);
        if(medicine == null) return Result.error(2012,"不存在该药品");

        Medicine new_medicine = updateMedicineService.updateMedicine(
                medicine_id,
                name,
                manufacturer,
                introduction,
                type);
        return Result.ok(medicine_id);
    }
}
