package Objects;
import java.util.ArrayList;
import Geom.Point3D;

/**
 * This class represents the Pacman creature, which aim is chasing fruits and eating them
 * @author Amit & Lihi
 */
public class Pacman {
	
	private Point3D p;
	private double radius;
	private double speed;
	private int ID;
	
	/**
	 * Default constructor
	 */
	public  Pacman() {
	}
	
	/**
	 * Constructor that gets 3Dpoint and creats a new Pacman with this point
	 * @param p is a 3Dpoint
	 */
	public Pacman(Point3D p) {
		this.p = new Point3D(p);
		this.radius = 1;
		this.speed = 1;
		this.ID = 0;
	}
	/**
	 * Constructor that gets 3Dpoint, id and creats a new Pacman with this point
	 * @param p is a 3Dpoint
	 * @param id is the id of the pacman
	 */
	public Pacman(Point3D p, int id) {
		this.p = new Point3D(p);
		this.radius = 1;
		this.speed = 1;
		this.ID = id;
		}
	
	/**
	 * Construstor that gets coords,speed radius and id of the Pacman and creates a new Pacman from it
	 * @param lon is longitude is x
	 * @param lat is lattitude is y
	 * @param alt is altitude is z
	 * @param speed is the speed of the pacman
	 * @param radius is the radius of the pacman
	 */
	public Pacman(double lon, double lat, double alt, double speed, double radius, int id)
	{
		this.p = new Point3D (lon,lat,alt);
		this.radius = radius;
		this.speed = speed;
		this.ID = id;

	}

	///*** Getters & Setters ***///
	
	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
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

	public Point3D getP() {
		return p;
	}

	public void setP(Point3D p) {
		this.p = p;
	}

	@Override
	public String toString() {
		return "pacman [radius=" + radius + ", speed=" + speed + ", p=" + p + "]";
	}
}
