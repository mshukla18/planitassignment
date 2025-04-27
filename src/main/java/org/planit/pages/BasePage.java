package org.planit.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

public abstract class BasePage {
    protected WebDriver webDriver;
    protected static Logger logger = LogManager.getLogger(BasePage.class);
    protected int pageTimeout = 10;
    public BasePage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }
}
