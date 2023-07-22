package com.guidersoft.test;

import com.guidersoft.pages.HomePage;
import com.guidersoft.utility.Browsers;
import com.guidersoft.utility.Driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static com.guidersoft.utils.Locators.*;

public class Tests {

    WebDriver driver;

    @Test
    public void loginTest() {
        driver = Driver.getDriver(Browsers.EDGE);
        HomePage homePage = new HomePage(driver);
        homePage.open(url);
        homePage.login("hasanozyer@acme.com", "hasanozyerDeneme");
        homePage.logout();
        driver.quit();
    }

    @Test
    public void searchTest() {
        HomePage homePage = new HomePage();
        homePage.open(url);
        homePage.search("mac");
        List<WebElement> list = homePage.getSearchList();
        Assert.assertEquals(list.size(), 4);

        List<WebElement> listMacBook = homePage.getSearchList("MacBook");
        Assert.assertEquals(listMacBook.size(), 3);
        Driver.getDriver().quit();


    }

    @Test
    public void hoverMenu(){
        HomePage homePage = new HomePage();
        homePage.open(url);
        homePage.walkOverMenu();
        Driver.getDriver().quit();
    }

}
