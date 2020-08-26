package censusanalyser;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class CensusAnalyserTest {
    private static final String INDIA_CENSUS_CSV_FILE_PATH = "./src/test/resources/IndiaStateCensusData.csv";
    private static final String WRONG_CSV_FILE_PATH =  "./src/main/resources/IndiaStateCensusData.csv";
    private static final String WRONG_FILE_TYPE_PATH =  "./src/test/resources/IndiaStateCensusData.txt";
    private static final String INCORRECT_DATA_CSV_FILE_PATH = "./src/test/resources/IndiaStateCensusDataIncorrectDelimiter.csv";
    private static final String INDIA_STATE_CODE_CSV_FILE_PATH = "./src/test/resources/IndiaStateCode.csv";
    private static final String WRONG_STATE_CODE_CSV_FILE_PATH = "./src/main/resources/IndiaStateCode.csv";
    private static final String WRONG_STATE_CODE_FILE_TYPE = "./src/main/resources/IndiaStateCode.txt";
    private static final String INDIA_STATE_CODE_INCORRECT_DATA_CSV_FILE_PATH = "./src/test/resources/IndiaStateCodeIncorrectData.csv";
    private static final String US_CENSUS_CSV_FILE_PATH = "./src/test/resources/USCensusData.csv";
    @Test
    public void givenIndianCensusCSVFile_whenProper_shouldReturnsCorrectRecordCount() {
        try {
            CensusAnalyser  censusAnalyser = new CensusAnalyser();
            int numberOfRecords = censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            Assert.assertEquals(29, numberOfRecords);
        } catch (CensusAnalyserException e) {
        }
    }

    @Test
    public void givenIndianCensusCSVFile_whenWrongPath_shouldThrowCustomException() {
        CensusAnalyser  censusAnalyser = new CensusAnalyser();
        ExpectedException expectedException = ExpectedException.none();
        expectedException.expect(CensusAnalyserException.class);

        try {
            censusAnalyser.loadIndiaCensusData(WRONG_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.INPUT_FILE_PROBLEM,e.type);
        }
    }

    @Test
    public void givenIndianCensusCSVFile_whenWrongFileExtension_shouldThrowCustomException() {
        CensusAnalyser  censusAnalyser = new CensusAnalyser();
        ExpectedException expectedException = ExpectedException.none();
        expectedException.expect(CensusAnalyserException.class);

        try {
            censusAnalyser.loadIndiaCensusData(WRONG_FILE_TYPE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.INPUT_FILE_PROBLEM,e.type);
        }
    }

    @Test
    public void givenIndianCensusCSVFile_whenIncorrectDelimiter_shouldThrowCustomException() {
        CensusAnalyser  censusAnalyser = new CensusAnalyser();
        ExpectedException expectedException = ExpectedException.none();
        expectedException.expect(CensusAnalyserException.class);

        try {
            censusAnalyser.loadIndiaCensusData(INCORRECT_DATA_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.INCORRECT_DATA_PROBLEM,e.type);
        }
    }

    @Test
    public void givenIndianCensusCSVFile_whenIncorrectHeader_shouldThrowCustomException() {
        CensusAnalyser  censusAnalyser = new CensusAnalyser();
        ExpectedException expectedException = ExpectedException.none();
        expectedException.expect(CensusAnalyserException.class);

        try {
            censusAnalyser.loadIndiaCensusData(INCORRECT_DATA_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.INCORRECT_DATA_PROBLEM,e.type);
        }
    }

    @Test
    public void givenIndiaStateCodeCSVFile_whenProper_shouldReturnsCorrectRecordCount() {
        try{
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            int numberOfRecords = censusAnalyser.loadIndiaStateCodeData(INDIA_STATE_CODE_CSV_FILE_PATH);
            Assert.assertEquals(29, numberOfRecords);
        } catch (CensusAnalyserException e) {
        }
    }

    @Test
    public void  givenIndiaStateCodeCSVFile_whenWrongPath_shouldThrowCustomException() {
        CensusAnalyser  censusAnalyser = new CensusAnalyser();
        ExpectedException expectedException = ExpectedException.none();
        expectedException.expect(CensusAnalyserException.class);

        try {
            censusAnalyser.loadIndiaCensusData(WRONG_STATE_CODE_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.INPUT_FILE_PROBLEM,e.type);
        }
    }

    @Test
    public void givenIndiaStateCodeFile_whenWrongFileExtension_shouldThrowCustomException() {
        CensusAnalyser  censusAnalyser = new CensusAnalyser();
        ExpectedException expectedException = ExpectedException.none();
        expectedException.expect(CensusAnalyserException.class);

        try {
            censusAnalyser.loadIndiaCensusData(WRONG_STATE_CODE_FILE_TYPE);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.INPUT_FILE_PROBLEM,e.type);
        }
    }

    @Test
    public void givenIndiaStateCodeFile_whenIncorrectDelimiter_shouldThrowCustomException() {
        CensusAnalyser  censusAnalyser = new CensusAnalyser();
        ExpectedException expectedException = ExpectedException.none();
        expectedException.expect(CensusAnalyserException.class);

        try {
            censusAnalyser.loadIndiaCensusData(INDIA_STATE_CODE_INCORRECT_DATA_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.INCORRECT_DATA_PROBLEM,e.type);
        }
    }

    @Test
    public void givenIndiaStateCodeFile_whenIncorrectHeader_shouldThrowCustomException() {
        CensusAnalyser  censusAnalyser = new CensusAnalyser();
        ExpectedException expectedException = ExpectedException.none();
        expectedException.expect(CensusAnalyserException.class);

        try {
            censusAnalyser.loadIndiaCensusData(INDIA_STATE_CODE_INCORRECT_DATA_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.INCORRECT_DATA_PROBLEM,e.type);
        }
    }

    @Test
    public void givenIndianCensusCSVFile_whenSortedOnState_shouldReturnSortedResult() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            String stateWiseSortedCensusData = censusAnalyser.getStateWiseSortedCensusData();
            IndiaCensusCSV[] censusCSV = new Gson().fromJson(stateWiseSortedCensusData, IndiaCensusCSV[].class);
            Assert.assertEquals("Andhra Pradesh", censusCSV[0].state);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.NO_CENSUS_DATA, e.type);
        }
    }

    @Test
    public void givenIndiaStateCodeFile_whenSortedOnStateCode_shouldReturnSortedResult() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            censusAnalyser.loadIndiaStateCodeData(INDIA_STATE_CODE_CSV_FILE_PATH);
            String stateWiseSortedCensusData = censusAnalyser.getStateWiseSortedCensusData();
            IndiaCensusCSV[] censusCSV = new Gson().fromJson(stateWiseSortedCensusData, IndiaCensusCSV[].class);
            Assert.assertEquals("AP", censusCSV[0].state);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.NO_CENSUS_DATA, e.type);
        }
    }

    @Test
    public void givenIndianCensusCSVFile_whenSortedOnPopulation_shouldReturnSortedResult() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            String stateWiseSortedCensusData = censusAnalyser.getPopulationWiseSortedCensusData();
            IndiaCensusCSV[] censusCSV = new Gson().fromJson(stateWiseSortedCensusData, IndiaCensusCSV[].class);
            Assert.assertEquals("Uttar Pradesh", censusCSV[0].state);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.NO_CENSUS_DATA, e.type);
        }
    }

    @Test
    public void givenIndianCensusCSVFile_whenSortedOnPopulationDensity_shouldReturnSortedResult() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            String stateWiseSortedCensusData = censusAnalyser.getPopulationDensityWiseSortedCensusData();
            IndiaCensusCSV[] censusCSV = new Gson().fromJson(stateWiseSortedCensusData, IndiaCensusCSV[].class);
            Assert.assertEquals("Bihar", censusCSV[0].state);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.NO_CENSUS_DATA, e.type);
        }
    }
    @Test
    public void givenIndianCensusCSVFile_whenSortedOnArea_shouldReturnSortedResult() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            String stateWiseSortedCensusData = censusAnalyser.getAreaWiseSortedCensusData();
            IndiaCensusCSV[] censusCSV = new Gson().fromJson(stateWiseSortedCensusData, IndiaCensusCSV[].class);
            Assert.assertEquals("Rajasthan", censusCSV[0].state);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.NO_CENSUS_DATA, e.type);
        }
    }
    @Test
    public void givenUSCensusCSVFile_whenSortedOnPopulation_shouldReturnSortedResult() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadUSCensusData(US_CENSUS_CSV_FILE_PATH);
            String stateWiseSortedCensusData = censusAnalyser.getPopulationWiseSortedCensusData();
            IndiaCensusCSV[] censusCSV = new Gson().fromJson(stateWiseSortedCensusData, IndiaCensusCSV[].class);
            Assert.assertEquals("California", censusCSV[0].state);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }

    }
    @Test
    public void givenUSCensusCSVFile_whenSortedOnArea_shouldReturnSortedResult() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadUSCensusData(US_CENSUS_CSV_FILE_PATH);
            String stateWiseSortedCensusData = censusAnalyser.getAreaWiseSortedCensusData();
            USCensusCSV[] censusCSV = new Gson().fromJson(stateWiseSortedCensusData, USCensusCSV[].class);
            Assert.assertEquals("Alaska", censusCSV[0].state);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }
}

