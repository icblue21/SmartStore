package menu;

import java.util.Scanner;

public abstract class Menu {
    public static Scanner scanner = new Scanner(System.in);

    public Menu(){}

    public static int displayMainMenu(){ // 작업 분야 선택 화면
        System.out.println();
        System.out.println("==============================");
        System.out.println(" 1. 분류 기준 ");
        System.out.println(" 2. 고객 정보 ");
        System.out.println(" 3. 고객 분류 ");
        System.out.println(" 4. 종료 ");
        System.out.println("==============================");
        System.out.print("원하시는 작업을 선택해주세요 : ");
        return scanner.nextInt();
    }

}
