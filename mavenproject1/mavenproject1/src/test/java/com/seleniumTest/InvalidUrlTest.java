/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seleniumTest;

import com.myproject.mavenproject1.Script;
import java.net.ConnectException;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
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
    public void testSimpleAdblockInstall() throws Exception {
        setup();

        downloader.setUrl("https://www.reddit.com/r/memes/invalidurl");

        // Connection shouldn't be successful
        // after adblock is installed, error is shown
        assertFalse(downloader.checkConnection(5));
  
        // Harvesting shouldn't pass
        // showing us error message based in "harvest" method
        assertThrows(ConnectException.class, () -> downloader.harvest(), "Connect exception");
        
    }
}