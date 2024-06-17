package com.ekz.ekzweb.mapper.project.prj;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ekz.ekzweb.domain.project.prj.po.Project;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface PrjMapper extends BaseMapper<Project> {

    /** 逻辑删除撤销 单个 Project Attribute*/
    @Update("UPDATE project  SET deleted = 0 WHERE prj_code = #{prjCode} ")
    void cancelLogicDeleteById(String prjCode);

    /** 物理删除 单个 Project Attribute*/
    @Delete("DELETE FROM project WHERE prj_code = #{prjCode} ")
    void physicsDeleteById(String prjCode);


//    @Select("SELECT prj_name FROM project WHERE prj_code = #{prjCode}")
//    Project getByPrjCode(String prjCode);
}
