package com.burkhardt.wiki.controller;

import com.burkhardt.wiki.resp.CommonResp;
import com.burkhardt.wiki.resp.StatisticResp;
import com.burkhardt.wiki.service.EbookSnapshotService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/ebook-snapshot")
public class EbookSnapshotController {

	@Resource
	private EbookSnapshotService ebookSnapshotService;

	@GetMapping("/get-statistic")
	public CommonResp getStatistic() {
		List<StatisticResp> statisticResp = ebookSnapshotService.getStatistic();
		CommonResp<List<StatisticResp>> commonResp = new CommonResp<>();
		commonResp.setContent(statisticResp);
		return commonResp;
	}

}
