package Map;

public class Map implements java.io.Serializable{
	 public  final static int MAP_HEIGHT = 30;
	 public  final static int MAP_WIDTH = 30;
	 private double randomNum;
	 public  Square[][] map;
	 public Square grassSquare = new Square("ground", true);
	 private Square treeSquare_01 = new Square("tree_01",false);
	 private Square treeSquare_02 = new Square("tree_02",false);
	 public  Square grass = new Square("grass",true);
	 private Square stone = new Square("stone",false);
	 private Square fence = new Square("fence",false);
	 private Square flower = new Square("flower", true);
	 private Square HPpotion = new Square("HPpotion",true);
	 private Square finalBoss = new Square("finalBoss", true);
	 
	 private Square martHouse_01 = new Square("martHouse_01", false);
	 private Square martHouse_02 = new Square("martHouse_02", false);
	 private Square martHouse_03 = new Square("martHouse_03", false);
	 private Square martHouse_04 = new Square("martHouse_04", false);
	 private Square martHouse_05 = new Square("martHouse_05", false);
	 private Square martHouse_06 = new Square("martHouse_06", false);
	 private Square martHouse_07 = new Square("martHouse_07", false);
	 private Square martHouse_08 = new Square("martHouse_08", false);
	 private Square martHouse_09 = new Square("martHouse_09", false);
	 private Square martHouse_10 = new Square("martHouse_10", false);
	 private Square martHouse_11 = new Square("martHouse_11", false);
	 private Square martHouse_12 = new Square("martHouse_12", false);
	 private Square martHouse_13 = new Square("martHouse_13", false);
	 private Square martHouse_14 = new Square("martHouse_14", false);
	 private Square martHouse_15 = new Square("martHouse_15", true);
	 private Square martHouse_16 = new Square("martHouse_16", false);
	 private Square[][] martHouseArray = {
			 {martHouse_01, martHouse_02,martHouse_03,martHouse_04,},
			 {martHouse_05, martHouse_06,martHouse_07,martHouse_08,},
			 {martHouse_09, martHouse_10,martHouse_11,martHouse_12,},
			 {martHouse_13, martHouse_14,martHouse_15,martHouse_16,}
			 
	 };
	 
	 
	 public Map(double randomNum){
		 this.randomNum = randomNum;
		 map = new Square[MAP_WIDTH][MAP_HEIGHT];
		 setMap();
	 }
	 
	 public void setMap(){
		 for(int i = 0; i<MAP_WIDTH; i++){
			 for(int j = 0; j<MAP_HEIGHT; j++){
				 map[i][j] = grassSquare;
			 }
		 }
		 setTree();
		 setGrass();
		 setMartHouse();
		 setStone();
		 setFence();
		 setFlower();
		 setHPpotion();
	 }
	 public void setHPpotion(){
		 map[27][27] = HPpotion;
		 map[27][3] = HPpotion;
	 }
	 public void setFlower(){
		 map[24][18] = flower;
		 map[7][8] = flower;
	 }
	 public void setFence(){
		 for(int i = 10; i < MAP_HEIGHT -10; i++){
			 map[20][i] = fence;
		 }
	 }
	 public void setStone(){
		for(int i = 1; i < MAP_WIDTH-2; i++){
			map[i][9] = stone; 
		}
		for(int j = 2; j<MAP_WIDTH-2; j++){
			map[j][20] = stone;
		}
		
		
	 }
	 public void setGrass(){
		 map[3][5] = grass;
		 map[3][6] = grass;
		 for(int i = 5; i <28; i++){
			 for(int j = 5; j<9; j++){
				 map[i][j] = grass;
			 }
		 }
		 for(int i = 5; i <12 ;i++){
			 for(int j = 12; j<20; j++){
				 map[i][j] = grass;
			 }
		 }
		 
	 }
	 
	 public void setTree(){
		 
		 for(int i = 0; i< MAP_WIDTH; i++){
			 map[i][0] = treeSquare_01;
			 map[i][1] = treeSquare_02;
			 map[i][MAP_WIDTH-2] = treeSquare_01;
			 map[i][MAP_WIDTH-1] = treeSquare_02;
		 }
		 for(int j = 0; j<MAP_HEIGHT; j++){
			 if(j%2 == 0){
				 map[0][j] = treeSquare_01;
				 map[MAP_HEIGHT-1][j] = treeSquare_01;
			 }
			 else{
				 map[0][j] = treeSquare_02; 
				 map[MAP_HEIGHT-1][j] = treeSquare_02;
			 }
				 
			 
		 }
		
		 
	 }
	 public void setMartHouse(){

		 for(int i = 0; i<4; i++ ){
			 for(int j =0; j<4; j++){
				 map[j+12][i+12] = martHouseArray[i][j];
			 }
		 }
	 }
	 
	 public String toString(){
		 String result ="";
		 for(int i = 0; i < MAP_HEIGHT; i++){
			 for(int j =0; j <MAP_WIDTH; j++){
				 result += map[i][j].toString()+" ";
				 if(j == 9){
					result = result + "\n";
				 }
			 }
		 }	 
		 return result;
	 }
	 
}
