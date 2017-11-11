package org.dj.bms.service;

import java.util.List;
import java.util.Map;

import org.dj.bms.model.Resource;

/**
 * @author Created by jason on 17/11/3.
 */
public interface ResourceService {
	List<Resource> findResourcesByRoleId(String roleId);

	List<Resource> findResources();

	List<Resource> findUserResources(Map<String, Object> paramMap);
}
