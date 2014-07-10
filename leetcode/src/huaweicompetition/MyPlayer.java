package huaweicompetition;

public class MyPlayer extends JavaPlayer {
	public class Pos {
		public Pos(int i, int j) {
			row = i;
			col = j;
		}

		Pos() {
		}

		public int row;
		public int col;
	};

	Pos[] people;
	Pos[] opposite;
	Pos[] box;
	Pos[] fence;
	int[][] distance;// 保存人到箱子+箱子到终点的最短距离
	int[] fencecount;// 统计障碍的个数
	int mincount = 0;// 障碍最少
	int totalrow;
	int totalcol;
	int mindis_me;
	int mindis_box;
	// int lastseconddir = -2;
	int lastdir = -1;// 上一次执行的动作
	int lastme = -1;// 上一次执行动作的人
	int lastme_row = -1;// 该人的横坐标
	int lastme_col = -1;// 该人的纵坐标
	int duplicate = 0;// 有无重复结构的标志
	boolean unmoved = false;// 上一次执行的动作是否未移动，即无效

	public void play() {

		int me = api_whoami();
		totalrow = api_getMatrixRow();
		totalcol = api_getMatrixCol();
		duplicate = 0;
		// 判断游戏是否为刚刚开始
		if (me == 1) {
			// 我是左方
			if (api_getGridInfo(0, 0) == me
					&& api_getGridInfo((totalrow - 1) / 2, 0) == me
					&& api_getGridInfo(totalrow - 1, 0) == me) {
				initial();
				peoplepos(me);
				fencepos();
			}
		} else {
			// 我是右方
			if (api_getGridInfo(0, totalcol - 1) == me
					&& api_getGridInfo((totalrow - 1) / 2, totalcol - 1) == me
					&& api_getGridInfo(totalrow - 1, totalcol - 1) == me) {
				initial();
				peoplepos(me);
				fencepos();
			}
		}
		api_log("i am xxx\n");
		api_log("the position of me" + me + "\n");
		// Pos people[mindis_me] = new Pos();
		// Pos box[mindis_box] = new Pos();
		// boolean getMe = false;
		// boolean getBox = false;
		int boxcount = 0;
		int mecount = 0;
		int opcount = 0;
		// 找到我的三个人的位置
		for (int i = 0; i < api_getMatrixRow() && mecount < 3; i++) {
			for (int j = 0; j < api_getMatrixCol(); j++) {
				if (api_getGridInfo(i, j) == me) {
					people[mecount].row = i;
					people[mecount].col = j;
					mecount++;
					if (mecount == 3) {
						break;
					}
				}
			}
		}
		// 找到对方的三个人的位置
		for (int i = 0; i < api_getMatrixRow() && mecount < 3; i++) {
			for (int j = 0; j < api_getMatrixCol(); j++) {
				if (api_getGridInfo(i, j) != me && api_getGridInfo(i, j) >= 1
						&& api_getGridInfo(i, j) <= 2) {
					opposite[mecount].row = i;
					opposite[mecount].col = j;
					opcount++;
					if (opcount == 3) {
						break;
					}
				}
			}
		}
		// 找到16个箱子的位置
		for (int i = 0; i < api_getMatrixRow() && boxcount < 16; i++) {
			for (int j = 0; j < api_getMatrixCol(); j++) {
				if (api_getGridInfo(i, j) == GRID_BOX) {
					box[boxcount].row = i;
					box[boxcount].col = j;
					boxcount++;
					if (boxcount == 16) {
						break;
					}
				}
			}
		}

		caldistance(me);
		mindistance(me);// 计算最短距离的人和箱子的位置
		api_log("the position of box_0 " + box[0].row + " " + box[0].col + "\n");
		api_log("the position of mindis_box " + mindis_box + "\n");
		api_log("the position of box " + box[mindis_box].row + " "
				+ box[mindis_box].col + "\n");
		api_log("the position of me_pos " + people[mindis_me].row + " "
				+ people[mindis_me].col + "\n");

		int dir = DIR_DOWN;
		dir = EnforceAction(me);
		// lastseconddir = lastdir;// 上上次的动作
		lastdir = dir;// 上一次执行的动作
		lastme = mindis_me;// 上一次执行动作的人
		lastme_row = people[mindis_me].row;
		lastme_col = people[mindis_me].col;
		api_log("i will do");
		api_push(people[mindis_me].row, people[mindis_me].col, dir);
		// modifyme(dir, me);
		return;
	}

	// 比赛开始前，初始化所有对象数组
	public void initial() {
		people = new Pos[3];
		for (int i = 0; i < 3; i++) {
			people[i] = new Pos();
		}
		box = new Pos[16];
		for (int i = 0; i < 16; i++) {
			box[i] = new Pos();
		}
		fence = new Pos[9];
		for (int i = 0; i < 9; i++) {
			fence[i] = new Pos();
		}
		opposite = new Pos[3];
		for (int i = 0; i < 3; i++) {
			opposite[i] = new Pos();
		}
		distance = new int[3][16];
		fencecount = new int[3];
	}

