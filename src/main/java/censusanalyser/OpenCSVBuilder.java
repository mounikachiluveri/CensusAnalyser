package censusanalyser;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.Reader;
import java.util.Iterator;

public class OpenCSVBuilder implements ICSVBuilder {

    @Override
    public Iterator getCSVFileIterator(Reader reader, Class csvClass) throws CensusAnalyserException {
        return null;
    }
}