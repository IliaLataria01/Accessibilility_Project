package com.deque.axe.accessibility_metrices;

import com.deque.axe.AXE;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.rules.TestName;
import org.openqa.selenium.WebDriver;

import java.net.URL;

import static org.junit.Assert.assertTrue;

public class TestAccessibilityWithIncludesAndExcludes {

    /**
     * Test includes and excludes
     */
    @Test
    public void testAccessibilityWithIncludesAndExcludes(WebDriver driver, URL scriptUrl, TestName testName) {
        JSONObject responseJSON = new AXE.Builder(driver, scriptUrl)
                .include("body")
                .exclude("h1")
                .analyze();

        JSONArray violations = responseJSON.getJSONArray("violations");

        if (violations.length() == 0) {
            assertTrue("No violations found", true);
        } else {
            AXE.writeResults(testName.getMethodName(), responseJSON);
            assertTrue(AXE.report(violations), false);
        }
    }
}
