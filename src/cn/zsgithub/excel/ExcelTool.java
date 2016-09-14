package cn.zsgithub.excel;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExcelTool {
	
	/**
	 * Excel分页导出
	 * @param headers 标题行
	 * @param list 数据集合
	 * @param sheetName sheet页的名称(sheet页有名称则赋值，没名称则为null)
	 * @return InputStream输入流
	 */
	public static InputStream export(String[] headers, List<Object[]> list, String sheetName) {
		//生成一个Excel文件
		HSSFWorkbook wb = new HSSFWorkbook();
		
		int rownum = 0;
		HSSFSheet sheet = StringUtils.isNotBlank(sheetName) ? 
				wb.createSheet(sheetName) : wb.createSheet();
				
		HSSFFont font = wb.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font.setFontName("宋体");
		font.setFontHeight((short) 300);
		HSSFCellStyle cellStyle = wb.createCellStyle();
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		HSSFDataFormat format = wb.createDataFormat();
		cellStyle.setDataFormat(format.getFormat("@"));
		cellStyle.setWrapText(true);
		sheet.setDefaultColumnWidth((short) 30);
		if (headers != null && headers.length > 0) {
			// 标题行不为空（设置标题行）
			HSSFRow row0 = sheet.createRow(rownum);
			for (int i = 0; i < headers.length; i++) {
				HSSFCell cell = row0.createCell((short) i);
				cell.setCellStyle(cellStyle);
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell.setCellValue(new HSSFRichTextString(headers[i]));
			}
			rownum++;
		}
		for (int i = 0; i < list.size(); i++) {
			HSSFRow rowcontent = sheet.createRow(rownum);
			Object[] objs = list.get(i);
			for (short columnIndex = 0; columnIndex < objs.length; columnIndex++) {
				HSSFCell cell = rowcontent.createCell((short) columnIndex);
				cell.setCellStyle(cellStyle);
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell.setCellStyle(cellStyle);
				if (objs[columnIndex] != null) {
					cell.setCellValue(new HSSFRichTextString(objs[columnIndex].toString()));
				} else {
					cell.setCellValue(new HSSFRichTextString(""));
				}
			}
			rownum++;
		}
		return toInputStream(wb);
	}
	/**
	 * Excel分页导出
	 * @param headers 标题行
	 * @param list 数据集合
	 * @param sheetNames sheet页的名称(sheet页有名称则赋值，没名称则为null)
	 * @param sheetNum sheet页的个数(如果为分页，由flag参数决定是否分页，当不分页时候，其值默认为0)
	 * @param sheetSize sheet页大小（如果为分页，由flag参数决定是否分页，当不分页时候，其值默认为0）
	 * @return InputStream输入流
	 */
	public static InputStream export(String[] headers, 
			List<Object[]> list,
			String[] sheetNames,
			int sheetNum, int sheetSize) {
		//生成一个Excel文件
		HSSFWorkbook wb = new HSSFWorkbook();
		// 分页导出
		for (int k = 0; k < sheetNum; k++) {
			// 行号
			int rownum = 0;
			HSSFSheet sheet =null;
			if(sheetNames[k]!=null&&!sheetNames[k].toString().equals("")){
				//sheet页有别名，则命别名
				sheet=wb.createSheet(sheetNames[k]);
			}else{
				//sheet页没有别名，则默认
				sheet=wb.createSheet();
			}
			HSSFFont font = wb.createFont();
			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			font.setFontName("宋体");
			font.setFontHeight((short) 300);
			HSSFCellStyle cellStyle = wb.createCellStyle();
			cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			HSSFDataFormat format = wb.createDataFormat();
			cellStyle.setDataFormat(format.getFormat("@"));
			cellStyle.setWrapText(true);
			sheet.setDefaultColumnWidth((short) 30);
			if (headers != null && headers.length > 0) {
				// 标题行不为空（设置标题行）
				HSSFRow row0 = sheet.createRow(rownum);
				for (int i = 0; i < headers.length; i++) {
					HSSFCell cell = row0.createCell((short) i);
					cell.setCellStyle(cellStyle);
					cell.setCellType(HSSFCell.CELL_TYPE_STRING);
					cell.setCellValue(new HSSFRichTextString(headers[i]));
				}
				rownum++;
			}
			// 行数据赋值
			List<Object[]> li = new ArrayList<Object[]>();
			if (k + 1 < sheetNum) {
				// 非最后一页
				li.addAll(list.subList(k * sheetSize, (k + 1)* sheetSize));
			} else {
				// 最后一页
				li.addAll(list.subList(k * sheetSize, list.size()));
			}
			for (int i = 0; i < li.size(); i++) {
				HSSFRow rowcontent = sheet.createRow(rownum);
				Object[] objs = li.get(i);
				for (short columnIndex = 0; columnIndex < objs.length; columnIndex++) {
					HSSFCell cell = rowcontent.createCell((short) columnIndex);
					cell.setCellType(HSSFCell.CELL_TYPE_STRING);
					cell.setCellStyle(cellStyle);
					if (objs[columnIndex] != null) {
						cell.setCellValue(new HSSFRichTextString(objs[columnIndex].toString()));
					}
				}
				rownum++;
			}

		}
		return toInputStream(wb);
	}
	
	private static ByteArrayInputStream toInputStream(HSSFWorkbook wb) {
		ByteArrayInputStream in = null;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			wb.write(bos);
			byte[] bytes = bos.toByteArray();
			in = new ByteArrayInputStream(bytes);
		} catch (Exception e) {
			throw new RuntimeException("获取Excel模板出错", e);
		} finally {
			try {
				bos.flush();
				bos.close();
			} catch (IOException e) {
				throw new RuntimeException("列表导出为Excel流关闭错误", e);
			}
		}
		return in;
	}
}
