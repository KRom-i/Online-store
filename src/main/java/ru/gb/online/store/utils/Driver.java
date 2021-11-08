package ru.gb.online.store.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;


public class Driver extends ChromeDriver {

    public boolean findElementByCssSelector(String url, String className){
        get(url);
        return findElement(By.cssSelector(className)).isEnabled ();
    }

    public int findElementsByCssSelector(String url, String className){
        get(url);
        return findElements(By.cssSelector(className)).size ();
    }


}
