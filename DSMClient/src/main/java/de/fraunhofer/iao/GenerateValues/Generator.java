package de.fraunhofer.iao.GenerateValues;

import java.time.Instant;

import org.apache.log4j.Logger;


public abstract class Generator {

	protected static final Logger log = Logger.getLogger(Generator.class);

	protected int min;
	protected int max;

	public Generator(int min, int max) {
		this.min = min;
		this.max = max;
	}

	public Generator() {
		
	}

	public double getNewGeneratedValue(double min, double max, Instant timestamp) {
		double generatedValue = generateValue(min, max, timestamp);
		if (!isInRange(generatedValue)) {
			log.warn("Generated value is out of range! Value: " + generatedValue
					+ " Range: [" + min + ";" + max + "]");
			return 0;
		}
		return generatedValue;
	}

	/**
	 * Checks if the given value is in range (inclusive).
	 *
	 * @param value
	 * @return
	 */
	protected boolean isInRange(double value) {
		return value >= min && value <= max;
	}

	/**
	 * Generates a random value in the given range (inclusive).
	 *
	 * @return
	 */
	protected abstract double generateValue(double min, double max, Instant timestamp);

}
