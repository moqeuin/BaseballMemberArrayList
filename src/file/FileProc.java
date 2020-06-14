package file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileProc {

	private File file;
	
	// 인스턴스를 생성할 때 문자열데이터를 매개변수로 받아서 경로대로 파일을 생성한다.
	public FileProc(String filename) {
		file = new File("d:\\tmp\\" + filename + ".txt");
	}
	
	// 파일을 생성하는 메소드.
	public void createFile() {
		try {
			if(file.createNewFile()) {
				System.out.println("파일 생성 성공!");
			}else {
				System.out.println("파일 생성 실패");
			}
		} catch (IOException e) {			
			e.printStackTrace();
		}
	}
	
	// File_save() : list의 데이터를 배열에 저장한 후 리턴받아서 파일에 저장. 
	public void File_save(String[] datas) {
		
		try {
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file)));
			
			for (int i = 0; i < datas.length; i++) {
				//받은 배열을 한 개씩 한 줄단위로 파일에 저장.
				pw.println(datas[i]);
			}			
			pw.close();
			
		} catch (IOException e) {			
			e.printStackTrace();
		}		
	}
	
	// File_load() : 파일에서 문자열데이터를 받아서 배열에 저장한 후 리턴해준다.
	public String[] File_load() {
			
			String datas[] = null;
			
			try {
				BufferedReader br = new BufferedReader(new FileReader(file));
			
				int count = 0;
				String str = "";
				
				//파일의 데이터를 한 줄씩 세서 저장할 데이터의 배열 크기를 측정.
				while( (str = br.readLine()) != null ) {
					count++;				
				} 
				br.close();
				
				// datas를 할당.
				datas = new String[count];
						
				// 배열 저장
				int w = 0;
				br = new BufferedReader(new FileReader(file));
				while( (str = br.readLine()) != null ) {
					//한 줄씩 배열에 저장.
					datas[w] = str;
					w++;
				}
				br.close();
				
			} catch (FileNotFoundException e) {		
				e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}	
			return datas;
		}
}