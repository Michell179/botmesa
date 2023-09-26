package com.arus.sistecredito.controller;


import com.arus.sistecredito.model.RobotModel;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class RobotController {


    public static void main(String[] args) throws IOException {

        /*Read Properties
        * This file should be  in the directory app*/
        ReadProperties properties = new ReadProperties();
        File jsonFile = new File(properties.getPath());

        ObjectMapper robotJson = new ObjectMapper();
        JsonNode robotNode = robotJson.readTree(jsonFile);
        JsonNode actionsNode = robotNode.get("actions");
        Queue<String> actionQueue = new LinkedList<>();
        Queue<String[]> valueQueue = new LinkedList<>();
        WebDriver driver = new ChromeDriver();

//        Create a Queue for consume actions in order of Json File
        actionsNode.fields().forEachRemaining(entry -> {
            String action = entry.getKey();
            JsonNode actionValueNode = entry.getValue();

            if (actionValueNode.isArray()) {
                String[] actionValues = new String[actionValueNode.size()];
                for (int i = 0; i < actionValueNode.size(); i++) {
                    actionValues[i] = actionValueNode.get(i).asText().replace("\"", "");
                }
                actionQueue.add(action.replace("\"", ""));
                valueQueue.add(actionValues);
            }
        });

        try{
            String url = String.valueOf(robotNode.get("url")).replace("\"", "");
            driver.get(url);
        }catch (Exception e){
            System.out.println(e);
        }

//        Execute Actions
        while(!actionQueue.isEmpty()){

            String[] values = valueQueue.poll();

            if (values != null) {
                switch (values[0]){
                    case "click":
                        driver.findElement(By.xpath(values[1])).click();
                        System.out.println("Click en el path " + values[1]);
                        break;
                    case "write":
                        driver.findElement(By.xpath(values[1])).sendKeys(values[2]);
                        System.out.println("Escribiendo...");
                        break;
                    case "wait":
                        int sleep = Integer.parseInt(values[1]);
                        try {
                            Thread.sleep(sleep);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                        System.out.println("Espera de " + (sleep / 1000) + " Segundos");
                        break;
                    case "tab":
                        driver.findElement(By.xpath(values[1])).sendKeys(Keys.TAB);
                        break;
                }
            }
        }
//        driver.close();
    }
}
