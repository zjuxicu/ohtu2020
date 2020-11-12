package ohtu;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import static org.junit.Assert.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class Stepdefs {
    //WebDriver driver = new ChromeDriver();
    WebDriver driver = new HtmlUnitDriver();
    String baseUrl = "http://localhost:4567";
    
    @Given("login is selected")
    public void loginIsSelected() {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("login"));       
        element.click();   
    }

    @Given("command new user is selected")
    public void newUserSelected(){
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("register new user"));
        element.click();
    }
    @Given("user with username {string} with password {string} is successfully created")
    public void newUserCreation(String username, String password) {
        newUserSelected();
        createUserWith(username, password);
    }

    @Given("user with username {string} and password {string} is tried to be created")
    public void invalidCreation(String username, String password) {
        newUserSelected();
        createUserWith(username, password);
    }
    @When("a valid username {string} and password {string} and matching password confirmation are entered")
    public void newValidUser(String username, String password){
        createUserWith(username, password);
    }    

    @Then("a new user is created")
    public void userCreatedSuccess() throws Throwable{
        pageHasContent("Welcome to Ohtu Application!");
    }

    @When("a invalid username {string} and password {string} and matching password confirmation are entered")
    public void invalidUsername(String username, String password){
        createUserWith(username, password);
    }

    @When("correct username {string} and password {string} are given")
    public void correctUsernameAndPasswordAreGiven(String username, String password) {
        logInWith(username, password);
    }    

    @When("nonexistent username {string} and password {string} are given")
    public void nonexistentUsernameAndPasswordAreGiven(String username, String password) {
        logInWith(username, password);
    }   

    @Then("user is logged in")
    public void userIsLoggedIn() throws Throwable {
        pageHasContent("Ohtu Application main page");
    }    

    @When("a valid username {string} and valid password {string} but password confirmation do not match")
    public void confirmationError(String username, String password){
        wrongConfirmation(username, password);
    }

    @When("correct username {string} and incorrect password {string} are given")
    public void correctUsernameAndIncorrectPasswordAreGiven(String username, String password) {
        logInWith(username, password);
    }    

    @When("a valid username {string} and invalid password {string} and matching password confirmation are entered")
    public void passwordTooShort(String username, String password){
        createUserWith(username, password);
    }

    @Then("user is not created and error {string} is reported")
    public void userIsNotCreatedAndErrorMessageIsGiven(String error) throws Throwable{
        pageHasError(error);
    }

    @Then("user is not logged in and error message is given")
    public void userIsNotLoggedInAndErrorMessageIsGiven() throws Throwable {
        pageHasContent("invalid username or password");
        pageHasContent("Give your credentials to login");
    }    

    @When("username {string} and password {string} are given")
    public void usernameAndPasswordAreGiven(String username, String password) throws Throwable {
        logInWith(username, password);
    }   
    
    @Then("system will respond {string}")
    public void systemWillRespond(String pageContent) throws Throwable {
        assertTrue(driver.getPageSource().contains(pageContent));
    }

    @After
    public void tearDown(){
        driver.quit();
    }
        
    /* helper methods */
 
    private void pageHasContent(String content) {
        assertTrue(driver.getPageSource().contains(content));
    }
    private void pageHasError(String error){
        assertTrue(driver.getPageSource().contains(error));
    }
        
    private void logInWith(String username, String password) {
        assertTrue(driver.getPageSource().contains("Give your credentials to login"));
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("login"));
        element.submit();  
    } 
    private void createUserWith(String username, String password){
        assertTrue(driver.getPageSource().contains("Create username and give password"));
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys(password);
        element.submit();
    }
    private void wrongConfirmation(String username, String password){
        assertTrue(driver.getPageSource().contains("Create username and give password"));
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys("ei sama");
        element.submit();
    }
}
