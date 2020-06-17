package com.covid19.backend.service.image;

import com.covid19.backend.dao.ImageMapper;
import com.covid19.backend.model.Image;
import com.covid19.backend.service.BaseService;
import com.covid19.backend.utils.Authentication;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Array;
import java.util.ArrayList;

@Service
public class GetImageInfoService extends BaseService{

    public Image getImageInfoByID(long image_id)
    {
        return imageMapper.selectImageByID(image_id);
    }


    public ArrayList<Image> getImageInfo(Image image) {
        if(image.getPatient_id() == null) image.setPatient_id("%");
        if(image.getScanning_doctor_id() == null) image.setScanning_doctor_id("%");
        if(image.getImage_picture() == null) image.setImage_picture("%");
        if(image.getConclusion() == null) image.setConclusion("%");
        if(image.getConclusion_doctor_id() == null) image.setConclusion_doctor_id("%");

        return imageMapper.selectImage(image);
    }
}
