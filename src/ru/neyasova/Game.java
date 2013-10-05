package ru.neyasova;

import java.util.*;

public class Game{

	public static void main(String[] args){
        Game game = new Game();
		game.setUpGame();
		game.startPlaying();
	}
		private GameHelper helper = new GameHelper();
		private ArrayList<Ship> shipList = new ArrayList<Ship>();
		private int numOfGuesses = 0;

        /** Создает корабли, дает им имена и помещает в список shipList. */
    	private void setUpGame(){
            Ship one = new Ship();
			one.setName("Баржа");
            Ship two = new Ship();
			two.setName("Миноносец");
            Ship three = new Ship();
			three.setName("Судно");
            Ship four = new Ship();
            four.setName("Пароход");
            Ship five = new Ship();
            five.setName("Лодка");
            Ship six = new Ship();
            six.setName("Плот");
            Ship seven = new Ship();
            seven.setName("Бумажный кораблик");

            shipList.add(one);
            shipList.add(two);
            shipList.add(three);
            shipList.add(four);
            shipList.add(five);
            shipList.add(six);
            shipList.add(seven);

			System.out.println("Потопите восемь разных плав.средств за минимальное количество выстрелов.");
            System.out.println("Используйте координаты a,b,c,d,e,f,g,i,k,m.");

            /** Создает координаты с помощье метода placeShip из класса GameHelper. */
			for(Ship shipToSet : shipList){
				ArrayList<String> newLocation = helper.placeShip(3);
                shipToSet.setLocationCells(newLocation);
			}
		}
        /** Принимает ход пользователя и передает его методу checkUserGuess.
         * Заканчивает игру вызовом метода finishGame. */
		private void startPlaying(){
			while(!shipList.isEmpty()){
				String userGuess = helper.getUserInput("Ваш выстрел: ");
				checkUserGuess(userGuess);
			}
			finishGame();
		}

        /** Увеличивает кол-во ходов.
         * Отправляет ход в метод checkYourself класса Ship, где он проверяется на попадание.
         * Сравнивает вернувшееся значение и удаляет подбитую клетку или выводит слово "Мимо" */
		private void checkUserGuess(String userGuess){
			numOfGuesses++;
			String result = "Мимо";
			
			for(Ship shipToTest : shipList){
				result = shipToTest.checkYourself(userGuess);
				if(result.equals("Попал")){
					break;
				}
				if(result.equals("Потопил")){
                    shipList.remove(shipToTest);
					break;
				}
			}
			System.out.println(result);
		}

        /** Выводит результаты игры. */
		private void finishGame(){
			System.out.println("Вы потопили все корабли!");
			if(numOfGuesses <= 24){
				System.out.println("Отличный результат! Потребовалось всего " + numOfGuesses + " выстрелов.");
			}else{
				System.out.println("Моя бабушка и то лучше стреляет! Потребовалось аж " + numOfGuesses + " выстрелов.");
			}
		}
}