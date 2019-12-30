package com.SaleForce.libraries;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class Excel_Libraries {

	public static String Folder_path = Utility_Libraries.Report_Folder_path;
	static String Excel_path =  Utility_Libraries.Report_Excel_path;
	static Workbook WB;
	
	//-------------------------------------------Excel reporter---------------------------------------------
	 public static void fExcelReporter(String Step_details,String Actual,String Status,String Time) throws Throwable
			{ 
				String Attribute[] = {"Step_details","Actual","Status","Time"};
					try{
						FileInputStream fin=new FileInputStream(Excel_path); 
						WB=WorkbookFactory.create(fin);
						}
					catch(Exception e){
						System.out.println(e);
						}
				Sheet sh = WB.getSheet("Sheet1");
				int Row_cnt = sh.getLastRowNum();
				Row newRow = sh.createRow(0);
				  	for(int j=0;j<=3;j++)
				  	{		  	
						newRow.createCell(j).setCellValue(Attribute[j]);
				  	}
				  
				String Attribute_value[] =  {Step_details,Actual,Status,Time};
				Row newRow1 = sh.createRow(Row_cnt+1);
					for(int i=0;i<=3;i++)
						{
						newRow1.createCell(i).setCellValue(Attribute_value[i]);
						}	
				FileOutputStream fout = new FileOutputStream(Excel_path);
				WB.write(fout);
				WB.close();
			}
		    
	//------------------------------------------Create Excel file------------------------------------------
	 public static String fCreateExcelfile() throws Throwable
			{
				Workbook Wb = null;
				if ((new File(Excel_path)).exists()==false) {	
			      		
					if (Excel_path.endsWith(".xls"))  {  
							Wb = new HSSFWorkbook();
						}
						else  {
							Wb = new XSSFWorkbook();
						}
					Wb.createSheet("Sheet1").createRow(0).createCell(0);
					FileOutputStream fout = new FileOutputStream(Excel_path);
					Wb.write(fout);
					Wb.close();
				}
				return Excel_path;
			}

	 public static void fWrite(String Attri_Value,String File_name,String Sheet_name) throws EncryptedDocumentException, InvalidFormatException, IOException
	 {
		 Workbook WB;
		 FileInputStream fin=new FileInputStream(File_name);
		 WB=WorkbookFactory.create(fin);
		 Sheet sh = WB.getSheet(Sheet_name);
		 Row rw_obj = sh.createRow(3);
		 rw_obj.createCell(0).setCellValue("OrderNumber");
		 rw_obj.createCell(1).setCellValue(Attri_Value);
		 FileOutputStream fout = new FileOutputStream(File_name);
		 WB.write(fout);
		 WB.close();
	 }
	 //------------------------------------------For data access--------------------------------------------
	 public static String fRead(String Attri_name,String File_name,String Sheet_name) throws Throwable
	     {
			FileInputStream fin=new FileInputStream(File_name);
			WB=WorkbookFactory.create(fin);
			Sheet sh = WB.getSheet(Sheet_name);
			int Row_cnt = sh.getLastRowNum();	
			   for(int i=0;i<=Row_cnt;i++)  
			   {
				   Row rw_obj = sh.getRow(i);
				   Cell cell_obj = rw_obj.getCell(0);
				   String Attri = cell_obj.toString();
				      if(Attri.equals(Attri_name)) 
				      {
						   Cell Attri_valu= rw_obj.getCell(1);
						   return Attri_valu.toString();
				      }
			   }
			return null;		  
		}
	 
	 public static String[][] fReadDataitera(String File_path,String Sheet_name) throws Throwable
     {
		String[][] tabArray = null;
		FileInputStream fin=new FileInputStream(File_path);
		WB=WorkbookFactory.create(fin);
		Sheet sh = WB.getSheet(Sheet_name);
		int totalrow = sh.getLastRowNum();
		int totalcol = sh.getRow(0).getPhysicalNumberOfCells();
		int row = 0;			
		tabArray = new String[totalrow][totalcol];
			 for(int i=1;i<=totalrow;i++)  
			   {
				 int col = 0;
					 for(int j=0;j<=totalcol-1;j++)  
					   {
						 Cell cell_obj = sh.getRow(i).getCell(j);
						 tabArray[row][col] = cell_obj.getStringCellValue();
						 col++;
					   } 
				 row++;
			   }
		return tabArray;	  		  
	}

}
