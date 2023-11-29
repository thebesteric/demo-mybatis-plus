package com.example.mybatis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.mybatis.pojo.Permission;
import com.example.mybatis.pojo.vo.PermissionVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {

    @Select("select p.* from permission as p, r_role_permission as rp where p.id = rp.permission_id and rp.role_id = #{role_id}")
    List<PermissionVO> selectPermissionByRoleId(String roleId);

}
