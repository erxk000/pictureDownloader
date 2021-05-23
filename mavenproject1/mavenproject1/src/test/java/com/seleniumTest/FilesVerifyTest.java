/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seleniumTest;

import com.myproject.mavenproject1.Script;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 *
 * @author hanib
 */
public class FilesVerifyTest {
    
    private Script downloader;
    
    public void setup() {
        downloader = new Script();
        DesiredCapabilities capabilities = downloader.setUpAdblock();
        downloader.initDriver(capabilities);
    }
    
    @Test
    public void testSimple() throws Exception {
        setup();
        downloader.setUrl("https://www.reddit.com/r/memes/");
        downloader.connect();

       if (downloader.harvest() == 1) {
           System.out.println("Test - No Files downloaded ");
       } else {
           System.out.println("Test - Files downloaded");
       }
    } 
}
