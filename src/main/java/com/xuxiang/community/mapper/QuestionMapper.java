package com.xuxiang.community.mapper;

import com.xuxiang.community.dto.QuestionDto;
import com.xuxiang.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface QuestionMapper {

    @Insert("INSERT INTO question(title,description,tag,gmt_create,gmt_modified,creator)VALUES(#{title},#{description},#{tag},#{gmtCreate},#{gmtModified},#{creator})")
    void creat( Question question);

    @Select("select * from question limit #{offset},#{size}")
    List<Question> list(@Param(value ="offset" ) Integer offset,@Param(value = "size") Integer size);

    @Select("select * from question where creator=#{userId} limit #{offset},#{size} ")
    List<Question> listById(Integer offset, Integer size, @Param("userId") Integer userId);

    @Select("select count(1) from question")
    Integer pageTootl();

    @Select("select count(1) from question where creator= #{userId} ")
    Integer pageTootlById(@Param("userId") Integer userId);


    @Select("select * from question where id=#{id}")
    Question getById(@Param("id") Integer id);
}
