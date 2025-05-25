package com.qzb.ai.mcp.server;

import jakarta.annotation.PostConstruct;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CityServer {

    private List<City> cityList = new ArrayList<>();

    @PostConstruct
    public void init() {
        cityList.addAll(
                List.of(
                        new City("Hello City", "This is a Hello City, everyone say Hello"),
                        new City("Java City", "This is a Java City, we are study java for work."),
                        new City("嘻嘻哈哈城市", "我们喜欢说嘻嘻哈哈，所以我们就是嘻嘻哈哈城市")
                )
        );
    }

    @Tool(name = "获取所有城市", description = "获取所有城市信息")
    public List<City> getCityList() {
        return cityList;
    }

    @Tool(name = "获取指定名称城市", description = "获取指定名称城市信息")
    public List<City> getCityByName(@ToolParam(description = "cityName") String cityName) {
        return cityList.stream().filter(city -> city.getName().equals(cityName)).collect(Collectors.toList());
    }

}
