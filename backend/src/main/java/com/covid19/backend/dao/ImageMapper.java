package com.covid19.backend.dao;

import com.covid19.backend.model.Image;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import java.util.ArrayList;

// MyBatis
@Mapper
@Component
public interface ImageMapper {
    @Select("SELECT * from image where image_id=#{image_id}")
    public Image selectImageByID(@Param("image_id") long image_id);

    @Select("SELECT * from image where " +
            "patient_id like concat('%',#{patient_id},'%') " +
            "and scanning_doctor_id like concat('%',#{scanning_doctor_id},'%') " +
            "and image_picture like concat('%',#{image_picture},'%') " +
            "and conclusion like concat('%',#{conclusion},'%') " +
            "and conclusion_doctor_id like concat('%',#{conclusion_doctor_id},'%')")
    public ArrayList<Image> selectImage(Image image);

    @Insert("insert into image(" +
            "patient_id, " +
            "scanning_doctor_id, " +
            "image_picture, " +
            "conclusion, " +
            "conclusion_doctor_id) " +
            "values (" +
            "#{patient_id}, " +
            "#{scanning_doctor_id}, " +
            "#{image_picture}, " +
            "#{conclusion}, " +
            "#{conclusion_doctor_id})"
    )
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "image_id", before = false, resultType = long.class)
    long insertImage(Image image);

    @Update("Update image set " +
            "patient_id=#{patient_id}, " +
            "scanning_doctor_id=#{scanning_doctor_id}, " +
            "image_picture=#{image_picture}, " +
            "conclusion=#{conclusion}, " +
            "conclusion_doctor_id=#{conclusion_doctor_id} " +
            "where hospital_id=#{hospital_id}")
    void updateImage(Image image);

    @Delete("delete from image where image_id = #{image_id}")
    void deleteImageByID(@Param("image_id") long image_id);
}
