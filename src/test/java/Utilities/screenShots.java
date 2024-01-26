package Utilities;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.comparator.NameFileComparator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;



public class screenShots   {

	//We must extend base test to use the driver !!!
	//to set screenshots folder name and it's path for example D:/screenshots/.
	//or we can type "." then folder name to create it in the project directory
	//the below will create the folder in the project directory
	private String screenshotsFolderName = "/screenshots/";//you have just to provide the location and foldername
	protected String fullDirectory = System.getProperty("user.dir") + screenshotsFolderName + "/";//i have to add ###test name and "/"##
	protected static String currentDateAndTime;
	//to use it in renaming the folder after test and to name the word file too !! if it's passed twice it'll take all the runs ect....
	protected static String endTimeOfTestCase;
//	protected static String screenshots_folder_Path_after_rename;
//	protected static String screenshots_folder_Path_before_rename;
	private static final ThreadLocal<String> screenshots_folder_Path_before_rename = new ThreadLocal<>();
	private static final ThreadLocal<String> screenshots_folder_Path_after_rename = new ThreadLocal<>();
	

	// Getting value of screenshots_folder_Path_before_rename  for thread local
		public static String getscreenshots_folder_Path_before_rename() {
			return screenshots_folder_Path_before_rename.get();// to return the value of this thread safe
		}
		// Getting value of screenshots_folder_Path_after_rename for thread local
		public static String getscreenshots_folder_Path_after_rename() {
			return screenshots_folder_Path_after_rename.get();// to return the value of this thread safe
		}
	/**
	 * Takes screenshot of whole page and uses the current date/time as the file name
	 * LAST SHAPE
	 * Take screenshot of whole page and uses the current date/time as the file name
	 * also provide screenshots for each test case in seperate folders with test case name
	 * ## to use it in normal @test method  As Below :
	 evidenceAndScreenShots.takeFullScreenshot(testName,testDescription);
	 */

