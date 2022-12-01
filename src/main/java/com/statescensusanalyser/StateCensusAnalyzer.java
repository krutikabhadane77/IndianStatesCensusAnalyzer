package com.statescensusanalyser;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.statescensusanalyser.StateAnalyzerException.ExceptionType;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

public class StateCensusAnalyzer {

    public int readCSVData(String FilePath) throws StateAnalyzerException {
        int count=0;
        try {
            try {
                Files.newBufferedReader(Paths.get(FilePath));
            }catch(IOException exception) {
                throw new StateAnalyzerException("Inavlid Path Name",
                        ExceptionType.INVALID_FILE_PATH);
            }
            Reader reader=Files.newBufferedReader(Paths.get(FilePath));
            CsvToBeanBuilder<CSVStateCensus> csvToBeanBuilder =
                    new CsvToBeanBuilder<CSVStateCensus>(reader);

            try {
                csvToBeanBuilder
                        .withIgnoreLeadingWhiteSpace(true)
                        .withSkipLines(1)
                        .withType(CSVStateCensus.class);
            }catch(IllegalStateException exception){
                throw new StateAnalyzerException("Invalid Class Type.",
                        ExceptionType.INVALID_CLASS_TYPE);
            }

            CsvToBean<CSVStateCensus> csvToBean=csvToBeanBuilder.build();
            Iterator<CSVStateCensus> csvIterator = csvToBean.iterator();

            while (csvIterator.hasNext()) {
                count++;
                CSVStateCensus csvData=csvIterator.next();
                System.out.println(csvData);
            }
            return count;

        }catch(IOException exception){
            exception.printStackTrace();
        }
        return 0;
    }
}