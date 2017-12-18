package de.fraunhofer.iao.GenerateValues;


/**
 * Is for Calculate the right Values we want to know.
 * @author dsm
 *
 */
public class Vector {

	private double x;
	private double y;

	public Vector(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public Vector(double x1, double y1, double x2, double y2) {
		this.x = x2 - x1;
		this.y = y2 - y1;
	}

	public Vector(Vector vector1, Vector vector2) {
		this(vector1.getX(), vector1.getY(), vector2.getX(), vector2.getY());
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double length() {
		return Math.sqrt((x * x) + (y * y));
	}

	public Vector normalize() {
		double length = length();
		return new Vector(x / length, y / length);
	}

	public Vector add(Vector vector) {
		return new Vector(this.x + vector.getX(), this.y + vector.getY());
	}

	public Vector scale(double val) {
		return new Vector(x * val, y * val);
	}

	public Vector scaleToX(double val) {
		double factor = val / x;
		return new Vector(x * factor, y * factor);
	}

	public Vector getVectorBetweenTwoVectors(Vector vector2, double x) {
		Vector ab = new Vector(this, vector2);
		return ab.scaleToX(x - this.x).add(this);
	}

	@Override
	public String toString() {
		return "(" + x + "/" + y + ")";
	}
}