package com.burkhardt.wiki.mapper;

import com.burkhardt.wiki.resp.StatisticResp;

import java.util.List;

public interface EbookSnapshotMapperCust {

	public void genSnapshot();

	List<StatisticResp> getStatistic();
	List<StatisticResp> get30Statistic();
}
