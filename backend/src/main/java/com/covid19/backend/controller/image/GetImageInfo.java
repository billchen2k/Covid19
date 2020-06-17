package com.covid19.backend.controller.image;

import com.covid19.backend.controller.BaseController;
import com.covid19.backend.model.Result;
import com.covid19.backend.model.Image;
import com.covid19.backend.service.image.GetImageInfoService;
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
@Api(tags = "肺部影像控制器", value = "和肺部影像有关的控制器")
public class GetImageInfo {
    @Autowired
    public GetImageInfoService getImageInfoService;

    @PostMapping("/image/getImageInfoByID")
    @ApiOperation(value = "根据图像ID获取图像信息", notes = "可以根据图像ID获取图像信息")
    @ApiImplicitParam(name = "image_id",value = "肺部成像ID")
    public Result<Image> getImageInfoByID(
            @RequestParam(value = "image_id") long image_id
    )
    {
        Image image = getImageInfoService.getImageInfoByID(image_id);
        if(image == null) return Result.error(2012,"不存在该图像");
        return Result.ok(image);
    }

    @PostMapping("/image/getImageInfo")
    @ApiOperation(value = "根据图像除ID外属性获取图像信息", notes = "可以根据图像除ID外属性获取图像信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "patient_id",value = "病人ID"),
            @ApiImplicitParam(name = "scanning_doctor_id",value = "扫描医生 ID"),
            @ApiImplicitParam(name = "image_picture",value = "肺部影像图片"),
            @ApiImplicitParam(name = "conclusion",value = "结论"),
            @ApiImplicitParam(name = "conclusion_doctor_id",value = "结论医生"),
    })
    public Result<ArrayList<Image>> getImageInfo(
            @RequestParam(value = "patient_id",required = false)String patient_id,
            @RequestParam(value = "scanning_doctor_id",required = false)String scanning_doctor_id,
            @RequestParam(value = "image_picture",required = false)String image_picture,
            @RequestParam(value = "conclusion",required = false)String conclusion,
            @RequestParam(value = "conclusion_doctor_id",required = false)String conclusion_doctor_id
    )
    {
        Image image = new Image();

        image.setPatient_id(patient_id);
        image.setScanning_doctor_id(scanning_doctor_id);
        image.setImage_picture(image_picture);
        image.setConclusion(conclusion);
        image.setConclusion_doctor_id(conclusion_doctor_id);

        ArrayList<Image> list = getImageInfoService.getImageInfo(image);
        if (list == null) return null;
        return Result.ok(list);
    }
}
