package com.myproj.discandtower;

public class Puzzle {
	public int puzzleSize;
	public String name;
	// Disc from top to bottom
	public int[] startOrder = new int[Disc.MaxDiscSize];
	public int[] targetOrder = new int[Disc.MaxDiscSize];
	// The resource ID of preview picture
	public int imageId;
	// Target tower (-1 means any tower)
	public int targetTower;
	
	private static Puzzle[] puzzles;
	
	public static Puzzle[] loadPuzzles() {
		if(puzzles == null) {
			puzzles = new Puzzle[52];
			for(int i = 0; i < puzzles.length; i++) {
				puzzles[i] = new Puzzle();
				puzzles[i].targetTower = -1;
			}
			puzzles[0].puzzleSize = 3;
			puzzles[0].name = "1-1";
			puzzles[0].startOrder = new int[]{1,2,0};
			puzzles[0].targetOrder = new int[]{0,1,2};
			puzzles[0].imageId = R.drawable.puzzle120;
			puzzles[0].targetTower = -1;
			
			puzzles[1].puzzleSize = 3;
			puzzles[1].name = "1-2";
			puzzles[1].startOrder = new int[]{0,2,1};
			puzzles[1].targetOrder = new int[]{0,1,2};
			puzzles[1].imageId = R.drawable.puzzle021;
			puzzles[1].targetTower = -1;
			
			puzzles[2].puzzleSize = 3;
			puzzles[2].name = "1-3";
			puzzles[2].startOrder = new int[]{2,0,1};
			puzzles[2].targetOrder = new int[]{0,1,2};
			puzzles[2].imageId = R.drawable.puzzle201;
			puzzles[2].targetTower = -1;
			
			puzzles[3].puzzleSize = 3;
			puzzles[3].name = "1-4";
			puzzles[3].startOrder = new int[]{1,0,2};
			puzzles[3].targetOrder = new int[]{0,1,2};
			puzzles[3].imageId = R.drawable.puzzle102;
			puzzles[3].targetTower = -1;
			
			puzzles[4].puzzleSize = 4;
			puzzles[4].name = "2-1";
			puzzles[4].startOrder = new int[]{0,3,1,2};
			puzzles[4].targetOrder = new int[]{0,1,2,3};
			puzzles[4].imageId = R.drawable.puzzle0312;
			puzzles[4].targetTower = -1;
			
			puzzles[5].puzzleSize = 4;
			puzzles[5].name = "2-2";
			puzzles[5].startOrder = new int[]{2,1,3,0};
			puzzles[5].targetOrder = new int[]{0,1,2,3};
			puzzles[5].imageId = R.drawable.puzzle2130;
			puzzles[5].targetTower = -1;
			
			puzzles[6].puzzleSize = 4;
			puzzles[6].name = "2-3";
			puzzles[6].startOrder = new int[]{2,3,1,0};
			puzzles[6].targetOrder = new int[]{0,1,2,3};
			puzzles[6].imageId = R.drawable.puzzle2310;
			puzzles[6].targetTower = -1;
			
			puzzles[7].puzzleSize = 4;
			puzzles[7].name = "2-4";
			puzzles[7].startOrder = new int[]{2,0,1,3};
			puzzles[7].targetOrder = new int[]{0,1,2,3};
			puzzles[7].imageId = R.drawable.puzzle2013;
			puzzles[7].targetTower = -1;
			
			puzzles[8].puzzleSize = 4;
			puzzles[8].name = "2-5";
			puzzles[8].startOrder = new int[]{0,2,1,3};
			puzzles[8].targetOrder = new int[]{0,1,2,3};
			puzzles[8].imageId = R.drawable.puzzle0213;
			puzzles[8].targetTower = -1;
			
			puzzles[9].puzzleSize = 4;
			puzzles[9].name = "2-6";
			puzzles[9].startOrder = new int[]{1,2,3,0};
			puzzles[9].targetOrder = new int[]{0,1,2,3};
			puzzles[9].imageId = R.drawable.puzzle1230;
			puzzles[9].targetTower = -1;
			
			puzzles[10].puzzleSize = 4;
			puzzles[10].name = "2-7";
			puzzles[10].startOrder = new int[]{3,0,2,1};
			puzzles[10].targetOrder = new int[]{0,1,2,3};
			puzzles[10].imageId = R.drawable.puzzle3021;
			puzzles[10].targetTower = -1;
			
			puzzles[11].puzzleSize = 4;
			puzzles[11].name = "2-8";
			puzzles[11].startOrder = new int[]{2,0,3,1};
			puzzles[11].targetOrder = new int[]{0,1,2,3};
			puzzles[11].imageId = R.drawable.puzzle2031;
			puzzles[11].targetTower = -1;
			
			puzzles[12].puzzleSize = 4;
			puzzles[12].name = "2-9";
			puzzles[12].startOrder = new int[]{3,1,0,2};
			puzzles[12].targetOrder = new int[]{0,1,2,3};
			puzzles[12].imageId = R.drawable.puzzle3102;
			puzzles[12].targetTower = -1;
			
			puzzles[13].puzzleSize = 4;
			puzzles[13].name = "2-10";
			puzzles[13].startOrder = new int[]{2,1,0,3};
			puzzles[13].targetOrder = new int[]{0,1,2,3};
			puzzles[13].imageId = R.drawable.puzzle2103;
			puzzles[13].targetTower = -1;
			
			puzzles[14].puzzleSize = 4;
			puzzles[14].name = "2-11";
			puzzles[14].startOrder = new int[]{0,2,3,1};
			puzzles[14].targetOrder = new int[]{0,1,2,3};
			puzzles[14].imageId = R.drawable.puzzle0231;
			puzzles[14].targetTower = -1;
			
			puzzles[15].puzzleSize = 4;
			puzzles[15].name = "2-12";
			puzzles[15].startOrder = new int[]{3,1,2,0};
			puzzles[15].targetOrder = new int[]{0,1,2,3};
			puzzles[15].imageId = R.drawable.puzzle3120;
			puzzles[15].targetTower = -1;
			
			puzzles[16].puzzleSize = 4;
			puzzles[16].name = "2-13";
			puzzles[16].startOrder = new int[]{0,1,3,2};
			puzzles[16].targetOrder = new int[]{0,1,2,3};
			puzzles[16].imageId = R.drawable.puzzle0132;
			puzzles[16].targetTower = -1;
			
			puzzles[17].puzzleSize = 5;
			puzzles[17].name = "3-1";
			puzzles[17].startOrder = new int[]{0,4,3,1,2};
			puzzles[17].targetOrder = new int[]{0,1,2,3,4};
			puzzles[17].imageId = R.drawable.puzzle04312;
			puzzles[17].targetTower = -1;
			
			puzzles[18].puzzleSize = 5;
			puzzles[18].name = "3-2";
			puzzles[18].startOrder = new int[]{2,1,0,3,4};
			puzzles[18].targetOrder = new int[]{0,1,2,3,4};
			puzzles[18].imageId = R.drawable.puzzle21034;
			puzzles[18].targetTower = -1;
			
			puzzles[19].puzzleSize = 5;
			puzzles[19].name = "3-3";
			puzzles[19].startOrder = new int[]{0,1,3,2,4};
			puzzles[19].targetOrder = new int[]{0,1,2,3,4};
			puzzles[19].imageId = R.drawable.puzzle01324;
			puzzles[19].targetTower = -1;
			
			puzzles[20].puzzleSize = 5;
			puzzles[20].name = "3-4";
			puzzles[20].startOrder = new int[]{3,0,2,1,4};
			puzzles[20].targetOrder = new int[]{0,1,2,3,4};
			puzzles[20].imageId = R.drawable.puzzle30214;
			puzzles[20].targetTower = -1;
			
			puzzles[21].puzzleSize = 5;
			puzzles[21].name = "3-5";
			puzzles[21].startOrder = new int[]{1,3,2,0,4};
			puzzles[21].targetOrder = new int[]{0,1,2,3,4};
			puzzles[21].imageId = R.drawable.puzzle13204;
			puzzles[21].targetTower = -1;
			
			puzzles[22].puzzleSize = 5;
			puzzles[22].name = "3-6";
			puzzles[22].startOrder = new int[]{3,1,0,4,2};
			puzzles[22].targetOrder = new int[]{0,1,2,3,4};
			puzzles[22].imageId = R.drawable.puzzle31042;
			puzzles[22].targetTower = -1;
			
			puzzles[23].puzzleSize = 5;
			puzzles[23].name = "3-7";
			puzzles[23].startOrder = new int[]{4,0,3,1,2};
			puzzles[23].targetOrder = new int[]{0,1,2,3,4};
			puzzles[23].imageId = R.drawable.puzzle40312;
			puzzles[23].targetTower = -1;
			
			puzzles[24].puzzleSize = 5;
			puzzles[24].name = "3-8";
			puzzles[24].startOrder = new int[]{1,3,4,0,2};
			puzzles[24].targetOrder = new int[]{0,1,2,3,4};
			puzzles[24].imageId = R.drawable.puzzle13402;
			puzzles[24].targetTower = -1;
			
			puzzles[25].puzzleSize = 5;
			puzzles[25].name = "3-9";
			puzzles[25].startOrder = new int[]{0,2,3,4,1};
			puzzles[25].targetOrder = new int[]{0,1,2,3,4};
			puzzles[25].imageId = R.drawable.puzzle02341;
			puzzles[25].targetTower = -1;
			
			puzzles[26].puzzleSize = 5;
			puzzles[26].name = "3-10";
			puzzles[26].startOrder = new int[]{3,4,1,0,2};
			puzzles[26].targetOrder = new int[]{0,1,2,3,4};
			puzzles[26].imageId = R.drawable.puzzle34102;
			puzzles[26].targetTower = -1;
			
			puzzles[27].puzzleSize = 5;
			puzzles[27].name = "3-11";
			puzzles[27].startOrder = new int[]{4,1,3,0,2};
			puzzles[27].targetOrder = new int[]{0,1,2,3,4};
			puzzles[27].imageId = R.drawable.puzzle41302;
			puzzles[27].targetTower = -1;
			
			puzzles[28].puzzleSize = 5;
			puzzles[28].name = "3-12";
			puzzles[28].startOrder = new int[]{3,1,4,0,2};
			puzzles[28].targetOrder = new int[]{0,1,2,3,4};
			puzzles[28].imageId = R.drawable.puzzle31402;
			puzzles[28].targetTower = -1;
			
			puzzles[29].puzzleSize = 5;
			puzzles[29].name = "3-13";
			puzzles[29].startOrder = new int[]{0,3,4,1,2};
			puzzles[29].targetOrder = new int[]{0,1,2,3,4};
			puzzles[29].imageId = R.drawable.puzzle03412;
			puzzles[29].targetTower = -1;
			
			puzzles[30].puzzleSize = 5;
			puzzles[30].name = "3-14";
			puzzles[30].startOrder = new int[]{1,3,0,4,2};
			puzzles[30].targetOrder = new int[]{0,1,2,3,4};
			puzzles[30].imageId = R.drawable.puzzle13042;
			puzzles[30].targetTower = -1;
			
			puzzles[31].puzzleSize = 5;
			puzzles[31].name = "3-15";
			puzzles[31].startOrder = new int[]{0,1,3,4,2};
			puzzles[31].targetOrder = new int[]{0,1,2,3,4};
			puzzles[31].imageId = R.drawable.puzzle01342;
			puzzles[31].targetTower = -1;
			
			puzzles[32].puzzleSize = 5;
			puzzles[32].name = "3-16";
			puzzles[32].startOrder = new int[]{2,0,4,1,3};
			puzzles[32].targetOrder = new int[]{0,1,2,3,4};
			puzzles[32].imageId = R.drawable.puzzle20413;
			puzzles[32].targetTower = -1;
			
			puzzles[33].puzzleSize = 5;
			puzzles[33].name = "3-17";
			puzzles[33].startOrder = new int[]{0,2,4,1,3};
			puzzles[33].targetOrder = new int[]{0,1,2,3,4};
			puzzles[33].imageId = R.drawable.puzzle02413;
			puzzles[33].targetTower = -1;
			
			puzzles[34].puzzleSize = 5;
			puzzles[34].name = "3-18";
			puzzles[34].startOrder = new int[]{2,1,3,4,0};
			puzzles[34].targetOrder = new int[]{0,1,2,3,4};
			puzzles[34].imageId = R.drawable.puzzle21340;
			puzzles[34].targetTower = -1;
			
			puzzles[35].puzzleSize = 5;
			puzzles[35].name = "3-19";
			puzzles[35].startOrder = new int[]{2,3,4,1,0};
			puzzles[35].targetOrder = new int[]{0,1,2,3,4};
			puzzles[35].imageId = R.drawable.puzzle23410;
			puzzles[35].targetTower = -1;
			
			puzzles[36].puzzleSize = 5;
			puzzles[36].name = "3-20";
			puzzles[36].startOrder = new int[]{3,4,1,2,0};
			puzzles[36].targetOrder = new int[]{0,1,2,3,4};
			puzzles[36].imageId = R.drawable.puzzle34120;
			puzzles[36].targetTower = -1;
			
			puzzles[37].puzzleSize = 5;
			puzzles[37].name = "3-21";
			puzzles[37].startOrder = new int[]{2,3,0,4,1};
			puzzles[37].targetOrder = new int[]{0,1,2,3,4};
			puzzles[37].imageId = R.drawable.puzzle23041;
			puzzles[37].targetTower = -1;
			
			puzzles[38].puzzleSize = 5;
			puzzles[38].name = "3-22";
			puzzles[38].startOrder = new int[]{2,0,3,4,1};
			puzzles[38].targetOrder = new int[]{0,1,2,3,4};
			puzzles[38].imageId = R.drawable.puzzle20341;
			puzzles[38].targetTower = -1;
			
			puzzles[39].puzzleSize = 5;
			puzzles[39].name = "3-23";
			puzzles[39].startOrder = new int[]{0,3,2,1,4};
			puzzles[39].targetOrder = new int[]{0,1,2,3,4};
			puzzles[39].imageId = R.drawable.puzzle03214;
			puzzles[39].targetTower = -1;
			
			puzzles[40].puzzleSize = 5;
			puzzles[40].name = "3-24";
			puzzles[40].startOrder = new int[]{3,0,4,1,2};
			puzzles[40].targetOrder = new int[]{0,1,2,3,4};
			puzzles[40].imageId = R.drawable.puzzle30412;
			puzzles[40].targetTower = -1;
			
			puzzles[41].puzzleSize = 5;
			puzzles[41].name = "3-25";
			puzzles[41].startOrder = new int[]{4,1,0,3,2};
			puzzles[41].targetOrder = new int[]{0,1,2,3,4};
			puzzles[41].imageId = R.drawable.puzzle41032;
			puzzles[41].targetTower = -1;
			
			puzzles[42].puzzleSize = 5;
			puzzles[42].name = "3-26";
			puzzles[42].startOrder = new int[]{0,4,1,3,2};
			puzzles[42].targetOrder = new int[]{0,1,2,3,4};
			puzzles[42].imageId = R.drawable.puzzle04132;
			puzzles[42].targetTower = -1;
			
			puzzles[43].puzzleSize = 5;
			puzzles[43].name = "3-27";
			puzzles[43].startOrder = new int[]{3,0,1,4,2};
			puzzles[43].targetOrder = new int[]{0,1,2,3,4};
			puzzles[43].imageId = R.drawable.puzzle30142;
			puzzles[43].targetTower = -1;
			
			puzzles[44].puzzleSize = 5;
			puzzles[44].name = "3-28";
			puzzles[44].startOrder = new int[]{1,0,3,4,2};
			puzzles[44].targetOrder = new int[]{0,1,2,3,4};
			puzzles[44].imageId = R.drawable.puzzle10342;
			puzzles[44].targetTower = -1;
			
			puzzles[45].puzzleSize = 5;
			puzzles[45].name = "3-29";
			puzzles[45].startOrder = new int[]{0,2,3,1,4};
			puzzles[45].targetOrder = new int[]{0,1,2,3,4};
			puzzles[45].imageId = R.drawable.puzzle02314;
			puzzles[45].targetTower = -1;
			
			puzzles[46].puzzleSize = 5;
			puzzles[46].name = "3-30";
			puzzles[46].startOrder = new int[]{0,3,1,4,2};
			puzzles[46].targetOrder = new int[]{0,1,2,3,4};
			puzzles[46].imageId = R.drawable.puzzle03142;
			puzzles[46].targetTower = -1;
			
			puzzles[47].puzzleSize = 5;
			puzzles[47].name = "3-31";
			puzzles[47].startOrder = new int[]{4,2,1,0,3};
			puzzles[47].targetOrder = new int[]{0,1,2,3,4};
			puzzles[47].imageId = R.drawable.puzzle42103;
			puzzles[47].targetTower = -1;
			
			puzzles[48].puzzleSize = 5;
			puzzles[48].name = "3-32";
			puzzles[48].startOrder = new int[]{3,2,1,0,4};
			puzzles[48].targetOrder = new int[]{0,1,2,3,4};
			puzzles[48].imageId = R.drawable.puzzle32104;
			puzzles[48].targetTower = -1;
			
			puzzles[49].puzzleSize = 5;
			puzzles[49].name = "3-33";
			puzzles[49].startOrder = new int[]{2,0,3,1,4};
			puzzles[49].targetOrder = new int[]{0,1,2,3,4};
			puzzles[49].imageId = R.drawable.puzzle20314;
			puzzles[49].targetTower = -1;
			
			puzzles[50].puzzleSize = 5;
			puzzles[50].name = "3-34";
			puzzles[50].startOrder = new int[]{3,2,0,1,4};
			puzzles[50].targetOrder = new int[]{0,1,2,3,4};
			puzzles[50].imageId = R.drawable.puzzle32014;
			puzzles[50].targetTower = -1;
			
			puzzles[51].puzzleSize = 5;
			puzzles[51].name = "3-35";
			puzzles[51].startOrder = new int[]{3,1,4,2,0};
			puzzles[51].targetOrder = new int[]{0,1,2,3,4};
			puzzles[51].imageId = R.drawable.puzzle31420;
			puzzles[51].targetTower = -1;
		}

		return puzzles;
	}
}
