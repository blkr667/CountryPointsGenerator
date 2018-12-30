package blkr.apps.countries.ponitsInCountryFilter.polygonReader;

import com.vividsolutions.jts.geom.MultiPolygon;

public interface PolygonFileReader {
	MultiPolygon readPolygonFromFile(String countryName) throws PolygonFileReaderException;
}
