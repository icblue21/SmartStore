import customer.Customer;
import exception.InputRangeException;
import group.Group;
import group.GroupType;
import group.Parameter;
import menu.CustomerMenu;
import menu.Menu;
import menu.ParameterMenu;
import menu.SummaryMenu;

import java.util.InputMismatchException;

public class Main {

    public static void test() {
        ParameterMenu.groups.add(new Group(GroupType.GENERAL, new Parameter(10, 100000)));
        ParameterMenu.groups.add(new Group(GroupType.VIP, new Parameter(20, 200000)));
        ParameterMenu.groups.add(new Group(GroupType.VVIP, new Parameter(30, 300000)));

        for(int i = 0; i < 20; ++i) {
            CustomerMenu.customers.add(new Customer("" + (char)(97 + i), (char)(97 + i) + "12345", i * 10, i * 100000));
        }

        CustomerMenu.customers.refresh(ParameterMenu.groups);
    }

    public static void main(String[] args){

        ParameterMenu.groups.Init();

        test(); // 테스트용 고객 정보 입력

        while(true) {
            try {
                int choice = Menu.displayMainMenu();
                if (choice == 1) {
                    ParameterMenu.manageParameter();
                } else if (choice == 2) {
                    CustomerMenu.manageCustomerData();
                } else if (choice == 3) {
                    SummaryMenu.manageSummaryMenu();
                } else {
                    if (choice == 4) {
                        System.out.println("\n프로그램 종료.");
                        break;
                    }
                    throw new InputRangeException();
                }
            } catch (InputMismatchException e1) {
                System.out.println("\n입력값이 유효하지 않습니다. 다시 입력해 주세요");
                Menu.scanner.next();
            } catch (InputRangeException e2){
                System.out.println(e2.getMessage());
            }
        }

        Menu.scanner.close();
    }
}
