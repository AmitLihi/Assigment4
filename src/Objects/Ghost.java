package Objects;

import Geom.Point3D;
/**
 * This class represents a ghost object,which aim is eating the player and interrupt his game
 * @author Amit & Lihi
 *
 */
public class Ghost {

	private Point3D p;
	private double radius;
	private double speed;
	private int ID;

	public Ghost() {
	}
	
	/**
	 * This constructor creates a ghost from a 3Dpoint
	 * @param p - is the point of the ghost
	 */
	public Ghost(Point3D p) {
		this.p = new Point3D(p);
		this.radius = 1;
		this.speed = 1;
		this.ID = 0;
	}

	/**
	 * This constructor creates a ghost from a 3Dpoint and an id
	 * @param p - is the point og the ghost
	 * @param id - is the id of the ghost
	 */
	public Ghost(Point3D p, int id) {
		this.p = new Point3D(p);
		this.radius = 1;
		this.speed = 1;
		this.ID = id;
	}

	/**
	 * This constructor creates a ghost from x,y,z, speed , radius and id
	 * @param lon is longitude is x
	 * @param lat is lattitude is y
	 * @param alt is altitude is z
	 * @param speed is the speed of the ghost
	 * @param radius is the radius of the ghost
	 * @param id is the id of the ghost
	 */
	public Ghost(double lon, double lat, double alt, double speed, double radius, int id)
	{
		this.p = new Point3D (lon,lat,alt);
		this.radius = radius;
		this.speed = speed;
		this.ID = id;
	}
	
	///***Getters & Setters***///

	public Point3D getP() {
		return p;
	}

	public void setP(Point3D p) {
		this.p = p;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}
}