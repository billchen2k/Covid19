package com.covid19.backend.dao;

import com.covid19.backend.model.Medicine;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import java.util.ArrayList;

// MyBatis
@Mapper
@Component
public interface MedicineMapper {
    @Select("SELECT * from medicine where medicine_id=#{medicine_id}")
    public Medicine selectMedicineByID(@Param("medicine_id") long medicine_id);

    @Select("SELECT * from medicine where " +
            "name like concat('%',#{name},'%') " +
            "and manufacturer like concat('%',#{manufacturer},'%') " +
            "and introduction like concat('%',#{introduction},'%') " +
            "and type like concat('%',#{type},'%')")
    public ArrayList<Medicine> selectMedicine(
            @Param("name") String name,
            @Param("manufacturer") String manufacturer,
            @Param("introduction") String introduction,
            @Param("type") String type
    );

    @Insert("insert into medicine(" +
            "name, " +
            "manufacturer, " +
            "introduction, " +
            "type) " +
            "values (" +
            "#{name}, " +
            "#{manufacturer}, " +
            "#{introduction}, " +
            "#{type})"
    )
    @SelectKey(statement = "SELECT LAST_INSERT_ID() as medicine_id", keyProperty = "medicine_id", before = false, resultType = long.class)
    long insertMedicine(Medicine doctor);

    @Update("Update medicine set " +
            "name=#{name}, " +
            "manufacturer=#{manufacturer}, " +
            "introduction=#{introduction}, " +
            "type=#{type} " +
            "where medicine_id=#{medicine_id}")
    void updateMedicineInfo(Medicine medicine);

    @Delete("delete from medicine where medicine_id = #{medicine_id}")
    void deleteMedicineByID(@Param("medicine_id") long medicine_id);
}
