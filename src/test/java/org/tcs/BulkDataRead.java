package org.tcs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BulkDataRead {
	
	public static void main(String[] args) throws IOException, InterruptedException {
		
		WebDriver driver=new ChromeDriver();
		driver.get("https://www.facebook.com/campaign/landing.php?campaign_id=14884913640&extra_1=s%7Cc%7C550525804929%7Cb%7Cfacebook%7C&placement=&creative=550525804929&keyword=facebook&partner_id=googlesem&extra_2=campaignid%3D14884913640%26adgroupid%3D128696220912%26matchtype%3Db%26network%3Dg%26source%3Dnotmobile%26search_or_content%3Ds%26device%3Dc%26devicemodel%3D%26adposition%3D%26target%3D%26targetid%3Dkwd-592856129%26loc_physical_ms%3D9153011%26loc_interest_ms%3D%26feeditemid%3D%26param1%3D%26param2%3D&gad_source=1&gclid=Cj0KCQiAoeGuBhCBARIsAGfKY7wLdOQw9qJxAZWK448Uya2xkgPLhxp1Bal9wC5e_Bx-xblevJhwa-AaAlEuEALw_wcB");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5)); 
		driver.manage().window().maximize();
	
		
		File execLoc=new File("D:\\LogcalProgramme\\ExcelBulkData\\excelfiles\\DataDriven_MultiValues.xlsx");
		
		FileInputStream f=new FileInputStream(execLoc);
		
		Workbook w=new XSSFWorkbook(f);
		
		Sheet sheet = w.getSheet("Multi");
		int rowCount = sheet.getLastRowNum();
		short colCount= sheet.getRow(1).getLastCellNum(); 
		System.out.println("RowCount: "+rowCount+" ColumnCount: "+colCount);
		
		for (int i = 1; i <= rowCount; i++) {
			
			Row row = sheet.getRow(i);
			
			String fName = row.getCell(0).getStringCellValue();
			String lName = row.getCell(1).getStringCellValue();
			String eMail = row.getCell(2).getStringCellValue();
			String txtPass = row.getCell(3).getStringCellValue();
			
			
			driver.findElement(By.name("firstname")).clear();
			driver.findElement(By.name("firstname")).sendKeys(fName);
			
			
			driver.findElement(By.name("lastname")).clear();
			driver.findElement(By.name("lastname")).sendKeys(lName);
			
		
			driver.findElement(By.name("reg_email__")).clear();
			driver.findElement(By.name("reg_email__")).sendKeys(eMail);
			
			driver.findElement(By.name("reg_email_confirmation__")).clear();
			driver.findElement(By.name("reg_email_confirmation__")).sendKeys(eMail);
			
		
			driver.findElement(By.id("password_step_input")).clear();;
			driver.findElement(By.id("password_step_input")).sendKeys(txtPass);
			
			System.out.println(i+"."+fName+" || "+lName+" || "+ eMail+ " || "+ txtPass);
			
			Thread.sleep(1500);
	 
		}
		driver.quit();
		}
		
	}

