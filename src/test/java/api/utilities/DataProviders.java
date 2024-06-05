package api.utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

import Utils.ConstantsHelper;

public class DataProviders {
	
	XcelUtils xl;
	
	@DataProvider(name ="data")
	public String[][] getAllData() throws IOException{
		String path = ConstantsHelper.excelPath;
		xl=new XcelUtils();
		int rowCount =xl.getRowCount(path , "Sheet1");
		int columnCount = xl.getColumnCount(path, "Sheet1");
		
		String apiData[][] = new String[rowCount][columnCount];
		
		for(int i=1;i<=rowCount;i++) {
			for(int j=0;j<columnCount;j++) {
				apiData[i-1][j] = xl.getCellData(path, "Sheet1", i, j);
			}
		}	
		
		return apiData;
	}
	
	@DataProvider(name ="usernames")
	public String[] getUSernames() throws IOException{
		String path = ConstantsHelper.excelPath;
		xl=new XcelUtils();
		int rowCount =xl.getRowCount(path , "Sheet1");
		
		String apiData[] = new String[rowCount];
		
		for(int i=1;i<=rowCount;i++) {
				apiData[i-1] = xl.getCellData(path, "Sheet1", i , 1);
			}		
		return apiData;
	}

}
