package com.lc.meq.how2j;

public class TestThread {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Hero gareen = new Hero();
		gareen.name = "盖伦";
		gareen.hp = 616;
		gareen.damage = 50;
		
		Hero teemo = new Hero();
		teemo.name = "提莫";
		teemo.hp = 300;
		teemo.damage = 30;
		Hero bh = new Hero();
		bh.name = "赏金猎人";
		bh.hp = 500;
		bh.damage = 65;
		
		Hero leesin = new Hero();
		leesin.name = "盲僧";
		leesin.hp = 455;
		leesin.damage = 80;
		
//		//盖伦攻击提莫
//		while (!teemo.isDead()) {
//			gareen.attackHero(teemo);
//		}
//		
//		//赏金猎人攻击盲僧
//		while (!leesin.isDead()) {
//			bh.attackHero(leesin);
//		}
		
		Thread t1 = new Thread() {
			public int seconds = 0;
			public void run() {
//				while (true) {
//					try {
//						Thread.sleep(1000);
//					} catch (Exception e) {
//						// TODO: handle exception
//						e.printStackTrace();
//					}
//					System.out.printf("已经玩了 %d 秒%n",seconds++);
//				}
				while (!teemo.isDead()) {
					gareen.attackHero(teemo);
				}
			}
		};
		t1.start();
		
		try {
			t1.join();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		Thread t2 = new Thread() {
			public void run() {
				while (!leesin.isDead()) {
					bh.attackHero(leesin);;
				}
			}
		};
		t2.start();
	}

}
