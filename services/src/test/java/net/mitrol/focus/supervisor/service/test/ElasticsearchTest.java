package net.mitrol.focus.supervisor.service.test;

import net.mitrol.focus.supervisor.core.service.ESHighLevelClientService;
import net.mitrol.focus.supervisor.service.test.config.TestConfig;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={ TestConfig.class })
@Ignore
public class ElasticsearchTest{

    @Autowired
    private ESHighLevelClientService esService;

    private String index;
    private String type;
    private String id;
    private Map<String, Object> data = new HashMap<String, Object>();

    @Before
    public void setUp() {
        index = "net-mitrol";
        type = "test";
        id = "hello";
        data.put("title", "hello world");
    }

    @Test
    @Ignore
    public void test_sync_create_delete_index() {
        String id_created = esService.buildIndexByParam(data, index, type, id);
        Assert.assertEquals(id, id_created);

        Map<String, Object> map = esService.searchDataByParam(index, type, id);
        Assert.assertNotNull(map);
        Assert.assertEquals(map.get("title"), "hello world");

        esService.deleteDataByParam(index, type, id);

        Map<String, Object> map1 = esService.searchDataByParam(index, type, id);
        Assert.assertNull(map1);
    }
}
