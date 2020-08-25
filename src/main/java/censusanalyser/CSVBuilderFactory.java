package censusanalyser;

public class CSVBuilderFactory {
    public static ICSVBuilder createCSVbuilder() {
        return new OpenCSVBuilder();
    }

    public static ICSVBuilder createCSVBuilder() {
        return new OpenCSVBuilder();
    }
}

