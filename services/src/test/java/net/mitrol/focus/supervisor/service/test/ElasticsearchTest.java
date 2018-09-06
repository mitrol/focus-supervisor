package net.mitrol.focus.supervisor.service.test;

import net.mitrol.ct.api.enums.AgentState;
import net.mitrol.focus.supervisor.core.service.ESHighLevelClientService;
import net.mitrol.focus.supervisor.core.service.ESInteractionStatsService;
import net.mitrol.focus.supervisor.service.test.config.TestConfig;
import net.mitrol.focus.supervisor.service.test.model.Direccion;
import net.mitrol.focus.supervisor.service.test.model.User;
import net.mitrol.focus.supervisor.service.test.model.Vendedor;
import net.mitrol.utils.DateTimeUtils;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.metrics.valuecount.ValueCountAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
public class ElasticsearchTest {

    @Autowired
    private ESHighLevelClientService esService;

    @Autowired
    private ESInteractionStatsService esInteractionStatsService;

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
    public void test_searc_by_user_index() {
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
    public void test_searc_by_vendedor_index() {
        List<Vendedor> result = esService.searchDataByIndex(vendedor_index, type, Vendedor.class);
        Assert.assertNotNull(result);
    }

    @Test
    @Ignore
    public void shouldBeSearchByField() {
        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("name", "martin");
        List<Vendedor> result = esService.searchDataByQuery(vendedor_index, type, Vendedor.class, matchQueryBuilder);
        Assert.assertNotNull(result);
    }

    @Test
    @Ignore
    public void shouldBeSearchByDifferentsFields() {
        MultiMatchQueryBuilder matchQueryBuilder = QueryBuilders.multiMatchQuery("Resistencia", "name", "direccion.localidad");
        List<Vendedor> result = esService.searchDataByQuery(vendedor_index, type, Vendedor.class, matchQueryBuilder);
        Assert.assertNotNull(result);
    }

    @Test
    @Ignore
    public void shouldBeSearchByTwoFields() {
        BoolQueryBuilder query = QueryBuilders.boolQuery()
                .filter(QueryBuilders.matchQuery("direccion.codigoPostal", "3700"))
                .filter(QueryBuilders.matchQuery("direccion.localidad", "Corrientes"));
        List<Vendedor> result = esService.searchDataByQuery(vendedor_index, type, Vendedor.class, query);
        Assert.assertNotNull(result);
    }

    /*
     * Test to working search for like as LIKE "%etc%"
     * */
    @Test
    @Ignore
    public void shouldBeSearchByMatchPhrases() {
        MoreLikeThisQueryBuilder moreLikeThisQuery = QueryBuilders.moreLikeThisQuery(new String[]{"name", "direccion.localidad"}, new String[]{"a"}, null);
        List<Vendedor> result = esService.searchDataByQuery(vendedor_index, type, Vendedor.class, moreLikeThisQuery);
        Assert.assertNotNull(result);
    }

    @Test
    @Ignore
    public void createVendedorWithDireccionAndDate() {
        Direccion direccion = new Direccion();
        direccion.setId_direccion("dir2");
        direccion.setCalle("frondizi");
        direccion.setCodigoPostal("3600");
        direccion.setLocalidad("Corriente");
        direccion.setNumero(1832);
        Vendedor vendedor = new Vendedor();
        vendedor.setId_vendedor("vendedor123");
        vendedor.setName("mrcelo");
        vendedor.setLastname("cruz");
        vendedor.setDireccion(direccion);
        vendedor.setDate(DateTimeUtils.getStringFromInstant(Instant.now(), DateTimeUtils.MITROL_DATE_HOUR_FORMAT));

        String id_created = esService.buildDocumentIndex(vendedor, vendedor_index, type, "");

        Assert.assertTrue(esService.exists(vendedor_index, type, id_created));
    }

    @Test
    @Ignore
    public void shouldBeSearchByDateField() {
        final DateTime now = DateTime.now();
        DateTime past = now.minusMinutes(60);
        RangeQueryBuilder a = QueryBuilders.rangeQuery("date.keyword").gte("09/07/2018 15:25:29").lte("09/07/2018 15:26:34");
        List<Vendedor> result = esService.searchDataByQuery(vendedor_index, type, Vendedor.class, a);
        Assert.assertNotNull(result);
    }

    @Test
    @Ignore
    public void createVendedorWithDireccionAndEnum() {
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
        vendedor.setDate(DateTimeUtils.getStringFromInstant(Instant.now(), DateTimeUtils.MITROL_DATE_HOUR_FORMAT));

        Map<AgentState, Duration> agentStateDurations = new HashMap<>();
        agentStateDurations.put(AgentState.NotReady, Duration.ofHours(1));
        vendedor.setAgentStateDurations(agentStateDurations);

        String id_created = esService.buildDocumentIndex(vendedor, vendedor_index, type, "");

        Assert.assertTrue(esService.exists(vendedor_index, type, id_created));
    }

    @Test
    @Ignore
    public void shouldBeSearchByDateWithDataComplexRompeSinJsonProperty() {
        final DateTime now = DateTime.now();
        DateTime past = now.minusMinutes(60);
        RangeQueryBuilder a = QueryBuilders.rangeQuery("date.keyword").gte("09/07/2018 15:25:29").lte("10/07/2018 15:26:34");
        List<Vendedor> result = esService.searchDataByQuery(vendedor_index, type, Vendedor.class, a);
        Assert.assertNotNull(result);
    }

    @Test
    @Ignore
    public void shouldBeSearchByDateWithDataComplexRompeConJsonPropertyAndDeserializer() {
        final DateTime now = DateTime.now();
        DateTime past = now.minusMinutes(60);
        RangeQueryBuilder a = QueryBuilders.rangeQuery("date.keyword").gte("10/07/2018 00:50:29").lte("10/07/2018 01:26:34");
        List<Vendedor> result = esService.searchDataByQuery(vendedor_index, type, Vendedor.class, a);
        Assert.assertNotNull(result);
    }

    @Test
    @Ignore
    public void shouldBeSearchByQueriesVendedorANDWhere() {
        QueryStringQueryBuilder queryInteractionStatsFilter = QueryBuilders.queryStringQuery("SELECT * FROM vendedor_index WHERE direccion.localidad: \"Corriente\"");//interactionStats.state= 'TALKING'");//interactionStats.state = \"TALKING\" ");
        List<Vendedor> resultInteractionStatsFilter = esService.searchDataByQuery("vendedor_index", type, Vendedor.class, queryInteractionStatsFilter);
        Assert.assertNotNull(resultInteractionStatsFilter);
    }

    @Test
    @Ignore
    public void shouldBeSearchByAggregation() {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        AbstractQueryBuilder abstractQueryBuilder = QueryBuilders.matchAllQuery();
        ValueCountAggregationBuilder aggregationBuildersTotal = AggregationBuilders.count("Talking").field("interactionStats.state.keyword");
        searchSourceBuilder.query(abstractQueryBuilder).aggregation(aggregationBuildersTotal);

        List<Object> resultInteractionStatsFilter = esService.searchDataByQueryAndAggregation("interactionstats_13-07-2018", type, Object.class, searchSourceBuilder);
        Assert.assertNotNull(resultInteractionStatsFilter);
    }

    @Test
    @Ignore
    public void shouldBeSearchByAggregationAndQuery() {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        AbstractQueryBuilder abstractQueryBuilder = QueryBuilders.matchQuery("interactionStats.state.keyword", "TALKING");
        ValueCountAggregationBuilder aggregationBuildersTotal = AggregationBuilders.count("Talking").field("interactionStats.state.keyword");
        searchSourceBuilder.query(abstractQueryBuilder).aggregation(aggregationBuildersTotal);

        List<Object> resultInteractionStatsFilter = esService.searchDataByQueryAndAggregation("interactionstats_13-07-2018", type, Object.class, searchSourceBuilder);
        Assert.assertNotNull(resultInteractionStatsFilter);
    }

    /*
     * List<SearchSourceBuilder> searchSourceBuilders
     * */
    @Test
    @Ignore
    public void shouldBeSearchMultipleFirstWidget() {
        List<SearchSourceBuilder> searchSourceBuilders = new ArrayList<>();

        /*
         *   Search by TALKING
         * */
        SearchSourceBuilder searchBuilderTALKING = new SearchSourceBuilder();
        AbstractQueryBuilder abstractQueryBuilder = QueryBuilders.matchQuery("interactionStats.state.keyword", "TALKING");
        ValueCountAggregationBuilder aggregationBuildersTotal = AggregationBuilders.count("Talking").field("interactionStats.state.keyword");
        searchBuilderTALKING.query(abstractQueryBuilder).aggregation(aggregationBuildersTotal);

        /*
         *   Search by RINGING
         * */
        SearchSourceBuilder searchBuilderRINGING = new SearchSourceBuilder();
        AbstractQueryBuilder abstractQueryBuilderRINGING = QueryBuilders.matchQuery("interactionStats.state.keyword", "RINGING");
        ValueCountAggregationBuilder aggregationBuildersRINGING = AggregationBuilders.count("Ringing").field("interactionStats.state.keyword");
        searchBuilderRINGING.query(abstractQueryBuilderRINGING).aggregation(aggregationBuildersRINGING);


        searchSourceBuilders.add(searchBuilderTALKING);
        searchSourceBuilders.add(searchBuilderRINGING);

        List<Object> resultInteractionStatsFilter = esService.multipleSearchDataByQueryAndAggregation("interactionstats_13-07-2018", type, Object.class, searchSourceBuilders);
        Assert.assertNotNull(resultInteractionStatsFilter);
    }

    @Test
    @Ignore
    public void shouldBeSearchMultipleWidget() {
        Map resultInteractionStatsFilter = esInteractionStatsService.countInteractionStats("", "10", "", null,
                null, null, true);
        Assert.assertNotNull(resultInteractionStatsFilter);
    }

    @Test
    @Ignore
    public void shouldBeSearchMultipleWidgetPreview() {
        Map resultInteractionStatsFilter = esInteractionStatsService.countInteractionStats("2018.08.27", "", "", null,
                null, null, false);
        Assert.assertNotNull(resultInteractionStatsFilter);
    }

    @Test
    @Ignore
    public void shouldBeSearchAgentStateWidgetPreview() {
        List<HashMap> resultInteractionStatsFilter = esInteractionStatsService.countAgentState("2018.08.31", "", "", null, false);
        Assert.assertNotNull(resultInteractionStatsFilter);
    }
}
