package org.dj.bms;

import com.github.pagehelper.PageInfo;
import org.dj.bms.dao.ConsumeMapper;
import org.dj.bms.model.Consume;
import org.dj.bms.query.IQueryInfo;
import org.dj.bms.query.QueryInfo;
import org.dj.bms.service.ConsumeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author jason
 * @date 2017年12月09日.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = BmsApplication.class)
@WebAppConfiguration
public class TestConsumption {


    @Autowired
    private ConsumeMapper mapper;

    @Test
    public void testConsumptionMapper() {
        Map<String, Object> paramsMap = new HashMap<String, Object>();
        paramsMap.put("corpId", "1");
        List<Map<String, Object>> list = mapper.selectConsumptions(paramsMap);
        list.forEach(stringObjectMap -> System.out.println(stringObjectMap));
    }


}
