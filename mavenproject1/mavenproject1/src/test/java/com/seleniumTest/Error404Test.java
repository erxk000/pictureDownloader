/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seleniumTest;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 *
 * @author hanib
 */
public class Error404Test {
    
    @Test
    public void testSimpleErrorTest() throws MalformedURLException, ProtocolException, IOException {
        
        //System.setProperty("webdriver.chrome.driver","./src\\main\\java\\res\\driver\\chromedriver.exe");
        //WebDriver driver = new ChromeDriver();
        
        //driver.get("https://www.reddit.com/r/memes/ss");
        
        //String actualTitle = driver.getTitle();
        
        //if (actualTitle.contains("Error 404 (Not Found)!!1")) {
        
            //assertEquals(actualTitle,"Error 404 (Not Found)!!1");
            //System.out.println("Error 404 (Not Found)!!1");
        //}
        
        URL webURL = new URL("https://www.reddit.com/r/memes/ss");
        
        HttpURLConnection c = (HttpURLConnection) webURL.openConnection();
        c.setRequestMethod("GET");
        c.connect();
        int code = c.getResponseCode();
                 
        if(c.getResponseCode() == 404) {

            System.out.print(webURL + " - " + code + " " + c.getResponseMessage() + " ");
        }
    }    
}

