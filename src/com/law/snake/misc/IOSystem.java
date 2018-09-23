package com.law.snake.misc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class IOSystem {

	public static final String path = ".\\snake.hs";
	
	public static void Save(Object players) {
		File save = new File(path);	
		try {
			FileOutputStream out = new FileOutputStream(save);
			ObjectOutputStream objout = new ObjectOutputStream(out);
			objout.writeObject(players);
			objout.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}
	
	public static Object Load() {
		Object obj = null;
		File save = new File(path);
		try {
			FileInputStream in = new FileInputStream(save);
			ObjectInputStream objin = new ObjectInputStream(in);
			obj = objin.readObject();
			objin.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return obj;
	}
	
	public static void createHSFile() {
		File save = new File(path);
		if(save.exists())
			return;
		else{
			HighscorePlayer[] players = new HighscorePlayer[5];
			players[0] = new HighscorePlayer(-1, "");
			players[1] = new HighscorePlayer(-1, "");
			players[2] = new HighscorePlayer(-1, "");
			players[3] = new HighscorePlayer(-1, "");
			players[4] = new HighscorePlayer(-1, "");
			
			Save(players);
		}
	}
	
	

}
