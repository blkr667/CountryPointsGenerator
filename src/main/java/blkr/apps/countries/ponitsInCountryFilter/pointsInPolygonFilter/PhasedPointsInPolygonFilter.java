package blkr.apps.countries.ponitsInCountryFilter.pointsInPolygonFilter;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import blkr.apps.countries.ponitsInCountryFilter.pointsInPolygonFilter.filterPhase.BruteForceFilterPhase;
import blkr.apps.countries.ponitsInCountryFilter.pointsInPolygonFilter.filterPhase.EnvelopeFilterPhase;
import blkr.apps.countries.ponitsInCountryFilter.pointsInPolygonFilter.filterPhase.FilterPhase;
import blkr.apps.countries.ponitsInCountryFilter.pointsInPolygonFilter.filterPhase.QuadtreeFilterPhase;

import com.vividsolutions.jts.geom.MultiPolygon;
import com.vividsolutions.jts.geom.Point;

public class PhasedPointsInPolygonFilter implements PointsInPolygonFilter {
	private final List<FilterPhase> filterPhases;
	
	public PhasedPointsInPolygonFilter(QuadtreeFilterPhase quadtreeFilterPhase,
			EnvelopeFilterPhase envelopeFilterPhase, BruteForceFilterPhase bruteForceFilterPhase) {
		this.filterPhases = Arrays.asList(
				quadtreeFilterPhase,
				envelopeFilterPhase,
				bruteForceFilterPhase);
	}
	
	public List<Point> filterPointsInsidePolygon(MultiPolygon polygon, List<Point> points) {
		List<Point> filteredPoints = points;
		
		Iterator<FilterPhase> phaseIterator = filterPhases.iterator();
		while(phaseIterator.hasNext())
			filteredPoints = phaseIterator.next().doFilter(polygon, filteredPoints);

        return filteredPoints;
	}
}

