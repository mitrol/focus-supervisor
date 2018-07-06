package net.mitrol.focus.supervisor.service.test;

import net.mitrol.focus.supervisor.core.service.ESHighLevelClientService;
import net.mitrol.focus.supervisor.service.test.model.Direccion;
import net.mitrol.focus.supervisor.service.test.model.User;
import net.mitrol.focus.supervisor.service.test.model.Vendedor;
import net.mitrol.focus.supervisor.service.test.config.TestConfig;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.MoreLikeThisQueryBuilder;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
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
public class ElasticsearchTest{

    @Autowired
    private ESHighLevelClientService esService;

    private String index;
    private String type;
    private String id;
    private String user_index;
    private String vendedor_index;
    private String direccion_index;
    private Map<String, Object> data = new HashMap<String, Object>();

    @Before
    public void setUp() {
        index = "net-mitrol";
        type = "_doc";
        id = "abcd1234";
        data.put("title", "hello world");
        user_index = "user_index";
        vendedor_index = "vendedor_index";
        direccion_index = "direccion_index";
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
        String user_id = "abcd1234";
        Map<String, Object> data_user = new HashMap<String, Object>();
        data_user.put("id", user_id);
        data_user.put("name", "marcelo");
        data_user.put("lastname", "cruz");
        data_user.put("mail", "marcelo@gmail.com");

        String id_created = esService.buildIndexByParam(data_user, user_index, type, user_id);
        Assert.assertTrue(esService.exists(index, type, id_created));
    }

    @Test
    @Ignore
    public void test_searc_by_user_index() throws IllegalAccessException, IOException, InvocationTargetException {
        String user_index = "user_index";
        String user_type = "_doc";
        List<User> result = esService.searchDataByIndex(user_index, user_type, User.class);
        Assert.assertNotNull(result);
    }

    @Test
    @Ignore
    public void create_entity_by_entity() {
        User user = new User();
        user.setId("mac700");
        user.setName("majo");
        user.setLastname("jose");
        user.setMail("");

        String id_created = esService.buildDocumentIndex(user, user_index, type, user.getId());

        Assert.assertTrue(esService.exists(user_index, type, id_created));
    }

    @Test
    @Ignore
    public void createVendedorWithDireccion() {
        Direccion direccion = new Direccion();
        direccion.setId_direccion("dir1");
        direccion.setCalle("cordoba");
        direccion.setCodigoPostal("3500");
        direccion.setLocalidad("Resistencia");
        direccion.setNumero(1887);
        Vendedor vendedor = new Vendedor();
        vendedor.setId_vendedor("vendedor657");
        vendedor.setName("martin");
        vendedor.setLastname("veuthey");
        vendedor.setDireccion(direccion);

        String id_created = esService.buildDocumentIndex(vendedor, vendedor_index, type, "");

        Assert.assertTrue(esService.exists(vendedor_index, type, id_created));
    }

    @Test
    @Ignore
    public void test_searc_by_vendedor_index() throws IllegalAccessException, IOException, InvocationTargetException {
        List<Vendedor> result = esService.searchDataByIndex(vendedor_index, type, Vendedor.class);
        Assert.assertNotNull(result);
    }

    @Test
    @Ignore
    public void shouldBeSearchByField() throws IllegalAccessException, IOException, InvocationTargetException {
        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("name", "martin");
        List<Vendedor> result = esService.searchDataByQuery(vendedor_index, type, Vendedor.class, matchQueryBuilder);
        Assert.assertNotNull(result);
    }

    @Test
    @Ignore
    public void shouldBeSearchByDifferentsFields() throws IllegalAccessException, IOException, InvocationTargetException {
        MultiMatchQueryBuilder matchQueryBuilder = QueryBuilders.multiMatchQuery("Resistencia", "name", "direccion.localidad");
        List<Vendedor> result = esService.searchDataByQuery(vendedor_index, type, Vendedor.class, matchQueryBuilder);
        Assert.assertNotNull(result);
    }

    /*
    * Test to working search for like as LIKE "%etc%"
    * */
    @Test
    @Ignore
    public void shouldBeSearchByMatchPhrases() throws IllegalAccessException, IOException, InvocationTargetException {
        MoreLikeThisQueryBuilder moreLikeThisQuery = QueryBuilders.moreLikeThisQuery(new String[]{"name", "direccion.localidad"}, new String[]{"a"}, null);
        List<Vendedor> result = esService.searchDataByQuery(vendedor_index, type, Vendedor.class, moreLikeThisQuery);
        Assert.assertNotNull(result);
    }
}
