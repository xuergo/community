package com.xuxiang.community.mapper;

import com.xuxiang.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QuesstionMapper {

    @Insert("INSERT INTO question(title，description，tag，gmt_create，gmt_modified，creator)" +
            " VALUES(#{title},#{description},#{tag},#{gmtCreate},#{gmtModified},#{creator})")
    void creat(Question question);

}
