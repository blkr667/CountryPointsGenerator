package blkr.apps.countries.ponitsInCountryFilter.pointsInPolygonFilter.filterPhase;

import java.util.List;
import java.util.stream.Collectors;

import com.vividsolutions.jts.geom.MultiPolygon;
import com.vividsolutions.jts.geom.Point;

public class BruteForceFilterPhase implements FilterPhase {
	@Override
	public List<Point> doFilter(MultiPolygon polygon, List<Point> points) {
		return points.stream().filter(point -> polygon.contains(point)).collect(Collectors.toList());
	}
}
