package blkr.apps.countries.ponitsInCountryFilter.pointsGenerator;

import java.util.List;
import com.vividsolutions.jts.geom.Point;

public interface PointsGenerator {
	List<Point> generatePointList(int size);
}
