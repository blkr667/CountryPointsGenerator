package blkr.apps.countries.ponitsInCountryFilter;

import java.util.List;

import com.vividsolutions.jts.geom.MultiPolygon;
import com.vividsolutions.jts.geom.Point;

import blkr.apps.countries.ponitsInCountryFilter.pointsGenerator.PointsGenerator;
import blkr.apps.countries.ponitsInCountryFilter.pointsInPolygonFilter.PointsInPolygonFilter;
import blkr.apps.countries.ponitsInCountryFilter.polygonReader.PolygonFileReader;
import blkr.apps.countries.ponitsInCountryFilter.polygonReader.PolygonFileReaderException;

public class RandomPointsFromCountryProvider implements PointsFromCountryProvider {
	private final PolygonFileReader polygonFileReader;
	private final PointsGenerator pointsGenerator;
	private final PointsInPolygonFilter pointsInPolygonFilter;
	
	public RandomPointsFromCountryProvider(
			PolygonFileReader polygonFileReader,
			PointsGenerator pointsGenerator,
			PointsInPolygonFilter pointsInPolygonFilter) {
		this.polygonFileReader = polygonFileReader;
		this.pointsGenerator = pointsGenerator;
		this.pointsInPolygonFilter = pointsInPolygonFilter;
	}
	
	public List<Point> providePointsFromCountry(String countryName) throws CountryPointsProviderException {
		try {
			MultiPolygon countryPolygon = polygonFileReader.readPolygonFromFile(countryName);
			List<Point> randomPoints = pointsGenerator.generatePointList(1000000);
			return pointsInPolygonFilter.filterPointsInsidePolygon(countryPolygon, randomPoints);
		} catch (PolygonFileReaderException e) {
			throw new CountryPointsProviderException("Country points not found. " + e.getMessage());
		}

	}
}
