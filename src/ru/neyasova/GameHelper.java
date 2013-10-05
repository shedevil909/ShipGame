package ru.neyasova;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Готовый класс из книги, стр. 182-183 в русском издании.
 */

public class GameHelper {

	private static final String alphabet = "abcdefgikm";
	private int gridLength = 10;
	private int gridSize = 100;
	private int[] grid = new int[gridSize];
	private int comCount = 0;

    /** Получает ход пользователя */
    public String getUserInput(String prompt){
        String inputLine = null;
        System.out.print(prompt + " ");
        try{
            BufferedReader is = new BufferedReader(new InputStreamReader(System.in));
            inputLine = is.readLine();
            if(inputLine.length() == 0) return null;
        }catch(IOException e){
            System.out.println("IOExeption: " + e);
        }
        return inputLine.toLowerCase();
    }

    /** Расставляет корабли по полю */
	public ArrayList<String> placeShip(int comSize){
		ArrayList<String> alphaCells = new ArrayList<String>();
		String[] alphacoords = new String[comSize];
		String temp = null;
		int[] coords = new int[comSize];
		int attempts = 0;
		boolean success = false;
		int location = 0;
		
		comCount++;
		int incr = 1;
		if((comCount%2) == 1){
			incr = gridLength;
		}
		
		while(!success & attempts++ < 200){
			location = (int) (Math.random() * gridSize);
			//System.out.print(" пробуем " + location);
			int x = 0;
			success = true;
			while(success && x < comSize){
				if(grid[location] == 0){
					coords[x++] = location;
					location += incr;
					if(location >= gridSize){
						success = false;
					}
					if(x>0 && (location%gridLength == 0)){
						success = false;
					}
				}else{
				//System.out.print(" используется " + location);
					success = false;
				}	
			}
		}
		
		int x = 0;
		int row = 0;
		int column = 0;
		//System.out.println("\n");
		while(x < comSize){
			grid[coords[x]] = 1;
			row = (int) (coords[x]/gridLength);
			column = coords[x]%gridLength;
			temp = String.valueOf(alphabet.charAt(column));
			
			alphaCells.add(temp.concat(Integer.toString(row)));
			x++;
			//Показывает координаты расставленных кораблей
            System.out.print(" coord " + x + " = " + alphaCells.get(x-1));
		}
		//Перевод строки после координатов каждого корабля
        System.out.println(" ");
		return alphaCells;
	}
}
