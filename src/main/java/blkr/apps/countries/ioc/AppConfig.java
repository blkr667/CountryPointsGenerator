package blkr.apps.countries.ioc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import blkr.apps.countries.ponitsInCountryFilter.PointsFromCountryProvider;
import blkr.apps.countries.ponitsInCountryFilter.RandomPointsFromCountryProvider;
import blkr.apps.countries.ponitsInCountryFilter.pointsGenerator.PointsGenerator;
import blkr.apps.countries.ponitsInCountryFilter.pointsGenerator.RandomPointsGenerator;
import blkr.apps.countries.ponitsInCountryFilter.pointsInPolygonFilter.PhasedPointsInPolygonFilter;
import blkr.apps.countries.ponitsInCountryFilter.pointsInPolygonFilter.PointsInPolygonFilter;
import blkr.apps.countries.ponitsInCountryFilter.pointsInPolygonFilter.filterPhase.BruteForceFilterPhase;
import blkr.apps.countries.ponitsInCountryFilter.pointsInPolygonFilter.filterPhase.EnvelopeFilterPhase;
import blkr.apps.countries.ponitsInCountryFilter.pointsInPolygonFilter.filterPhase.QuadtreeFilterPhase;
import blkr.apps.countries.ponitsInCountryFilter.polygonReader.PolygonFileReader;
import blkr.apps.countries.ponitsInCountryFilter.polygonReader.PolygonShapeFileReader;
import blkr.apps.countries.ponitsInCountryFilter.polygonReader.utils.PolygonParser;
import blkr.apps.countries.ponitsInCountryFilter.polygonReader.utils.ShapeFileReader;

@Configuration
public class AppConfig {
	@Bean
    public PointsFromCountryProvider pointsFromCountryProvider() {
		PolygonFileReader polygonReader = polygonFileReader();
		PointsGenerator pointsGenerator = pointsGenerator();
		PointsInPolygonFilter pointsInPolygonFilter = pointsInPolygonFilter();
		return new RandomPointsFromCountryProvider(polygonReader, pointsGenerator, pointsInPolygonFilter);
    }
	
	@Bean
    public PointsGenerator pointsGenerator() {
        return new RandomPointsGenerator();
    }
	
	@Bean
    public PolygonFileReader polygonFileReader() {
        return new PolygonShapeFileReader(shapeFileReader(), polygonParser());
    }
	
	@Bean
    public PointsInPolygonFilter pointsInPolygonFilter() {
        return new PhasedPointsInPolygonFilter(quadtreeFilterPhase(), envelopeFilterPhase(), bruteForceFilterPhase());
    }
	
	@Bean
    public QuadtreeFilterPhase quadtreeFilterPhase() {
        return new QuadtreeFilterPhase();
    }
	
	@Bean
    public EnvelopeFilterPhase envelopeFilterPhase() {
        return new EnvelopeFilterPhase();
    }
	
	@Bean
    public BruteForceFilterPhase bruteForceFilterPhase() {
        return new BruteForceFilterPhase();
    }
	
	@Bean
    public ShapeFileReader shapeFileReader() {
        return new ShapeFileReader();
    }
	
	@Bean
    public PolygonParser polygonParser() {
        return new PolygonParser();
    }
}