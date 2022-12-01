package com.statescensusanalyser;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.statescensusanalyser.StateAnalyzerException.ExceptionType;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.StreamSupport;

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

    public int readStateCodeCSVData(String FilePath) throws StateAnalyzerException {

        try {
            Files.newBufferedReader(Paths.get(FilePath));
            Reader reader=Files.newBufferedReader(Paths.get(FilePath));
            CsvToBean<CSVStates> csvToBean =
                    new CsvToBeanBuilder<CSVStates>(reader)
                            .withIgnoreLeadingWhiteSpace(true)
                            .withSkipLines(1)
                            .withType(CSVStates.class).build();

            Iterator<CSVStates> csvIterator = csvToBean.iterator();

            Iterable<CSVStates> csvItrable= () -> csvIterator;
            int count=(int) StreamSupport.stream(csvItrable.spliterator(),false)
                    .count();
            return count;
        }catch(IOException exception) {
            throw new StateAnalyzerException("Invalid Path Name",
                    ExceptionType.INVALID_FILE_PATH);
        }catch(IllegalStateException exception){
            throw new StateAnalyzerException("Invalid Class Type.",
                    ExceptionType.INVALID_CLASS_TYPE);
        }
    }
}