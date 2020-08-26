package censusanalyser;

public class CSVBuilderException extends Exception {

    enum ExceptionType {
        CENSUS_FILE_PROBLEM,
        INCORRECT_DATA_PROBLEM,
        UNABLE_TO_PARSE;
    }

    ExceptionType type;

    public CSVBuilderException(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }

    public CSVBuilderException(String message, ExceptionType type, Throwable cause) {
        super(message, cause);
        this.type = type;
    }

    public CSVBuilderException(String message, String name) {
        super(message);
        this.type = ExceptionType.valueOf(name);
    }
}
