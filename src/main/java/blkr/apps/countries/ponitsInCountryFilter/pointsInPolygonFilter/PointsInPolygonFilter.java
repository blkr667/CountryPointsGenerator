package blkr.apps.countries.ponitsInCountryFilter.pointsInPolygonFilter;

import java.util.List;

import com.vividsolutions.jts.geom.MultiPolygon;
import com.vividsolutions.jts.geom.Point;

public interface PointsInPolygonFilter {
	List<Point> filterPointsInsidePolygon(MultiPolygon polygon, List<Point> points);
}

