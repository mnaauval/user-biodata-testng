package com.nexsoft.utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenShoot {

	public String screenShoot(WebDriver driver) {
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String waktu = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		String namaFile = "D:\\Project\\Java\\TestNG\\UserBiodata\\src\\test\\resources\\ss\\" + waktu + ".PNG";
		File screenshoot = new File(namaFile);
		try {
			FileUtils.copyFile(srcFile, screenshoot);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return namaFile;
	}
}
