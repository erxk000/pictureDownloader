/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myproject.mavenproject1;

import static com.thoughtworks.selenium.SeleneseTestBase.assertEquals;

import java.util.Scanner;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import static java.lang.Thread.sleep;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

//@author gush

public class MainClass {

    public static void main(String[] args) {
        Script script = new Script();

        // setup adblock and options
        DesiredCapabilities desiredCapabilities = script.setUpAdblock();

        // initialize driver
        script.initDriver(desiredCapabilities);

        script.setUrl("https://www.reddit.com/r/memes/");

        // Check URL connection to ensure that the driver can connect
        if (script.checkConnection()) {
            script.connect();

            try {
                // Start harvesting
                script.harvest(5);
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                //successfull print out message 
                System.out.println("Download Successfull");
            }
        }
    }
}