package censusanalyser;

public class IndiaCensusDAO {
    public String state;
    public String stateCode;
    public int population;
    public int densityPerSqKm;
    public int areaInAqKm;

    public IndiaCensusDAO(IndiaCensusCSV indiaCensusCSV) {
        state = indiaCensusCSV.state;
        areaInAqKm = indiaCensusCSV.areaInAqKm;
        densityPerSqKm =indiaCensusCSV.densityPerSqKm;
        population = indiaCensusCSV.population;
    }

    public IndiaCensusDAO(IndiaStateCSV indiaStateCSV){
        stateCode = indiaStateCSV.stateCode;
    }
}