package org.vision.service.admin.persistence.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.vision.service.admin.persistence.model.VisionClient;

@Mapper
public interface VisionClientMapper {
    @Delete({
            "delete from vision_client",
            "where id = #{id,jdbcType=CHAR}"
    })
    int deleteByPrimaryKey(String id);

    @Insert({
            "insert into vision_client (id, wx_client_id, ",
            "name, age, birthday, ",
            "phone_number, province, ",
            "city, county, detail_address, ",
            "gender, id_number)",
            "values (#{id,jdbcType=CHAR}, #{wxClientId,jdbcType=CHAR}, ",
            "#{name,jdbcType=VARCHAR}, #{age,jdbcType=INTEGER}, #{birthday,jdbcType=DATE}, ",
            "#{phoneNumber,jdbcType=VARCHAR}, #{province,jdbcType=VARCHAR}, ",
            "#{city,jdbcType=VARCHAR}, #{county,jdbcType=VARCHAR}, #{detailAddress,jdbcType=VARCHAR}, ",
            "#{gender,jdbcType=BIT}, #{idNumber,jdbcType=VARCHAR})"
    })
    int insert(VisionClient record);

    @InsertProvider(type = VisionClientSqlProvider.class, method = "insertSelective")
    int insertSelective(VisionClient record);

    @Select({
            "select",
            "id, wx_client_id, name, age, birthday, phone_number, province, city, county, ",
            "detail_address, gender, id_number",
            "from vision_client",
            "where id = #{id,jdbcType=CHAR}"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.CHAR, id = true),
            @Result(column = "wx_client_id", property = "wxClientId", jdbcType = JdbcType.CHAR),
            @Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
            @Result(column = "age", property = "age", jdbcType = JdbcType.INTEGER),
            @Result(column = "birthday", property = "birthday", jdbcType = JdbcType.DATE),
            @Result(column = "phone_number", property = "phoneNumber", jdbcType = JdbcType.VARCHAR),
            @Result(column = "province", property = "province", jdbcType = JdbcType.VARCHAR),
            @Result(column = "city", property = "city", jdbcType = JdbcType.VARCHAR),
            @Result(column = "county", property = "county", jdbcType = JdbcType.VARCHAR),
            @Result(column = "detail_address", property = "detailAddress", jdbcType = JdbcType.VARCHAR),
            @Result(column = "gender", property = "gender", jdbcType = JdbcType.BIT),
            @Result(column = "id_number", property = "idNumber", jdbcType = JdbcType.VARCHAR)
    })
    VisionClient selectByPrimaryKey(String id);

    @UpdateProvider(type = VisionClientSqlProvider.class, method = "updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(VisionClient record);

    @Update({
            "update vision_client",
            "set wx_client_id = #{wxClientId,jdbcType=CHAR},",
            "name = #{name,jdbcType=VARCHAR},",
            "age = #{age,jdbcType=INTEGER},",
            "birthday = #{birthday,jdbcType=DATE},",
            "phone_number = #{phoneNumber,jdbcType=VARCHAR},",
            "province = #{province,jdbcType=VARCHAR},",
            "city = #{city,jdbcType=VARCHAR},",
            "county = #{county,jdbcType=VARCHAR},",
            "detail_address = #{detailAddress,jdbcType=VARCHAR},",
            "gender = #{gender,jdbcType=BIT},",
            "id_number = #{idNumber,jdbcType=VARCHAR}",
            "where id = #{id,jdbcType=CHAR}"
    })
    int updateByPrimaryKey(VisionClient record);
}