package com.covid19.backend.controller.image;

import com.covid19.backend.controller.BaseController;
import com.covid19.backend.model.Result;
import com.covid19.backend.model.Image;
import com.covid19.backend.service.image.DeleteImageService;
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
public class DeleteImage {
    @Autowired
    public DeleteImageService deleteImageService;
    @Autowired
    public GetImageInfoService getImageInfoService;

    @PostMapping("/image/deleteImageByID")
    @ApiOperation(value = "删除图像信息",notes = "可以删除图像的信息")
    @ApiImplicitParam(name = "image_id",value = "图像ID")
    public Result deleteImageByID(
            @RequestParam(value = "image_id") long image_id,
            HttpServletRequest request)
    {
        if(deleteImageService.checkCurrentUserInfo(request) == -1) return Result.error(Result.CODE_UNAUTHORIZED, "账号信息错误。");
        Image image = getImageInfoService.getImageInfoByID(image_id);
        if(image == null) return Result.error(2012,"不存在该图像");
        deleteImageService.deleteImageByID(image_id);
        return Result.ok();
    }
}
