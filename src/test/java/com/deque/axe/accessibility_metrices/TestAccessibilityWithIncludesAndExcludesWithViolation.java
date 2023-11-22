package com.deque.axe.accessibility_metrices;

import com.deque.axe.AXE;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.rules.TestName;
import org.openqa.selenium.WebDriver;

import java.net.URL;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestAccessibilityWithIncludesAndExcludesWithViolation {

    /**
     * Test includes and excludes with violation
     */
    @Test
    public void testAccessibilityWithIncludesAndExcludesWithViolation(WebDriver driver, URL scriptUrl, TestName testName) {
        JSONObject responseJSON = new AXE.Builder(driver, scriptUrl)
                .include("body")
                .exclude("div")
                .analyze();

        JSONArray violations = responseJSON.getJSONArray("violations");

        JSONArray nodes = ((JSONObject)violations.get(0)).getJSONArray("nodes");
        JSONArray target = ((JSONObject)nodes.get(0)).getJSONArray("target");

        if (violations.length() == 1) {
            assertEquals(String.valueOf(target), "[\"span\"]");
        } else {
            AXE.writeResults(testName.getMethodName(), responseJSON);
            assertTrue("No violations found", false);
        }
    }
}
