package org.planit.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;

public class CartPage extends BasePage {

    @FindBy(how = How.XPATH, using = "//tr[@ng-repeat='item in cart.items()']")
    private List<WebElement> tableRows;

    @FindBy(how = How.XPATH, using = "//a[text()='Check Out']")
    private WebElement checkOutBtn;

    @FindBy(how=How.XPATH, using = "//strong[contains(@class, 'total')]")
    private WebElement total;

    @FindBy(how=How.XPATH, using = "//td[4]")
    private List<WebElement> subTotalColumn;

    public CartPage(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(webDriver, this);
        new WebDriverWait(webDriver, Duration.ofSeconds(pageTimeout))
                .until(ExpectedConditions.elementToBeClickable(checkOutBtn));
        logger.info("Cart Page is displayed");
    }

    public HashMap<String, String> getItemDetails(String name) {
        for (WebElement tableRow : tableRows) {
            try {
                WebElement item = tableRow.findElement(By.xpath("./td[contains(text(),'" + name + "')]"));
                List<WebElement> pricingElements = item.findElements(By.xpath("parent::tr/td[starts-with(text(), '$')]"));
                HashMap<String, String> itemDetails = new HashMap<>();
                itemDetails.put("itemPrice", pricingElements.get(0).getText());
                itemDetails.put("itemSubTotal", pricingElements.get(1).getText());
                itemDetails.put("itemQuantity",  item.findElement(By.xpath("parent::tr/td/input[@name='quantity']")).getAttribute("value"));
                logger.info("Details for " + name + " itemPrice " + itemDetails.get("itemPrice")
                        + " itemQuantity " + itemDetails.get("itemQuantity")
                + " itemSubTotal " + itemDetails.get("itemSubTotal"));
                return itemDetails;
            } catch (Exception e) { }
        }
        return null;
    }

    public String getTotalOnPage() {
        return total.getText().substring(7);
    }
    public Double getTotalOfSubTotal() {
        return subTotalColumn.stream().map(value -> Double.parseDouble(value.getText().substring(1)) )
                .reduce(0.00, Double::sum);

    }
}
