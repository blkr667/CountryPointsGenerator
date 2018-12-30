package blkr.apps.countries.ponitsInCountryFilter.pointsInPolygonFilter.filterPhase;

import java.util.List;

import com.vividsolutions.jts.geom.MultiPolygon;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.index.quadtree.Quadtree;

public class QuadtreeFilterPhase implements FilterPhase {
	@SuppressWarnings("unchecked")
	@Override
	public List<Point> doFilter(MultiPolygon polygon, List<Point> points) {
		Quadtree t = new Quadtree();
		points.forEach(point -> {
			 t.insert(point.getEnvelopeInternal(), point);
		});
		return t.query(polygon.getEnvelopeInternal());
	}

}
