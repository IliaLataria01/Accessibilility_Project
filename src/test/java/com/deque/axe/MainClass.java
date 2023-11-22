package com.deque.axe;

import com.deque.axe.accessibility_metrices.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

public class MainClass {
    @Rule
    public TestName testName = new TestName();

    private WebDriver driver;

    private static final URL scriptUrl = MainClass.class.getResource("/axe.min.js");

    /**
     * Instantiate the WebDriver and navigate to the test site
     */

    private Properties loadProperties(String fileName) {
        Properties properties = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(fileName)) {
            if (input == null) {
                System.out.println("Sorry, unable to find " + fileName);
                return properties;
            }
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    @Before
    public void setUp() {
        // ChromeDriver needed to test for Shadow DOM testing support
        System.setProperty("webdriver.chrome.driver","/Users/ilialataria/Downloads/chromedriver-mac-arm64/chromedriver");
        driver = new ChromeDriver();

        Properties properties = loadProperties("config.properties");
        String baseUrl = properties.getProperty("base.url");

        driver.get(baseUrl);
    }

    /**
     * Ensure we close the WebDriver after finishing
     */
    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void findAccessibilityViolation() {
        TestAccessibility testAccessibility = new TestAccessibility();
        testAccessibility.testAccessibility(driver,scriptUrl,testName);
//
//        TestAccessibilityWithFewInclude testAccessibilityWithFewInclude = new TestAccessibilityWithFewInclude();
//        testAccessibilityWithFewInclude.testAccessibilityWithFewInclude(driver,scriptUrl,testName);
//
//        TestAccessibilityWithIncludesAndExcludes testAccessibilityWithIncludesAndExcludes = new TestAccessibilityWithIncludesAndExcludes();
//        testAccessibilityWithIncludesAndExcludes.testAccessibilityWithIncludesAndExcludes(driver,scriptUrl,testName);
//
//        TestAccessibilityWithIncludesAndExcludesWithViolation testAccessibilityWithIncludesAndExcludesWithViolation =  new TestAccessibilityWithIncludesAndExcludesWithViolation();
//        testAccessibilityWithIncludesAndExcludesWithViolation.testAccessibilityWithIncludesAndExcludesWithViolation(driver,scriptUrl,testName);
//
//        TestAccessibilityWithOptions testAccessibilityWithOptions = new TestAccessibilityWithOptions();
//        testAccessibilityWithOptions.testAccessibilityWithOptions(driver,scriptUrl,testName);
//
//        TestAccessibilityWithSelector testAccessibilityWithSelector = new TestAccessibilityWithSelector();
//        testAccessibilityWithSelector.testAccessibilityWithSelector(driver,scriptUrl,testName);
//
//        TestAccessibilityWithSkipFrames testAccessibilityWithSkipFrames = new TestAccessibilityWithSkipFrames();
//        testAccessibilityWithSkipFrames.testAccessibilityWithSkipFrames(driver,scriptUrl,testName);
//
//        TestAccessibilityWithWebElement testAccessibilityWithWebElement = new TestAccessibilityWithWebElement();
//        testAccessibilityWithWebElement.testAccessibilityWithShadowElement(driver,scriptUrl,testName);

    }




}
