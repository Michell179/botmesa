package com.arus.sistecredito.Main;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.v115.indexeddb.model.Key;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import javax.swing.plaf.TableHeaderUI;
import java.awt.event.KeyEvent;
import java.time.Duration;

public class Main {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();
        Actions action = new Actions(driver);

        try {
            driver.get("https://csm3.serviceaide.com/#login");

            Thread.sleep(1000);

            WebElement textBox = driver.findElement(By.id("textfield-1016-inputEl"));
            WebDriverWait s = new WebDriverWait(driver, Duration.ofSeconds(3));
            s.until(d ->{
                textBox.sendKeys("stiven.barrera-marco@arus.com.co");
                return true;
            });
            textBox.sendKeys(Keys.TAB);

            WebElement password = driver.findElement(By.id("textfield-1017-inputEl"));
            WebDriverWait p = new WebDriverWait(driver, Duration.ofSeconds(3));
            s.until(d ->{
                password.sendKeys("Podemos2021+");
                return true;
            });



            Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(2));
            wait.until(d -> password.isDisplayed());
            password.click();
            password.sendKeys("Podemos2021+");

            Thread.sleep(1000);

            WebElement submitButton = driver.findElement(By.id("button-1021-btnEl"));
            submitButton.click();

            Thread.sleep(3000);

            WebElement bandeja = driver.findElement(By.xpath("//*[@id=\"ca-filterbutton-1101-btnInnerEl\"]"));
            bandeja.click();

            Thread.sleep(3000);


            WebElement ticket;
            int ticketCounter = 2;
            String ticketPath;
            String validation;

            do {
                ticketPath = "/html/body/div[3]/div[2]/div[1]/div/div[2]/div/div[1]/div/div/div/div[1]/div/div[1]/div[2]/div[1]/div[5]/div/table/tbody/tr["+ ticketCounter +"]/td/div/table/tbody/tr[1]/td[5]/div/div/table/tbody/tr[3]/td[1]";
                ticket = driver.findElement(By.xpath(ticketPath));
                validation = ticket.getText();
                if (!validation.equals("Queued | None")) ticketCounter++;

            }while (!validation.equals("Queued | None"));


            action.doubleClick(ticket).perform();

        }catch (Exception e){
            System.out.println(e);
        }


    }
}
