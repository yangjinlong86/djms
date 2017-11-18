package org.dj.bms.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.dj.bms.dao.RoleMapper;
import org.dj.bms.model.Role;
import org.dj.bms.query.QueryBean;
import org.dj.bms.service.BaseService;
import org.dj.bms.service.RoleService;
import org.dj.bms.utils.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

/**
 * @author Created by jason on 17/11/3.
 */
@Service
public class RoleServiceImpl extends BaseService implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public int saveOrUpdate(Role role){
        if(StringUtils.isBlank(role.getId())){
            role.setId(IdGenerator.generateUUID());
            return roleMapper.insertSelective(role);
        }
        return roleMapper.updateByPrimaryKeySelective(role);
    }

    @Override
    public List<Role> findRoleListByUserId(String userId) {
        return roleMapper.findRoleListByUserId(userId);
    }


    @Override
    public PageInfo<Role> selectRoles(QueryBean queryBean) {
        PageHelper.startPage(queryBean.getPageNum(), queryBean.getLimitNum()).setOrderBy("ROLE_NAME ASC");
        Map<String, String> paramsMap = null;
        try {
            paramsMap = BeanUtils.describe(queryBean);
        } catch (IllegalAccessException e) {
            logger.error(e);
        } catch (InvocationTargetException e) {
            logger.error(e);
        } catch (NoSuchMethodException e) {
            logger.error(e);
        }
        List<Role> list = roleMapper.findRoles(paramsMap);
        PageInfo<Role> pageInfo = new PageInfo<Role>(list);
        return pageInfo;
    }

    @Override
    public Role findByRoleName(String roleName) {
        return null;
    }

    @Override
    public int deleteRoleByIds(String ids) {
        String[] idArr = null;
        if (ids.contains(",")) {
            idArr = ids.split(",");
        } else {
            idArr = new String[]{ids};
        }
        return roleMapper.deleteByIds(idArr);
    }
}
