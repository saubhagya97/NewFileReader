package com.file.reader.service;

import java.io.InputStreamReader;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.file.reader.exception.FileProcessingException;
import com.file.reader.exception.InvalidRequestException;
import com.file.reader.utils.ErrorCodes;
import com.file.reader.utils.UserConstants;
import com.opencsv.CSVReader;

@Service("csvReader")
public class CsvFileReaderServiceImpl implements FileReaderService {

	@Override
	public List<String[]> readFile(MultipartFile file) throws InvalidRequestException, FileProcessingException {
//		try {
//			if (isCsvFile(file)) {
//				return processCsvFile(file);
//			} else {
//				throw new InvalidRequestException(ErrorCodes.INVALID_INPUT, "It is not a csv file");
//			}
//		} catch (Exception e) {
//			throw new FileProcessingException(ErrorCodes.FILE_PROCESSING_ERROR, "Error processing the CSV file");
//		}
//
//	}
//
//	private List<String[]> processCsvFile(MultipartFile file) throws FileProcessingException {
		try (CSVReader csvReader = new CSVReader(new InputStreamReader(file.getInputStream()))) {
			List<String[]> listOfCsvObject = csvReader.readAll();
			if (CollectionUtils.isEmpty(listOfCsvObject)) {
				throw new InvalidRequestException(ErrorCodes.DATA_NOT_FOUND, "CSV file is empty");
			}
			return listOfCsvObject;
		} catch (Exception e) {
			throw new FileProcessingException(ErrorCodes.FILE_PROCESSING_ERROR, "Error processing the CSV file");
		}
	}

	@Override
	public String fileType() {
		return UserConstants.CSV_FILE;
	}

}
