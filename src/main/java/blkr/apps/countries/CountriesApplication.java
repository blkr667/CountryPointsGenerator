package blkr.apps.countries;

import com.vividsolutions.jts.geom.Point;

import blkr.apps.countries.ponitsInCountryFilter.CountryPointsProviderException;
import blkr.apps.countries.ponitsInCountryFilter.PointsFromCountryProvider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class CountriesApplication {
	private static final Logger LOGGER = LoggerFactory.getLogger(CountriesApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(CountriesApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
			try {
				String countryName = args != null && args.length > 0 ? args[0] : "Poland";
				long startTime = System.nanoTime();
				LOGGER.info("Generating and filtering points for country " + countryName + " has started");
				PointsFromCountryProvider pointsFromCountryProvider = ctx.getBean(PointsFromCountryProvider.class);
				List<Point> points = pointsFromCountryProvider.providePointsFromCountry(countryName);
				points.forEach(point -> LOGGER.info(point.toString()));
				LOGGER.info("Found points number: " + points.size());
				LOGGER.info("Time required in milliseconds: " + TimeUnit.MILLISECONDS.convert(System.nanoTime() - startTime, TimeUnit.NANOSECONDS));
			} catch(CountryPointsProviderException e) {
				LOGGER.info("Finding points failed. " + e.getMessage());
			}
		};
	}
}