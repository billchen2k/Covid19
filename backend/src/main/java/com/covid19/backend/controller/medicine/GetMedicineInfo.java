package com.covid19.backend.controller.medicine;

import com.covid19.backend.controller.BaseController;
import com.covid19.backend.model.Result;
import com.covid19.backend.model.Medicine;
import com.covid19.backend.service.medicine.GetMedicineInfoService;
import com.github.pagehelper.*;
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

/**
 * 获取药品信息的控制器
 **/
@RestController
@Api(tags = "药品控制器", value = "和药品有关的控制器")
public class GetMedicineInfo {
    @Autowired
    public GetMedicineInfoService getMedicineInfoService;

    @PostMapping("/medicine/getMedicineInfoByID")
    @ApiOperation(value = "根据药品ID获取药品信息", notes = "可以根据药品ID获取药品信息")
    @ApiImplicitParam(name = "medicine_id",value = "药品ID")
    public Result<Medicine> getMedicineInfoByID(
            @RequestParam(value = "medicine_id") long medicine_id
    )
    {
        Medicine medicine = getMedicineInfoService.getMedicineInfoByID(medicine_id);
        if(medicine == null) return Result.error(2012,"不存在该药品");
        return Result.ok(medicine);
    }

    @PostMapping("/medicine/getMedicineInfo")
    @ApiOperation(value = "根据药品除ID外属性获取药品信息", notes = "可以根据药品除ID外属性获取药品信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name",value = "药品名称"),
            @ApiImplicitParam(name = "manufacturer",value = "生产厂家"),
            @ApiImplicitParam(name = "introduction",value = "详细介绍"),
            @ApiImplicitParam(name = "type",value = "类型")
    })
    public Result<ArrayList<Medicine>> getMedicineInfo(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "manufacturer",required = false)String manufacturer,
            @RequestParam(value = "introduction",required = false)String introduction,
            @RequestParam(value = "type",required = false)String type,
            @RequestParam Integer page, // 分页
            @RequestParam Integer size
    )
    {
        Page<Medicine> pageInfo = PageHelper.startPage(page, size);
        ArrayList<Medicine> list = getMedicineInfoService.getMedicineInfo(
                name,
                manufacturer,
                introduction,
                type);
        if (list == null) return null;
        return Result.pagedOk(pageInfo);
    }
}
