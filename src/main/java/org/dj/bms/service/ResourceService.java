package org.dj.bms.service;

import org.dj.bms.model.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Created by jason on 17/11/3.
 */
public interface ResourceService {
    List<Resource> findResourcesByRoleId(String roleId);
}
