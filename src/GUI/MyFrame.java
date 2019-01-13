package GUI;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

import Algorithm.Convert;
import Coords.LatLonAlt;
import Geom.Point3D;
import Objects.Ghost;
import Objects.Me;
import Objects.Object_Collections;
import Objects.Pacman;
import Robot.Play;

/**
 * This class represents the game's GUI 
 * @author Amit & Lihi
 */
public class MyFrame extends JFrame implements MouseListener{

	private BufferedImage myImage;
	private Object_Collections oc = new Object_Collections();
	private ArrayList<String> board_data = new ArrayList();
	private Convert convert = new Convert();
	private Play play1;
	private int setObject = -1;	
	private double azimuth = 0;


	/**
	 * Constructor which responsible of open the MouseListener and send to initGUI function
	 */
	public MyFrame() 
	{
		initGUI();		
		this.addMouseListener(this);
	}

	/**
	 * This function is responsible for the menueBar,ActionListener and uploading the file
	 */
	private void initGUI() 
	{
		//insert buttons to the manueBar
		MenuBar menuBar = new MenuBar();
		Menu add = new Menu("Add"); 
		MenuItem me = new MenuItem("Me");
		Menu file = new Menu("File"); 
		MenuItem csv = new MenuItem("Import from CSV");
		MenuItem save = new MenuItem("Save to CSV");
		Menu play = new Menu("Play"); 
		MenuItem run = new MenuItem("Run the oc");

		menuBar.add(file);
		file.add(csv);
		file.add(save);
		menuBar.add(add);
		add.add(me);
		menuBar.add(play);
		play.add(run);

		this.setMenuBar(menuBar);

		me.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				setSetObject(0);
			}
		});

		//this is the import from csv button
		csv.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {	
				JFileChooser chooser = new JFileChooser();
				File F = new File("C:\\Users\\Amit\\eclipse-workspace\\As4\\data");
				File namedir;File namepath;
				chooser.setCurrentDirectory(F);
				chooser.showOpenDialog(null);
				namedir = chooser.getCurrentDirectory();
				namepath = chooser.getSelectedFile();
				play1 = new Play(namepath.getPath());
				oc.readFile(namepath.getPath());
				convert.convertObject_Collections2Pix(oc);//converting to pixels
				play1.setIDs(203,204,3333);
				board_data = play1.getBoard();
				for(int i=0;i<board_data.size();i++) {
					System.out.println(board_data.get(i));
				}
				System.out.println();
				System.out.println("Init Player Location should be set using the bounding box info");
				repaint();
			}
		});

		save.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new File("C:\\Users\\Amit\\eclipse-workspace\\As3"));
				int retrival = chooser.showSaveDialog(null);
				if (retrival == JFileChooser.APPROVE_OPTION) {
					oc.writeFile(chooser.getSelectedFile().getPath()+".csv");
				}
				repaint();
			}
		});

		play.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(play1 != null) {
					flag = false; 
					startThread();
				}
				else System.out.println("Load a game before running");
			}
		});

		addWindowListener(new WindowAdapter() 
		{public void windowClosing(Window e) 
		{dispose(); System.exit(0);}  
		});

		try {
			//try to read the image (convert)
			myImage = ImageIO.read(new File("example.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void startThread() {
		MyThreads m = new MyThreads(this); 
		m.start();
	}

	public void paintAgain(){
		repaint();	
	}

	int x = -1; int y = -1;
	int hRect = 0; int wRect = 0; // the Subtraction between start point and end point
	boolean flag = true; // this flag will be change to false after starting the game

	/**
	 * This function is a graphic function of java which load the actual oc and vizualize it 
	 */
	public void paint(Graphics g)
	{
		int w = this.getWidth();
		int h = this.getHeight();
		int r = 10;
		g.drawImage(myImage, 0, 0, w, h, this);
		if(oc.getMe() != (null)) {
			x = (int)oc.getMe().getP().x() - (r / 2);
			y = (int)oc.getMe().getP().y() - (r / 2);
			g.setColor(Color.pink);
			g.fillOval(x,y,w/60, h/30);
		}

		for (int i = 0; i < oc.sizePacman(); i++) {
			x = (int)oc.getPacman(i).getP().x() - (r / 2);
			y = (int)oc.getPacman(i).getP().y() - (r / 2);
			g.setColor(Color.yellow);
			g.fillOval(x,y,w/60,h/30);
		}

		for (int i = 0; i < oc.sizeFruit(); i++) {
			x = (int)oc.getFruit(i).getP().x() - (r / 2);
			y = (int)oc.getFruit(i).getP().y() - (r / 2);
			g.setColor(Color.green);
			g.fillOval(x,y,w/100,h/60);
		}

		for (int i = 0; i < oc.sizeGhost(); i++) {
			x = (int)oc.getGhost(i).getP().x() - (r / 2);
			y = (int)oc.getGhost(i).getP().y() - (r / 2);
			g.setColor(Color.red);
			g.fillOval(x,y,w/60,h/30);
		}

		for (int i = 0; i < oc.sizeBlack_Rectangle(); i++) {
			hRect =(int)Math.abs(( oc.getBlack_Rectangle(i).getpStart().y()-oc.getBlack_Rectangle(i).getpEnd().y()));
			wRect =(int)Math.abs(( oc.getBlack_Rectangle(i).getpEnd().x()-oc.getBlack_Rectangle(i).getpStart().x()));
			g.setColor(Color.black);
			g.fillRect((int)oc.getBlack_Rectangles().get(i).getpEnd().x(),(int)oc.getBlack_Rectangles().get(i).getpStart().y(), wRect, hRect);
		}
		g.setFont(new Font("Arial", Font.BOLD, 20)); 
		g.setColor(Color.black);
		g.drawString(play1.getStatistics(), 50, 640);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		System.out.println("mouse Clicked");
		System.out.println("("+ arg0.getX() + "," + arg0.getY() +")");
		x = arg0.getX(); y = arg0.getY();
		if(setObject == 0) {
			if(flag == true) {
				oc.setMe(new Me(new Point3D (x,y,0),0));
				Point3D p = new Point3D(convert.convert2Coords(myImage.getHeight(), myImage.getWidth(),oc.getMe().getP().x(),oc.getMe().getP().y()));
				play1.setInitLocation(p.y(),p.x());
			}
			else {
				azimuth = convert.azimuth(convert.convert2Coords(myImage.getHeight(), myImage.getWidth(),y,x), convert.convert2Coords(myImage.getHeight(), myImage.getWidth(),oc.getMe().getP().x(),oc.getMe().getP().y()));
			}
		}
		repaint();
	}


	///***Getters & setters***///

	public BufferedImage getMyImage() {return myImage;}
	public void setMyImage(BufferedImage myImage) {this.myImage = myImage;}
	public Object_Collections getoc() {return oc;}
	public void setoc(Object_Collections oc) {this.oc = oc;}
	public int getSetObject() {return setObject;}
	public void setSetObject(int setObject) {this.setObject = setObject;}
	public ArrayList<String> getBoard_data() {return board_data;}
	public void setBoard_data(ArrayList<String> board_data) {this.board_data = board_data;}
	public Convert getConvert() {return convert;}
	public void setConvert(Convert convert) {this.convert = convert;}
	public Play getPlay1() {return play1;}
	public void setPlay1(Play play1) {this.play1 = play1;}
	public double getAzimuth() {return azimuth;}
	public void setAzimuth(double azimuth) {this.azimuth = azimuth;}
	@Override
	public void mouseEntered(MouseEvent arg0) {}
	@Override
	public void mouseExited(MouseEvent arg0) {}
	@Override
	public void mousePressed(MouseEvent arg0) {}
	@Override
	public void mouseReleased(MouseEvent arg0) {}

	public static void main(String[] args) {

		MyFrame window = new MyFrame();
		window.setVisible(true);
		window.setSize(1400,700);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
}