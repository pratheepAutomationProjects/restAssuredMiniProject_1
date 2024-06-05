package Utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class extentReports {

	public static ExtentReports getReportObject() {
		String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.SS").format(new Date());
		String extentReporter= ConstantsHelper.ExtentReportsPath + timestamp + ".html";
		 ExtentSparkReporter  esr = new ExtentSparkReporter(extentReporter);
		 esr.config().setDocumentTitle("Automation RestAssured Report");
		 esr.config().setReportName("Automation Report by PR");
		 ExtentReports er = new ExtentReports();
		 er.attachReporter(esr);
		 er.setSystemInfo("Hostname", "aven");
		 er.setSystemInfo("username", "root");
		 return er;
	}
	
}
