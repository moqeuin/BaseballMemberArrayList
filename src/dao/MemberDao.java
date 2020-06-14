package dao;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

import dto.Batter;
import dto.Human;
import dto.Pitcher;
import file.FileProc;

public class MemberDao {
	
	Scanner sc = new Scanner(System.in);
	
	// List는 ArrayList를 상속시켰으므로  변수로 선언가능.
	private List<Human> list = new ArrayList<>(); 
	private int memberNumber;
	// FileProc클래스의 loadData()와 saveData()를 호출하기 위해서 FileProc 변수 선언.
	private FileProc fp;
	
	public MemberDao() {
		// 이름을 매개변수로 대입해서 FileProc의 생성자에서 경로대로 파일을 생성.
		fp = new FileProc("baseball"); 
		fp.createFile();
		// 인스턴스를 생성할 때 파일의 데이터를 가져옴.
		this.Data_load();
		
		memberNumber = list.get(list.size()-1).getNumber();
		if(memberNumber<2000) {
			memberNumber+=1;
		}
		else {
			memberNumber-=1000;
			memberNumber+=1;
		}				
	}
	
	// insert : 데이터를 먼저 입력 후에 그 데이터를 통해서 인스턴스로 생성하고 리스트에 추가.
	public void insert() {	
		
		// 투수/타자 ?
		System.out.print("투수(1)/타자(2) = ");
		int pos = sc.nextInt();	
		
		// human
		System.out.print("이름 = ");
		String name = sc.next();
		
		System.out.print("나이 = ");
		int age = sc.nextInt();
		
		System.out.print("신장 = ");
		double height = sc.nextDouble();
				
		// 투수	1000 ~ 
		if(pos == 1) {
			// win
			System.out.print("승 = ");
			int win = sc.nextInt();
			
			// lose
			System.out.print("패 = ");
			int lose = sc.nextInt();
			
			// defense
			System.out.print("방어율 = ");
			double defence = sc.nextDouble();
			
			list.add(new Pitcher(memberNumber, name, age, height, win, lose, defence));
			
		}		
		// 타자  2000 ~ 
		else if(pos == 2) {
			
			Batter bat = new Batter();
			
			// 선수 등록 번호
			bat.setNumber(memberNumber + 1000);
			bat.setName(name);
			bat.setAge(age);
			bat.setHeight(height);			
						
			// 타수
			System.out.print("타수 = ");
			int batcount = sc.nextInt();
			bat.setBatcount(batcount);
						
			// 안타수
			System.out.print("안타수 = ");
			int hit = sc.nextInt();
			bat.setHit(hit);
			
			// 타율
			System.out.print("타율 = ");
			double hitAvg = sc.nextDouble();
			bat.setHitAvg(hitAvg);
			
			list.add(bat);
		}		
		memberNumber++;			
	}	
	
	// delete : seacrh()로 이름을 가진 선수의 인덱스번호로 데이터를 삭제.
	public void delete() {
		
		System.out.print("삭제하고 싶은 선수명 입력 = ");
		String name = sc.next();
		
		if(name.equals("")) {
			System.out.println("이름을 정확히 입력해 주십시오.");
			return;		// continue
		}
		
		int findIndex = search(name);
		if(findIndex == -1) {
			System.out.println("선수 명단에 없습니다. 삭제할 수 없습니다");
			return;
		}	
		// 삭제
		list.remove(findIndex);		
	}	
	
