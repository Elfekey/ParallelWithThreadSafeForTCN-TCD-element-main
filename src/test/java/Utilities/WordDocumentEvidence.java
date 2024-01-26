package Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

public class WordDocumentEvidence  extends screenShots{


	/*---------------------------------------saving the images to word file--------------------------
	 * Last shape
	 *    to take all screenshots from a folder to word file
	 *    to use it As Below :
     EvidenceAndScreenShots evidenceAndScreenShots= new EvidenceAndScreenShots();
	evidenceAndScreenShots.saveAllScreenShotsIntoWordDocument(result.getName(), result.getMethod().getDescription(),status);
	 * */
	//i added the status to take images from every folder with it's status
	public synchronized void saveAllScreenShotsIntoWordDocument(String TestSuiteName ,String TCName, String TCDescription, String status) {
		try {
			// Create the docx object
			// Step 1: Creating a blank document
			XWPFDocument document = new XWPFDocument();
			// Step 2: Creating a Paragraph using
			// createParagraph() method
			XWPFParagraph paragraph
			= document.createParagraph();
			//for inputs with wordDocument
			XWPFRun run = paragraph.createRun();
//			// Step 3: Creating a File output stream of word
//			// document at the required location
//			//the below location must be declared before ,,,i did it when i take the screenshots
//			FileOutputStream fos = new FileOutputStream(
//					new File(fullDirectory + tCName + "_" + tCDescription + "_" + status + "_" + lastTimeOfTestCase + ".docx"));//right place with right test name

			// Step 3 : Get the source folder and list of files (includes images and
			// sub-folders)   where we get the images
			//			File imagesSrcFilePath = new File(fullDirectory +itestListenerDOTgetName+"_"+testcaseDescription+"_"+status+"_"+lastTimeOfTestCase);
//			File imagesSrcFilePath = new File(fullDirectory +TestSuiteName+"/"+ status+ "_"+TCName + "_"+TCDescription+ "/");
			File imagesSrcFilePath = new File(getscreenshots_folder_Path_after_rename());
			//the below is dynamic 
//			File imagesSrcFilePath = new File(screenshots_folder_Path_after_rename);
			
			// Step 4 :check first if the scr files folder exist or not if ok continue else throw exception
			//####check first if the screenshots folder exists####
			if (imagesSrcFilePath.exists() && imagesSrcFilePath.isDirectory()) {
				
				// Step 5: Creating a File output stream of word
				// document at the required location
				//the below location must be declared before ,,,i did it when i take the screenshots
				FileOutputStream fos = new FileOutputStream(
//						new File(fullDirectory + TCName + "_" + TCDescription + "_" + status + "_" + endTimeOfTestCase + ".docx"));//right place with right test name
//						new File(fullDirectory +TestSuiteName+"/"+ status+ "_"+TCName + "_"+TCDescription + ".docx"));//right place with right test name
				new File(fullDirectory +TestSuiteName+"/"+ status+ "_"+TCName + "_"+TCDescription + ".docx"));//right place with right test name
						//the below is dynamic
//						new File(screenshots_folder_Path_after_rename+ ".docx"));//right place with right test name

			 
			//array of files to get the list of items inside the src folder path
			File[] list = imagesSrcFilePath.listFiles();
			//Sort files function
			sortFiles(list);
			//Step 6 : printing the number of found items
			System.out.println("Source folder item list " + list.length);

			// Step 7 : Iterate through the files in the source folder
			for (int images = 0; images < list.length; images++) {
				if (list[images].isFile()) {
					System.out.println("Found File name - " + list[images].getName());

					// Step 8 : Create fis"file input stream " for images
					FileInputStream fis = new FileInputStream(list[images].getPath());

					// adding the image type & images width and height
					int imageType = XWPFDocument.PICTURE_TYPE_PNG;
					int width = 500;
					int height = 550;

					// step 9 : adding the found images using the fis
					run.addPicture(fis, imageType,
							list[images].getPath(), Units.toEMU(width), Units.toEMU(height));
					fis.close();
				}
			}
			//Last step adding every things we opened
			document.write(fos);
			fos.close();
			document.close();
			System.out.println("Images Inserted to word File");
			}//if end
			else {
				throw new Exception("The screenShots Folder Not Exist to add the images to Wrod File");
			
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
}