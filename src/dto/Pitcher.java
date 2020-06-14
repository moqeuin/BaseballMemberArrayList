package dto;

public class Pitcher extends Human {

	private int win; // 승수
	private int lose;// 패전수
	private double defence;	// 방어율
	
	public Pitcher() {
	}
	// super()을 이용해서 Human의 멤버변수를 추가해서 인스턴스 생성.
	public Pitcher(int number, String name, int age, double height, int win, int lose, double defence) {
		
		super(number, name, age, height);
		this.win = win;
		this.lose = lose;
		this.defence = defence;
	}

	public int getWin() {
		return win;
	}

	public void setWin(int win) {
		this.win = win;
	}

	public int getLose() {
		return lose;
	}

	public void setLose(int lose) {
		this.lose = lose;
	}

	public double getDefence() {
		return defence;
	}

	public void setDefence(double defence) {
		this.defence = defence;
	}

	@Override
	public String toString() {
		return super.toString() + "-" + win + "-" + lose + "-" + defence;
	}	
}