	// 在比赛开始时，找到所有障碍的位置;
	public void fencepos() {
		int temp = 0;
		int count = 0;
		for (int i = 0; i < api_getMatrixRow(); i++) {
			for (int j = 0; j < api_getMatrixCol(); j++) {
				if (api_getGridInfo(i, j) == GRID_FENCE) {
					fence[count].row = i;
					fence[count].col = j;
					count++;
					if (i < 6) {
						fencecount[0]++;
					} else if (i < 12) {
						fencecount[1]++;
					} else {
						{
							fencecount[2]++;
						}
					}
				}
			}
		}
		temp = Math.min(fencecount[0], fencecount[1]);
		temp = Math.min(mincount, fencecount[2]);
		if (temp == fencecount[0]) {
			mincount = 0;
		} else if (temp == fencecount[1]) {
			mincount = 1;
		} else if (temp == fencecount[2]) {
			mincount = 2;
		}
	}

	// 比赛开始前，初始化人的位置;
	public void peoplepos(int me) {
		if (me == 1) {
			people[0].row = 0;
			people[0].col = 0;
			people[1].row = (totalrow - 1) / 2;
			people[1].col = 0;
			people[2].row = totalrow - 1;
			people[2].col = 0;
		} else {
			people[0].row = 0;
			people[0].col = totalcol - 1;
			people[1].row = (totalrow - 1) / 2;
			people[1].col = totalcol - 1;
			people[2].row = totalrow - 1;
			people[2].col = totalcol - 1;
		}
	}

