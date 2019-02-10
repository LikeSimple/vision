package org.vision.service.admin.persistence.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.vision.service.admin.persistence.model.WxClient;

@Mapper
public interface WxClientMapper {
    @Delete({
            "delete from wx_client",
            "where id = #{id,jdbcType=CHAR}"
    })
    int deleteByPrimaryKey(String id);

    @Insert({
            "insert into wx_client (id, public_id, ",
            "app_id, open_id, name, ",
            "wx_nick, avatar_image, ",
            "gender, age, birthday, ",
            "id_number, province, ",
            "city, county, detail_address)",
            "values (#{id,jdbcType=CHAR}, #{publicId,jdbcType=INTEGER}, ",
            "#{appId,jdbcType=VARCHAR}, #{openId,jdbcType=VARCHAR}, #{name,jdbcType=VARCHAR}, ",
            "#{wxNick,jdbcType=VARCHAR}, #{avatarImage,jdbcType=VARCHAR}, ",
            "#{gender,jdbcType=BIT}, #{age,jdbcType=INTEGER}, #{birthday,jdbcType=DATE}, ",
            "#{idNumber,jdbcType=VARCHAR}, #{province,jdbcType=VARCHAR}, ",
            "#{city,jdbcType=VARCHAR}, #{county,jdbcType=VARCHAR}, #{detailAddress,jdbcType=VARCHAR})"
    })
    int insert(WxClient record);

    @InsertProvider(type = WxClientSqlProvider.class, method = "insertSelective")
    int insertSelective(WxClient record);

    @Select({
            "select",
            "id, public_id, app_id, open_id, name, wx_nick, avatar_image, gender, age, birthday, ",
            "id_number, province, city, county, detail_address",
            "from wx_client",
            "where id = #{id,jdbcType=CHAR}"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.CHAR, id = true),
            @Result(column = "public_id", property = "publicId", jdbcType = JdbcType.INTEGER),
            @Result(column = "app_id", property = "appId", jdbcType = JdbcType.VARCHAR),
            @Result(column = "open_id", property = "openId", jdbcType = JdbcType.VARCHAR),
            @Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
            @Result(column = "wx_nick", property = "wxNick", jdbcType = JdbcType.VARCHAR),
            @Result(column = "avatar_image", property = "avatarImage", jdbcType = JdbcType.VARCHAR),
            @Result(column = "gender", property = "gender", jdbcType = JdbcType.BIT),
            @Result(column = "age", property = "age", jdbcType = JdbcType.INTEGER),
            @Result(column = "birthday", property = "birthday", jdbcType = JdbcType.DATE),
            @Result(column = "id_number", property = "idNumber", jdbcType = JdbcType.VARCHAR),
            @Result(column = "province", property = "province", jdbcType = JdbcType.VARCHAR),
            @Result(column = "city", property = "city", jdbcType = JdbcType.VARCHAR),
            @Result(column = "county", property = "county", jdbcType = JdbcType.VARCHAR),
            @Result(column = "detail_address", property = "detailAddress", jdbcType = JdbcType.VARCHAR)
    })
    WxClient selectByPrimaryKey(String id);

    @UpdateProvider(type = WxClientSqlProvider.class, method = "updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(WxClient record);

    @Update({
            "update wx_client",
            "set public_id = #{publicId,jdbcType=INTEGER},",
            "app_id = #{appId,jdbcType=VARCHAR},",
            "open_id = #{openId,jdbcType=VARCHAR},",
            "name = #{name,jdbcType=VARCHAR},",
            "wx_nick = #{wxNick,jdbcType=VARCHAR},",
            "avatar_image = #{avatarImage,jdbcType=VARCHAR},",
            "gender = #{gender,jdbcType=BIT},",
            "age = #{age,jdbcType=INTEGER},",
            "birthday = #{birthday,jdbcType=DATE},",
            "id_number = #{idNumber,jdbcType=VARCHAR},",
            "province = #{province,jdbcType=VARCHAR},",
            "city = #{city,jdbcType=VARCHAR},",
            "county = #{county,jdbcType=VARCHAR},",
            "detail_address = #{detailAddress,jdbcType=VARCHAR}",
            "where id = #{id,jdbcType=CHAR}"
    })
    int updateByPrimaryKey(WxClient record);
}