package net.mitrol.focus.supervisor.mitacd.connector.client;

import java.time.Duration;
import java.time.Instant;
import net.mitrol.acd.client.interfaces.MitAcdClientListener;
import net.mitrol.focus.supervisor.models.*;


public interface SSupMitAcdClientListener extends MitAcdClientListener {

    void onReady(SSupMitAcdClient ssupMitAcdClient);
    
    void onCampaignIntervalStats(SSupMitAcdClient ssupMitAcdClient, CampaignIntervalStats campaignIntervalStats);

    void onCampaignDailyStats(SSupMitAcdClient ssupMitAcdClient, CampaignDailyStats campaignDailyStats);

    void onListIntervalStats(SSupMitAcdClient sSupMitAcdClient, ListIntervalStats listIntervalStats);

    void onListDailyStats(SSupMitAcdClient ssupMitAcdClient, ListDailyStats listDailyStats);

    void onAgentIntervalStats(SSupMitAcdClient ssupMitAcdClient, AgentIntervalStats agentIntervalStats);

    void onAgentDailyStats(SSupMitAcdClient ssupMitAcdClient, AgentDailyStats agentDailyStats);

    void onInteractionsStats(SSupMitAcdClient ssupMitAcdClient, InteractionStats interactionStatsss);

    void onBatchFinished(SSupMitAcdClient ssupMitAcdClient, Integer intervalId, Instant serverDateTime, Duration intervalDuration);
}
