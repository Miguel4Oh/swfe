package com.ortega.webapp.servlet.swfe.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ApplicationTest {

    WebDriver driver;

    @BeforeEach
    void getUp() {
        driver = new ChromeDriver();
    }

//    @Test
//    public void testApplication() {
//        // Implement your test logic here
//        // For example, you can navigate to a URL and perform assertions
//        driver.get("http://swfe.site/swfe/login.jsp");
//        // Add assertions to verify the page title or other elements
//        //String title = driver.getTitle();
//        //assert title.equals("Expected Title");
//    }
}
