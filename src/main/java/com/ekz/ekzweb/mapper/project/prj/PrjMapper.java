package com.ekz.ekzweb.mapper.project.prj;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ekz.ekzweb.domain.project.prj.po.AttributePO;
import com.ekz.ekzweb.domain.project.prj.po.Project;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface PrjMapper extends BaseMapper<Project> {

    /** 逻辑删除撤销 单个 Project Attribute*/
    @Update("UPDATE project  SET deleted = 0 WHERE prj_code = #{prjCode} ")
    void cancelLogicDeleteById(String prjCode);

    /** 物理删除 单个 Project Attribute*/
    @Delete("DELETE FROM project WHERE prj_code = #{prjCode} ")
    void physicsDeleteById(String prjCode);

    @Select("SELECT prj_Code, deleted, prj_Name, department, bu, customer, business_Model , core_Invest, product_Type, create_Time, creator, attribute_Update_Time, attribute_Updater  FROM project WHERE deleted = 0 ")
    List<AttributePO> getLogicDeleteProject();
}
