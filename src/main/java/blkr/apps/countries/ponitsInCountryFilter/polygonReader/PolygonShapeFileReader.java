package blkr.apps.countries.ponitsInCountryFilter.polygonReader;

import java.io.IOException;

import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.data.simple.SimpleFeatureIterator;
import org.geotools.data.simple.SimpleFeatureSource;
import org.geotools.filter.text.cql2.CQL;
import org.geotools.filter.text.cql2.CQLException;
import org.opengis.filter.Filter;
import org.springframework.beans.factory.annotation.Autowired;

import com.vividsolutions.jts.geom.MultiPolygon;

import blkr.apps.countries.ponitsInCountryFilter.polygonReader.utils.PolygonParser;
import blkr.apps.countries.ponitsInCountryFilter.polygonReader.utils.ShapeFileReader;

public class PolygonShapeFileReader implements PolygonFileReader {
	private final ShapeFileReader shapeFileReader;
	private final PolygonParser polygonParser;
	private static final String CQL_NAME_QUERY = "NAME_EN like '%%%s%%'";

	@Autowired
	public PolygonShapeFileReader(ShapeFileReader shapeFileReader, PolygonParser polygonParser) {
		this.shapeFileReader = shapeFileReader;
		this.polygonParser = polygonParser;
	}

	public MultiPolygon readPolygonFromFile(String countryName) throws PolygonFileReaderException {
		try {
			SimpleFeatureSource featureSource = shapeFileReader.readShapeFileResource();
			SimpleFeatureCollection featureCollection = featureSource.getFeatures(getCountryFilter(countryName));
			return getPolygonFromColelction(featureCollection);
		} catch (CQLException | IOException e) {
			throw new PolygonFileReaderException("Can not find country polygon inside ShapeFile: " + e.getMessage());
		}
	}

	private Filter getCountryFilter(String countryName) throws CQLException {
		return CQL.toFilter(String.format(CQL_NAME_QUERY, toQueryNameFormat(countryName)));
	}

	private String toQueryNameFormat(String countryName) {
		return countryName.substring(0, 1).toUpperCase() + countryName.substring(1).toLowerCase();
	}

	private MultiPolygon getPolygonFromColelction(SimpleFeatureCollection simpleFeatureCollection)
			throws PolygonFileReaderException {
		try (SimpleFeatureIterator iterator = simpleFeatureCollection.features()) {
			if (iterator.hasNext())
				return polygonParser.parse(iterator.next());
		}
		throw new PolygonFileReaderException("Polygon for given country name not found in shape file.");
	}
}