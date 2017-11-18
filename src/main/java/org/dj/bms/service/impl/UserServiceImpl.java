package org.dj.bms.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.dj.bms.dao.UserMapper;
import org.dj.bms.dao.UserRoleMapper;
import org.dj.bms.enumeration.DBEnum;
import org.dj.bms.model.Role;
import org.dj.bms.model.User;
import org.dj.bms.model.UserRole;
import org.dj.bms.query.UserQueryBean;
import org.dj.bms.service.BaseService;
import org.dj.bms.service.UserService;
import org.dj.bms.utils.BeanUtils;
import org.dj.bms.utils.EncryptUtil;
import org.dj.bms.utils.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.List;

/**
 * @author Created by jason on 17/10/29.
 */
@Service
public class UserServiceImpl extends BaseService implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    /**
     * @param user
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int saveOrUpdate(User user) {
        if (user == null) {
            return DBEnum.OPERATION_FAILED.getValue();
        }
        int resCount = 0;
        if (StringUtils.isNotBlank(user.getId())) {
            // 更新用户
            user.setUpdateTime(Calendar.getInstance().getTime());
            resCount = userMapper.updateByPrimaryKeySelective(user);
            return resCount;
        } else {
            // 新建用户
            user.setId(IdGenerator.generateUUID());
            user.setCreateTime(Calendar.getInstance().getTime());
            EncryptUtil.encryptPassword(user);
            resCount += userMapper.insertSelective(user);
            return resCount;
        }
    }

    @Override
    public List<Role> findCurrentUserRoles(User user) {
        // TODO 查询当前用户拥有的角色
        return null;
    }

    @Override
    public User findByUsername(String name) {
        return userMapper.selectByUserName(name);
    }

    @Override
    public int countByUsername(User user){
        return userMapper.countByUsername(user);
    }

    @Override
    public User selectByUserId(String id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteByUserId(String id) {
        int count = 0;
        count += userRoleMapper.deleteUserRoleByUserId(id);
        count += userMapper.deleteByPrimaryKey(id);
        return count;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteByUserIds(String[] idArr) {
        int count = 0;
        count += userRoleMapper.deleteUserRoleByUserIds(idArr);
        count += userMapper.deleteByUserIds(idArr);
        return count;
    }

    @Override
    public PageInfo<User> selectUsers(UserQueryBean userQueryBean) {
        PageHelper.startPage(userQueryBean.getPageNum(), userQueryBean.getLimitNum()).setOrderBy("CREATE_TIME DESC");
        List<User> list = userMapper.selectUsers(BeanUtils.convertBean2Map(userQueryBean));
        PageInfo<User> pageInfo = new PageInfo<User>(list);
        return pageInfo;
    }

    private int deleteUserRoleByUserId(String userId) {
        return userRoleMapper.deleteUserRoleByUserId(userId);
    }

    /**
     * 保存用户和角色对应关系
     * @param user
     * @return
     */
    @Override
    public int saveUserRole(User user) {
        int resCount = 0;
        if (StringUtils.isBlank(user.getRoleValues())) {
            return 0;
        }
        // 先清空该用户的对应关系
        this.deleteUserRoleByUserId(user.getId());
        String[] roleIdArr = user.getRoleValues().split(",");
        for (int i = 0; i < roleIdArr.length; i++) {
            UserRole userRole = new UserRole();
            userRole.setUserId(user.getId());
            userRole.setRoleId(roleIdArr[i]);
            resCount += userRoleMapper.insertSelective(userRole);
        }
        return resCount;
    }

}
