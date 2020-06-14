package main;

import java.util.Scanner;
import dao.MemberDao;

public class MainClass {

	public static void main(String[] args) {	
		Scanner sc = new Scanner(System.in);
		
		MemberDao dao = new MemberDao();
	
		while(true) {
			
			System.out.println("1. 선수 정보 추가");
			System.out.println("2. 선수 정보 삭제");
			System.out.println("3. 선수 정보 검색");
			System.out.println("4. 선수 정보 수정");
			System.out.println("5. 선수 정보 일괄 출력");		
			System.out.println("6. 데이터를 파일에 저장");
			System.out.println("7. 타율순으로 정렬");
			System.out.println("8. 방어율순으로 정렬");
			System.out.println("9. 프로그램 종료");				
			System.out.print("메뉴 번호 입력 >>> ");
			
			int choice = sc.nextInt();
			
			switch( choice ) {
				case 1:
					dao.insert();
					break;
				case 2:
					dao.delete();
					break;
				case 3:
					dao.select();
					break;
				case 4:
					dao.update();
					break;
				case 5:
					dao.allprint();
					break;					
				case 6:
					dao.Data_save();
					break;
				case 7:
					dao.defence_rank();
					break;
				case 8:
					dao.hitAvg_rank();
					break;
				case 9:
					System.out.println("프로그램을 종료합니다");
					System.exit(0);					
					break;			
			}			
		}				
	}
}