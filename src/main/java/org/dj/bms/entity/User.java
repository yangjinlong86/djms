package org.dj.bms.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author Created by jason on 17/10/29.
 */
@Entity
public class User {
    @Id
    private String id;
    private String name;
    private String password;
    private String lastLoginIp;
    private Date lastLoginTime;

    public User(){
        super();
    }

    public User(String id, String name, String password, String lastLoginIp, Date lastLoginTime) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.lastLoginIp = lastLoginIp;
        this.lastLoginTime = lastLoginTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }
}
