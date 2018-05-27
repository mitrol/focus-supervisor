package net.mitrol.focus.supervisor.mitacd.connector.test;

import net.mitrol.acd.client.entities.MitAcdConnectionInfo;
import net.mitrol.acd.client.interfaces.MitAcdClientListener;
import net.mitrol.acd.client.tcp.MitAcdClient;
import net.mitrol.focus.supervisor.mitacd.connector.client.SSupMitAcdClient;
import net.mitrol.focus.supervisor.mitacd.connector.client.SSupMitAcdClientListener;
import net.mitrol.focus.supervisor.models.*;
import org.junit.Assert;
import org.junit.Test;
import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class SSupMitAcdClientTest {

    @Test
    public void connectFetchDisconnect() throws InterruptedException {

        CountDownLatch readyCountDownLatch = new CountDownLatch(1);
        CountDownLatch closedCountDownLatch = new CountDownLatch(1);
        CountDownLatch batchFinishedDownLatch = new CountDownLatch(3);

        final class SSupMitAcdClientListener1 implements SSupMitAcdClientListener {

            private SSupMitAcdClient ssupMitAcdClient;

            public SSupMitAcdClient getSsupMitAcdClient() {
                return ssupMitAcdClient;
            }

            private void setSSupMitAcdClient(SSupMitAcdClient ssupMitAcdClient) {
                this.ssupMitAcdClient = ssupMitAcdClient;
            }

            @Override
            public String getClientIdentifier(MitAcdClient mitAcdClient) {
                return "ssup";
            }

            @Override
            public void onConnected(MitAcdClient mitAcdClient) {
                ssupMitAcdClient.start();
            }

            @Override
            public void onClosed(MitAcdClient mitAcdClient, DisconnectedReason disconnectedReason) {
                closedCountDownLatch.countDown();
            }

            @Override
            public void onReady(SSupMitAcdClient ssupMitAcdClient) {
                readyCountDownLatch.countDown();
            }

            @Override
            public void onCampaignIntervalStats(SSupMitAcdClient ssupMitAcdClient, CampaignIntervalStats campaignIntervalStats) {
                Assert.assertNotNull(campaignIntervalStats);
            }

            @Override
            public void onCampaignDailyStats(SSupMitAcdClient ssupMitAcdClient, CampaignDailyStats campaignDailyStats) {
                Assert.assertNotNull(campaignDailyStats);
            }

            @Override
            public void onListIntervalStats(SSupMitAcdClient sSupMitAcdClient, ListIntervalStats listIntervalStats) {
                Assert.assertNotNull(listIntervalStats);
            }

            @Override
            public void onListDailyStats(SSupMitAcdClient ssupMitAcdClient, ListDailyStats listDailyStats) {
                Assert.assertNotNull(listDailyStats);
            }

            @Override
            public void onAgentIntervalStats(SSupMitAcdClient ssupMitAcdClient, AgentIntervalStats agentIntervalStats) {
                Assert.assertNotNull(agentIntervalStats);
            }

            @Override
            public void onAgentDailyStats(SSupMitAcdClient ssupMitAcdClient, AgentDailyStats agentDailyStats) {
                Assert.assertNotNull(agentDailyStats);
            }

            @Override
            public void onInteractionsStats(SSupMitAcdClient ssupMitAcdClient, InteractionStats interactionStats) {
                Assert.assertNotNull(interactionStats);
            }

            @Override
            public void onBatchFinished(SSupMitAcdClient ssupMitAcdClient, Integer intervalId, Instant serverDateTime, Duration intervalDuration) {
                batchFinishedDownLatch.countDown();
            }
        }

        SSupMitAcdClientListener1 ssupMitAcdClientListener = new SSupMitAcdClientListener1();
        SSupMitAcdClient ssupMitAcdClient = new SSupMitAcdClient(ssupMitAcdClientListener, new MitAcdConnectionInfo("192.168.41.212", 10403), Duration.ofMillis(500));
        ssupMitAcdClientListener.setSSupMitAcdClient(ssupMitAcdClient);
        ssupMitAcdClient.connect();

        readyCountDownLatch.await(5, TimeUnit.SECONDS);
        Assert.assertEquals(0, readyCountDownLatch.getCount());

        batchFinishedDownLatch.await(30, TimeUnit.SECONDS);
        Assert.assertEquals(0, batchFinishedDownLatch.getCount());

        ssupMitAcdClient.close(MitAcdClientListener.DisconnectedReason.Normal);
        closedCountDownLatch.await(5, TimeUnit.SECONDS);
        Assert.assertEquals(0, closedCountDownLatch.getCount());
    }
}
