import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class App {
	public static void main(String[] args){
		// read all input files into respective arrays
		String[] names = null;
		
		String[] InputPositions = null;
		int[] positions = null;
		
		String[] InputWidths = null;
		int[] widths = null;
		
		
		BufferedReader nameBr = null;
		BufferedReader positionBr = null;
		BufferedReader widthBr = null;
		try {
			
			//Reading name.properties file into a string and then splitting string to get array of elements 
			nameBr = new BufferedReader(new FileReader(new File("name.properties")));
			StringBuilder sb = new StringBuilder();
			String line = null;

			while((line = nameBr.readLine()) != null){
				sb.append(line+",");
			}
			sb.deleteCharAt(sb.length()-1);
			names = sb.toString().split(",");
			
			//Reading position.properties file into a string and then splitting string to get array of elements 
			positionBr = new BufferedReader(new FileReader(new File("position.properties")));
			sb = new StringBuilder();
			line = null;

			while((line = positionBr.readLine()) != null){
				sb.append(line+",");
			}
			sb.deleteCharAt(sb.length()-1);
			InputPositions = sb.toString().split(",");
			positions = new int[InputPositions.length];
			for(int i = 0; i<positions.length; i++){
				positions[i] = Integer.parseInt(InputPositions[i]);
			}
			
			
			//Reading width.properties file into a string and then splitting string to get array of elements 
			widthBr = new BufferedReader(new FileReader(new File("width.properties")));
			sb = new StringBuilder();
			line = null;

			while((line = widthBr.readLine()) != null){
				sb.append(line+",");
			}
			sb.deleteCharAt(sb.length()-1);
			InputWidths = sb.toString().split(",");
			widths = new int[InputWidths.length];
			for(int i = 0; i<widths.length; i++){
				widths[i] = Integer.parseInt(InputWidths[i]);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {

			try {

				if (nameBr != null)
					nameBr.close();
				if (positionBr != null)
					positionBr.close();
				if (widthBr != null)
					widthBr.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}
		
		
		// read fixedwidthfile   {you can read fixedwidth line by line if file is very big, but here i will be loading whole file once}
		ArrayList<String> records = new ArrayList<>();
		
		BufferedReader recordBr = null;
		try {
			recordBr = new BufferedReader(new FileReader(new File("fixedwidthfile.fw")));
			String line = null;
			while((line = recordBr.readLine())!=null){
				records.add(line);
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {

			try {

				if (recordBr != null)
					recordBr.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}
		
		
		
		//Now just get substrings from each record in records ArrayList. We already have substring position and widths.
		
		//Output ArrayList containing Comma Separated Lines
		ArrayList<String> csvRecords = new ArrayList<>();
		
		
		for(int i=0; i<records.size(); i++){
			StringBuilder sb = new StringBuilder();
			for(int j=0; j<positions.length; j++){
				sb.append(records.get(i).substring(positions[j]-1, (positions[j]-1+widths[j])).trim()+",");
			}
			sb.deleteCharAt(sb.length()-1);
			csvRecords.add(sb.toString());
		}
		
		//Now you can use csvRecords for printing or writing to csv file or writing to excel sheet.
		
		//printing heading first
		for(String heading: names){
			System.out.print(heading+",");
		}
		System.out.println();
		
		//printing all records in csvRecords ArrayList
		for(String line : csvRecords){
			System.out.println(line);
		}
		
		
		
		
	}
}
