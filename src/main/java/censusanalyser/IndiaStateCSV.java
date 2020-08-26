package censusanalyser;

import com.opencsv.bean.CsvBindByName;

public class IndiaStateCSV {
    @CsvBindByName(column = "StateName", required = true)
    public String state;

    @CsvBindByName(column = "StateCode", required = true)
    public String stateCode;
    public String stateName;

    @Override
    public String toString() {
        return "IndiaStateCSV{" +
                "state='" + state + '\'' +
                ", stateCode='" + stateCode + '\'' +
                '}';
    }
}
