package blkr.apps.countries.ponitsInCountryFilter;

import java.util.List;

import com.vividsolutions.jts.geom.Point;

public interface PointsFromCountryProvider {
	List<Point> providePointsFromCountry(String countryName) throws CountryPointsProviderException;
}
