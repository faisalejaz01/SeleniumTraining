package BasicExercisesTestNG;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class NewTest {

	WebDriver driver;

	@BeforeTest
	public void invokeBrowser() {

		String chromeDriverPath = "d:\\work\\tools\\chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", chromeDriverPath);

		driver = new ChromeDriver();

		// driver.manage().deleteAllCookies();
		// driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}

	// Exercise 1 and 5.
	@Test(priority = 1)
	public void testLogin() {

		driver.get("https://the-internet.herokuapp.com/login");
		driver.findElement(By.id("username")).sendKeys("tomsmith");
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("SuperSecretPassword!");
		driver.findElement(By.xpath("//button[@type='submit']")).submit();

		String validate = driver.findElement(By.xpath("//h4[@class='subheader']")).getText();

		Assert.assertEquals(validate, "Welcome to the Secure Area. When you are done click logout below.",
				("Test 1: Login Failed!"));

	}

	@Test(priority = 2)
	public void testDropDown() {

		driver.navigate().to("http://the-internet.herokuapp.com/dropdown");

		Select dropDownMenu = new Select(driver.findElement(By.id("dropdown")));
		dropDownMenu.selectByVisibleText("Option 1");

	}

	@Test(priority = 3)
	public void testRadioButton() {

		driver.navigate().to("http://orasi.github.io/Chameleon/sites/unitTests/orasi/core/interfaces/radioGroup.html");

		List<WebElement> MaleRadioByIndex = driver.findElements(By.id("genderm"));
		MaleRadioByIndex.get(0).click();

		WebElement radioButtonFemale = driver.findElement(By.id("genderf"));
		radioButtonFemale.click();

	}

	@Test(priority = 4)
	public void testWebTables() {
		driver.navigate().to("https://the-internet.herokuapp.com/tables");
		WebElement table1 = driver.findElement(By.id("table1"));
		table1.findElement(By.xpath("//table[@id='table1']//td[contains(text(),'jdoe@hotmail.com')]"));
		table1.findElement(By.xpath("//table[@id='table1']//tbody//tr[3]//td[6]//a[2]")).click();

	}

	@Test(priority = 6)
	public void testDynamicWait() {
		driver.navigate().to("https://the-internet.herokuapp.com/dynamic_loading/1");

		WebDriverWait wait = new WebDriverWait(driver, 10);

		WebElement start = driver.findElement(By.xpath("//button[contains(text(),'Start')]"));
		start.click();

		wait.until(ExpectedConditions.textToBePresentInElement(driver.findElement(By.xpath("//div[@id='finish']")),
				"Hello World!"));

	}

	@Test(priority = 7)
	public void testAlerts() {

		driver.navigate().to("https://the-internet.herokuapp.com/javascript_alerts");

		WebElement jsConfirmBTN = driver.findElement(By.xpath("//button[@onclick='jsConfirm()']"));
		jsConfirmBTN.click();

		driver.switchTo().alert().accept();

	}

	@Test(priority = 8)
	public void testWindows() {

		driver.navigate().to("https://the-internet.herokuapp.com/windows");
		String childWindow = null;
		String parentWindow = driver.getWindowHandle();

		driver.findElement(By.xpath("//a[contains(text(),'Click Here')]")).click();

		Set<String> allWindows = driver.getWindowHandles();
		int count = allWindows.size();
		System.out.println("Windows Count: " + count);

		for (String temp : allWindows) {
			if (!temp.equals(parentWindow)) {
				childWindow = temp;
			}
		}

		System.out.println("Parent Window: " + parentWindow);
		System.out.println("Child Window: " + childWindow + "\n");

		driver.switchTo().window(childWindow);

		String newWindowText = driver.findElement(By.xpath("//div[@class='example']")).getText();
		System.out.println("Text from new window: " + newWindowText + "\n");
		driver.close();
		driver.switchTo().window(parentWindow);


	}

	@Test(priority = 9)
	public void testFileUpload() {

		File filePath = new File(".\\uploadTest.txt");
		String absPath = filePath.getAbsolutePath();

		driver.navigate().to("https://the-internet.herokuapp.com/upload");
		WebElement chooseFile = driver.findElement(By.id("file-upload"));
		WebElement uploadButton = driver.findElement(By.id("file-submit"));

		chooseFile.sendKeys(absPath);
		uploadButton.submit();

		String expectedResult = "File Uploaded!";
		String actualResult = driver.findElement(By.xpath("//h3[contains(text(),'File Uploaded!')]")).getText();

		Assert.assertEquals(actualResult, expectedResult);

	}

	@Test(priority = 10)
	public void testWaitForHiddenObject() throws InterruptedException {

		driver.navigate().to("https://the-internet.herokuapp.com/dynamic_loading/2");

		WebElement startButton = driver.findElement(By.xpath("//button[contains(text(),'Start')]"));
		startButton.click();

		String expectedResult = "Hello World!";
		String actualResult = driver.findElement(By.xpath("//h4[contains(text(),'Hello World!')]")).getText();

		Assert.assertEquals(actualResult, expectedResult);

	}

	@Test(priority = 11)
	public void testWaitForTextAttribute() {

		driver.navigate().to("https://the-internet.herokuapp.com/dynamic_loading/2");

		WebElement startButton = driver.findElement(By.xpath("//button[contains(text(),'Start')]"));
		startButton.click();

		WebDriverWait wait = new WebDriverWait(driver, 5000L);

		WebElement loadingBar = driver.findElement(By.id("loading"));
		wait.until(ExpectedConditions.invisibilityOf(loadingBar));
		driver.close();

	}

}
