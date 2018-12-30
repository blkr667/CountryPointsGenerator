package blkr.apps.countries.ponitsInCountryFilter.polygonReader.utils;

import java.io.File;
import java.io.IOException;

import org.geotools.data.FileDataStore;
import org.geotools.data.FileDataStoreFinder;
import org.geotools.data.simple.SimpleFeatureSource;
import org.springframework.core.io.ClassPathResource;

public class ShapeFileReader {
	private static final String FILE_NAME = "ne_110m_admin_0_countries/ne_110m_admin_0_countries.shp";
	
	public SimpleFeatureSource readShapeFileResource() throws IOException {
		File file = new ClassPathResource(FILE_NAME).getFile();
		FileDataStore store = FileDataStoreFinder.getDataStore(file);
		SimpleFeatureSource featureSource = store.getFeatureSource();
	    return featureSource;
	}
}
