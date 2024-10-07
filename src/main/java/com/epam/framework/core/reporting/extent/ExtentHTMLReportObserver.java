package com.epam.framework.core.reporting.extent;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.epam.framework.core.logging.logger.LogLevel;
import com.epam.framework.core.reporting.HTMLReportObserver;
import com.epam.framework.ui.UiConstants;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Map;
import java.util.Properties;

public class ExtentHTMLReportObserver implements HTMLReportObserver {

    private static final Logger logger = LoggerFactory.getLogger(ExtentHTMLReportObserver.class);
    private static final Properties prop = loadConfigFile("system.properties");
    private static ExtentReports extentReports = new ExtentReports();
    private static ExtentTest methodTest;
    private static ThreadLocal<ExtentTest> extentTestThreadLocal = new ThreadLocal<>();
    private static ExtentTest extentTestNodeLocal;
    private static final String OUTPUT_DIRECTORY = prop.getProperty("html.report.out.directory", "target/html-report/");
    private static final String HTML_REPORT_NAME = prop.getProperty("html.report.name", "report.html");
    private static final String XML_CONFIG_FILENAME = prop.getProperty("html.report.xmlconfig.name", "extent-config.xml");


    @Override
    public synchronized void setUp() {
        String currentDateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy_MM_dd HH_mm"));
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter(new File(FilenameUtils.normalize(OUTPUT_DIRECTORY + currentDateTime+File.separator+ HTML_REPORT_NAME)));
        configureExtentReportFromXml(htmlReporter);
        extentReports.attachReporter(htmlReporter);
    }

    @Override
    public void setUp(String reportName) {
        String currentDateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy_MM_dd HH_mm"));
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter(FilenameUtils.normalize(OUTPUT_DIRECTORY + reportName + currentDateTime+File.separator + HTML_REPORT_NAME));
        configureExtentReportFromXml(htmlReporter);
        extentReports.attachReporter(htmlReporter);
    }

    @Override
    public synchronized void tearDown() {
        extentReports.flush();
        extentTestThreadLocal.remove();
    }

    @Override
    public synchronized void createTest(String testName) {
        methodTest = extentReports.createTest(testName);
        extentTestThreadLocal.set(methodTest);
    }

    @Override
    public void createTest(String testName, Map<String, Object> additionalParams) {
        methodTest = extentReports.createTest(testName);
        extentTestThreadLocal.set(methodTest);
    }

    @Override
    public void createNode(String step) {
        extentTestNodeLocal = methodTest.createNode(step);
    }

    private synchronized ExtentTest getNode(){
        return extentTestThreadLocal.get();
    }

    @Override
    public synchronized void log(LogLevel messageType, String message) {
        switch (messageType) {
            case WARNING:
                warning(message);
                break;
            case ERROR:
                error(message);
                break;
            case DEBUG:
                error("DEBUG: " + message);
                break;
            case INFO:
            default:
                info(message);
                break;
        }

    }

    @Override
    public void log(LogLevel messageType, String message, Throwable throwable) {
        switch (messageType) {
            case WARNING:
                warning(message, throwable);
                break;
            case ERROR:
                error(message, throwable);
                break;
            case DEBUG:
                error("DEBUG: " + message, throwable);
                break;
            case INFO:
            default:
                info(message, throwable);
                break;
        }
    }

    @Override
    public synchronized void info(String message) {
        methodTest.info(message);
    }

    @Override
    public synchronized void info(String message, Throwable throwable) {
        methodTest.info(message);
        methodTest.info(throwable);
    }

    @Override
    public synchronized void pass(String message) {
        methodTest.pass(message);
    }

    @Override
    public synchronized void fail(String message) {
        //methodTest.fail(message);
        methodTest.fail(message);
    }

    @Override
    public synchronized void fail(String message, Throwable throwable) {
        methodTest.fail(message);
        methodTest.fail(throwable);
    }

    @Override
    public synchronized void skip(String message) {
        methodTest.skip(message);
    }

    @Override
    public synchronized void skip(String message, Throwable throwable) {
        methodTest.skip(message);
        methodTest.skip(throwable);
    }

    @Override
    public synchronized void warning(String message) {
        methodTest.warning(message);
    }

    @Override
    public synchronized void warning(String message, Throwable throwable) {
        methodTest.warning(message);
        methodTest.warning(throwable);
    }

    @Override
    public synchronized void error(String message) {
        methodTest.fail(message);
    }

    @Override
    public synchronized void error(String message, Throwable throwable) {
        methodTest.fail(message);
        methodTest.fail(throwable);
    }

    @Override
    public synchronized void addScreenshot(String screenshotName, String description) {

        String newFileName = screenshotName + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

        File destFilePath = new File(FilenameUtils.normalize(OUTPUT_DIRECTORY + "/screenshots/"));
        if(!destFilePath.exists()){
            destFilePath.mkdirs();
        }
        File destFile = new File(FilenameUtils.normalize(OUTPUT_DIRECTORY + "/screenshots/" + newFileName + ".png"));
        try {
            Robot r = new Robot();
            Rectangle capture =
                    new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
            BufferedImage Image = r.createScreenCapture(capture);
            ImageIO.write(Image, "png", destFile);
            methodTest.info(description, MediaEntityBuilder.createScreenCaptureFromPath(destFile.getAbsolutePath()).build());
        } catch (Exception e) {
            logger.info("Getting exception While taking the Screenshots", e);
        }
    }

    @Override
    public synchronized void addFile(String FileName, String description,String FileData){
        String newFileName = FileName + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        try {
            File destFile = new File(FilenameUtils.normalize(OUTPUT_DIRECTORY + "/screenshots/" + newFileName));
            FileUtils.writeStringToFile(destFile, FileData);
            methodTest.info("<a href='" + destFile.getAbsolutePath() + "'>click to view text</a>");
        }
        catch (Exception e){
            logger.info("Getting exception While taking the Screenshots", e);
        }
    }

    private static synchronized Properties loadConfigFile(String propFile) {
        Properties prop = new Properties();
        InputStream resourceAsStream = UiConstants.class.getClassLoader().getResourceAsStream(propFile);
        try {
            prop.load(resourceAsStream);
        } catch (IOException e) {
            logger.info("Getting Exception while reading the property file.", e);
            return null;
        }
        return prop;
    }

    private synchronized ExtentTest getTest() {
        return extentTestThreadLocal.get();
    }

    private synchronized void configureExtentReportFromXml(ExtentSparkReporter htmlReporter) {

        try {
            //String configXmlFile = getClass().getClassLoader().getResource(XML_CONFIG_FILENAME).getPath();
            String configXmlFile = FilenameUtils.normalize(System.getProperty("user.dir")
                    +File.separator
                    +"src\\test\\resources\\"+XML_CONFIG_FILENAME);
            logger.info("Loading Extent Report configuration parameters from XML File{}", configXmlFile);
            htmlReporter.loadXMLConfig(configXmlFile);
        } catch (Exception ex) {
            //setting the Dark mode for default. Rest Settings keeping default
            ex.printStackTrace();
            logger.info("Got the exception while reading Extent report parameters from xml file. " +
                    "So setting Dark Mode and keeping rest as default");

        }

    }
}