package org.vision.wechat.persistence.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.vision.wechat.persistence.model.VisionClientPO;
import org.vision.wechat.persistence.model.VisionClientPOExample;

@Mapper
public interface VisionClientPOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vision_client
     *
     * @mbg.generated Mon Mar 04 15:29:42 CST 2019
     */
    long countByExample(VisionClientPOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vision_client
     *
     * @mbg.generated Mon Mar 04 15:29:42 CST 2019
     */
    int deleteByExample(VisionClientPOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vision_client
     *
     * @mbg.generated Mon Mar 04 15:29:42 CST 2019
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vision_client
     *
     * @mbg.generated Mon Mar 04 15:29:42 CST 2019
     */
    int insert(VisionClientPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vision_client
     *
     * @mbg.generated Mon Mar 04 15:29:42 CST 2019
     */
    int insertSelective(VisionClientPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vision_client
     *
     * @mbg.generated Mon Mar 04 15:29:42 CST 2019
     */
    List<VisionClientPO> selectByExample(VisionClientPOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vision_client
     *
     * @mbg.generated Mon Mar 04 15:29:42 CST 2019
     */
    VisionClientPO selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vision_client
     *
     * @mbg.generated Mon Mar 04 15:29:42 CST 2019
     */
    int updateByExampleSelective(@Param("record") VisionClientPO record, @Param("example") VisionClientPOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vision_client
     *
     * @mbg.generated Mon Mar 04 15:29:42 CST 2019
     */
    int updateByExample(@Param("record") VisionClientPO record, @Param("example") VisionClientPOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vision_client
     *
     * @mbg.generated Mon Mar 04 15:29:42 CST 2019
     */
    int updateByPrimaryKeySelective(VisionClientPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vision_client
     *
     * @mbg.generated Mon Mar 04 15:29:42 CST 2019
     */
    int updateByPrimaryKey(VisionClientPO record);
}