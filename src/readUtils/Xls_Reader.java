package readUtils;



import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFCreationHelper;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFHyperlink;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Xls_Reader {
    public String path;
    public FileInputStream fis = null;
    public FileOutputStream fileOut = null;
    private XSSFWorkbook workbook = null;
    private XSSFSheet sheet = null;
    private XSSFRow row = null;
    private XSSFCell cell = null;

    public Xls_Reader(String path) {
        this.path = path;

        try {
            this.fis = new FileInputStream(path);
            this.workbook = new XSSFWorkbook(this.fis);
            this.sheet = this.workbook.getSheetAt(0);
            this.fis.close();
        } catch (Exception var3) {
            var3.printStackTrace();
        }

    }

    public int getRowCount(String sheetName) {
        int index = this.workbook.getSheetIndex(sheetName);
        if(index == -1) {
            return 0;
        } else {
            this.sheet = this.workbook.getSheetAt(index);
            int number = this.sheet.getLastRowNum() + 1;
            return number;
        }
    }

    public String getCellData(String sheetName, String colName, int rowNum) {
        try {
            if(rowNum <= 0) {
                return "";
            } else {
                int e = this.workbook.getSheetIndex(sheetName);
                int col_Num = -1;
                if(e == -1) {
                    return "";
                } else {
                    this.sheet = this.workbook.getSheetAt(e);
                    this.row = this.sheet.getRow(0);

                    for(int cellText = 0; cellText < this.row.getLastCellNum(); ++cellText) {
                        if(this.row.getCell(cellText).getStringCellValue().trim().equals(colName.trim())) {
                            col_Num = cellText;
                        }
                    }

                    if(col_Num == -1) {
                        return "";
                    } else {
                        this.sheet = this.workbook.getSheetAt(e);
                        this.row = this.sheet.getRow(rowNum - 1);
                        if(this.row == null) {
                            return "";
                        } else {
                            this.cell = this.row.getCell(col_Num);
                            if(this.cell == null) {
                                return "";
                            } else if(this.cell.getCellType() == 1) {
                                return this.cell.getStringCellValue();
                            } else if(this.cell.getCellType() != 0 && this.cell.getCellType() != 2) {
                                if(this.cell.getCellType() == 3) {
                                    return "";
                                } else {
                                    return String.valueOf(this.cell.getBooleanCellValue());
                                }
                            } else {
                                String var11 = String.valueOf(this.cell.getNumericCellValue());
                                if(HSSFDateUtil.isCellDateFormatted(this.cell)) {
                                    double d = this.cell.getNumericCellValue();
                                    Calendar cal = Calendar.getInstance();
                                    cal.setTime(HSSFDateUtil.getJavaDate(d));
                                    var11 = String.valueOf(cal.get(1)).substring(2);
                                    var11 = cal.get(5) + "/" + cal.get(2) + 1 + "/" + var11;
                                }

                                return var11;
                            }
                        }
                    }
                }
            }
        } catch (Exception var10) {
            var10.printStackTrace();
            return "row " + rowNum + " or column " + colName + " does not exist in xls";
        }
    }

    public String getCellData(String sheetName, int colNum, int rowNum) {
        try {
            if(rowNum <= 0) {
                return "";
            } else {
                int e = this.workbook.getSheetIndex(sheetName);
                if(e == -1) {
                    return "";
                } else {
                    this.sheet = this.workbook.getSheetAt(e);
                    this.row = this.sheet.getRow(rowNum - 1);
                    if(this.row == null) {
                        return "";
                    } else {
                        this.cell = this.row.getCell(colNum);
                        if(this.cell == null) {
                            return "";
                        } else if(this.cell.getCellType() == 1) {
                            return this.cell.getStringCellValue();
                        } else if(this.cell.getCellType() != 0 && this.cell.getCellType() != 2) {
                            return this.cell.getCellType() == 3?"":String.valueOf(this.cell.getBooleanCellValue());
                        } else {
                            String cellText = String.valueOf(this.cell.getNumericCellValue());
                            return cellText;
                        }
                    }
                }
            }
        } catch (Exception var6) {
            var6.printStackTrace();
            return "row " + rowNum + " or column " + colNum + " does not exist  in xls";
        }
    }

    public boolean setCellData(String sheetName, String colName, int rowNum, String data) {
        try {
            this.fis = new FileInputStream(this.path);
            this.workbook = new XSSFWorkbook(this.fis);
            if(rowNum <= 0) {
                return false;
            } else {
                int e = this.workbook.getSheetIndex(sheetName);
                int colNum = -1;
                if(e == -1) {
                    return false;
                } else {
                    this.sheet = this.workbook.getSheetAt(e);
                    this.row = this.sheet.getRow(0);

                    for(int cs = 0; cs < this.row.getLastCellNum(); ++cs) {
                        if(this.row.getCell(cs).getStringCellValue().trim().equals(colName)) {
                            colNum = cs;
                        }
                    }

                    if(colNum == -1) {
                        return false;
                    } else {
                        this.sheet.autoSizeColumn(colNum);
                        this.row = this.sheet.getRow(rowNum - 1);
                        if(this.row == null) {
                            this.row = this.sheet.createRow(rowNum - 1);
                        }

                        this.cell = this.row.getCell(colNum);
                        if(this.cell == null) {
                            this.cell = this.row.createCell(colNum);
                        }

                        XSSFCellStyle var9 = this.workbook.createCellStyle();
                        var9.setWrapText(true);
                        this.cell.setCellStyle(var9);
                        this.cell.setCellValue(data);
                        this.fileOut = new FileOutputStream(this.path);
                        this.workbook.write(this.fileOut);
                        this.fileOut.close();
                        return true;
                    }
                }
            }
        } catch (Exception var8) {
            var8.printStackTrace();
            return false;
        }
    }

    public boolean setCellData(String sheetName, String colName, int rowNum, String data, String url) {
        try {
            this.fis = new FileInputStream(this.path);
            this.workbook = new XSSFWorkbook(this.fis);
            if(rowNum <= 0) {
                return false;
            } else {
                int e = this.workbook.getSheetIndex(sheetName);
                int colNum = -1;
                if(e == -1) {
                    return false;
                } else {
                    this.sheet = this.workbook.getSheetAt(e);
                    this.row = this.sheet.getRow(0);

                    for(int createHelper = 0; createHelper < this.row.getLastCellNum(); ++createHelper) {
                        if(this.row.getCell(createHelper).getStringCellValue().trim().equalsIgnoreCase(colName)) {
                            colNum = createHelper;
                        }
                    }

                    if(colNum == -1) {
                        return false;
                    } else {
                        this.sheet.autoSizeColumn(colNum);
                        this.row = this.sheet.getRow(rowNum - 1);
                        if(this.row == null) {
                            this.row = this.sheet.createRow(rowNum - 1);
                        }

                        this.cell = this.row.getCell(colNum);
                        if(this.cell == null) {
                            this.cell = this.row.createCell(colNum);
                        }

                        this.cell.setCellValue(data);
                        XSSFCreationHelper var13 = this.workbook.getCreationHelper();
                        XSSFCellStyle hlink_style = this.workbook.createCellStyle();
                        XSSFFont hlink_font = this.workbook.createFont();
                        hlink_font.setUnderline((byte)1);
                        hlink_font.setColor(IndexedColors.BLUE.getIndex());
                        hlink_style.setFont(hlink_font);
                        XSSFHyperlink link = var13.createHyperlink(4);
                        link.setAddress(url);
                        this.cell.setHyperlink(link);
                        this.cell.setCellStyle(hlink_style);
                        this.fileOut = new FileOutputStream(this.path);
                        this.workbook.write(this.fileOut);
                        this.fileOut.close();
                        return true;
                    }
                }
            }
        } catch (Exception var12) {
            var12.printStackTrace();
            return false;
        }
    }

    public boolean addSheet(String sheetname) {
        try {
            this.workbook.createSheet(sheetname);
            FileOutputStream fileOut = new FileOutputStream(this.path);
            this.workbook.write(fileOut);
            fileOut.close();
            return true;
        } catch (Exception var4) {
            var4.printStackTrace();
            return false;
        }
    }

    public boolean removeSheet(String sheetName) {
        int index = this.workbook.getSheetIndex(sheetName);
        if(index == -1) {
            return false;
        } else {
            try {
                this.workbook.removeSheetAt(index);
                FileOutputStream fileOut = new FileOutputStream(this.path);
                this.workbook.write(fileOut);
                fileOut.close();
                return true;
            } catch (Exception var5) {
                var5.printStackTrace();
                return false;
            }
        }
    }

    public boolean addColumn(String sheetName, String colName) {
        try {
            this.fis = new FileInputStream(this.path);
            this.workbook = new XSSFWorkbook(this.fis);
            int e = this.workbook.getSheetIndex(sheetName);
            if(e == -1) {
                return false;
            } else {
                XSSFCellStyle style = this.workbook.createCellStyle();
                style.setFillForegroundColor((short)55);
                style.setFillPattern((short)1);
                this.sheet = this.workbook.getSheetAt(e);
                this.row = this.sheet.getRow(0);
                if(this.row == null) {
                    this.row = this.sheet.createRow(0);
                }

                if(this.row.getLastCellNum() == -1) {
                    this.cell = this.row.createCell(0);
                } else {
                    this.cell = this.row.createCell(this.row.getLastCellNum());
                }

                this.cell.setCellValue(colName);
                this.cell.setCellStyle(style);
                this.fileOut = new FileOutputStream(this.path);
                this.workbook.write(this.fileOut);
                this.fileOut.close();
                return true;
            }
        } catch (Exception var5) {
            var5.printStackTrace();
            return false;
        }
    }

    public boolean removeColumn(String sheetName, int colNum) {
        try {
            if(!this.isSheetExist(sheetName)) {
                return false;
            } else {
                this.fis = new FileInputStream(this.path);
                this.workbook = new XSSFWorkbook(this.fis);
                this.sheet = this.workbook.getSheet(sheetName);
                XSSFCellStyle e = this.workbook.createCellStyle();
                e.setFillForegroundColor((short)55);
                XSSFCreationHelper createHelper = this.workbook.getCreationHelper();
                e.setFillPattern((short)0);

                for(int i = 0; i < this.getRowCount(sheetName); ++i) {
                    this.row = this.sheet.getRow(i);
                    if(this.row != null) {
                        this.cell = this.row.getCell(colNum);
                        if(this.cell != null) {
                            this.cell.setCellStyle(e);
                            this.row.removeCell(this.cell);
                        }
                    }
                }

                this.fileOut = new FileOutputStream(this.path);
                this.workbook.write(this.fileOut);
                this.fileOut.close();
                return true;
            }
        } catch (Exception var6) {
            var6.printStackTrace();
            return false;
        }
    }

    public boolean isSheetExist(String sheetName) {
        int index = this.workbook.getSheetIndex(sheetName);
        if(index == -1) {
            index = this.workbook.getSheetIndex(sheetName.toUpperCase());
            return index != -1;
        } else {
            return true;
        }
    }

    public int getColumnCount(String sheetName) {
        if(!this.isSheetExist(sheetName)) {
            return -1;
        } else {
            this.sheet = this.workbook.getSheet(sheetName);
            this.row = this.sheet.getRow(0);
            return this.row == null?-1:this.row.getLastCellNum();
        }
    }

    public boolean addHyperLink(String sheetName, String screenShotColName, String testCaseName, int index, String url, String message) {
        url = url.replace('\\', '/');
        if(!this.isSheetExist(sheetName)) {
            return false;
        } else {
            this.sheet = this.workbook.getSheet(sheetName);

            for(int i = 2; i <= this.getRowCount(sheetName); ++i) {
                if(this.getCellData(sheetName, 0, i).equalsIgnoreCase(testCaseName)) {
                    this.setCellData(sheetName, screenShotColName, i + index, message, url);
                    break;
                }
            }

            return true;
        }
    }

    public int getCellRowNum(String sheetName, String colName, String cellValue) {
        for(int i = 2; i <= this.getRowCount(sheetName); ++i) {
            if(this.getCellData(sheetName, colName, i).equalsIgnoreCase(cellValue)) {
                return i;
            }
        }

        return -1;
    }
  
    
	public static void getExcelrecords(String Path){
		Xls_Reader xx2 = new Xls_Reader(Path);
		Set<String> SetD = new HashSet<String>();
		SetD=xx2.getSet(Path);
	}
	
    public static Set<String> getSet(String excelPath){
    	Set<String> SetA = new HashSet<String>();
    	 Xls_Reader xx = new Xls_Reader(excelPath);
    		int rowCount = xx.getRowCount("listitems");
    		for (int i = ExcelConstants.rowNoName; i <= rowCount; i++) {
    			for (int j = 0; j < 1; j++) {
    				SetA.add(xx.getCellData("listitems", j, i));
    			}
    		}
			return SetA;
    }
    

	public static Set<String> Createset(Set<String> set1) {
		Set<String> swt = new HashSet<String>();
		for (String string : set1) {
			String[] tag = string.split(" ");
			List<String> list = Arrays.asList(tag);
			swt.addAll(list);
//			for (Iterator iterator = swt.iterator(); iterator.hasNext();) {
//				Object o = iterator.next();
//			}
		}
		return swt;
	}

	
	public static Set<String> intersection(Set<String> set1, Set<String> set2) {

		double sizeofset1 = set1.size();
		System.out.println("sizeofset1   :"+sizeofset1);
		double sizeofset2 = set2.size();
		System.out.println("sizeofset2   :"+sizeofset2);

		int count = 0;
		Set<String> tmp = new HashSet<String>();
		for (String firstRecord : set1) {
			for (String secondRecord : set2) {
				
				
				if (firstRecord.equalsIgnoreCase(secondRecord)) {
					count++;

					tmp.add(firstRecord);
				}
			}
		}
		System.out.println("Total Common elements in both the Sets are : "+ count);
		double percantageOfCommonWordsWithset1 = (count / sizeofset1 * 100);
		double percantageOfCommonWordsWithset2 = (count / sizeofset2 * 100);
		System.out
				.println("Percentage  of Common words with Respect to Set 1 : "
						+ percantageOfCommonWordsWithset1 + "%");
		System.out
				.println("Percentage of Common words with Respect to Set 2 : "
						+ percantageOfCommonWordsWithset2 + "%");
		System.out.println(tmp.size());
		return tmp;
	}
    
    
}
