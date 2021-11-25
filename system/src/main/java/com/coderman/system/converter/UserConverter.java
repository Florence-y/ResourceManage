package com.coderman.system.converter;

import com.coderman.common.model.system.Department;
import com.coderman.common.model.system.User;
import com.coderman.common.vo.system.UserVO;
import com.coderman.system.mapper.DepartmentMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Florence
 * @Date 2020/3/7 19:56
 * @Version 1.0
 **/
@Component
public class UserConverter {

    @Autowired
    private DepartmentMapper departmentMapper;

    /**
     * 转voList
     *
     * @param users
     * @return
     */
    public List<UserVO> converterToUserVOList(List<User> users) {
        List<UserVO> userVOS = new ArrayList<>();
        if (!CollectionUtils.isEmpty(users)) {
            for (User user : users) {
                UserVO userVO = converterToUserVO(user);
                userVOS.add(userVO);
            }
        }
        return userVOS;
    }

    /**
     * 转vo
     *
     * @return
     */
    public UserVO converterToUserVO(User user) {
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);
        userVO.setStatus(user.getStatus() == 0);
        Department department = departmentMapper.selectByPrimaryKey(user.getDepartmentId());
        if (department != null && department.getName() != null) {
            userVO.setDepartmentName(department.getName());
            userVO.setDepartmentId(department.getId());
        }
        return userVO;
    }

}
