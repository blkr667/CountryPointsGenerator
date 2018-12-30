package blkr.apps.countries.ponitsInCountryFilter.pointsGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;

public class RandomPointsGenerator implements PointsGenerator {
	private static final int MAX_LATITUDE = 90;
	private static final int MAX_LONGTITUDE = 180;
	
	public List<Point> generatePointList(int size) {
		List<Point> points = new ArrayList<Point>();
		for(int i = 0; i < size; ++i)
			points.add(createRandomPoint());
		return points; 
	}
	
	private Point createRandomPoint() {
		GeometryFactory geometryFactory = new GeometryFactory();
		Coordinate coordinate = new Coordinate(
				generateRandomNumberInRange(MAX_LATITUDE), 
				generateRandomNumberInRange(MAX_LONGTITUDE));
		return geometryFactory.createPoint(coordinate);
	}
	
	private double generateRandomNumberInRange(int positiveAndNegativeRange) {
		return ThreadLocalRandom.current().nextDouble(-positiveAndNegativeRange, positiveAndNegativeRange);
	}
	

}
