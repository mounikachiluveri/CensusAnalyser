package censusanalyser;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class CensusAnalyser {

    Map<String, IndiaCensusDAO> censusMap;

    public CensusAnalyser() {
        this.censusMap = new HashMap<String, IndiaCensusDAO>();
    }

    public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<IndiaCensusCSV> censusCSVIterator = csvBuilder.getCSVFileIterator(reader, IndiaCensusCSV.class);
            Iterable<IndiaCensusCSV> censusCSVIterable = () -> censusCSVIterator;
            StreamSupport.stream(censusCSVIterable.spliterator(),false)
                    .forEach(censusCSV -> censusMap.put(censusCSV.state, new IndiaCensusDAO(censusCSV)));
            return this.censusMap.size();
        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.INPUT_FILE_PROBLEM);
        } catch (RuntimeException e){
            throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.INCORRECT_DATA_PROBLEM);
        } catch (CSVBuilderException e) {
            throw new CensusAnalyserException(e.getMessage(), e.type.name());
        }
    }

    public  int loadIndiaStateCodeData(String csvFilePath) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<IndiaStateCSV> stateCSVIterator = csvBuilder.getCSVFileIterator(reader, IndiaStateCSV.class);
            Iterable<IndiaStateCSV> censusCSVIterable = () -> stateCSVIterator;
            StreamSupport.stream(censusCSVIterable.spliterator(),false)
                    .filter(csvState -> censusMap.get(csvState.stateName) != null)
                    .forEach(censusCSV -> censusMap.get(censusCSV.stateName).state = censusCSV.stateCode);
            return this.censusMap.size();
        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.INPUT_FILE_PROBLEM);
        } catch (RuntimeException e){
            throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.INCORRECT_DATA_PROBLEM);
        } catch (CSVBuilderException e) {
            throw new CensusAnalyserException(e.getMessage(), e.type.name());
        }
    }

    private static <E> int getCount(Iterator<E> iterator){
        Iterable<E> csvIterable = () -> iterator;
        return (int) StreamSupport.stream(csvIterable.spliterator(), false).count();
    }

    public String getStateWiseSortedCensusData() throws CensusAnalyserException {
        if (censusMap == null || censusMap.size() == 0){
            throw new CensusAnalyserException("No Census Data", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<IndiaCensusDAO> censusComparator = Comparator.comparing(census -> census.state);
        List<IndiaCensusDAO> censusDAOList = censusMap.values().stream().collect(Collectors.toList());
        censusDAOList = ascendingSort(censusComparator, censusDAOList);
        return new Gson().toJson(censusDAOList);
    }

    public String getPopulationWiseSortedCensusData() throws CensusAnalyserException {
        if (censusMap == null || censusMap.size() == 0){
            throw new CensusAnalyserException("No Census Data", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<IndiaCensusDAO> censusComparator = Comparator.comparing(census -> census.population);
        List<IndiaCensusDAO> censusDAOList = censusMap.values().stream().collect(Collectors.toList());
        censusDAOList = descendingSort(censusComparator, censusDAOList);
        return new Gson().toJson(censusDAOList);
    }

    public String getPopulationDensityWiseSortedCensusData() throws CensusAnalyserException {
        if (censusMap == null || censusMap.size() == 0){
            throw new CensusAnalyserException("No Census Data", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<IndiaCensusDAO> censusComparator = Comparator.comparing(census -> census.densityPerSqKm);
        List<IndiaCensusDAO> censusDAOList = censusMap.values().stream().collect(Collectors.toList());
        censusDAOList = descendingSort(censusComparator, censusDAOList);
        return new Gson().toJson(censusDAOList);
    }

    private List<IndiaCensusDAO> descendingSort(Comparator<IndiaCensusDAO> csvComparator, List<IndiaCensusDAO> censusList) {
        for (int i = 0; i < censusList.size() - 1; i++ ){
            for (int j = 0; j < censusList.size() - i - 1; j++ ){
                IndiaCensusDAO census1 = censusList.get(j);
                IndiaCensusDAO census2 = censusList.get(j + 1);
                if (csvComparator.compare(census1, census2) < 0){
                    censusList.set(j,census2);
                    censusList.set(j+1, census1);
                }
            }
        }
        return censusList;
    }

    private <IndiaCensusDAO> List<IndiaCensusDAO> ascendingSort(Comparator<IndiaCensusDAO> csvComparator, List<IndiaCensusDAO> censusList) {
        for (int i = 0; i < censusList.size() - 1; i++ ){
            for (int j = 0; j < censusList.size() - i - 1; j++ ){
                IndiaCensusDAO census1 = censusList.get(j);
                IndiaCensusDAO census2 = censusList.get(j + 1);
                if (csvComparator.compare(census1, census2) > 0){
                    censusList.set(j,census2);
                    censusList.set(j+1, census1);
                }
            }
        }
        return censusList;
    }


}