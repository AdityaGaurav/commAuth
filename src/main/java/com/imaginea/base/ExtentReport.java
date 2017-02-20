package com.imaginea.base;

import java.awt.Color;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.IReporter;
import org.testng.IResultMap;
import org.testng.IRetryAnalyzer;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.ITestAnnotation;
import org.testng.internal.annotations.IAnnotationTransformer;
import org.testng.xml.XmlSuite;

import com.imaginea.setUp.BaseClass;
import com.madgag.gif.fmsware.AnimatedGifEncoder;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


public class ExtentReport implements IReporter, ITestListener, IRetryAnalyzer, IAnnotationTransformer {
    private static ExtentReports extent;
    private String screenShotNameWithTimeStamp;
    static Map<String, String> map = new HashMap<>();
    private int retryCount = 0;
    private int maxRetryCount = 1;

    /**
     * Retry failure Test Case Only.
     * 
     */
    public boolean retry(ITestResult result) {
        if (retryCount < maxRetryCount) {
            System.out.println("Retrying test " + result.getName() + " with status "
                    + getResultStatusName(result.getStatus()) + " for the " + (retryCount + 1) + " time(s).");
            retryCount++;
            return true;
        }
        return false;
    }

    /**
     * Add Retry listener to each test case
     * 
     */
    @Override
    public void transform(ITestAnnotation testannotation, Class testClass, Constructor testConstructor,
            Method testMethod) {
        IRetryAnalyzer retry = testannotation.getRetryAnalyzer();

        if (retry == null) {
            testannotation.setRetryAnalyzer(ExtentReport.class);
        }

    }

    /**
     * Get Status of Test Result
     * 
     * @param status
     * @return
     */
    public String getResultStatusName(int status) {
        String resultName = null;
        if (status == 1)
            resultName = "SUCCESS";
        if (status == 2)
            resultName = "FAILURE";
        if (status == 3)
            resultName = "SKIP";
        return resultName;
    }

    /**
     * Generate HTML Report
     */
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
        extent = new ExtentReports(outputDirectory + File.separator + "mViewerTestReport.html", true);

