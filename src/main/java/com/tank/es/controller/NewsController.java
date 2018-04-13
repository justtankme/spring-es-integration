package com.tank.es.controller;

import java.io.IOException;

import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.tank.es.core.common.Result;
import com.tank.es.model.News;

@RestController
public class NewsController {
	@Autowired
    private RestHighLevelClient rhlClient;
	
	@GetMapping("/hello")
	public Result<?> hello(){
		return Result.ok();
	}
	
	@PostMapping("/news")
	public Result<News> addTest() {
        String index = "demo_index";
		String type = "demo_type";
		IndexRequest indexRequest = new IndexRequest(index, type);
        News news = new News();
        news.setTitle("中国产小型无人机的“对手”来了，俄微型拦截导弹便宜量又多");
        news.setTag("军事");
        news.setPublishTime("2018-01-24T23:59:30Z");
        String source = JSON.toJSONString(news);
        indexRequest.source(source, XContentType.JSON);
        try {
            rhlClient.index(indexRequest);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return Result.ok();
    }
}
