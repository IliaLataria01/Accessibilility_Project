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

public class TestAccessibilityWithWebElement {

    /**
     * Test a page with Shadow DOM violations
     */
    public void testAccessibilityWithShadowElement(WebDriver driver, URL scriptUrl, TestName testName) {
//        driver.get("http://localhost:5005/shadow-error.html");

        JSONObject responseJSON = new AXE.Builder(driver, scriptUrl).analyze();

        JSONArray violations = responseJSON.getJSONArray("violations");

        JSONArray nodes = ((JSONObject)violations.get(0)).getJSONArray("nodes");
        JSONArray target = ((JSONObject)nodes.get(0)).getJSONArray("target");

        if (violations.length() == 1) {
//			assertTrue(AXE.report(violations), true);
            assertEquals(String.valueOf(target), "[[\"#upside-down\",\"ul\"]]");
        } else {
            AXE.writeResults(testName.getMethodName(), responseJSON);
            assertTrue("No violations found", false);

        }
    }
}
