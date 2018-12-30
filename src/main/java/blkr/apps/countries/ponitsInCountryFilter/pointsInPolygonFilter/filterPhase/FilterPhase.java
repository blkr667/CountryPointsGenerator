package blkr.apps.countries.ponitsInCountryFilter.pointsInPolygonFilter.filterPhase;

import java.util.List;

import com.vividsolutions.jts.geom.MultiPolygon;
import com.vividsolutions.jts.geom.Point;

@FunctionalInterface
public interface FilterPhase {
	List<Point> doFilter(MultiPolygon polygon, List<Point> points);
}
