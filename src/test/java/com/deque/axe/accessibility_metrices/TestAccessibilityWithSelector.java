package com.deque.axe.accessibility_metrices;

import com.deque.axe.AXE;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.rules.TestName;
import org.openqa.selenium.WebDriver;

import java.net.URL;

import static org.junit.Assert.assertTrue;

public class TestAccessibilityWithSelector {

    /**
     * Test a specific selector or selectors
     */
    public void testAccessibilityWithSelector(WebDriver driver, URL scriptUrl, TestName testName) {
        JSONObject responseJSON = new AXE.Builder(driver, scriptUrl)
                .include("title")
                .include("p")
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
