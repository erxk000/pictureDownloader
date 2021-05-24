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
 * @author hanib
 */
public class HappyCaseTest {

    private Script downloader;

    public void setup() {
        downloader = new Script();

        DesiredCapabilities capabilities = downloader.setUpAdblock();
        downloader.initDriver(capabilities);
    }

    @Test
    public void testHappyPath() throws Exception {
        setup();

        downloader.setUrl("https://www.reddit.com/r/memes/");

        // Connection should be successful
        assertTrue(downloader.checkConnection());

        downloader.connect();

        // Harvesting should pass
        downloader.harvest(1);
    }
}

