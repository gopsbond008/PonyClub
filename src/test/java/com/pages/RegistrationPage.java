package com.pages;



import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;


import com.abstractPages.AbstractMain;
import cucumber.api.DataTable;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

public class RegistrationPage extends AbstractMain {
    public static By LoginPageCreateAccountButton = By.cssSelector(".downloadsblock-link:nth-child(4) .btn-anim2");
    public static By RegistrationPage = By.cssSelector("#Main");
    public static By AddressDetailsLink = By.cssSelector(".step-wrapper:nth-child(2) .step-wrapper-cta-title");
    public static By TitleDropDown = By.cssSelector(".selectBox.form-control.selectBox-dropdown");
    public static By FirstName = By.cssSelector("#FirstName");
    public static By LastName = By.cssSelector("#LastName");
    public static By TelephoneNumber = By.cssSelector("#TelephoneNumber");
    public static By PostCode = By.cssSelector("#PostCode");
    public static By Address1 = By.cssSelector("#Address1");
    public static By Address2 = By.cssSelector("#Address2");
    public static By Town = By.cssSelector("#Town");
    public static By AccountDetailLink = By.cssSelector(".row:nth-child(7) .button");
    public static By eMail = By.cssSelector("#Email");
    public static By ConfirmEmail = By.cssSelector("#ConfirmEmail");
    public static By Password = By.cssSelector("#Password");
    public static By ConfirmPassword = By.cssSelector("#ConfirmPassword");
    public static By Terms = By.cssSelector("#GDPR");
    public static By Conditions = By.cssSelector("#TsAndCs");
    public static By CreateAccountButton = By.cssSelector(".btn_white_to_red > .btn-anim2");


    public void goToRegistrationPage(String createAnAccount) throws InterruptedException {
       action.clickElement(LoginPageCreateAccountButton);

    }

    public void verifyRegistrationPage() {
        Assert.assertTrue(driver.getCurrentUrl().contains("/registration"));
        Assert.assertTrue(driver.getTitle().contains("Registration - Pony Club"));
        Assert.assertTrue(action.getElementText(RegistrationPage).contains("Registration"));
        Assert.assertTrue(action.getElementText(RegistrationPage).contains("Personal Details"));
        Assert.assertTrue(action.getElementText(RegistrationPage).contains("Address Details"));
        Assert.assertTrue(action.getElementText(RegistrationPage).contains("Account Details"));
        Assert.assertTrue(action.getElementText(RegistrationPage).contains("Terms & Conditions"));
        Assert.assertTrue(action.isElementPresent(CreateAccountButton));

    }

    public void enterPersonalDetails(DataTable personalDetails) throws InterruptedException {

        WebElement dropdown = driver.findElement(TitleDropDown);
        dropdown.click();
        Thread.sleep(5000);
        action.clickElement(By.cssSelector("body > ul.selectBox-dropdown-menu.selectBox-options.selectBox-selectBox-dropdown-menu.form-control-selectBox-dropdown-menu.selectBox-options-bottom > li:nth-child(54) > a"));

        List<Map<String, String>> list = personalDetails.asMaps(String.class, String.class);
        action.sendElement(FirstName, list.get(0).get("FirstName"));
        action.sendElement(LastName, list.get(0).get("LastName"));
        action.sendElement(TelephoneNumber, list.get(0).get("TelephoneNumber"));

    }

    public void enterAddressDetails(DataTable address) throws InterruptedException {

        action.clickElement(AddressDetailsLink);
        Thread.sleep(2000);
        List<Map<String, String>> list = address.asMaps(String.class, String.class);
        action.sendElement(PostCode, list.get(0).get("PostCode"));
//        action.sendElement(Address1, list.get(0).get("Address 1"));
//        action.sendElement(Address2, list.get(0).get("Address 2"));
//        action.sendElement(Town, list.get(0).get("Town"));
        Thread.sleep(5000);
        action.clickElement(By.cssSelector("li:nth-child(3) span:nth-child(1)"));
        Thread.sleep(5000);
    }

    public void enterAccountDetails(DataTable account) throws InterruptedException {
        action.clickElement(By.cssSelector(".step-wrapper:nth-child(3) .step-wrapper-cta-title"));
        Random rn = new Random();
        int rNumber = rn.nextInt(10000 - 10 + 1) + 1;
        String rNum = String.valueOf(rNumber);
        Thread.sleep(5000);
        List<Map<String, String>> list = account.asMaps(String.class, String.class);
        action.sendElement(eMail, rNum+list.get(0).get("eMail"));
        action.sendElement(ConfirmEmail, rNum+list.get(0).get("ConfirmEmail"));
        action.sendElement(Password, list.get(0).get("Password"));
        action.sendElement(ConfirmPassword, list.get(0).get("ConfirmPassword"));
        Thread.sleep(5000);


    }
    public void acceptTermsAndConditions() throws InterruptedException {
        action.clickElement(By.cssSelector(".row:nth-child(6) .btn-anim2"));
        Thread.sleep(5000);
        action.clickElement(Terms);
        action.clickElement(Conditions);
        Thread.sleep(5000);

    }

    public void submitRegistration(String createAccount) throws InterruptedException {

        action.clickElement(CreateAccountButton);
        Thread.sleep(5000);
    }
    public void verifyRegistrationConfirmation(String thankYou) throws InterruptedException {
        Assert.assertTrue(action.getElementText(RegistrationPage).contains(thankYou));
        Assert.assertTrue(action.getElementText(RegistrationPage).contains("An email has been sent to you to enable you to activate your account. Please click the link in the email to go to the Login Page"));
        Thread.sleep(5000);
    }

}
