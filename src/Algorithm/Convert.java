package Algorithm;

import java.awt.Component;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;

import Geom.Point3D;
import Objects.Object_Collections;
/**
 * This class convert global coordinates to pixels and pixels back to coordinates 
 * @author Amit & Lihi
 */
public class Convert {
	private BufferedImage myImage;
	//distance downright and upleft
	private Point3D downright =  new Point3D(35.21240500,32.101858,0);
	private Point3D upLeft = new Point3D(35.202574,32.106046,0);
	final double mapLongitudeStart = 35.202574, mapLatitudeStart = 32.106046;
	final double mapLongitude = 35.21240500-mapLongitudeStart, mapLatitude = 32.101858-mapLatitudeStart;

	/**
	 * Default constructor that will load the image
	 */
	public Convert() {
		try {
			myImage = ImageIO.read(new File("example.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
/**
 * This function convert from x,y cordinate to x,y pixels
 * @param height is the height of the map
 * @param wight is the wight of the map
 * @param longitude is x
 * @param latitude is y
 * @return the pixel point 
 */
	public Point3D convert2Pix(int height, int wight, double longitude, double latitude){
		double x = longitude - mapLongitudeStart;
		double y = mapLatitudeStart - latitude;
		int x1 = (int) (wight*(x/mapLongitude));
		int y1 = Math.abs((int) (height*(y/mapLatitude)));
		return new Point3D(x1, y1);
	}

	/**
	 * This function convert from x,y pixels to x,y cordinate
	 * @param height is the height of the map
	 * @param wight is the wight of the map
	 * @param x
	 * @param y
	 * @return the cordinate point
	 */
	public Point3D convert2Coords(int height, int wight, double x, double y){
		double y1 = ((y*mapLatitude)/height);
		double x1 = ((x*mapLongitude)/wight);
		double latitude = mapLatitudeStart+y1;
		double longitude = x1+mapLongitudeStart;
		return new Point3D(longitude, latitude);
	}

	/**
	 * This function calculates the distance between two points
	 * @param p1 is the first point
	 * @param p2 is the second point
	 * @return the distance between p1 and p2
	 */
	public double distance(Point3D p1, Point3D p2) {
		return Math.sqrt(Math.pow(p1.x()-p2.x(), 2)+Math.pow(p1.y()-p2.y(), 2));
	}
	/**
	 * convert Object_Collections from coords to pixs
	 * @param oc
	 * @return
	 */
	public Object_Collections convertObject_Collections2Pix (Object_Collections oc) {

		if(oc.getMe() != null) {
			oc.getMe().setP(convert2Pix(myImage.getHeight() ,myImage.getWidth(), oc.getMe().getP().x(), oc.getMe().getP().y()));
		}
		for (int i = 0; i <oc.sizePacman(); i++) {
			oc.getPacman(i).setP(convert2Pix(myImage.getHeight(),myImage.getWidth(), oc.getPacman(i).getP().x(), oc.getPacman(i).getP().y()));
		}
		for (int i = 0; i <oc.sizeFruit(); i++) {
			oc.getFruit(i).setP(convert2Pix(myImage.getHeight(),myImage.getWidth(), oc.getFruit(i).getP().x(), oc.getFruit(i).getP().y()));
		}
		for (int i = 0; i <oc.sizeGhost(); i++) {
			oc.getGhost(i).setP(convert2Pix(myImage.getHeight(),myImage.getWidth(), oc.getGhost(i).getP().x(), oc.getGhost(i).getP().y()));
		}
		for (int i = 0; i <oc.sizeBlack_Rectangle(); i++) {
			oc.getBlack_Rectangle(i).setpStart(convert2Pix(myImage.getHeight(),myImage.getWidth(), oc.getBlack_Rectangle(i).getpStart().x(), oc.getBlack_Rectangle(i).getpStart().y()));
			oc.getBlack_Rectangle(i).setpEnd(convert2Pix(myImage.getHeight(),myImage.getWidth(), oc.getBlack_Rectangle(i).getpEnd().x(), oc.getBlack_Rectangle(i).getpEnd().y()));
		}
		return oc;
	}
/**
 * Thias function calculates the azimuth between two points
 * @param p1 is the first point
 * @param p2 is the second point
 * @return the azimuth between p1 and p2
 */
	public double azimuth(Point3D p1, Point3D p2) {
		double left = Math.sin(Math.toRadians(p2.y()) - Math.toRadians(p1.y()))*Math.cos(Math.toRadians(p2.x()));
		double right = Math.cos(Math.toRadians(p1.x()))*Math.sin(Math.toRadians(p2.x()))-Math.sin(Math.toRadians(p1.x()))*Math.cos(Math.toRadians(p2.x()))*Math.cos(Math.sin(Math.toRadians(p2.y()) - Math.toRadians(p1.y()))*Math.cos(Math.toRadians(p2.x())));
		double	azimuth = Math.atan2(left, right);
		azimuth = Math.toDegrees(azimuth);
		if(azimuth<0) azimuth+=360;
		return azimuth;
	}
}