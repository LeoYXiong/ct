package com.ibm.issac.toolkit.poi.excel;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Hyperlink;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.ibm.issac.toolkit.DevLog;
import com.ibm.issac.toolkit.util.StringUtil;

/**
 * ����EXCEL xlsx�ļ�����������־��������� �����ֻ֧��һ��SHEET
 * 
 * @author issac
 * 
 */
public class SingleSheetExcelWriter {
	public static SingleSheetExcelWriter inst = new SingleSheetExcelWriter();
	private Workbook wb;

	private SingleSheetExcelWriter() {
		wb = new XSSFWorkbook();
		wb.createSheet();
	}

	/**
	 * ����һ��ROW��rowName����EXCEL��ĸ������Ϊ�˷����Ժ�Ϊ�����������ݵ�
	 * 
	 * @param rowName
	 * @return
	 */
	public Row createRow(int rowIndex) {
		Row r = wb.getSheetAt(0).createRow(rowIndex);
		return r;
	}

	public Cell createHeaderCell(int rowIndex, int cellIndex, String cellValue) {
		Row row = wb.getSheetAt(0).getRow(rowIndex);
		if (row == null) {
			DevLog.debug("EXCEL: Row at index " + rowIndex + " does not exist. It is created for cell creation.");
			row = this.createRow(rowIndex);
		}
		Cell cell = row.getCell(cellIndex);
		if (cell == null) {
			//DevLog.debug("EXCEL: Cell at index "+cellIndex+" does not exist. It is created for value "+cellValue+".");
			cell = row.createCell(cellIndex);
		}else{
			DevLog.debug("EXCEL: When creating cell @ "+cellIndex+", it existed. The old value "+cell.getStringCellValue()+" will be over-written.");
		}
		cell.setCellValue(cellValue);
		// ������ʽΪHEADER��ʽ
		CellStyle style = wb.createCellStyle();
		style.setFillBackgroundColor(IndexedColors.GREY_40_PERCENT.getIndex());
		style.setFillPattern(CellStyle.ALIGN_FILL);
		cell.setCellStyle(style);
		return cell;
	}

	public Cell createDataCell(int rowIndex, int cellIndex, String cellValue) {
		Row row = wb.getSheetAt(0).getRow(rowIndex);
		if (row == null) {
			DevLog.debug("EXCEL: Row at index " + rowIndex + " does not exist. It is created for cell creation.");
			row = this.createRow(rowIndex);
		}
		Cell cell = row.getCell(cellIndex);
		if (cell == null) {
			//DevLog.debug("EXCEL: Cell at index "+cellIndex+" does not exist. It is created for value "+cellValue+".");
			cell = row.createCell(cellIndex);
		}else{
			DevLog.debug("EXCEL: When creating cell @ "+cellIndex+", it existed. The old value "+cell.getStringCellValue()+" will be over-written.");
		}
		cell.setCellValue(cellValue);
		return cell;
	}

	public void writeToDisk(String fileName) {
		try {
			// �����п�
			for (int i = 0; i < 10; i++) {
				//wb.getSheetAt(0).autoSizeColumn(i);
				wb.getSheetAt(0).setColumnWidth(i, 30);
			}
			// д�����
			FileOutputStream fileOut = new FileOutputStream(fileName);
			wb.write(fileOut);
			fileOut.close();
			DevLog.debug("Output file " + fileName + " has been written to the current directory.");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public CellStyle buildHyperlinkStyle() {
		// cell style for hyperlinks
		// by default hyperlinks are blue and underlined
		CellStyle hlink_style = wb.createCellStyle();
		Font hlink_font = wb.createFont();
		hlink_font.setUnderline(Font.U_SINGLE);
		hlink_font.setColor(IndexedColors.BLUE.getIndex());
		hlink_style.setFont(hlink_font);
		return hlink_style;
	}

	public Hyperlink buildHyperlink(String addressStr) {
		CreationHelper createHelper = wb.getCreationHelper();
		Hyperlink link = createHelper.createHyperlink(Hyperlink.LINK_URL);
		if (StringUtil.isReadable(addressStr)) {
			link.setAddress(addressStr);
			return link;
		}
		return null;
	}
}
