package ru.neyasova;

import java.util.*;

public class Ship {
    private ArrayList<String> locationCells;
	private String name;

    /** Записывает координаты 3 своих клеток */
    public void setLocationCells(ArrayList<String> loc){
        locationCells = loc;
    }

    /** Сеттер для имени корабля */
	public void setName(String n){
		name = n;
	}

    /** Проверяет попадание */
    public String checkYourself(String userInput) {
       
        String result = "Мимо";
		
		int index = locationCells.indexOf(userInput);
		
		if(index >= 0){
			locationCells.remove(index);
			
			if(locationCells.isEmpty()){
                result = "Потопил";
				System.out.println(name + " затонул.");
				}else{
				result = "Попал";
			}
		}
		return result;
    }
}