/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seleniumTest;


import java.io.File;
import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author hanib
 */
public class AdblockInstallTest {

    @Test
    public void testSimpleAdblockInstall() throws Exception {
        
        System.setProperty("webdriver.chrome.driver","./src\\main\\java\\res\\driver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        
        HashMap<String, Object> chromePref = new HashMap<String, Object>();
        
        ChromeOptions options = new ChromeOptions();
        options.addExtensions(new File("./src\\main\\java\\res\\chromeExtension\\extension_4_33_0_0.crx"));
        
        DesiredCapabilities desiredCapabilities = DesiredCapabilities.chrome();
        
        desiredCapabilities.setCapability(ChromeOptions.CAPABILITY, options);
        
        options.setExperimentalOption("prefs", chromePref);
        
        //setting up driver with already given extensions and paths
        driver = new ChromeDriver(desiredCapabilities);
        
        ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
        driver.switchTo().window(tabs2.get(0));
        
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        driver.get("https://www.reddit.com/r/memes/");
        
        // Check the title of the page
        sleep(21000);
          (new WebDriverWait(driver, 20)).until(new ExpectedCondition<Boolean>() {
            
            @Override
            public Boolean apply(WebDriver d) {
                
                return d.getTitle().contains("/r/Memes the original since 2008");
            }
        });

        driver.quit();
    }
}

