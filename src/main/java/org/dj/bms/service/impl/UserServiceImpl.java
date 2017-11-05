package org.dj.bms.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.dj.bms.dao.UserMapper;
import org.dj.bms.enumeration.DBEnum;
import org.dj.bms.model.Role;
import org.dj.bms.model.User;
import org.dj.bms.service.UserService;
import org.dj.bms.utils.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author Created by jason on 17/10/29.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    /**
     *
     * @param user
     * @return
     */
    @Override
    public int saveOrUpdate(User user) {
        if(user == null){
            return DBEnum.SAVE_FAILED.getValue();
        }

        if(StringUtils.isNotBlank(user.getId())){
            // 更新用户
            user.setUpdateTime(Calendar.getInstance().getTime());
            return userMapper.updateByPrimaryKeySelective(user);
        }else{
            // 新建用户
            user.setId(IdGenerator.generateUUID());
            user.setCreateTime(Calendar.getInstance().getTime());
            return userMapper.insertSelective(user);
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
    public PageInfo<User> selectAllUsers(@RequestParam(value = "pageNum", required = false, defaultValue="1") Integer pageNum,
                                   @RequestParam(value = "pageSize", required = false, defaultValue="10") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize).setOrderBy("create_time desc");
        List<User> list = userMapper.selectAllUsers();
        PageInfo<User> pageInfo = new PageInfo<User>(list);
        return  pageInfo;
    }

}
