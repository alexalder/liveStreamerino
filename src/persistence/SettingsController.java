package persistence;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SettingsController {
	private String path;
	private List<String> streams;
	
	public String getPath() throws IOException {
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream("settings.bin"));
		try {
			path = (String)ois.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		ois.close();
		return path;
	}
	
	public List<String> getStreams() throws IOException {
		streams= new ArrayList<String>();
		String line;

		BufferedReader reader = new BufferedReader(new FileReader("streams.txt"));
		while ((line = reader.readLine()) != null) {
			StringTokenizer tokenizer = new StringTokenizer(line);
			streams.add(tokenizer.nextToken().trim());
		}
		reader.close();
		return streams;
	}
	
	public void scriviPath(String path){
			ObjectOutputStream ois = null;
			try {
				ois = new ObjectOutputStream(new FileOutputStream("settings.bin"));
				ois.writeObject(path);
				ois.close();
			} catch (IOException e) {

			}
		}
	
	public void SaveStreams(String stream){
		PrintWriter pw = null;
		try {
			try {
				pw = new PrintWriter(new FileWriter("streams.txt"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (!(streams.contains(stream)))
				streams.add(stream);
			for (String s : streams)
				pw.println(s);
		}
		finally {
			if (pw != null)
		        pw.close();
		}
	}
}