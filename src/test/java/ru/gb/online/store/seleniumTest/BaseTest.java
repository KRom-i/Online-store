package ru.gb.online.store.seleniumTest;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import ru.gb.online.store.utils.Driver;

public class BaseTest {

    Driver driver;

    @BeforeMethod
    public void setUp() {
        this.driver = new Driver ();
//        this.driver.manage().window().maximize();
    }

    @AfterMethod
    public void shutdown() {
        driver.quit();
    }
}
