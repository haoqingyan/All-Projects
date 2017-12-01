package Map;

import Trainer.Trainer;

public class blueHouseMap implements java.io.Serializable{
	 public  final static int blueHouseMAP_HEIGHT = 10;
	 public  final static int blueHouseMAP_WIDTH = 10;
	 public Square [][] map;
	 private Square blueHouseGround = new Square("blueHouseGround", true);
	 private Square outHouse = new Square("outHouse", true);
	 private Square finalBoss = new Square("finalBoss", true);
	 
	 public blueHouseMap(){
		 map = new Square[blueHouseMAP_WIDTH][blueHouseMAP_HEIGHT];
		 setMap();
	 }
	 public void setMap(){
		 setGround();
		 setOutHouse();
		 setFinalBoss();
		 
	 }
	 
	 public void setGround(){
		 for(int i =0; i<blueHouseMAP_HEIGHT; i++){
			 for(int j =0; j<blueHouseMAP_WIDTH; j++){
				 map[i][j] = blueHouseGround;
			 }
		 }
	 }
	 public void setOutHouse(){
		 map[5][9] = outHouse;
	 }
	 public void setFinalBoss(){
		 map[2][2] = finalBoss;
	 }
}
