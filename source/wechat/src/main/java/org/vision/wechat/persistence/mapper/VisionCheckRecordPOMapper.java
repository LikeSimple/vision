package org.vision.wechat.persistence.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;
import org.vision.wechat.model.CheckRecordGetListBO;
import org.vision.wechat.model.CheckRecordListBO;
import org.vision.wechat.persistence.model.VisionCheckRecordPO;
import org.vision.wechat.persistence.model.VisionCheckRecordPOExample;

@Mapper
public interface VisionCheckRecordPOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vision_check_record
     *
     * @mbg.generated Mon Mar 04 15:29:42 CST 2019
     */
    long countByExample(VisionCheckRecordPOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vision_check_record
     *
     * @mbg.generated Mon Mar 04 15:29:42 CST 2019
     */
    int deleteByExample(VisionCheckRecordPOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vision_check_record
     *
     * @mbg.generated Mon Mar 04 15:29:42 CST 2019
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vision_check_record
     *
     * @mbg.generated Mon Mar 04 15:29:42 CST 2019
     */
    int insert(VisionCheckRecordPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vision_check_record
     *
     * @mbg.generated Mon Mar 04 15:29:42 CST 2019
     */
    int insertSelective(VisionCheckRecordPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vision_check_record
     *
     * @mbg.generated Mon Mar 04 15:29:42 CST 2019
     */
    List<VisionCheckRecordPO> selectByExample(VisionCheckRecordPOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vision_check_record
     *
     * @mbg.generated Mon Mar 04 15:29:42 CST 2019
     */
    VisionCheckRecordPO selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vision_check_record
     *
     * @mbg.generated Mon Mar 04 15:29:42 CST 2019
     */
    int updateByExampleSelective(@Param("record") VisionCheckRecordPO record, @Param("example") VisionCheckRecordPOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vision_check_record
     *
     * @mbg.generated Mon Mar 04 15:29:42 CST 2019
     */
    int updateByExample(@Param("record") VisionCheckRecordPO record, @Param("example") VisionCheckRecordPOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vision_check_record
     *
     * @mbg.generated Mon Mar 04 15:29:42 CST 2019
     */
    int updateByPrimaryKeySelective(VisionCheckRecordPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vision_check_record
     *
     * @mbg.generated Mon Mar 04 15:29:42 CST 2019
     */
    int updateByPrimaryKey(VisionCheckRecordPO record);
    
    List<CheckRecordListBO> list(CheckRecordGetListBO bo);
    
    List<VisionCheckRecordPO> activityClientList(@Param("activityId") String activityId, @Param("clientId") String clientId);
}