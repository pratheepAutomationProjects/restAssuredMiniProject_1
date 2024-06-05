package Utils;

import java.io.File;
import org.testng.IAnnotationTransformer;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.ITestAnnotation;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

public class SuiteListener  implements ITestListener, IAnnotationTransformer {
	ExtentReports extent = extentReports.getReportObject();
	ExtentTest test;
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test);
	}

	// On Test Failures adds screenshots
	public void onTestFailure(ITestResult result) {
		String filePath = null;
		extentTest.get().log(Status.FAIL,
				MarkupHelper.createLabel(result.getName() + "-TestCase failed", ExtentColor.RED));
		extentTest.get().log(Status.FAIL,
				MarkupHelper.createLabel(result.getThrowable() + "-TestCase failed", ExtentColor.RED));
	
		// screenshot path here
		filePath = System.getProperty("user.dir") + File.separator + "Screenshot" + File.separator +result.getMethod().getMethodName() + ".png";
		extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
	}

	public void onTestSuccess(ITestResult result) {
		extentTest.get().log(Status.PASS,
				MarkupHelper.createLabel(result.getName() + "-TestCase passed", ExtentColor.GREEN));
	}

	public void onTestSkipped(ITestResult result) {
		extentTest.get().log(Status.FAIL,
				MarkupHelper.createLabel(result.getName() + "-TestCase failed", ExtentColor.ORANGE));
	}

	public void onFinish(ITestContext context) {
		extent.flush();
	}

	// Retry on Test Failures
	public void transform(ITestAnnotation annotation) {
		// not implemented
		annotation.setRetryAnalyzer(RetryAnlyzer.class);
	}

}
