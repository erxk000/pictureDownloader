/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seleniumTest;

import com.myproject.mavenproject1.Script;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;

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

        // Clean target directory
        for (File file : new File(downloader.getTargetPath()).listFiles()) {
            file.delete();
        }
    }
    
    @Test
    public void testVerifyFiles() throws Exception {
        setup();
        downloader.setUrl("https://www.reddit.com/r/memes/");

        // Connection should be successful
        assertTrue(downloader.checkConnection());

        // Init downloader for 3 images
        int requestedCount = 3;
        downloader.connect();
        downloader.harvest(requestedCount);

        // Verify that requested amount of images has been downloaded
        File targetDirectory = new File(downloader.getTargetPath());
        int numberOfFiles = targetDirectory.list().length;
        assertEquals(requestedCount, targetDirectory.list().length);
    }
}