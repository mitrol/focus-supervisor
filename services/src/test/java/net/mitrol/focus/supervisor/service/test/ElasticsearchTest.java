package net.mitrol.focus.supervisor.service.test;

import net.mitrol.focus.supervisor.core.service.ESHighLevelClientService;
import net.mitrol.focus.supervisor.core.service.model.User;
import net.mitrol.focus.supervisor.service.test.config.TestConfig;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
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
        type = "_doc";
        id = "abcd1234";
        data.put("title", "hello world");
    }

    @Test
    @Ignore
    public void test_sync_create_delete_index() {
        String id_created = esService.buildIndexByParam(data, index, type, "");
        Assert.assertTrue(esService.exists(index, type, id_created));

        Map<String, Object> map = esService.searchDataByParam(index, type, id_created);
        Assert.assertNotNull(map);
        Assert.assertEquals(map.get("title"), "hello world");

        esService.deleteDataByParam(index, type, id_created);

        Map<String, Object> map1 = esService.searchDataByParam(index, type, id_created);
        Assert.assertNull(map1);
    }

    @Test
    @Ignore
    public void test_sync_create_user_index() {
        String user_index = "user_index";
        String user_type = "_doc";
        String user_id = "abcd1234";
        Map<String, Object> data_user = new HashMap<String, Object>();
        data_user.put("id", user_id);
        data_user.put("name", "marcelo");
        data_user.put("lastname", "cruz");
        data_user.put("mail", "marcelo@gmail.com");

        String id_created = esService.buildIndexByParam(data_user, user_index, user_type, user_id);
        Assert.assertTrue(esService.exists(index, type, id_created));
    }

    @Test
    @Ignore
    public void test_searc_by_user_index() throws IllegalAccessException, IOException, InvocationTargetException {
        String user_index = "user_index";
        String user_type = "_doc";
        String user_id = "abcd1234";
        Map<String, Object> data_user = new HashMap<String, Object>();
        List<User> result = esService.searchDataByIndex(user_index, user_type, User.class);
        Assert.assertNotNull(result);
    }
}
