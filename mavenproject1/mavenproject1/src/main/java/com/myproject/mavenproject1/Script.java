package com.myproject.mavenproject1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class Script {

    private WebDriver driver;
    private String url;
    private String targetPath = "./results";

    public void initDriver(DesiredCapabilities desiredCapabilities) {
        //set webdriver location
        System.setProperty("webdriver.chrome.driver", "./src/main/java/res/driver/chromedriver.exe");

        //setting up driver with already given extensions and paths
        driver = new ChromeDriver(desiredCapabilities);

        //maximizing browser window
        driver.manage().window().maximize();

        //creating array of tabs, switching to first one
        ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs2.get(0));
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    public DesiredCapabilities setUpAdblock() {
        HashMap<String, Object> chromePref = new HashMap<String, Object>();
        chromePref.put("download.default_directory", targetPath);

        ChromeOptions options = new ChromeOptions();
        options.addExtensions(new File("./src/main/java/res/chromeExtension/extension_4_33_0_0.crx"));
        options.setExperimentalOption("prefs", chromePref);

        DesiredCapabilities desiredCapabilities = DesiredCapabilities.chrome();
        desiredCapabilities.setCapability(ChromeOptions.CAPABILITY, options);

        return desiredCapabilities;
    }

    public void connect() {
        //Directing browser to given URL
        driver.get(url);

        //5 seconds wait
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

        /*for slow internet connection wait for certain element
         * in this case element "className"
         */
        //sleep(20000);
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("_2_tDEnGMLxpM6uOa2kaDB3")));

        //Assigning browser our current address
        String actualTitle = driver.getTitle();
    }

    public boolean checkConnection() {
        return checkConnection(5);
    }

    public boolean checkConnection(int attemptsLeft) {
        try {
            URL webURL = new URL(url);
            //in case of URL not found, stopping script and showing Error mssg
            HttpURLConnection c = (HttpURLConnection) webURL.openConnection();
            c.setRequestMethod("GET");
            c.connect();

            if (c.getResponseCode() == 200) {
                System.out.println(webURL + " - " + c.getResponseCode() + " " + c.getResponseMessage() + " ");
                return true;
            } else if (c.getResponseCode() == 502 && attemptsLeft > 0) {
                // Return on timeout
                return checkConnection(attemptsLeft--);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return false;
    }

    public void harvest(int targetCount) throws InterruptedException, IOException {
        //creating a list and storing src links into it
        List<WebElement> list = (List<WebElement>) driver.findElements(By.className("_2_tDEnGMLxpM6uOa2kaDB3"));

        if(list == null || list.isEmpty()) {
            System.out.println("list: " + list.size());
            System.out.println("Connect exception");
            driver.quit();
            throw new java.net.ConnectException();
        }
        
        //setting up download path for pictures
        int count = 0;
        File targetDirectory = new File(targetPath);
        if (!targetDirectory.exists()) {
            targetDirectory.mkdir();
        }


        //loop to iterate between given elements "By.className"
        for (WebElement element : list) {
           count++;
           
            if (count == targetCount + 1) {
                break; 
            } else {  
                //creating src string and tying it up with src element found in className
                String src = element.getAttribute("src");
                System.out.println(src);
                URL imageURL = new URL(src);

                //reading image, saving it with given path and name
                BufferedImage saveImage = ImageIO.read(imageURL);
                ImageIO.write(saveImage, "png", new File(targetDirectory, "meme " + count + ".png"));

                //delay after each downloaded picture
                sleep(2000); 
            }
        }

        driver.quit();
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setTargetPath(String path) {
        this.targetPath = path;
    }

    public String getTargetPath() {
        return targetPath;
    }
}