	public void caldistance(int me) {
		if (me == 1) {// 我在左方
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 16; j++) {
					// 判断箱子是否在第一列或者最后一列
					if (box[j].col == totalcol - 1 || box[j].col == 0) {
						distance[i][j] = Integer.MAX_VALUE;
						continue;
					}
					// 判断箱子的左右是否还有箱子或者障碍物或者对方的人
					if (api_getGridInfo(box[j].row, box[j].col - 1) == GRID_BOX
							|| api_getGridInfo(box[j].row, box[j].col + 1) == GRID_BOX
							|| api_getGridInfo(box[j].row, box[j].col - 1) == GRID_FENCE
							|| api_getGridInfo(box[j].row, box[j].col + 1) == GRID_FENCE
							|| api_getGridInfo(box[j].row, box[j].col - 1) == PLAYER_R
							|| api_getGridInfo(box[j].row, box[j].col + 1) == PLAYER_R) {
						// 判断箱子的上下是否还有箱子或者障碍物或者对方的人
						if (api_getGridInfo(box[j].row + 1, box[j].col) == GRID_BOX
								|| api_getGridInfo(box[j].row - 1, box[j].col) == GRID_BOX
								|| api_getGridInfo(box[j].row + 1, box[j].col) == GRID_FENCE
								|| api_getGridInfo(box[j].row - 1, box[j].col) == GRID_FENCE
								|| api_getGridInfo(box[j].row + 1, box[j].col) == PLAYER_R
								|| api_getGridInfo(box[j].row - 1, box[j].col) == PLAYER_R
								|| box[j].row + 1 == totalrow
								|| box[j].row - 1 == -1) {
							distance[i][j] = Integer.MAX_VALUE;
							continue;
						}
					}
					// 人与箱子在同一行
					if (people[i].row == box[j].row) {
						if (people[i].col > box[j].col) {
							distance[i][j] = people[i].col - box[j].col
									+ totalcol + 2 - box[j].col;
							for (int k = box[j].col - 1; k < totalcol - 1; k++) {
								if (api_getGridInfo(box[j].row, k) == GRID_FENCE) {
									distance[i][j] = distance[i][j] + 5;
									break;
								}
							}
						} else {
							distance[i][j] = totalcol - people[i].col - 2;
							for (int k = box[j].col - 1; k < totalcol - 1; k++) {
								if (api_getGridInfo(box[j].row, k) == GRID_FENCE) {
									distance[i][j] = distance[i][j] + 5;
									break;
								}
							}
						}

					}
					// 人与箱子在同一列
					else if (people[i].col == box[j].col) {
						distance[i][j] = Math.abs(people[i].row - box[j].row)
								+ totalcol - people[i].col;
						for (int k = box[j].col - 1; k < totalcol - 1; k++) {
							if (api_getGridInfo(box[j].row, k) == GRID_FENCE) {
								distance[i][j] = distance[i][j] + 5;
								break;
							}
						}

					}
					// 人在箱子的右侧
					else if (people[i].col > box[j].col) {
						distance[i][j] = people[i].col - box[j].col + 1
								+ Math.abs(people[i].row - box[j].row)
								+ totalcol - 1 - box[j].col;
						for (int k = box[j].col - 1; k < totalcol - 1; k++) {
							if (api_getGridInfo(box[j].row, k) == GRID_FENCE) {
								distance[i][j] = distance[i][j] + 5;
								break;
							}
						}

					}
					// 人在箱子的左侧
					else {
						distance[i][j] = Math.abs(people[i].row - box[j].row)
								+ totalcol - 2 - people[i].col;
						for (int k = box[j].col - 1; k < totalcol - 1; k++) {
							if (api_getGridInfo(box[j].row, k) == GRID_FENCE) {
								distance[i][j] = distance[i][j] + 5;
								break;
							}
						}
					}

					// 箱子右边有对方的人
					if (api_getGridInfo(box[j].row, box[j].col + 1) == PLAYER_R) {
						distance[i][j] = distance[i][j] + 40;// 40为我自定义的数字
					}
				}
			}
		} else {// 我在右方
			for (int i = 2; i >= 0; i--) {
				for (int j = 15; j >= 0; j--) {
					if (box[j].col == totalcol - 1 || box[j].col == 0) {
						distance[i][j] = Integer.MAX_VALUE;
						continue;
					}
					// 判断箱子的左右是否还有箱子或者障碍物或者对方的人
					if (api_getGridInfo(box[j].row, box[j].col - 1) == GRID_BOX
							|| api_getGridInfo(box[j].row, box[j].col + 1) == GRID_BOX
							|| api_getGridInfo(box[j].row, box[j].col - 1) == GRID_FENCE
							|| api_getGridInfo(box[j].row, box[j].col + 1) == GRID_FENCE
							|| api_getGridInfo(box[j].row, box[j].col - 1) == PLAYER_L
							|| api_getGridInfo(box[j].row, box[j].col + 1) == PLAYER_L) {
						// 判断箱子的上下是否还有箱子或者障碍物或者对方的人
						if (api_getGridInfo(box[j].row + 1, box[j].col) == GRID_BOX
								|| api_getGridInfo(box[j].row - 1, box[j].col) == GRID_BOX
								|| api_getGridInfo(box[j].row + 1, box[j].col) == GRID_FENCE
								|| api_getGridInfo(box[j].row - 1, box[j].col) == GRID_FENCE
								|| api_getGridInfo(box[j].row + 1, box[j].col) == PLAYER_L
								|| api_getGridInfo(box[j].row - 1, box[j].col) == PLAYER_L
								|| box[j].row + 1 == totalrow
								|| box[j].row - 1 == -1) {
							distance[i][j] = Integer.MAX_VALUE;
							continue;
						}
					}
					// 人与箱子在同一行
					if (people[i].row == box[j].row) {
						if (people[i].col > box[j].col) {
							distance[i][j] = people[i].col - 1;
							for (int k = 1; k <= box[j].col + 1; k++) {
								if (api_getGridInfo(box[j].row, k) == GRID_FENCE) {
									distance[i][j] = distance[i][j] + 5;
									break;
								}
							}
						} else {
							distance[i][j] = box[j].col - people[i].col + 3
									+ box[j].col;
							for (int k = 1; k <= box[j].col + 1; k++) {
								if (api_getGridInfo(box[j].row, k) == GRID_FENCE) {
									distance[i][j] = distance[i][j] + 5;
									break;
								}
							}
						}
					}
					// 人与箱子在同一列
					else if (people[i].col == box[j].col) {
						distance[i][j] = Math.abs(people[i].row - box[j].row)
								+ 1 + box[j].col;
						for (int k = 1; k <= box[j].col + 1; k++) {
							if (api_getGridInfo(box[j].row, k) == GRID_FENCE) {
								distance[i][j] = distance[i][j] + 5;
								break;
							}
						}
					}
					// 人在箱子的右侧
					else if (people[i].col > box[j].col) {
						distance[i][j] = people[i].col - 1
								+ Math.abs(people[i].row - box[j].row);
						for (int k = 1; k <= box[j].col + 1; k++) {
							if (api_getGridInfo(box[j].row, k) == GRID_FENCE) {
								distance[i][j] = distance[i][j] + 5;
								break;
							}
						}
					}
					// 人在箱子的左侧
					else {
						distance[i][j] = Math.abs(people[i].row - box[j].row)
								+ box[j].col - people[i].col + 1 + box[j].col;
						for (int k = 1; k <= box[j].col + 1; k++) {
							if (api_getGridInfo(box[j].row, k) == GRID_FENCE) {
								distance[i][j] = distance[i][j] + 5;
								break;
							}
						}
					}

					// 箱子左边有对方的人
					if (api_getGridInfo(box[j].row, box[j].col - 1) == PLAYER_L) {
						distance[i][j] = distance[i][j] + 40;// 40为我自定义的数字
					}
				}
			}
		}
	}

	// 计算出最短距离的人和箱子
	public void mindistance(int me) {
		int temp = Integer.MAX_VALUE;
		if (me == 1) {
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 16; j++) {
					if (distance[i][j] < temp) {
						temp = distance[i][j];
						mindis_me = i;
						mindis_box = j;
						api_log("distance[i][j] = " + temp);
					} else if (distance[i][j] == temp && mincount == i) {
						temp = distance[i][j];
						mindis_me = i;
						mindis_box = j;
						api_log("distance[i][j] = " + temp);
					}
				}
			}
		} else {
			for (int i = 2; i >= 0; i--) {
				for (int j = 15; j >= 0; j--) {
					if (distance[i][j] < temp) {
						temp = distance[i][j];
						mindis_me = i;
						mindis_box = j;
						api_log("distance[i][j] = " + temp);
					} else if (distance[i][j] == temp && mincount == i) {
						temp = distance[i][j];
						mindis_me = i;
						mindis_box = j;
						api_log("distance[i][j] = " + temp);
					}
				}
			}
		}

	}

	// // 修改我的位置
	// public void modifyme(int dir, int me) {
	// if (dir == DIR_DOWN) {
	// if (api_getGridInfo(people[mindis_me].row + 1,
	// people[mindis_me].col) == me)
	// people[mindis_me].row = people[mindis_me].row + 1;
	//
	// } else if (dir == DIR_UP) {
	// if (api_getGridInfo(people[mindis_me].row - 1,
	// people[mindis_me].col) == me)
	// people[mindis_me].row = people[mindis_me].row - 1;
	// } else if (dir == DIR_LEFT) {
	// if (api_getGridInfo(people[mindis_me].row,
	// people[mindis_me].col - 1) == me)
	// people[mindis_me].col = people[mindis_me].col - 1;
	// } else {
	// if (api_getGridInfo(people[mindis_me].row,
	// people[mindis_me].col + 1) == me)
	// people[mindis_me].col = people[mindis_me].col + 1;
	// }
	// }

	// 执行动作
	public int EnforceAction(int me) {
		int dir = DIR_DOWN;
		if (me == 1) {// 我在左边
			if (api_getGridInfo(box[mindis_box].row, box[mindis_box].col + 1) != GRID_BOX
					&& api_getGridInfo(box[mindis_box].row,
							box[mindis_box].col - 1) != GRID_BOX
					&& api_getGridInfo(box[mindis_box].row,
							box[mindis_box].col - 1) != GRID_FENCE
					&& api_getGridInfo(box[mindis_box].row,
							box[mindis_box].col + 1) != GRID_FENCE
					&& api_getGridInfo(box[mindis_box].row,
							box[mindis_box].col - 1) != PLAYER_R
					&& api_getGridInfo(box[mindis_box].row,
							box[mindis_box].col + 1) != PLAYER_R) {// 左右没有障碍物或者箱子或者对方
				if (people[mindis_me].col > box[mindis_box].col) // 人在箱子右边
				{
					if (people[mindis_me].row < box[mindis_box].row) // 上边
					{
						if (left_no_fence(me))// 左边没障碍，则走左边
							dir = DIR_LEFT;
						else if (down_no_fence(me))// 下边没障碍，则走下边
							dir = DIR_DOWN;
						else if (up_no_fence(me))// 上边没障碍，则走上边
							dir = DIR_UP;
						else
							dir = DIR_RIGHT;
					} else if (people[mindis_me].row > box[mindis_box].row) // 下边
					{
						if (left_no_fence(me))// 左边没障碍，则走左边
							dir = DIR_LEFT;
						else if (up_no_fence(me))// 上边没障碍，则走上边
							dir = DIR_UP;
						else if (down_no_fence(me))// 下边没障碍，则走下边
							dir = DIR_DOWN;
						else
							dir = DIR_RIGHT;
					} else // 一条线
					{
						if (down_no_fence(me))// 下边没障碍，则走下边
							dir = DIR_DOWN;
						else if (up_no_fence(me))// 上边没障碍，则走上边
							dir = DIR_UP;
						else if (left_no_fence(me))// 左边没障碍，则走左边
							dir = DIR_LEFT;
						else
							dir = DIR_RIGHT;// 否则走右边
					}
				} else if (people[mindis_me].col < box[mindis_box].col) // 人在箱子左边
				{
					if (people[mindis_me].row < box[mindis_box].row) // 上边
					{
						if (down_no_fence(me))// 下边没障碍，则走下边
							dir = DIR_DOWN;
						else if (right_no_fence(me))// 右边没障碍，则走右边
							dir = DIR_RIGHT;
						else if (left_no_fence(me))// 左边没障碍，则走左边
							dir = DIR_LEFT;
						else
							dir = DIR_UP;
					} else if (people[mindis_me].row > box[mindis_box].row) // 下边
					{
						if (up_no_fence(me))// 上边没障碍，则走上边
							dir = DIR_UP;
						else if (right_no_fence(me))// 右边没障碍，则走右边
							dir = DIR_RIGHT;
						else if (left_no_fence(me))// 左边没障碍，则走左边
							dir = DIR_LEFT;
						else
							dir = DIR_DOWN;
					} else // 一条线
					{
						if (right_no_fence(me))// 右边没障碍，则走右边
							dir = DIR_RIGHT;
						else if (up_no_fence(me))// 上边没障碍，则走上边
							dir = DIR_UP;
						else if (down_no_fence(me))// 下边没障碍，则走下边
							dir = DIR_DOWN;
						else
							dir = DIR_LEFT;
					}

				} else // 垂直
				{
					if (left_no_fence(me))// 左边没障碍，则走左边
						dir = DIR_LEFT;
					else if (people[mindis_me].row < box[mindis_box].row) {// 人在箱子上方
						if (down_no_fence(me))// 下边没障碍，则走下边
							dir = DIR_DOWN;
						else if (left_no_fence(me))// 左边没障碍，则走左边
							dir = DIR_LEFT;
						else
							dir = DIR_UP;
					} else if (people[mindis_me].row > box[mindis_box].row) {// 人在箱子下方
						if (up_no_fence(me))// 上边没障碍，则走上边
							dir = DIR_UP;
						else if (down_no_fence(me))// 下边没障碍，则走下边
							dir = DIR_LEFT;
						else
							dir = DIR_DOWN;
					}
				}
			} else {// 左右有障碍物或者箱子或者对方（那么要先把这个结构消除）
				duplicate = 1;
				dir = deletefence(me);
			}
		} else {// 我在右边
			if (api_getGridInfo(box[mindis_box].row, box[mindis_box].col + 1) != GRID_BOX
					&& api_getGridInfo(box[mindis_box].row,
							box[mindis_box].col - 1) != GRID_BOX
					&& api_getGridInfo(box[mindis_box].row,
							box[mindis_box].col - 1) != GRID_FENCE
					&& api_getGridInfo(box[mindis_box].row,
							box[mindis_box].col + 1) != GRID_FENCE
					&& api_getGridInfo(box[mindis_box].row,
							box[mindis_box].col - 1) != PLAYER_L
					&& api_getGridInfo(box[mindis_box].row,
							box[mindis_box].col + 1) != PLAYER_L) {// 左右没有障碍物或者箱子或者对方
				if (people[mindis_me].col > box[mindis_box].col) // 人在箱子右边
				{
					if (people[mindis_me].row < box[mindis_box].row) // 上边
					{
						if (down_no_fence(me))// 下边没障碍，则走下边
							dir = DIR_DOWN;
						else if (left_no_fence(me))// 左边没障碍，则走左边
							dir = DIR_LEFT;
						else if (right_no_fence(me))// 右边没障碍，则走右边
							dir = DIR_RIGHT;
						else
							dir = DIR_UP;
					} else if (people[mindis_me].row > box[mindis_box].row) // 下边
					{
						if (up_no_fence(me))// 上边没障碍，则走上边
							dir = DIR_UP;
						else if (left_no_fence(me))// 左边没障碍，则走左边
							dir = DIR_LEFT;
						else if (right_no_fence(me))// 右边没障碍，则走右边
							dir = DIR_RIGHT;
						else
							dir = DIR_DOWN;
					} else // 一条线
					{
						if (left_no_fence(me))// 左边没障碍，则走左边
							dir = DIR_LEFT;
						else if (down_no_fence(me))// 下边没障碍，则走下边
							dir = DIR_DOWN;
						else if (up_no_fence(me))// 上边没障碍，则走上边
							dir = DIR_UP;
						else
							dir = DIR_RIGHT;
					}
				} else if (people[mindis_me].col < box[mindis_box].col) // 人在箱子左边
				{
					if (people[mindis_me].row < box[mindis_box].row) // 上边
					{
						if (right_no_fence(me))// 右边没障碍，则走右边
							dir = DIR_RIGHT;
						else if (down_no_fence(me))// 下边没障碍，则走下边
							dir = DIR_DOWN;
						else if (up_no_fence(me))// 上边没障碍，则走上边
							dir = DIR_UP;
						else
							dir = DIR_LEFT;
					} else if (people[mindis_me].row > box[mindis_box].row) // 下边
					{
						if (right_no_fence(me))// 右边没障碍，则走右边
							dir = DIR_RIGHT;
						else if (up_no_fence(me))// 上边没障碍，则走上边
							dir = DIR_UP;
						else if (down_no_fence(me))// 下边没障碍，则走下边
							dir = DIR_DOWN;
						else
							dir = DIR_LEFT;

					} else // 一条线
					{
						if (down_no_fence(me))// 下边没障碍，则走下边
							dir = DIR_DOWN;
						else if (up_no_fence(me))// 上边没障碍，则走上边
							dir = DIR_UP;
						else if (right_no_fence(me))// 右边没障碍，则走右边
							dir = DIR_RIGHT;
						else
							dir = DIR_LEFT;
					}

				} else // 垂直
				{
					if (right_no_fence(me))// 右边没障碍，则走右边
						dir = DIR_RIGHT;
					else if (people[mindis_me].row < box[mindis_box].row) {// 人在箱子上方
						if (down_no_fence(me))// 下边没障碍，则走下边
							dir = DIR_DOWN;
						else if (left_no_fence(me))// 左边没障碍，则走左边
							dir = DIR_LEFT;
						else
							dir = DIR_UP;
					} else if (people[mindis_me].row > box[mindis_box].row) {// 人在箱子下方
						if (up_no_fence(me))// 上边没障碍，则走上边
							dir = DIR_UP;
						else if (down_no_fence(me))// 下边没障碍，则走下边
							dir = DIR_LEFT;
						else
							dir = DIR_DOWN;
					}
				}
			} else {// 左右有障碍物或者箱子或者对方（那么要先把这个结构消除）
				duplicate = 1;
				dir = deletefence(me);
			}
		}
		return dir;
	}

	// 左右有障碍物或者箱子或者对方（那么要先把这个结构消除）
	public int deletefence(int me) {
		int dir = DIR_DOWN;
		if (people[mindis_me].col > box[mindis_box].col) // 人在箱子右边
		{
			if (people[mindis_me].row < box[mindis_box].row) // 上边
			{
				if (left_no_fence(me))// 左边没障碍，则走左边
					dir = DIR_LEFT;
				else if (down_no_fence(me))// 下边没障碍，则走下边
					dir = DIR_DOWN;
				else if (up_no_fence(me))// 上边没障碍，则走上边
					dir = DIR_UP;
				else
					dir = DIR_RIGHT;
			} else if (people[mindis_me].row > box[mindis_box].row) // 下边
			{
				if (left_no_fence(me))// 左边没障碍，则走左边
					dir = DIR_LEFT;
				else if (up_no_fence(me))// 上边没障碍，则走上边
					dir = DIR_UP;
				else if (down_no_fence(me))// 下边没障碍，则走下边
					dir = DIR_DOWN;
				else
					dir = DIR_RIGHT;
			} else // 一条线
			{
				if (down_no_fence(me))// 下边没障碍，则走下边
					dir = DIR_DOWN;
				else if (up_no_fence(me))// 上边没障碍，则走上边
					dir = DIR_UP;
				else if (left_no_fence(me))// 左边没障碍，则走左边
					dir = DIR_LEFT;
				else
					dir = DIR_RIGHT;// 否则走右边
			}
		} else if (people[mindis_me].col < box[mindis_box].col) // 人在箱子左边
		{
			if (people[mindis_me].row < box[mindis_box].row) // 上边
			{
				if (right_no_fence(me))// 右边没障碍，则走右边
					dir = DIR_RIGHT;
				else if (down_no_fence(me))// 下边没障碍，则走下边
					dir = DIR_DOWN;
				else if (up_no_fence(me))// 上边没障碍，则走上边
					dir = DIR_UP;
				else
					dir = DIR_LEFT;

			} else if (people[mindis_me].row > box[mindis_box].row) // 下边
			{
				if (right_no_fence(me))// 右边没障碍，则走右边
					dir = DIR_RIGHT;
				else if (up_no_fence(me))// 上边没障碍，则走上边
					dir = DIR_UP;
				else if (down_no_fence(me))// 下边没障碍，则走下边
					dir = DIR_DOWN;
				else
					dir = DIR_LEFT;
			} else // 一条线
			{
				if (up_no_fence(me))// 上边没障碍，则走上边
					dir = DIR_UP;
				else if (down_no_fence(me))// 下边没障碍，则走下边
					dir = DIR_DOWN;
				else if (right_no_fence(me))// 右边没障碍，则走右边
					dir = DIR_RIGHT;
				else
					dir = DIR_LEFT;
			}

		} else // 垂直
		{

			if (people[mindis_me].row < box[mindis_box].row) // 上边
			{
				if (down_no_fence(me))// 下边没障碍，则走下边
					dir = DIR_DOWN;
				else if (left_no_fence(me))// 左边没障碍，则走左边
					dir = DIR_LEFT;
				else if (right_no_fence(me))// 右边没障碍，则走右边
					dir = DIR_RIGHT;
				else
					dir = DIR_UP;
			} else if (people[mindis_me].row > box[mindis_box].row) // 下边
			{
				if (up_no_fence(me))// 上边没障碍，则走上边
					dir = DIR_UP;
				else if (right_no_fence(me))// 右边没障碍，则走右边
					dir = DIR_RIGHT;
				else if (left_no_fence(me))// 左边没障碍，则走左边
					dir = DIR_LEFT;
				else
					dir = DIR_DOWN;
			}
		}
		return dir;
	}

	public boolean up_no_fence(int me) {// 上边没障碍，则走上边
		int oppo = 0;
		if (me == 1) {
			oppo = 2;
		} else
			oppo = 1;
		api_log(api_getGridInfo(people[mindis_me].row - 3,
				people[mindis_me].col) + "");
		if (api_getGridInfo(people[mindis_me].row - 1, people[mindis_me].col) != GRID_FENCE
				&& isupnofence()
				&& api_getGridInfo(people[mindis_me].row - 1,
						people[mindis_me].col) != oppo
				&& (api_getGridInfo(people[mindis_me].row - 1,
						people[mindis_me].col) != GRID_BOX || people[mindis_me].row - 1 > 0)
				&& ((people[mindis_me].row - 3 < 0) || 	TwoFence(people[mindis_me].row - 3,
						people[mindis_me].col) != true || TwoFence(
						people[mindis_me].row -2, people[mindis_me].col+1) != true)
				&& ((people[mindis_me].row - 3 < 0) || TwoFence(people[mindis_me].row - 3,
						people[mindis_me].col) != true || TwoFence(
						people[mindis_me].row -2, people[mindis_me].col-1) != true)
				&& (people[mindis_me].row - 1) >= 0
				// && (lastseconddir != DIR_UP || lastdir != DIR_DOWN || (lastme
				// != mindis_me && down_me(me) == false))// 判断是否重复走
				&& (lastdir != DIR_DOWN || (lastme != mindis_me && down_me(me) == false))// 判断是否重复走
				&& (isummoved() != true || lastdir != DIR_UP || op_more(me))) {// 判断上一步是否没走成功，而本次是否与上次相同
			return true;
		} else {
			return false;
		}
	}

	public boolean isupnofence() {// 判断该人的上一行到箱子之间有没有障碍
		// if (box[mindis_box].row > people[mindis_me].row) {
		// return true;
		// } else {
		if (box[mindis_box].col > people[mindis_me].col) {
			for (int i = people[mindis_me].col; i < box[mindis_box].col; i++) {
				if (api_getGridInfo(people[mindis_me].row - 1, i) == GRID_FENCE
						&& (i - people[mindis_me].col) <= 2
						&& (people[mindis_me].row - box[mindis_box].row) == 1) {
					return false;
				}
			}
			return true;
		} else if (box[mindis_box].col < people[mindis_me].col) {
			for (int i = people[mindis_me].col; i > box[mindis_box].col; i--) {
				if (api_getGridInfo(people[mindis_me].row - 1, i) == GRID_FENCE
						&& (people[mindis_me].col - i) <= 2
						&& (people[mindis_me].row - box[mindis_box].row) == 1) {
					return false;
				}
			}
			return true;
		} else {
			if (api_getGridInfo(people[mindis_me].row - 1,
					people[mindis_me].col) == GRID_FENCE) {
				return false;
			} else
				return true;
		}
		// }
	}

	public boolean down_no_fence(int me) {// 下边没障碍，则走下边
		int oppo = 0;
		if (me == 1) {
			oppo = 2;
		} else
			oppo = 1;
		api_log(api_getGridInfo(people[mindis_me].row + 3,
				people[mindis_me].col) + "");
		if (api_getGridInfo(people[mindis_me].row + 1, people[mindis_me].col) != GRID_FENCE
				&& isdownnofence()
				&& api_getGridInfo(people[mindis_me].row + 1,
						people[mindis_me].col) != oppo
				&& (api_getGridInfo(people[mindis_me].row + 1,
						people[mindis_me].col) != GRID_BOX || people[mindis_me].row + 2 < totalrow)
				&& ((people[mindis_me].row + 3 > totalrow - 1) || TwoFence(people[mindis_me].row + 3,
						people[mindis_me].col) != true || TwoFence(
						people[mindis_me].row +2, people[mindis_me].col + 1) != true)
				&& ((people[mindis_me].row + 3 > totalrow - 1) || TwoFence(people[mindis_me].row + 3,
						people[mindis_me].col) != true || TwoFence(
						people[mindis_me].row+2, people[mindis_me].col-1) != true)
				&& (people[mindis_me].row + 1) < totalrow
				// && (lastseconddir != DIR_DOWN || lastdir != DIR_UP || (lastme
				// != mindis_me && down_me(me) == false))// 判断是否重复走
				&& (lastdir != DIR_UP || (lastme != mindis_me && down_me(me) == false))// 判断是否重复走
				&& (isummoved() != true || lastdir != DIR_DOWN || op_more(me))) {// 判断上一步是否没走成功，而本次是否与上次相同
			return true;
		} else {
			return false;
		}
	}

	public boolean isdownnofence() {// 判断该人的下一行到箱子之间有没有障碍
		// if (box[mindis_box].row <= people[mindis_me].row) {
		// return true;
		// } else {
		if (box[mindis_box].col > people[mindis_me].col) {
			for (int i = people[mindis_me].col; i < box[mindis_box].col; i++) {
				if (api_getGridInfo(people[mindis_me].row + 1, i) == GRID_FENCE
						&& (i - people[mindis_me].col) <= 2
						&& (box[mindis_box].row - people[mindis_me].row) == 1) {
					return false;
				}
			}
			return true;
		} else if (box[mindis_box].col < people[mindis_me].col) {
			for (int i = people[mindis_me].col; i > box[mindis_box].col; i--) {
				if (api_getGridInfo(people[mindis_me].row + 1, i) == GRID_FENCE
						&& (people[mindis_me].col - i) <= 2
						&& (box[mindis_box].row - people[mindis_me].row) == 1) {
					return false;
				}
			}
			return true;
		} else {
			if (api_getGridInfo(people[mindis_me].row + 1,
					people[mindis_me].col) == GRID_FENCE) {
				return false;
			} else
				return true;
		}
		// }
	}

	public boolean left_no_fence(int me) {// 左边没障碍，则走左边
		int oppo = 0;
		if (me == 1) {
			oppo = 2;
		} else
			oppo = 1;
		if (api_getGridInfo(people[mindis_me].row, people[mindis_me].col - 1) != GRID_FENCE
				&& api_getGridInfo(people[mindis_me].row,
						people[mindis_me].col - 1) != oppo
				&& people[mindis_me].col - 1 >= 0
				&& (TwoFence(people[mindis_me].row + 1,
						people[mindis_me].col - 2) != true || TwoFence(
						people[mindis_me].row, people[mindis_me].col - 3) != true)
				&& (TwoFence(people[mindis_me].row - 1,
						people[mindis_me].col - 2) != true || TwoFence(
						people[mindis_me].row, people[mindis_me].col - 3) != true)

				// && (lastseconddir != DIR_LEFT || lastdir != DIR_RIGHT ||
				// lastme != mindis_me)// 判断是否重复走
				&& (lastdir != DIR_RIGHT || lastme != mindis_me)// 判断是否重复走
				&& (people[mindis_me].row == box[mindis_box].row
						|| (duplicate == 1)
						|| ((me == 1) && (people[mindis_me].col > box[mindis_box].col))
						|| ((me == 2) && (people[mindis_me].col < box[mindis_box].col)) || people[mindis_me].col - 1 != box[mindis_box].col)// 判断走后是否会垂直
				&& (isummoved() != true || lastdir != DIR_LEFT || op_more(me))) {// 判断上一步是否没走成功，而本次是否与上次相同
			return true;
		} else {
			return false;
		}
	}

	public boolean right_no_fence(int me) {// 右边没障碍，则走右边
		int oppo = 0;
		if (me == 1) {
			oppo = 2;
		} else
			oppo = 1;
		if (api_getGridInfo(people[mindis_me].row, people[mindis_me].col + 1) != GRID_FENCE
				&& api_getGridInfo(people[mindis_me].row,
						people[mindis_me].col + 1) != oppo
				&& people[mindis_me].col + 1 < totalcol
				&& (TwoFence(people[mindis_me].row + 1,
						people[mindis_me].col + 2) != true || TwoFence(
						people[mindis_me].row, people[mindis_me].col + 3) != true)
				&& (TwoFence(people[mindis_me].row - 1,
						people[mindis_me].col + 2) != true || TwoFence(
						people[mindis_me].row, people[mindis_me].col + 3) != true)
				// && (lastseconddir != DIR_RIGHT || lastdir != DIR_LEFT ||
				// lastme != mindis_me)// 判断是否重复走
				&& (lastdir != DIR_LEFT || lastme != mindis_me)// 判断是否重复走
				&& (people[mindis_me].row == box[mindis_box].row
						|| (duplicate == 1)
						|| ((me == 1) && (people[mindis_me].col > box[mindis_box].col))
						|| ((me == 2) && (people[mindis_me].col < box[mindis_box].col)) || people[mindis_me].col + 1 != box[mindis_box].col)// 判断走后是否会垂直
				&& (isummoved() != true || lastdir != DIR_RIGHT || op_more(me))) {// 判断上一步是否没走成功，而本次是否与上次相同
			return true;
		} else {
			return false;
		}
	}

	public boolean TwoFence(int i, int j) {
		if (api_getGridInfo(i, j) == GRID_FENCE
				|| api_getGridInfo(i, j) == GRID_BOX) {
			return true;
		} else {
			return false;
		}
	}

	// 上一次动作未执行成功，即未移动，而本次又要执行该人的该动作
	public boolean isummoved() {
		if (lastme == mindis_me
				&& (lastme_row == people[mindis_me].row && lastme_col == people[mindis_me].col)) {
			return true;
		} else {
			return false;
		}
	}

	// 判断从上到下的动作是否还有本方人员
	public boolean down_me(int me) {
		for (int i = people[mindis_me].col; i < 19; i++) {
			if (api_getGridInfo(people[mindis_me].row, i) == me)
				return true;
		}
		for (int i = 0; i < people[mindis_me].col; i++) {
			if (api_getGridInfo(people[mindis_me].row + 1, i) == me)
				return true;
		}
		return false;
	}

	// 判断从下到上的动作是否还有本方人员
	public boolean up_me(int me) {
		for (int i = people[mindis_me].col; i < 19; i++) {
			if (api_getGridInfo(people[mindis_me].row - 1, i) == me)
				return true;
		}
		for (int i = 0; i < people[mindis_me].col; i++) {
			if (api_getGridInfo(people[mindis_me].row, i) == me)
				return true;
		}
		return false;
	}

	// 判断是对方的人并且多与对方
	public boolean op_more(int me) {
		if (isopposite(me) && morethanoppesite(me)) {
			return true;
		} else {
			return false;
		}
	}

	// 判断不能移动的情况下是否为对方的人
	public boolean isopposite(int me) {
		if (me == 1) {
			if (lastdir == DIR_RIGHT) {
				if (api_getGridInfo(lastme_row, lastme_col + 2) == PLAYER_R) {
					return true;
				} else
					return false;
			} else if (lastdir == DIR_LEFT) {
				if (api_getGridInfo(lastme_row, lastme_col - 2) == PLAYER_R) {
					return true;
				} else
					return false;
			} else if (lastdir == DIR_DOWN) {
				if (api_getGridInfo(lastme_row + 2, lastme_col) == PLAYER_R) {
					return true;
				} else
					return false;
			} else {
				if (api_getGridInfo(lastme_row + 2, lastme_col) == PLAYER_R) {
					return true;
				} else
					return false;
			}
		} else {
			if (lastdir == DIR_RIGHT) {
				if (api_getGridInfo(lastme_row, lastme_col + 2) == PLAYER_L) {
					return true;
				} else
					return false;
			} else if (lastdir == DIR_LEFT) {
				if (api_getGridInfo(lastme_row, lastme_col - 2) == PLAYER_L) {
					return true;
				} else
					return false;
			} else if (lastdir == DIR_DOWN) {
				if (api_getGridInfo(lastme_row + 2, lastme_col) == PLAYER_L) {
					return true;
				} else
					return false;
			} else {
				if (api_getGridInfo(lastme_row + 2, lastme_col) == PLAYER_L) {
					return true;
				} else
					return false;
			}
		}
	}

	// 判断当前我已完成的个数是否多于对方
	public boolean morethanoppesite(int me) {
		int totalme = 0;
		int totalop = 0;
		if (me == 1) {
			for (int i = 0; i < 19; i++) {
				if (api_getGridInfo(i, 18) == GRID_BOX) {
					totalme++;
				}
				if (api_getGridInfo(i, 0) == GRID_BOX) {
					totalop++;
				}
			}
			if (totalme > totalop) {
				return true;
			} else if (totalme < totalop) {
				return false;
			} else {// 完成的箱子数相同，判断箱子远离度
				int totalboxme = 0;
				int totalboxop = 0;
				for (int i = 0; i < 16; i++) {
					totalboxme = totalboxme + box[i].col;
					totalboxop = totalboxop + 18 - box[i].col;
				}
				if (totalboxme > totalboxop) {
					return true;
				} else if (totalboxme < totalboxop) {
					return false;
				} else {// 箱子远离度相同，判断人员远离度（在此，不进行判断谁先完成，后面需要再进行判断）
					int totaldisme = 0;
					int totaldisop = 0;
					for (int i = 0; i < 3; i++) {
						totaldisme = totaldisme + people[i].col;
						totaldisop = totaldisop + 18 - opposite[i].col;
					}
					if (totaldisme > totaldisop) {
						return true;
					} else {
						return false;
					}
				}
			}
		} else {
			for (int i = 0; i < 19; i++) {
				if (api_getGridInfo(i, 0) == GRID_BOX) {
					totalme++;
				}
				if (api_getGridInfo(i, 18) == GRID_BOX) {
					totalop++;
				}
			}
			if (totalme > totalop) {
				return true;
			} else if (totalme < totalop) {
				return false;
			} else {// 完成的箱子数相同，判断箱子远离度
				int totalboxme = 0;
				int totalboxop = 0;
				for (int i = 0; i < 16; i++) {
					totalboxme = totalboxme + 18 - box[i].col;
					totalboxop = totalboxop + box[i].col;
				}
				if (totalboxme > totalboxop) {
					return true;
				} else if (totalboxme < totalboxop) {
					return false;
				} else {// 箱子远离度相同，判断人员远离度（在此，不进行判断谁先完成，后面需要再进行判断）
					int totaldisme = 0;
					int totaldisop = 0;
					for (int i = 0; i < 3; i++) {
						totaldisme = totaldisme + 18 - people[i].col;
						totaldisop = totaldisop + opposite[i].col;
					}
					if (totaldisme > totaldisop) {
						return true;
					} else {
						return false;
					}
				}
			}
		}

	}
}
