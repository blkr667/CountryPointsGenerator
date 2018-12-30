package blkr.apps.countries.ponitsInCountryFilter.pointsInPolygonFilter.filterPhase;

import java.util.List;
import java.util.stream.Collectors;

import com.vividsolutions.jts.geom.MultiPolygon;
import com.vividsolutions.jts.geom.Point;

public class EnvelopeFilterPhase implements FilterPhase {
	@Override
	public List<Point> doFilter(MultiPolygon multiPolygon, List<Point> points) {
		final double maxX = multiPolygon.getEnvelopeInternal().getMaxX();
		final double minX = multiPolygon.getEnvelopeInternal().getMinX();
		final double maxY = multiPolygon.getEnvelopeInternal().getMaxY();
		final double minY = multiPolygon.getEnvelopeInternal().getMinY();

		return points.stream().filter(point -> 
			minX <= point.getX() &&
			point.getX() <= maxX &&
			minY <= point.getY() &&
			point.getY() <= maxY
		).collect(Collectors.toList());
	}

}