        for (ISuite suite : suites) {
            Map<String, ISuiteResult> result = suite.getResults();

            for (ISuiteResult r : result.values()) {
                ITestContext context = r.getTestContext();
                buildTestNodes(context.getPassedTests(), LogStatus.PASS);
                buildTestNodes(context.getFailedTests(), LogStatus.FAIL);
                buildTestNodes(context.getSkippedTests(), LogStatus.SKIP);
            }
        }
        extent.flush();
        extent.close();
    }

    /**
     * Build HTML Report
     * 
     * @param tests
     * @param status
     */
    private void buildTestNodes(IResultMap tests, LogStatus status) {
        ExtentTest test;
        if (tests.getAllResults().size() > 0) {
            for (ITestResult result : tests.getAllResults()) {
                test = extent.startTest(result.getMethod().getMethodName());

                test.getTest().setStartedTime(getTime(result.getStartMillis()));
                test.getTest().setEndedTime(getTime(result.getEndMillis()));

                for (String group : result.getMethod().getGroups())
                    test.assignCategory(group);

                String message = "Test " + status.toString().toLowerCase() + "ed";

                if (result.getThrowable() != null)
                    message = result.getThrowable().getMessage();
                String failedScreenPng = null;

                failedScreenPng = System.getProperty("user.dir")
                        + "\\target\\screenshot\\" + BaseClass.getDriverInstance()
                                .get(result.getMethod().getMethodName()).getCapabilities().getBrowserName()
                        + result.getName() + "\\" + result.getName() + "_failed" + ".png";

                try {
                    createGif(new File(failedScreenPng));
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                String failedScreenGif = System.getProperty("user.dir")
                        + "\\target\\screenshot\\" + BaseClass.getDriverInstance()
                                .get(result.getMethod().getMethodName()).getCapabilities().getBrowserName()
                        + result.getName() + "\\" + result.getName() + "_failed" + ".gif";
                String imgSrc = "";
                if (new File(failedScreenGif).isFile()) {

                    imgSrc = "<div class='col l4 m6 s12'><div class='card-panel'><img src=" + failedScreenGif
                            + " style=\"width:304px%;height:228px;\"></div></div>";
                }
                test.log(status, message, imgSrc + map.get(result.getMethod().getMethodName()).toString());
                extent.endTest(test);
            }
        }
    }

    private Date getTime(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.getTime();
    }

    public void onTestStart(ITestResult result) {

    }

    /**
     * Store instance of driver to be shown in report
     */
    public void onTestSuccess(ITestResult result) {
        // TODO Auto-generated method stub
        map.put(result.getMethod().getMethodName(),
                BaseClass.getDriverInstance().get(result.getMethod().getMethodName()).toString());
        System.out.println(result.getMethod().getMethodName() + "==>"+ getResultStatusName(result.getStatus()));
    }

    /**
     * Capture Screen Shot on Failure and attach in HTML
     */
    public void onTestFailure(ITestResult result) {
        map.put(result.getMethod().getMethodName(),
                BaseClass.getDriverInstance().get(result.getMethod().getMethodName()).toString());
        System.out.println(result.getMethod().getMethodName() + "==>"+ getResultStatusName(result.getStatus()));
        try {
            if (!result.isSuccess()) {
                captureScreenShot(result.getName(),
                        BaseClass.getDriverInstance().get(result.getMethod().getMethodName()), result.getName());
            }
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * Capture screen shot
     * 
     * @param screenShotName
     * @param driver
     * @param methodName
     * @throws InterruptedException
     * @throws IOException
     */
    private void captureScreenShot(String screenShotName, RemoteWebDriver driver, String methodName)
            throws InterruptedException, IOException {
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        screenShotNameWithTimeStamp = currentDateAndTime();
        String androidModel = screenShotNameWithTimeStamp;
        screenShotAndFrame(screenShotName, 2, scrFile, methodName, androidModel, driver);

    }

    /**
     * Get Current Date and Time
     * 
     * @return
     */
    private String currentDateAndTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ISO_DATE_TIME;
        return now.truncatedTo(ChronoUnit.SECONDS).format(dtf);
    }

    public void screenShotAndFrame(String screenShotName, int status, File scrFile, String methodName, String model,
            RemoteWebDriver driver) {
        String failedScreen = System.getProperty("user.dir") + "\\target\\screenshot\\"
                + driver.getCapabilities().getBrowserName() + methodName + "\\" + methodName + "_failed" + ".png";

        try {
            if (status == ITestResult.FAILURE) {
                FileUtils.copyFile(scrFile, new File(failedScreen));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized static ExtentReports logOutPut(String imgSrc, String headerName) {
        imgSrc = "<div class='col l4 m6 s12'><div class='card-panel'><h4 class='md-display-4'>" + headerName
                + "</h4><img src=" + imgSrc + " style=\"width:304px%;height:228px;\"></div></div>";
        extent.loadConfig(new File(System.getProperty("user.dir") + "/extent.xml"));
        extent.setTestRunnerOutput(imgSrc);
        return extent;
    }

    public void onTestSkipped(ITestResult result) {
        // TODO Auto-generated method stub
        System.out.println(result.getMethod().getMethodName() + "==>"+ getResultStatusName(result.getStatus()));
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // TODO Auto-generated method stub

    }

    public void onStart(ITestContext context) {
        File screenShot = new File(System.getProperty("user.dir") + "/target/screenshot/");
        if (screenShot.isDirectory()) {
            try {
                FileUtils.deleteDirectory(screenShot);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public void onFinish(ITestContext context) {
        // TODO Auto-generated method stub
    }

    public static void createAnimatedGif(File testScreenshots, File animatedGif) throws IOException {
        AnimatedGifEncoder encoder = new AnimatedGifEncoder();
        encoder.start(animatedGif.getAbsolutePath());
        encoder.setDelay(1500 /* 1.5 seconds */);
        encoder.setQuality(10 /* highest */);
        encoder.setRepeat(1 /* infinite */);
        encoder.setTransparent(Color.WHITE);

        int width = 0;
        int height = 0;
        if (testScreenshots.exists()) {
            BufferedImage bufferedImage = ImageIO.read(testScreenshots);
            width = Math.max(bufferedImage.getWidth(), width);
            height = Math.max(bufferedImage.getHeight(), height);

            encoder.setSize(width, height);
            encoder.addFrame(ImageIO.read(testScreenshots));
            encoder.finish();
        }
    }

    /**
     * Create Gif File
     * 
     * @throws IOException
     */
    public static void createGif(File file) throws IOException {
        if (file.getName().contains("png")) {
            createAnimatedGif(file, new File(file.getParent() + "/" + file.getName().replace(".png", "") + ".gif"));
            file.deleteOnExit();
        }
    }

}