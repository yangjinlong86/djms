package org.dj.bms.query;


/**
 * @author jason
 * @date 17/11/19.
 */
public class RoleQueryBean extends QueryBean {

    private String roleName;
    private String roleDesc;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }
}
