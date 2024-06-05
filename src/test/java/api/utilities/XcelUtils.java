package api.utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XcelUtils {
	public static FileInputStream fs;
	public static XSSFWorkbook wb;
	public static XSSFSheet ws;
	public static int rowCount;
	public static XSSFRow row;
	public static XSSFCell cell;
	public static FileOutputStream fo;

	public int getRowCount(String xlPath, String xcelSheet) throws IOException {
		fs = new FileInputStream(xlPath);
		wb = new XSSFWorkbook(fs);
		ws = wb.getSheet(xcelSheet);
		rowCount = ws.getLastRowNum();
		fs.close();
		wb.close();
		return rowCount;
	}

	public int getColumnCount(String xlPath, String xcelSheet) throws IOException {
		fs = new FileInputStream(xlPath);
		wb = new XSSFWorkbook(fs);
		ws = wb.getSheet(xcelSheet);
		row = ws.getRow(0);
		int cellCount = row.getLastCellNum();
		wb.close();
		return cellCount;
	}

	public String getCellData(String xlPath, String xcelSheet, int rownum, int colnum) throws IOException {
		fs = new FileInputStream(xlPath);
		wb = new XSSFWorkbook(fs);
		ws = wb.getSheet(xcelSheet);
		row = ws.getRow(rownum);
		cell = row.getCell(colnum);
		String data;
		try {
			DataFormatter format = new DataFormatter();
			String cellValue = format.formatCellValue(cell);
			return cellValue;
		} catch (Exception e) {
			data = "";
		}
		wb.close();
		fs.close();
		return data;
	}


	public static void setCellData(String xlPath, String xcelSheet, int rownum, int colnum, String data ) throws IOException {
		
		fs = new FileInputStream(xlPath);
		wb = new XSSFWorkbook(fs);
		ws = wb.getSheet(xcelSheet);
		row = ws.getRow(rownum);
		cell = row.createCell(colnum);
		cell.setCellValue(data);
		fo = new FileOutputStream(xlPath);
		wb.write(fo);
		wb.close();
		fs.close();
		fo.close();
		
	}

}
