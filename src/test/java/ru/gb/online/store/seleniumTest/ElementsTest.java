package ru.gb.online.store.seleniumTest;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class ElementsTest extends BaseTest {

    /**
     Задание 3.
     Написать selenium test, страницу c товарами (что там находятся товары)
     */

    @DataProvider(name = "urlClassName")
    public Object[][] urlClassName() {
        return new Object[][]{
                {"http://127.0.0.1:5000/shop", ".product_card"},
                {"http://127.0.0.1:5000/shop", ".card_img"},
                {"http://127.0.0.1:5000/shop", ".product_card_content"},
                {"http://127.0.0.1:5000/shop", ".product_card_heading"},
                {"http://127.0.0.1:5000/shop", ".product_card_info"},
        };
    }

    @Test(dataProvider = "urlClassName")
    public void find(String url, String className) {
        Assert.assertTrue (driver.findElementByCssSelector (url, className));
    }


    @DataProvider(name = "sizeUrlClassName")
    public Object[][] sizeUrlClassName() {
        return new Object[][]{
                { 1, "http://127.0.0.1:5000/shop?size=1", ".product_card"},
                { 10, "http://127.0.0.1:5000/shop?size=10", ".card_img"},
                { 50, "http://127.0.0.1:5000/shop?size=50", ".product_card_content"},
                { 100, "http://127.0.0.1:5000/shop?size=100", ".product_card_heading"},
                { 200,"http://127.0.0.1:5000/shop?size=200", ".product_card_info"},
        };
    }

    @Test (dataProvider = "sizeUrlClassName")
    public void pageSizeTest(int size, String url, String className) {
        Assert.assertEquals (size, driver.findElementsByCssSelector (url, className));
    }

}
