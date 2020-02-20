package com.lc.meq.how2j;

public class Hero {

	public String name;
	public float hp;

	public int damage;

	public void attackHero(Hero h) {
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		h.hp -= damage;
		System.out.println(String.format("%s 正在攻击 %s, %s的血变成了 %.0f%n",name,h.name,h.name,h.hp));
	
		if(h.isDead()) {
			System.out.println(h.name + "死了！");
		}
	}
	
	public boolean isDead() {
		return 0 >= hp ? true : false;
	}
}
