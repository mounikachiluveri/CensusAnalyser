package censusanalyser;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;

public class CensusAnalyser {
    static List<IndiaCensusCSV> censusCSVList = null;
    static List<IndiaStateCSV> stateCSVList = null;

    public static int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            censusCSVList = csvBuilder.getCSVFileList(reader,IndiaCensusCSV.class);
            return censusCSVList.size();
        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.INPUT_FILE_PROBLEM);
        } catch (RuntimeException e){
            throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.INCORRECT_DATA_PROBLEM);
        } catch (CSVBuilderException e) {
            throw new CensusAnalyserException(e.getMessage(), e.type.name());
        }
    }

    public static int loadIndiaStateCodeData(String csvFilePath) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            stateCSVList = csvBuilder.getCSVFileList(reader,IndiaStateCSV.class);
            return stateCSVList.size();
        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.INPUT_FILE_PROBLEM);
        } catch (RuntimeException e){
            throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.INCORRECT_DATA_PROBLEM);
        } catch (CSVBuilderException e) {
            throw new CensusAnalyserException(e.getMessage(), e.type.name());
        }
    }

    public String getStateWiseSortedCensusData() throws CensusAnalyserException {
        if (censusCSVList == null || censusCSVList.size() == 0){
            throw new CensusAnalyserException("No Census Data", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<IndiaCensusCSV> censusComparator = Comparator.comparing(census -> census.state);
        censusCSVList = sort(censusComparator, censusCSVList);
        return new Gson().toJson(censusCSVList);
    }

    public String getStateCodeWiseSortedCensusData() throws CensusAnalyserException {
        if (stateCSVList == null || stateCSVList.size() == 0){
            throw new CensusAnalyserException("No Census Data", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<IndiaStateCSV> stateComparator = Comparator.comparing(census -> census.stateCode);
        stateCSVList = sort(stateComparator, stateCSVList);
        return new Gson().toJson(stateCSVList);
    }

    private <E> List<E> sort(Comparator<E> csvComparator, List<E> censusList) {
        for (int i = 0; i < censusList.size() - 1; i++ ){
            for (int j = 0; j < censusList.size() - i - 1; j++ ){
                E census1 = censusList.get(j);
                E census2 = censusList.get(j + 1);
                if (csvComparator.compare(census1, census2) > 0){
                    censusList.set(j,census2);
                    censusList.set(j+1, census1);
                }
            }
        }
        return censusList;
    }

}