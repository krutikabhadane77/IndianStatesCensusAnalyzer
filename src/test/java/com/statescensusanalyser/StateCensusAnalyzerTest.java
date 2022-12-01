package com.statescensusanalyser;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
public class StateCensusAnalyzerTest {

    private final String CENSUS_CSV_PATH="C:\\Terminalcommand\\linux-content\\IndianStatesCensusAnalyserProblem\\src\\IndiaStateCensusData.csv";
    private final String INVALID_CENSUS_CSV_PATH="C:\\Terminalcommand\\linux-content\\IndianStatesCensusAnalyserProblem\\src\\IndiaStateCensusData.csv";
    private final String STATECODE_CSV_PATH="C:\\Terminalcommand\\linux-content\\IndianStatesCensusAnalyserProblem\\src\\IndiaStateCode.csv";
    private final String INVALID_STATECODE_CSV_PATH="IndiaStateCode.csv";
    private StateCensusAnalyzer analyser;

    @Before
    public void init() {
        analyser = new StateCensusAnalyzer();
    }

    @Test
    public void CensusCSVFileReturnsCorrectNoOfEntries() throws StateAnalyzerException {
        int stateCount = analyser.readCSVData(CENSUS_CSV_PATH);
        assertEquals(28, stateCount);
    }

    @Test
    public void IncorrectCSVFilePathThrowsCustomExceptionInvalidFilePath(){
        try {
            analyser.readCSVData(INVALID_CENSUS_CSV_PATH);
        } catch (StateAnalyzerException e) {
            e.printStackTrace();
            assertEquals(StateAnalyzerException.ExceptionType.INVALID_FILE_PATH, e.type);
        }
    }

    @Test
    public void CodeCSVFileReturnsCorrectNoOfEntries() throws StateAnalyzerException{
        int stateCount = analyser.readStateCodeCSVData(STATECODE_CSV_PATH);
        assertEquals(36, stateCount);
    }

    @Test
    public void IncorrectCSVFilePath_ThrowsCustomExceptionInvalidFilePath2(){
        try {
            analyser.readStateCodeCSVData(INVALID_STATECODE_CSV_PATH);
        } catch (StateAnalyzerException e) {
            e.printStackTrace();
            assertEquals(StateAnalyzerException.ExceptionType.INVALID_FILE_PATH, e.type);
        }
    }

}