	// select : seacrh()로 이름을 가진 선수의 인덱스번호로 데이터를 출력.
	public void select() {		
		
		System.out.print("검색하고 싶은 선수명 = ");
		String name = sc.next();
		
		
		if(name.equals("")) {
			System.out.println("이름을 정확히 입력해 주십시오.");
			return;		
		}
		
		int findIndex = search(name);
		
		if(findIndex == -1) {
			System.out.println("선수 명단에 없습니다.");
		}
		// 찾은 선수의 데이터 출력.
		else {
			
			Human h = list.get(findIndex);
			
			System.out.println("번호:" + h.getNumber());
			System.out.println("이름:" + h.getName());
			System.out.println("나이:" + h.getAge());
			System.out.println("신장:" + h.getHeight());
			
			if(h instanceof Pitcher) {
				System.out.println("승리:" + ((Pitcher)h).getWin());
				System.out.println("패전:" + ((Pitcher)h).getLose());
				System.out.println("방어율:" + ((Pitcher)h).getDefence());
			}
			else if(h instanceof Batter) {
				System.out.println("타수:" + ((Batter)h).getBatcount() );
				System.out.println("안타수:" + ((Batter)h).getHit() );
				System.out.println("타율:" + ((Batter)h).getHitAvg() );
			}
		}		
	}	
	
	// update : 선수의 이름을 통해 선수가 있는 list의 인덱스 번호를 구해서 list데이터를 가져와서 데이터를 수정.
	public void update() {		
		System.out.print("수정하고 싶은 선수명 = ");
		String name = sc.next();
		
		int findIndex = search(name);
		if(findIndex == -1) {
			System.out.println("선수 명단에 없습니다.");
			return;
		}
		
		Human h = list.get(findIndex);
		
		// 인덱스번호로 통해서 가져온 list의 데이터가 Pitcher클래스형인지 Batter클래스형인지 판단해서 데이터를 수정입력.
		if(h instanceof Pitcher) {
			System.out.print("승 = ");
			int win = sc.nextInt();
			
			System.out.print("패 = ");
			int lose = sc.nextInt();
			
			System.out.print("방어율 = ");
			double defence = sc.nextDouble();
			
			Pitcher pit = (Pitcher)h;
			pit.setWin(win);
			pit.setLose(lose);
			pit.setDefence(defence);			
		}
		else if(h instanceof Batter) {
			System.out.print("타수 = ");
			int batcount = sc.nextInt();
			
			System.out.print("안타수 = ");
			int hit = sc.nextInt();
			
			System.out.print("타율 = ");
			double hitAvg = sc.nextDouble();
			
			Batter bat = (Batter)h;
			bat.setBatcount(batcount);
			bat.setHit(hit);
			bat.setHitAvg(hitAvg);			
		}		
	}
		
	// allprint : 순환문을 통해서 list의 데이터를 toString()로 모두 출력.
	public void allprint() {	
		
		for (int i = 0; i < list.size(); i++) {
			Human h = list.get(i);		
			// toString()는 object의 메소드로 모든 클래스에서 오버라이딩이 가능.
			// 그래서 상위클래스, 하위 클래스가 모두 오버라이딩을 했다면 h.toString()은 Pitcher와 Batter의 메소드를 호출.
			System.out.println(h.toString());				
		}
	}
		
	// search :  입력한 이름을 통해서 List에서 인덱스번호를 리턴하고 없으면 -1을 리턴.
	public int search(String name) {
		
		int index = -1;
		
		for (int i = 0; i < list.size(); i++) {		
			if(name.equals(list.get(i).getName())) {
				index = i;
				break;			
			}
		}
		return index;
	}
	
