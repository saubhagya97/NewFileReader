package com.file.reader.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.file.reader.exception.InvalidRequestException;
import com.file.reader.utils.ErrorCodes;
import com.file.reader.utils.Util;

@Service
public class FileHelper {

    private Map<String, FileReaderService> fileProcessorMap;

//All the implementation class of file reader service come into picture with the help of @Autowired annotation
    public FileHelper(@Autowired Collection<FileReaderService> fileReaders) {
        fileProcessorMap = new HashMap<>();
        for (FileReaderService fileReader : fileReaders) {
            fileProcessorMap.put(fileReader.fileType(), fileReader);
        }
    }

    List<String[]> readFile(MultipartFile file) throws InvalidRequestException, Exception {
        String fileType = Util.findFileType(file);
        FileReaderService fileReaderService = fileProcessorMap.get(fileType);
        if (null == fileReaderService) {
            throw new InvalidRequestException(ErrorCodes.DATA_NOT_FOUND, "File is empty for file tpye " + fileType);
        }
        return fileReaderService.readFile(file);
    }

}
