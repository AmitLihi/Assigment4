package GUI;
import Algorithm.Algorithm;
import MySQL.MySQL;
import Objects.Object_Collections;
/**
 * This class is responsible for the threads 
 * @author Amit & Lihi
 */
public class MyThreads extends Thread {
	private MyFrame mf = new MyFrame();
	private Algorithm ma = new Algorithm();
	private MySQL mq;
	
	/**
	 * Constructor that gets myframe object and creats the threat 
	 * @param mf if myframe object
	 */
	public MyThreads(MyFrame mf) {
		this.mf = mf;
	}

	@Override
	public void run() {
		mf.getPlay1().start(); // default max time is 100 seconds (1000*100 ms).
		int i=0;
		while(mf.getPlay1().isRuning()) {
			i++;
			System.out.println("***** "+i+"******");		
			String info = mf.getPlay1().getStatistics();
			System.out.println(info);
			mf.setBoard_data(mf.getPlay1().getBoard());
			for(int a=0;a<mf.getBoard_data().size();a++) {
				System.out.println(mf.getBoard_data().get(a));
			}
			mf.setoc(new Object_Collections());
			mf.getoc().read(mf.getBoard_data());
			if(mf.isAutoOrmanu())mf.setAzimuth(ma.mainAlgorithm(mf.getoc()));
			mf.getPlay1().rotate(mf.getAzimuth()); 
			mf.getConvert().convertObject_Collections2Pix(mf.getoc());//converting to pixels
			mf.paintAgain();
			try {
				MyThreads.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		//mq = new MySQL (mf.getPlay1().getStatistics(), 1149748017);
		System.out.println("**** Done Game (user stop) ****");
	}	
}
