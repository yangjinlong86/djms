package org.dj.bms.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.dj.bms.dao.UserMapper;
import org.dj.bms.dao.UserRoleMapper;
import org.dj.bms.enumeration.DBEnum;
import org.dj.bms.model.Role;
import org.dj.bms.model.User;
import org.dj.bms.model.UserRole;
import org.dj.bms.query.UserQueryBean;
import org.dj.bms.service.UserService;
import org.dj.bms.utils.EncryptUtil;
import org.dj.bms.utils.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

/**
 * @author Created by jason on 17/10/29.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;


    /**
     *
     * @param user
     * @return
     */
    @Override
    public int saveOrUpdate(User user) {
        if(user == null){
            return DBEnum.OPERATION_FAILED.getValue();
        }
        int resCount = 0;
        if(StringUtils.isNotBlank(user.getId())){
            // 更新用户
            user.setUpdateTime(Calendar.getInstance().getTime());
            resCount = userMapper.updateByPrimaryKeySelective(user);
            if(resCount != DBEnum.OPERATION_FAILED.getValue()){
                this.deleteUserRoleByUserId(user.getId());
                this.saveUserRole(user);
                resCount++;
            }
            return resCount;
        }else{
            // 新建用户
            user.setId(IdGenerator.generateUUID());
            user.setCreateTime(Calendar.getInstance().getTime());
            EncryptUtil.encryptPassword(user);
            userMapper.insertSelective(user);
            if(resCount != DBEnum.OPERATION_FAILED.getValue()){
                this.deleteUserRoleByUserId(user.getId());
                this.saveUserRole(user);
                resCount++;
            }
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
    public String getUserPassword(String id) {
        // TODO 根据用户ID查询用户密码
        return null;
    }

    @Override
    public User selectByUserId(String id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public int deleteByUserId(String id) {
        return userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public PageInfo<User> selectUsers(UserQueryBean userQueryBean) {
        PageHelper.startPage(userQueryBean.getPageNum(), userQueryBean.getLimitNum()).setOrderBy("create_time desc");
        Map<String, String> paramsMap = null;
        try {
            paramsMap = BeanUtils.describe(userQueryBean);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        List<User> list = userMapper.selectUsers(paramsMap);
        PageInfo<User> pageInfo = new PageInfo<User>(list);
        return  pageInfo;
    }

    @Override
    public User selectByUserName(String name) {
        return userMapper.selectByUserName(name);
    }

    @Override
    public int selectCountUser() {
        return userMapper.selectCountUser();
    }


    private int deleteUserRoleByUserId(String userId){
        return userRoleMapper.deleteUserRoleByUserId(userId);
    }

    private int saveUserRole(User user){
        int resCount = 0;
        if(StringUtils.isBlank(user.getRoleValues())){
            return 0;
        }
        String[] roleIdArr = null;
        if(user.getRoleValues().length() > 1){
            roleIdArr = user.getRoleValues().split(",");
        }else{
            roleIdArr[0] = user.getRoleValues();
        }
        for(int i = 0; i<roleIdArr.length; i++){
            UserRole userRole = new UserRole();
            userRole.setUserId(user.getId());
            userRole.setRoleId(roleIdArr[i]);
            resCount += userRoleMapper.insertSelective(userRole);
        }
        return resCount;
    }


}