	public synchronized void takeFullScreenshot(String TestSuiteName,String TCName,String TCDescription,WebDriver driver) {
		try {
			
			//taking the screenshot
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File file = screenshot.getScreenshotAs(OutputType.FILE);
			//Initializing screenshots name
//			screenshots_folder_Path_before_rename=fullDirectory +TestSuiteName+"/"+ TCName + "_"+TCDescription+ "/";
			screenshots_folder_Path_before_rename.set(fullDirectory +TestSuiteName+"/"+ TCName + "_"+TCDescription+ "/");;
			
			// add the folder path and screenshot name
			// i added the screen shot status with the path to make folder fo each run of each test case run
			//the first part untill "/" is for the folder path after that is the screenshot name
//			FileUtils.copyFile(file, new File(fullDirectory + TCName + "_"+TCDescription+ "/" + TCName + "_" + TCDescription + "_" + GetCurrenDateAndTime() + ".png"));
			//the below is dynamic with testsuite name 
			//make it static
//			FileUtils.copyFile(file, new File( screenshots_folder_Path_before_rename+ TCName + "_" + TCDescription + "_" + GetCurrenDateAndTime() + ".png"));
			//dynamic
			FileUtils.copyFile(file, new File( getscreenshots_folder_Path_before_rename()+ TCName + "_" + TCDescription + "_" + GetCurrenDateAndTime() + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// test screenshot






	//end test screenshot
	//changeing the fodler name to the folder with status name
	public synchronized void renameScreenShotsFolder(String TestSuiteName,String TCName, String TCDescription, String status){
		endTimeOfTestCase = GetCurrenDateAndTime();
		//getting the old folder directory and it's name
//		File oldName = new File(fullDirectory +TestSuiteName+"/"+ TCName + "_"+TCDescription+ "/");
		//the below is dynamic with testsuite name   //
		File oldName = new File(getscreenshots_folder_Path_before_rename());
		//creating new folder name and directory
		//the below if we want to rename with end time of test case //#but not good if same test case will be running twice at the same time 
//		File newName = new File(fullDirectory+tCName+"_"+tCDescription+"_"+status+"_"+endTimeOfTestCase);
//		File newName = new File(fullDirectory+TestSuiteName+"/"+tCName+"_"+status+"_"+tCDescription);
		//String with the path of the screenshots to be used 
//		screenshots_folder_Path_after_rename = fullDirectory +TestSuiteName+"/"+ status+ "_"+TCName + "_"+TCDescription+ "/";
		screenshots_folder_Path_after_rename.set(fullDirectory +TestSuiteName+"/"+ status+ "_"+TCName + "_"+TCDescription+ "/");;
//		File newName = new File(fullDirectory +TestSuiteName+"/"+ status+ "_"+TCName + "_"+TCDescription+ "/");
		//dynamic
		File newName = new File(getscreenshots_folder_Path_after_rename());
//		screenshots_folder_Path_after_rename = fullDirectory+TestSuiteName+"/"+tCName+"_"+status+"_"+tCDescription;
		//if the old name is exist then rename it to the new name we created
		if (oldName.renameTo(newName)) {
			//after renaming success print this
			System.out.println("folder renamed successfully");
		} else {
			//printing this if the renaming failed
			System.out.println("Failed to rename folder---Folder Not Exist");
		}

	}

	//Creating a method to get current date and time to use it in naming
	public synchronized static String GetCurrenDateAndTime() {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy HH-mm-ss-SSS");
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH-mm-ss");
		LocalDateTime dateTime = LocalDateTime.now();
		currentDateAndTime = dateTime.format(formatter);//test
		return currentDateAndTime;
	}

	
	public void sortFiles(File[] files_to_be_sorted) throws Exception
	{
		//#sorting files first 
		//array with the files
//		File[] files = files;
		if(files_to_be_sorted != null && files_to_be_sorted.length > 0) {
//	        Arrays.sort(files, LastModifiedFileComparator.LASTMODIFIED_COMPARATOR);
//			Arrays.parallelSort(files,Collections.reverseOrder());
			Arrays.sort(files_to_be_sorted, NameFileComparator.NAME_COMPARATOR);	  
//			Arrays.sort(files_to_be_sorted, Comparator.comparingLong(File::lastModified));
			//Or, if you want it in descending order, just reverse it:
//			Arrays.sort(files, Comparator.comparingLong(File::lastModified).reversed());
			System.out.println("Files sorted...");
		}
		else {
			throw new Exception("The screenShots Folder Not Exist to be sorted");
		}
		
		//#end of sorting files
		
	}
	


	//	/*---------------------------------------saving the images to word file--------------------------
	//     * Last shape
	//     *    to take all screenshots from a folder to word file
	//     *    to use it As Below :
	//     EvidenceAndScreenShots evidenceAndScreenShots= new EvidenceAndScreenShots();
	//	evidenceAndScreenShots.saveAllScreenShotsIntoWordDocument(result.getName(), result.getMethod().getDescription(),status);
	//     * */
	//	//i added the status to take images from every folder with it's status
	//	public void saveAllScreenShotsIntoWordDocument(String itestListenerDOTgetName, String testcaseDescription, String status) {
	//		try {
	//			// Create the docx object
	//			// Step 1: Creating a blank document
	//			XWPFDocument document = new XWPFDocument();
	//			// Step 2: Creating a Paragraph using
	//			// createParagraph() method
	//			XWPFParagraph paragraph
	//					= document.createParagraph();
	//			//for inputs with wordDocument
	//			XWPFRun run = paragraph.createRun();
	//			// Step 3: Creating a File output stream of word
	//			// document at the required location
	//			//the below location must be declared before ,,,i did it when i take the screenshots
	//			FileOutputStream fos = new FileOutputStream(
	////					new File(screenshotsFolderNameAndPath + itestListenerDOTgetName + "_" + testcaseDescription + "_" + status + "_" + GetCurrenDateAndTime() + ".docx"));//right place with right test name
	//					new File(screenshotsFolderNameAndPath + itestListenerDOTgetName + "_" + testcaseDescription + "_" + status + "_" + lastTimeOfTestCase + ".docx"));//right place with right test name
	//
	//			// Step 4 : Get the source folder and list of files (includes images and
	//			// sub-folders)   where we get the images
	//			File imagesSrcFilePath = new File(fullDirectory +itestListenerDOTgetName+"_"+testcaseDescription+"_"+status+"_"+lastTimeOfTestCase);
	//
	//			//array of files to get the list of items inside the src folder path
	//			File[] list = imagesSrcFilePath.listFiles();
	//
	//			//Step 5 : printing the number of found items
	//			System.out.println("Source folder item list " + list.length);
	//
	//			// Step 6 : Iterate through the files in the source folder
	//			for (int images = 0; images < list.length; images++) {
	//				if (list[images].isFile()) {
	//					System.out.println("Found File name - " + list[images].getName());
	//
	//					// Step 7 : Create fis"file input stream " for images
	//					FileInputStream fis = new FileInputStream(list[images].getPath());
	//
	//					// adding the image type & images width and height
	//					int imageType = XWPFDocument.PICTURE_TYPE_PNG;
	//					int width = 500;
	//					int height = 550;
	//
	//					// step 8 : adding the found images using the fis
	//					run.addPicture(fis, imageType,
	//							list[images].getPath(), Units.toEMU(width), Units.toEMU(height));
	//					fis.close();
	//				}
	//			}
	//			//Last step adding every things we opened
	//			document.write(fos);
	//			fos.close();
	//			document.close();
	//		} catch (Exception e) {
	//			e.printStackTrace();
	//		}
	//	}







	//	//we can take the below from the screenshots evidence class object
	////    private String screenshotsFolderNameAndPath = "./screenshots/";//you have just to provide the location and foldername
	////    private String fullDirectory = System.getProperty("user.dir") + "/" + screenshotsFolderNameAndPath + "/";//i have to add ###test name and "/"##
	//    public synchronized void InsertAllImagesToTheReport(String itestListenerDOTgetName, String testcaseDescription, String status) {
	//        // sub-folders)   where we get the images
	//        File imagesSrcFilePath = new File(fullDirectory + itestListenerDOTgetName + "_" + testcaseDescription + "_" + status);
	//       String imagesPath = fullDirectory + itestListenerDOTgetName + "_" + testcaseDescription + "_" + status+"/";
	//        //array of files to get the list of items inside the src folder path
	//        File[] list = imagesSrcFilePath.listFiles();
	//
	//        //Step 5 : printing the number of found items
	//        System.out.println("Source folder item list " + list.length);
	//
	//        // Step 6 : Iterate through the files in the source folder
	//        for (int images = 0; images < list.length; images++) {
	//            if (list[images].isFile()) {
	//                System.out.println("Found File name - " + list[images].getName());
	//                test.addScreenCaptureFromPath(imagesPath+list[images].getName());
	//
	//            }
	//
	//        }
	//    }
	//    //Extent Report setup
	//    /*The ExtentHtmlReporter is used for creating an HTML file, and it accepts a file path as a parameter.
	//    The file path represents the path in which our extent report would be generated.
	//    *#also to it's object we set the configuration of the report html page
	//    * */
	////    ExtentHtmlReporter htmlReporter =  new ExtentHtmlReporter(System.getProperty("user.dir")+"/Reports/extentReport.html");
	//    public  static ExtentSparkReporter htmlReporter;
	//	public static ExtentReports extent;
	//	public static ExtentTest test;
	//	
	//    public void setUpExtent() {
	//      
	//      htmlReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/Reports/extentReport.html");
	////    ExtentReports
	////   The ExtentReports class is used for creating the tests.
	//      extent = new ExtentReports();
	//      extent.attachReporter(	htmlReporter);
	//
	//      htmlReporter.config().setDocumentTitle("Automation Report");
	//      htmlReporter.config().setReportName("report Name");
	////        htmlReporter.config().setTimeStampFormat("EEEE, dd  MMMM , yyyy, hh:mm a '('zzz')'");
	//      htmlReporter.config().setTimeStampFormat("EEEE, dd  MMMM , yyyy, hh:mm a ");
	//      htmlReporter.config().setTheme(Theme.STANDARD);//Theme.DARK   or  Theme.STANDARD
	//    }
	/*The below is how to use it in normal  test class

-----------test method
@Test(priority = 1,description ="Test Case One Say Hi" )
	public void TC001() throws InterruptedException {
		evidenceAndScreenShots= new EvidenceAndScreenShots();
		//getting the current test name
		String testName =new Object(){}.getClass().getEnclosingMethod().getName() ;
		// because the parameter for @ must be constant
		String testDescription ="Test Case One Say Hi";

		oGooglePage= new GooglePage(driver);
		evidenceAndScreenShots.takeFullScreenshot(testName,testDescription);
		driver.get("https://www.google.com");
		evidenceAndScreenShots.takeFullScreenshot(testName,testDescription);
		driver.manage().window().maximize();
	}

	-------------after test method where we do most of things
@AfterMethod
	public void close(ITestResult result) throws InterruptedException {
		try
		{
			if(result.getStatus() == ITestResult.SUCCESS)
			{
				evidenceAndScreenShots= new EvidenceAndScreenShots();
				String status = "Passed";
				System.out.println(result.getName()+"passed **********");
//rename the folder to new name with status
				evidenceAndScreenShots.renameScreenShotsFolder(result.getName(), result.getMethod().getDescription(),status);
//save screenshots to word evidence file
				evidenceAndScreenShots.saveAllScreenShotsIntoWordDocument(result.getName(), result.getMethod().getDescription(),status);
			}

			else if(result.getStatus() == ITestResult.FAILURE)
			{
				evidenceAndScreenShots= new EvidenceAndScreenShots();
				String status = "Failed";

				System.out.println("Failed ***********");
//rename the folder to new name with status
				evidenceAndScreenShots.renameScreenShotsFolder(result.getName(), result.getMethod().getDescription(),status);
//save screenshots to word evidence file
				evidenceAndScreenShots.saveAllScreenShotsIntoWordDocument(result.getName(), result.getMethod().getDescription(),status);

			}

			else if(result.getStatus() == ITestResult.SKIP ){
				evidenceAndScreenShots= new EvidenceAndScreenShots();
				String status = "Skipped";
				System.out.println("Skipped***********");
//rename the folder to new name with status
				evidenceAndScreenShots.renameScreenShotsFolder(result.getName(), result.getMethod().getDescription(),status);
//save screenshots to word evidence file
				evidenceAndScreenShots.saveAllScreenShotsIntoWordDocument(result.getName(), result.getMethod().getDescription(),status);

			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		TearDown();
	}

	 * */

}