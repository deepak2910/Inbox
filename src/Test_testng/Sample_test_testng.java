package Test_testng;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;


public class Sample_test_testng
{
	private  Date d=new Date();
	private  long time;
	static WebDriver obj=new FirefoxDriver();
	@BeforeTest
	 void open_browser()
	{
		obj.get("https://mail.google.com/");
		
	}
	
	void input_fields()
	{
		WebElement username=obj.findElement(By.name("Email"));
		username.sendKeys("testmail291993@gmail.com");
		WebElement passwd=obj.findElement(By.name("Passwd"));
		passwd.sendKeys("deepak123!");
		WebElement submit=obj.findElement(By.name("signIn"));
		submit.click();
	}
	
	void send_mail()
	{
		time=d.getTime();
		obj.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		WebElement compose=obj.findElement(By.xpath(".//*[@class='T-I J-J5-Ji T-I-KE L3']"));
		compose.click();
		WebElement to=obj.findElement(By.name("to"));
		to.sendKeys("testmail291993@gmail.com");
		WebElement mail_content=obj.findElement(By.xpath("//input[@class='aoT']"));
		mail_content.sendKeys(String.valueOf(time));
		obj.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement send=obj.findElement(By.xpath(".//*[@class='T-I J-J5-Ji aoO T-I-atl L3']"));
		send.click();
		
		
	}
	
	 int check_mail_inbox()
	{
		obj.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		WebElement inbox=obj.findElement(By.xpath(".//div[@class='aio UKr6le']/span[1][@class='nU n1']/a[@tabindex='0']"));
		inbox.click();
		obj.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		System.out.println("Inbox refreshed!!");
		try
		{
			Thread.sleep(5000);
		}
		catch(Exception e){}
		WebElement mail=obj.findElement(By.xpath(".//*[@id=':2m']/div[2]/div/table/tbody/tr[1]/td[6]//b"));
		System.out.println("Sent mail id:"+String.valueOf(time));
		System.out.println("Recieved mail id:"+mail.getText());
		if(String.valueOf(time).equals(mail.getText()))
		{
			return 1;
		}
		else
		{
			return 0;
		}
		
	}
	 
	@Test
	public void run_test()
	{
		
		input_fields();
		send_mail();
		int flag=check_mail_inbox();
		Assert.assertEquals(flag, 1);
		close_window();
		
	}
	@AfterTest
	void close_window()
	 {
		 obj.close();
	 }
}