	// Data_save : 저장할 데이터를 배열에 저장하고 리턴하는 역할.
	public void Data_save() {
		
		//한 선수의 데이터를 배열의 하나씩 저장하기 위해서 list의 크기만큼 배열할당.
		String datas[] = new String[list.size()];
			
		for (int i = 0; i < datas.length; i++) {
			Human h = list.get(i);
			// 1001-홍길동-24-178.1-10-3-0.12와 같은 형식으로 데이터를 저장.
			datas[i] = h.toString();
		}	
		fp.File_save(datas);		
	}
	// Data_load : 파일의 데이터를 가진 배열을 리턴 받아서 list의 저장하는 역할.
	public void Data_load() {
		//한 줄씩 저장한 배열을 호출해서 다시 배열에 저장.
		String datas[] = fp.File_load();
		Human human = null;	
		for (int i = 0; i < datas.length; i++) {
			// datas[0 ~ n-1]	
			// datas[0] => 1000-홍길동-24-178.1-10-2-0.12
			// datas[1] => 2001-일지매-21-181.1-21-11-0.34
			
			// "-"토큰 단위로 배열에 한 문자열씩 data[]에 저장.
			String data[] = datas[i].split("-");
			
			int memberNumber = Integer.parseInt(data[0]);
			//선수등록번호를 기준으로 Pitcher인지 Batter인지 판정.
			if(memberNumber < 2000) {		// 투수				
				human = new Pitcher(Integer.parseInt(data[0]), 
									data[1], 
									Integer.parseInt(data[2]), 
									Double.parseDouble(data[3]), 
									Integer.parseInt(data[4]), 
									Integer.parseInt(data[5]), 
									Double.parseDouble(data[6]) );
			}
			else {
				human = new Batter(	Integer.parseInt(data[0]), 
									data[1], 
									Integer.parseInt(data[2]), 
									Double.parseDouble(data[3]), 
									Integer.parseInt(data[4]), 
									Integer.parseInt(data[5]), 
									Double.parseDouble(data[6]) );
			}
			//생성된 인스턴스를 리스트에 추가.
			list.add(human);
		}		
	}
	// defence_rank : 투수의 방어율을 오름차순으로 정렬.
	public void defence_rank(){
		
		ArrayList<Human> sortList = new ArrayList<Human>();
		// 투수데이터만 저장된 리스트를 리턴받음.
		sortList = postionCheck(1);
				
		for (int i = 0; i < sortList.size()-1; i++) {			
			for (int j = i + 1; j < sortList.size(); j++) {
				
				Human tmp = null;
				Pitcher p1 = (Pitcher)sortList.get(i);
				Pitcher p2 = (Pitcher)sortList.get(j);
				// 앞에 인덱스번호(i)의 방어율이 더 큰 경우 뒤에 인덱스번호(j)와 데이터를 바꾸어준다. 
				if(p1.getDefence()>p2.getDefence()) {
					tmp = p1;
					sortList.set(i, p2);
					sortList.set(j, tmp);
				}		
			}	
		}
		//정렬된 데이터 출력.
		for (int i = 0; i < sortList.size(); i++) {
			Human h = sortList.get(i);
			System.out.println(h.toString());
		}		
	}
	
	// hitAvg_rank() : 타자의 타율을 내림차순으로 정렬.
	public void hitAvg_rank(){
		
		ArrayList<Human> sortList = new ArrayList<Human>();
		// 타자데이터만 저장된 리스트를 리턴받음.
		sortList = postionCheck(2);
			
		for (int i = 0; i < sortList.size()-1; i++) {
			for (int j = i + 1; j < sortList.size(); j++) {
				
				Human tmp = null;
				Batter b1 = (Batter)sortList.get(i);
				Batter b2 = (Batter)sortList.get(j);
				// 앞에 인덱스번호(i)의 타율이 더 작은 경우 뒤에 인덱스번호(j)와 데이터를 바꾸어준다. 
				if(b1.getHitAvg()<b2.getHitAvg()) {
					tmp = b1;
					sortList.set(i, b2);
					sortList.set(j, tmp);
				}		
			}	
		}
		//정렬된 데이터 출력.
		for (int i = 0; i < sortList.size(); i++) {
			Human h = sortList.get(i);
			System.out.println(h.toString());
		}		
	}
	
	//  postionCheck : 매개변수가 1일 경우 투수, 매개변수가 2일 경우 타자, 그리고 그 포지션의 데이터만 리스트의 저장.
	public ArrayList<Human> postionCheck(int position) {
		
		//선택한 포지션의 데이터를 저장할 리스트.
		ArrayList<Human> reList = new ArrayList<Human>();
		
		for (int i = 0; i < list.size(); i++) {
			Human h = list.get(i);
			//투수로 선택했을 경우.
			if(position==1) {
				if(h.getNumber()<2000) {
					reList.add(h);
				}
			}
			//타자로 선택했을 경우.
			else {
				if(h.getNumber()>=2000) {
					reList.add(h);
				}
			}
		}
		return reList;
	}
}