package org.nocoder.djms;

import org.nocoder.djms.model.Organization;
import org.nocoder.djms.service.OrganizationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Date;

/**
 * @author jason
 * @date 17/11/22.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DJMSApplication.class)
@WebAppConfiguration
public class TestOrganization {

    @Autowired
    private OrganizationService organizationService;

    @Test
    public void testOrganization(){
        Organization organization;
        for(int i = 0; i<10; i++){
            organization = new Organization();
            organization.setpId("1");
            organization.setName(i + "分店");
            organization.setType("1");
            organization.setCreateTime(new Date());
            organization.setUpdateTime(new Date());
            organization.setCreateUser("1");
            organization.setComment("备注信息");
            organizationService.saveOrUpdate(organization);
        }
    }
}
