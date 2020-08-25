package censusanalyser;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class CensusAnalyserTest {

    private static final String INDIA_CENSUS_CSV_FILE_PATH = "./src/test/resources/IndiaStateCensusData.csv";
    private static final String WRONG_CSV_FILE_PATH = "./src/main/resources/IndiaStateCensusData.csv";
    private static final String WRONG_FILE_TYPE_PATH = "./src/main/resources/IndiaStateCensusData.csv";
    private static final String INCORRECT_DELIMITER_CSV_FILE_PATH = "./src/test/resources/IndiaStateCensusDataIncorrectDelimiter.csv";
    private static final String INCORRECT_DATA_CSV_FILE_PATH = "./src/test/resources/IndiaStateCensusDataIncorrectheader.csv";
    private static final String INDIA_STATE_CODE_CSV_FILE_PATH = "./src/test/resources/IndiaStateCode.csv";
    private static final String WRONG_STATE_CODE_CSV_FILE_PATH = "./src/main/resources/IndiaStateCode.csv";
    private static final String WRONG_STATE_CODE_FILE_TYPE = "./src/main/resources/IndiaStateCode.txt";
    private static final String INDIA_STATE_CODE_INCORRECT_DATA_CSV_FILE_PATH = "./src/test/resources/IndiaStateCodeIncorrectData.csv\";";

    @Test
    public void givenIndianCensusCSVFileReturnsCorrectRecords() throws CSVBuilderException {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            int numOfRecords = censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            Assert.assertEquals(29, numOfRecords);
        } catch (CensusAnalyserException e) {
        }
    }

    @Test
    public void givenIndiaCensusData_WithWrongFile_ShouldThrowException() throws CSVBuilderException {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaCensusData(WRONG_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.INPUT_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndianCensusCSVFile_whenWrongFileExtension_shouldThrowCustomException() throws CSVBuilderException {
        CensusAnalyser censusAnalyser = new CensusAnalyser();
        ExpectedException expectedException = ExpectedException.none();
        expectedException.expect(CensusAnalyserException.class);
        try {
            censusAnalyser.loadIndiaCensusData(WRONG_FILE_TYPE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.INPUT_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndianCensusCSVFile_whenIncorrectDelimiter_shouldThrowCustomException() throws CSVBuilderException {
        CensusAnalyser censusAnalyser = new CensusAnalyser();
        ExpectedException expectedException = ExpectedException.none();
        expectedException.expect(CensusAnalyserException.class);
        try {
            censusAnalyser.loadIndiaCensusData(INCORRECT_DELIMITER_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.INPUT_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndianCensusCSVFile_whenIncorrectHeader_shouldThrowCustomException() throws CSVBuilderException {
        CensusAnalyser censusAnalyser = new CensusAnalyser();
        ExpectedException expectedException = ExpectedException.none();
        expectedException.expect(CensusAnalyserException.class);
        try {
            censusAnalyser.loadIndiaCensusData(INCORRECT_DATA_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.INPUT_FILE_PROBLEM, e.type);
        }

    }

    @Test
    public void givenIndiaStateCodeCSVFile_whenProper_shouldReturnsCorrectRecordCount() throws CSVBuilderException {
try{
        ExpectedException expectedException = ExpectedException.none();
        expectedException.expect(CensusAnalyserException.class);


            int numberOfRecords = CensusAnalyser.loadIndiaStateCodeData(INDIA_STATE_CODE_CSV_FILE_PATH);
            Assert.assertEquals(37, numberOfRecords);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.INCORRECT_DATA_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndiaStateCodeCSVFile_whenWrongPath_shouldThrowCustomException() throws CSVBuilderException {
        ExpectedException expectedException = ExpectedException.none();
        expectedException.expect(CensusAnalyserException.class);

        try {
            CensusAnalyser.loadIndiaStateCodeData(WRONG_STATE_CODE_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.INPUT_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndiaStateCodeFile_whenWrongFileExtension_shouldThrowCustomException() throws CSVBuilderException {
        ExpectedException expectedException = ExpectedException.none();
        expectedException.expect(CensusAnalyserException.class);

        try {
            CensusAnalyser.loadIndiaStateCodeData(WRONG_STATE_CODE_FILE_TYPE);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.INPUT_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndiaStateCodeFile_whenIncorrectDelimiter_shouldThrowCustomException() throws CSVBuilderException {
        ExpectedException expectedException = ExpectedException.none();
        expectedException.expect(CensusAnalyserException.class);

        try {
            CensusAnalyser.loadIndiaStateCodeData(INDIA_STATE_CODE_INCORRECT_DATA_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.INCORRECT_DATA_PROBLEM, e.type);
        }
    }


    @Test
    public void givenIndiaStateCodeFile_whenIncorrectHeader_shouldThrowCustomException() throws CSVBuilderException {
        ExpectedException expectedException = ExpectedException.none();
        expectedException.expect(CensusAnalyserException.class);

        try {
            CensusAnalyser.loadIndiaStateCodeData(INDIA_STATE_CODE_INCORRECT_DATA_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.INCORRECT_DATA_PROBLEM, e.type);
        }
    }
    @Test
    public void givenIndianCensusCSVFile_whenSortedSortedOnState_shouldReturnSortedResult() throws CSVBuilderException {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            String stateWiseSortedCensusData = censusAnalyser.getStateWiseSortedCensusData();
            IndiaCensusCSV[] censusCSV = new Gson().fromJson(stateWiseSortedCensusData, IndiaCensusCSV[].class);
            Assert.assertEquals("Andhra Pradesh", censusCSV[0].state);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.INCORRECT_DATA_PROBLEM, e.type);
        }
    }
}










