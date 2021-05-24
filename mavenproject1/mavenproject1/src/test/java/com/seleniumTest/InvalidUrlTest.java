/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seleniumTest;

import com.myproject.mavenproject1.Script;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.ConnectException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author hanib
 */
public class InvalidUrlTest {

    private Script downloader;

    public void setup() {
        downloader = new Script();
        DesiredCapabilities capabilities = downloader.setUpAdblock();
        downloader.initDriver(capabilities);
    }

    @Test
    public void testInvalidUrl() throws Exception {
        setup();

        downloader.setUrl("https://www.reddit.com/r/memes/invalidurl");

        // Test that invalid URL is catched during connectivity check
        assertFalse(downloader.checkConnection());

        // If connectivity check fails, the following should as well
        assertThrows(TimeoutException.class, () -> downloader.connect(), "TimeoutException was expected");
        assertThrows(ConnectException.class, () -> downloader.harvest(1), "ConnectException was expected");
    }
}

