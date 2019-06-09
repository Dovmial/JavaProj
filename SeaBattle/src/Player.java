import java.util.Random;
import java.util.Scanner;

public class Player extends Players{
    Player() {
        ships = new Ship[10];
        name = "Игрок";
        scores = 0;
        Scanner scan = new Scanner(System.in);
        int choose;
        while (true) {
            System.out.print("Расстановка кораблей (1 -автоматически | 2 - вручную): ");
            if (scan.hasNextInt()){
                choose = scan.nextInt();
                if(choose != 2 && choose != 1) {
                    System.out.println("Ошибка. Неверные данные.");
                    continue;
                }
                break;
            }
            else{
                System.out.println("Ошибка. Неверные данные.");
                scan.nextLine();
            }

        }
        if(choose == 2)
            createShipsLocation();
        else
            randomGenerateShips();
    }

    public Point move() {
        Point shoot = new Point();
        System.out.print("Строка ");
        shoot.x = getEnteredNumber();
        System.out.print("Столбец ");
        shoot.y = getEnteredNumber();

        return shoot;
    }

    /**
     * Расстановка кораблей (Ручная)
     */
    private void createShipsLocation() {
        int lenghtShip = 0;
        boolean direct;

        System.out.println("Режим ручной расстановки кораблей");

        Create_New_Ship: for (int i = 0; i < 10; i++) {
            Point startPointShip = new Point();
            switch (i) {
                case 0:
                    lenghtShip = 4;
                    break;
                case 1:
                case 2:
                    lenghtShip = 3;
                    break;
                case 3:
                case 4:
                case 5:
                    lenghtShip = 2;
                    break;
                case 6:
                case 7:
                case 8:
                case 9:
                    lenghtShip = 1;
                    break;
            }
            System.out.println("\nКорабль №" + (i+1));
            System.out.println("Длина корабля " + lenghtShip + ".\nВведите координаты начального отсека корабля.");

            System.out.print("Строка ");
            startPointShip.x = getEnteredNumber();
            System.out.print("Столбец ");
            startPointShip.y = getEnteredNumber();
            direct = getDirect();

            ships[i] = new Ship(lenghtShip, startPointShip, direct);
            if (i > 0) {
                for (int j = 0; j < i; j++) {
                    if (ships[i].isIntersect(ships[j])) {
                        System.out.printf("\nЯчейки уже заняты другими кораблями (j = %s). Попробуйте ввести параметры заново.",j);
                        i--;
                        continue Create_New_Ship; // пересечение есть => новая попытка создания
                    }
                }
            }

        }

    }

    /**
     * запрос координат [1-10]
     */
    private int getEnteredNumber() {
        Scanner scan = new Scanner(System.in);
        int num;
        while (true) {
            System.out.print("№ [1-10]: ");
            if (scan.hasNextInt()) {
                num = scan.nextInt();
                if (num > 10 || num < 1) {
                    System.out.println("Ошибка. Выход за диапазон!");
                    continue;
                }
                return num;
            }
            else {
                System.out.println("\nТребуется число!");
                scan.nextLine();
            }
        }
    }
    private boolean getDirect() {
        int bool;
        Scanner scan = new Scanner(System.in);
        while (true) {
            System.out.print("Ориентация [0 - вертикально; 1 - горизонтально]: ");
            if (scan.hasNextInt()) {
                bool = scan.nextInt();
                if (bool == 0 || bool == 1) {
                    break;
                }
                else
                    System.out.println("Ошибка. Неверное значение!");
            }
        }
        return (bool == 1);
    }

}


