package com.file.reader.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.file.reader.exception.FileProcessingException;
import com.file.reader.exception.InvalidRequestException;
import com.file.reader.utils.UserConstants;

@Service("excelReader")
public class ExcelFileReaderServiceImpl implements FileReaderService {

	@Override
	public List<String[]> readFile(MultipartFile file)
			throws InvalidRequestException, FileProcessingException, IOException {

//		try {
//			return readExcelToObjectList(file);
//
//		} catch (IOException e) {
//			throw new FileProcessingException(ErrorCodes.FILE_PROCESSING_ERROR, "Error processing the uploaded file.");
//		} catch (Exception e) {
//			throw new FileProcessingException(ErrorCodes.UNKNOWN_ERROR,
//					"An unknown error occurred while processing the file.");
//		}
//
//	}
//
//	private List<String[]> readExcelToObjectList(MultipartFile file) throws IOException {
		try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
			Sheet sheet = workbook.getSheetAt(0);
			List<String[]> listOfExcelObject = new ArrayList<>();
			for (Row raw : sheet) {
				String[] excelArray = new String[raw.getLastCellNum()];
				for (int i = 0; i < raw.getLastCellNum(); i++) {
					Cell cell = raw.getCell(i);
					excelArray[i] = (cell != null) ? cell.toString() : null;
				}
				listOfExcelObject.add(excelArray);
			}
			return listOfExcelObject;
		}

	}

	public String fileType() {
		return UserConstants.EXCEL_FILE;
	}

}
