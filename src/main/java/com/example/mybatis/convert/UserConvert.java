package com.example.mybatis.convert;

import com.example.mybatis.pojo.User;
import com.example.mybatis.pojo.vo.UserVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * UserConvert
 *
 * @author wangweijun
 * @version v1.0
 * @since 2023-11-28 00:14:11
 */
@Mapper(componentModel = "spring")
public abstract class UserConvert {

    public static final UserConvert INSTANCE = Mappers.getMapper(UserConvert.class);

    public abstract UserVO toUserVO(User user);

    public abstract List<UserVO> toUserVO(List<User> users);
}
