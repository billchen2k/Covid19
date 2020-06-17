package com.covid19.backend.controller.medicine;

import com.covid19.backend.controller.BaseController;
import com.covid19.backend.model.Result;
import com.covid19.backend.model.Medicine;
import com.covid19.backend.service.medicine.CreateMedicineService;
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
 * 创建药品控制器
 */
@RestController
@Api(tags = "药品控制器", value = "和药品有关的控制器")
public class CreateMedicine extends BaseController{
    @Autowired
    public CreateMedicineService createMedicineService;

    @PostMapping("/medicine/createMedicine")
    @ApiOperation(value = "创建药品信息", notes = "可以创建药品信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name",value = "药品名称"),
            @ApiImplicitParam(name = "manufacturer",value = "生产厂家"),
            @ApiImplicitParam(name = "introduction",value = "详细介绍"),
            @ApiImplicitParam(name = "type",value = "类型")
    })
    public Result createMedicine(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "manufacturer",required = false)String manufacturer,
            @RequestParam(value = "introduction",required = false)String introduction,
            @RequestParam(value = "type",required = false)String type,
            HttpServletRequest request)
    {
        if(createMedicineService.checkCurrentUserInfo(request) == -1)
            return Result.error(Result.CODE_UNAUTHORIZED, "账号信息错误。");

        Medicine new_medicine = createMedicineService.createMedicine(
                name,
                manufacturer,
                introduction,
                type);
        return Result.ok(new_medicine);
    }
}
