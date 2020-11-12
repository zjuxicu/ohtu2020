package ohtu;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.Random;

public class Tester {
    static Random r;
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.get("http://localhost:4567");
        
        sleep(2);
        
        //WebElement element = driver.findElement(By.linkText("login"));
        WebElement element = driver.findElement(By.linkText("register new user"));
        element.click();

        sleep(2);

        r = new Random();
        element = driver.findElement(By.name("username"));
        element.sendKeys("arto"+r.nextInt(100000)); 
        //element.sendKeys("tero");
        element = driver.findElement(By.name("password"));
        element.sendKeys("akkep");
        //element.sendKeys("terotero");
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys("akkep");
        //element = driver.findElement(By.name("login"));
        element = driver.findElement(By.name("signup"));
        
        sleep(3);
        element.submit();
        element = driver.findElement(By.linkText("continue to application mainpage"));
        element.click();
        sleep(2);
        element = driver.findElement(By.linkText("logout"));
        element.click();
        sleep(3);
        
        driver.quit();
    }
    
    private static void sleep(int n){
        try{
            Thread.sleep(n*1000);
        } catch(Exception e){}
    }
}
