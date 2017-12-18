package de.fraunhofer.iao.GenerateValues;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.time.temporal.WeekFields;
import java.util.Random;

/**
 * Generates realistic flexibility Values for different Flexibilityfacilities.
 * @author dsm
 *
 */
public class RealisticValueGenerator extends Generator {

	private double oneWeekInSeconds = 604800;


	private double smallAmplitude = 10;
	

	public RealisticValueGenerator(int min, int max, Instant start) {
		super(min, max);
	}

	public RealisticValueGenerator() {
	}

	/**
	 * Generates possible value by adding the random noise and the predicted
	 * value.
	 */
	@Override
	protected double generateValue(double min, double max, Instant timestamp) {
		double total = getExpectedValue(min, max, timestamp);
		if (total <= min) {
			total = min;
		}

		if (total >= max) {
			total = max;
		}
		return total;
	}

	/**
	 * Gets predicted value for the Instant timestamp.
	 */
	public double getExpectedValue(double min, double max, Instant timestamp) {
		double wantedTime = convertTimestampToFunctionRangeValue(timestamp);

		int dayNumber = (int) convertTimestampToFunctionRangeValue(timestamp);

		double[] valueDays = new double[9];

		for (int i = 1; i < 9; i++) {
			valueDays[i] = noise(max * 0.2, getFunctionValue(min, max, i));
		}

		double test = getValueInBetween(valueDays, dayNumber, dayNumber + 1,wantedTime);
		return test;
	}

	public double getValueInBetween(double[] valueDays, int startDay,int endDay, double wantedValue) {
		
		Vector vector1 = new Vector(startDay, valueDays[startDay]);
		Vector vector2 = new Vector(endDay, valueDays[endDay]);
		
		Vector vectorResult = vector1.getVectorBetweenTwoVectors(vector2, wantedValue);
		
		double returnValue = noise(smallAmplitude, vectorResult.getY());
		return returnValue;
	}

	public double noise(double amplitude, double value) {
		double noise = (new Random().nextDouble() * 2.0 - 1.0) * amplitude;
		return noise + value;

	}


	public double getFunctionValue(double min, double max, double time) {
		min *= 0.8;
		max *= 0.8;
		min -= smallAmplitude;
		max += smallAmplitude;
		double fx = (0.00121796010355268000 * Math.pow(time, 6))
				- (0.02920611181853870000 * Math.pow(time, 5))
				+ (0.26287287911800000000 * Math.pow(time, 4))
				- (1.09259944279452000000 * Math.pow(time, 3))
				+ (1.99494847738064000000 * Math.pow(time, 2))
				- (0.90205338953954600000 * Math.pow(time, 1))
				+ 0.17234949780208100000;
		fx -= 0.05;
		fx = ((fx * (max - min)) + min);

		return fx;
	}

	/**
	 * Converts Instant timestamp the corresponding x value of the range [1;8)
	 * (Mon-Mon)
	 */
	public double convertTimestampToFunctionRangeValue(Instant timestamp) {
		LocalDateTime localDate = timestamp.atZone(ZoneId.systemDefault())
				.toLocalDateTime();
		LocalDateTime firstDayOfWeek = localDate
				.with(WeekFields.ISO.dayOfWeek(), 1)
				.truncatedTo(ChronoUnit.DAYS);
		double secondsDiff = ChronoUnit.SECONDS.between(firstDayOfWeek,
				localDate);
		return ((secondsDiff / oneWeekInSeconds) * 7) + 1;
	}
}
