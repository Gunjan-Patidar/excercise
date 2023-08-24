package excercise;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FileUpload {
	
	public static void main(String []args) throws InterruptedException, IOException {
	String downloadPath=System.getProperty("user.dir");
System.setProperty("webdriver.chrome.driver", "C:\\Users\\hp\\Desktop\\Drivers\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		
		options.addArguments("--remote-allow-origins=*");
	HashMap<String, Object> chromePrefs = new HashMap<String, Object>();

       chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("download.default_directory", downloadPath);
		
		options.setExperimentalOption("prefs", chromePrefs);
		WebDriver driver=new ChromeDriver(options);
		
		driver.get("https://www.ilovepdf.com/pdf_to_jpg");
		driver.findElement(By.cssSelector("a[id='pickfiles']")).click();
		Thread.sleep(3000);
		
		Runtime.getRuntime().exec("C:\\Users\\hp\\Documents\\fileupload.exe");
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[id='processTask']")));
		
		driver.findElement(By.cssSelector("button[id='processTask']")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[id='pickfiles']")));
		driver.findElement(By.cssSelector("a[id='pickfiles']")).click();
		Thread.sleep(5000);
		File f=new File(downloadPath+"/ilovepdf_pages-to-jpg.zip");
		//"C:\\Users\\hp\\Downloads\\ilovepdf_pages-to-jpg.zip"
		if(f.exists())
		{
			System.out.println("file found");
		}
		if(f.delete())
		{
			System.out.println("file deleted");
		}
		
		
		
		
	}
	

}
