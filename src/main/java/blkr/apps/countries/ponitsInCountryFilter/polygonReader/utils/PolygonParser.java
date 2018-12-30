package blkr.apps.countries.ponitsInCountryFilter.polygonReader.utils;

import java.util.Arrays;

import org.locationtech.jts.geom.Geometry;

import org.opengis.feature.simple.SimpleFeature;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LinearRing;
import com.vividsolutions.jts.geom.MultiPolygon;
import com.vividsolutions.jts.geom.Polygon;
import com.vividsolutions.jts.geom.impl.CoordinateArraySequence;

public class PolygonParser {
	public MultiPolygon parse(SimpleFeature polygonFeature) {
		GeometryFactory geometryFactory = new GeometryFactory();
		Geometry geometry = (Geometry) polygonFeature.getDefaultGeometry();
		Polygon[] polygons = new Polygon[geometry.getNumGeometries()];
		for (int i=0; i<geometry.getNumGeometries(); i++) {
			polygons[i] = parseGeometry(geometry.getGeometryN(i), geometryFactory);
		}
		return new MultiPolygon(polygons, geometryFactory);
	}
	
	private Polygon parseGeometry(Geometry geometry, GeometryFactory geometryFactory) {
		Coordinate[] coordinates = Arrays
				.stream(geometry.getCoordinates())
				.map(this::parseCoordinate)
				.toArray(Coordinate[]::new);
		CoordinateArraySequence coordinateArraySequence = new CoordinateArraySequence(coordinates);
		LinearRing linearRing = new LinearRing(coordinateArraySequence, geometryFactory);
		return new Polygon(linearRing, null, geometryFactory);
	}
	
	private Coordinate parseCoordinate(org.locationtech.jts.geom.Coordinate coordinate) {
		return new Coordinate(coordinate.getX(), coordinate.getY());
	}
}
