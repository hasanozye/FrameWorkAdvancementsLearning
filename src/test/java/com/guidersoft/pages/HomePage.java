package com.guidersoft.pages;

import com.guidersoft.utility.Browsers;
import com.guidersoft.utility.Driver;
import com.guidersoft.utils.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.guidersoft.utils.Locators.*;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class HomePage extends BaseTest {


    public HomePage(WebDriver driver) {
        super.driver = driver;
        wait = new WebDriverWait(super.driver, Duration.ofSeconds(10));

    }

    public HomePage() {
        this(Driver.getDriver(Browsers.CHROME));
    }

    public void open(String url) {
        driver.get(url);
    }

    public void login(String username, String password) {
        waitForPresence(lLoginLink);
        click(lMyAccount);
        click(lLoginLink);
        sendKeys(lLoginForUsername, username);
        sendKeys(lLoginFormPassword, password);
        click(lLoginFormSubmitButton);
        waitForPresence(lLogOutlink);
    }

    public void logout() {
        waitForPresence(lLogOutlink);
        click(lMyAccount);
        click(lLogOutlink);
        waitForPresence(lLoginLink);

    }

    public void search(String text) {
        sendKeys(lSearchInput, text);
        click(lSearchButton);
        wait.until(ExpectedConditions.titleContains("Search"));
    }

    public List<WebElement> getSearchList() {
        return driver.findElements(lListedProducts);
    }

    public List<WebElement> getSearchList(String text) {
        return getSearchList()
                .stream()
                .filter(e -> e.getText().contains(text))
                .collect(Collectors.toList());
    }


    public void walkOverMenu() {
        List<WebElement> menus = driver.findElements(lMainMenuLinks);
        menus.forEach(e -> {
            hover(e);
            if (e.findElements(By.xpath(".//ul")).size() > 0) {
                List<WebElement> subList = e.findElements(By.xpath(".//li"));
                subList.forEach(s -> {
                    hover(s);
                    actionWait(500);
                });
            }
            actionWait(500);
        });
    }


}
