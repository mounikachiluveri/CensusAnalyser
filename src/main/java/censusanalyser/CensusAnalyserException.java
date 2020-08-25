package censusanalyser;

public class CensusAnalyserException extends Exception {

    public CensusAnalyserException(String message) {
    }

    enum ExceptionType {
        INCORRECT_DATA_PROBLEM, UNABLE_TO_PARSE, CENSUS_FILE_PROBLEM, INPUT_FILE_PROBLEM,NO_CENSUS_DATA;
    }

    ExceptionType type;

    public CensusAnalyserException(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }

    public CensusAnalyserException(String message, ExceptionType type, Throwable cause) {
        super(message, cause);
        this.type = type;
    }
}
