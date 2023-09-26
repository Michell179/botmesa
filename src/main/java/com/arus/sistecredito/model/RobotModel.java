package com.arus.sistecredito.model;


import com.fasterxml.jackson.annotation.JsonProperty;


public class RobotModel {

    private String name;
    private String url;
    private Actions actions;


    @JsonProperty("name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("url")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @JsonProperty("actions")
    public Actions getActions() { return actions; }

    public void setActions(Actions actions) { this.actions = actions; }
}
