package com.epam.framework.ui.driver;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {
    @Before
    public void setUp() {
        DriverManager.initializeDriver("chrome", "latest", "Windows 10", "local", null);
    }

    @After
    public void tearDown() {
    }

    @After("@Last")
    public void tearDownAfterAll() {
        DriverManager.quitDriver();
    }
}
