package menu;

import customer.Customer;
import customer.Customers;
import exception.InputEmptyException;
import exception.InputFormatException;
import exception.InputRangeException;
import group.Group;
import group.GroupType;

import java.util.Arrays;
import java.util.Comparator;

public class SummaryMenu extends Menu {

    public SummaryMenu() {
    }

    public static void manageSummaryMenu() {
        while (true) {
            try {
                int choice = displaySummaryMenu();
                if (choice == 1) {
                    displaySummary(loadCustomerData());
                } else if (choice == 2) {
                    displaySummaryByName();
                } else if (choice == 3) {
                    displaySummaryByUseTime();
                } else if (choice == 4) {
                    displaySummaryByTotalPayment();
                } else {
                    if (choice == 5) {
                        return;
                    }
                    throw new InputRangeException();
                }
            } catch (NumberFormatException e1) {
                System.out.println("잘못된 입력입니다. 숫자를 입력해주세요");
            } catch (InputRangeException e2) {
                System.out.println(e2.getMessage());
            }
        }
    }

    public static int displaySummaryMenu() {

        while (true) {
            try {
                System.out.println();
                System.out.println("==============================");
                System.out.println(" 1. 등급 별 고객 분류 ");
                System.out.println(" 2. 등급 별 고객 분류 (이름순)");
                System.out.println(" 3. 등급 별 고객 분류 (총 이용 시간 순)");
                System.out.println(" 4. 등급 별 고객 분류 (총 결제 금액 순)");
                System.out.println(" 5. 이전으로");
                System.out.println("==============================");
                System.out.print("원하시는 작업을 선택하세요 : ");
                int choice = Integer.parseInt(Menu.scanner.next());
                if (choice >= 1 && choice <= 5) {
                    return choice;
                } else {
                    throw new InputRangeException();
                }
            } catch (InputRangeException e1) {
                System.out.println(e1.getMessage());
            }
        }
    }

    public static Customers[] loadCustomerData() {
        Customers[] customersByGroup = new Customers[GroupType.values().length]; // 4가지 그룹별 고객들을 저장할 배열

        for (int i = 0; i < ParameterMenu.groups.length(); i++) {
            Group grp = ParameterMenu.groups.get(i);
            customersByGroup[i] = grp.getCustomers(CustomerMenu.customers);
        }

        return customersByGroup;
    }

    public static void displaySummary(Customers[] customersByGroup) {

        for (int i = 0; i < ParameterMenu.groups.length(); i++) {
            Group grp = ParameterMenu.groups.get(i);

            int customerCount = 0;
            if (customersByGroup[i] != null && !customersByGroup[i].isEmpty()) {
                customerCount = customersByGroup[i].getCount();
            }

            System.out.println("==============================");
            if (!grp.getType().equals(GroupType.NONE)) {
                String groupType = grp.getType().toString();
                System.out.println(groupType + "그룹 : " + customerCount + "명");

                if (grp.getParam() == null) {
                    System.out.println("분류 기준 : 없음");
                } else {
                    System.out.println("분류 기준 : " + grp.getParam().toString());
                }
            } else {
                System.out.println("비회원 : " + customerCount + "명");
            }
            System.out.println("==============================");
            if (customersByGroup[i] != null && !customersByGroup[i].isEmpty()) {

                for (int j = 0; j < customerCount; j++) {
                    Customer custom = customersByGroup[i].get(j);

                    System.out.println("No. " + (j + 1) + " -> " + custom);
                }
            } else {
                System.out.println("고객 정보가 존재하지 않습니다.");
            }
        }
    }

    public static void displaySummaryByName() {

        while (true) {
            String orderTypeStr = choiceOrderType().toUpperCase();
            if (orderTypeStr.equals("END")) {
                return;
            }
            if (orderTypeStr.equals("오름차순")) {
                orderTypeStr = "ASC";
            } else if (orderTypeStr.equals("내림차순")) {
                orderTypeStr = "DESC";
            }

            try {
                OrderType orderType = OrderType.valueOf(orderTypeStr);
                if (orderType == OrderType.ASC) {
                    System.out.println("오름차순");
                    sortByName(OrderType.ASC);
                } else if (orderType == OrderType.DESC) {
                    System.out.println("내림차순");
                    sortByName(OrderType.DESC);
                } else {
                    throw new IllegalArgumentException();
                }
            } catch (IllegalArgumentException e1) {
                System.out.println("유효하지 않은 입력 형식입니다. 다시 입력해주세요");
            }
        }
    }

    public static void displaySummaryByUseTime() {

        while (true) {
            String orderTypeStr = choiceOrderType().toUpperCase();
            if (orderTypeStr.equals("END")) {
                return;
            }
            if (orderTypeStr.equals("오름차순")) {
                orderTypeStr = "ASC";
            } else if (orderTypeStr.equals("내림차순")) {
                orderTypeStr = "DESC";
            }

            try {
                OrderType orderType = OrderType.valueOf(orderTypeStr);
                if (orderType == OrderType.ASC) {
                    System.out.println("오름차순");
                    sortByUseTime(OrderType.ASC);
                } else if (orderType == OrderType.DESC) {
                    System.out.println("내림차순");
                    sortByUseTime(OrderType.DESC);
                } else {
                    throw new IllegalArgumentException();
                }
            } catch (IllegalArgumentException e1) {
                System.out.println("유효하지 않은 입력 형식입니다. 다시 입력해주세요");
            }
        }
    }

    public static void displaySummaryByTotalPayment() {

        while (true) {
            String orderTypeStr = choiceOrderType().toUpperCase();
            if (orderTypeStr.equals("END")) {
                return;
            }
            if (orderTypeStr.equals("오름차순")) {
                orderTypeStr = "ASC";
            } else if (orderTypeStr.equals("내림차순")) {
                orderTypeStr = "DESC";
            }

            try {
                OrderType orderType = OrderType.valueOf(orderTypeStr);
                if (orderType == OrderType.ASC) {
                    System.out.println("오름차순");
                    sortByTotalPayment(OrderType.ASC);
                } else if (orderType == OrderType.DESC) {
                    System.out.println("내림차순");
                    sortByTotalPayment(OrderType.DESC);
                } else {
                    throw new IllegalArgumentException();
                }
            } catch (IllegalArgumentException e1) {
                System.out.println("유효하지 않은 입력 형식입니다. 다시 입력해주세요");
            }
        }
    }

    public static void sortByName(OrderType orderType){
        Customers[] customersByGroup = loadCustomerData();

        if( orderType != null && !orderType.equals("")){
            for(int i=0; i< customersByGroup.length; i++){
                Customer[] customers = customersByGroup[i].getCustomers();
                if( orderType == OrderType.ASC){
                    Arrays.sort(customers);
                } else{
                    Arrays.sort(customers, new Comparator<Customer>() {
                        @Override
                        public int compare(Customer o1, Customer o2) {
                            return o1.compareTo(o2) * -1;
                        }
                    });
                }
                customersByGroup[i].setCustomers(customers);
            }
            displaySummary(customersByGroup);
        }
    }

    public static void sortByUseTime(OrderType orderType){
        Customers[] customersByGroup = loadCustomerData();

        if( orderType != null && !orderType.equals("")){
            for(int i=0; i< customersByGroup.length; i++){
                Customer[] customers = customersByGroup[i].getCustomers();
                if( orderType == OrderType.ASC){
                    Arrays.sort(customers, new Comparator<Customer>() {
                        @Override
                        public int compare(Customer o1, Customer o2) {
                            if( o1.getUseTime() > o2.getUseTime()) {
                                return 1;
                            } else if ( o1.getUseTime() == o2.getUseTime()) {
                                return o1.getName().compareTo(o2.getName());
                            } else {
                                return -1;
                            }
                        }
                    });
                } else{
                    Arrays.sort(customers, new Comparator<Customer>(){
                        @Override
                        public int compare(Customer o1, Customer o2){
                            if( o1.getUseTime() > o2.getUseTime()) {
                                return -1;
                            } else if ( o1.getUseTime() == o2.getUseTime()) {
                                return o1.getName().compareTo(o2.getName());
                            } else {
                                return 1;
                            }
                        }
                    });
                }
                customersByGroup[i].setCustomers(customers);
            }
            displaySummary(customersByGroup);
        }
    }

    public static void sortByTotalPayment(OrderType orderType){
        Customers[] customersByGroup = loadCustomerData();

        if( orderType != null && !orderType.equals("")){
            for(int i=0; i< customersByGroup.length; i++){
                Customer[] customers = customersByGroup[i].getCustomers();
                if( orderType == OrderType.ASC){
                    Arrays.sort(customers, new Comparator<Customer>() {
                        @Override
                        public int compare(Customer o1, Customer o2) {
                            if(o1.getTotalPayment() > o2.getTotalPayment()){
                                return 1;
                            } else if ( o1.getTotalPayment() == o2.getTotalPayment()){
                                return o1.getName().compareTo(o2.getName());
                            } else {
                                return -1;
                            }
                        }
                    });
                } else{
                    Arrays.sort(customers, new Comparator<Customer>() {
                        @Override
                        public int compare(Customer o1, Customer o2) {
                            if(o1.getTotalPayment() > o2.getTotalPayment()){
                                return -1;
                            } else if ( o1.getTotalPayment() == o2.getTotalPayment()){
                                return o1.getName().compareTo(o2.getName());
                            } else {
                                return 1;
                            }
                        }
                    });
                }
                customersByGroup[i].setCustomers(customers);
            }
            displaySummary(customersByGroup);
        }
    }

    public static String choiceOrderType() {
        while (true) {
            try {
                System.out.println();
                System.out.println("** 종료하고 싶으시면 'end'를 입력하세요 **");
                System.out.print("정렬 방식을 선택하세요 (ASC(오름차순), DESC(내림차순)) : ");
                String choice = Menu.scanner.next().toUpperCase();
                if( choice.equals("오름차순")) {
                    choice = "ASC";
                } else if ( choice.equals("내림차순")){
                    choice = "DESC";
                }
                if (choice == null) {
                    throw new NullPointerException();
                }
                if (choice == "") {
                    throw new InputEmptyException();
                }
                if (choice.equals("END")) {
                    return choice;
                }

                try {
                    OrderType orderType = OrderType.valueOf(choice);

                    for (int i = 0; i < OrderType.values().length; i++) {
                        if (orderType == OrderType.values()[i]) {
                            return choice;
                        }
                    }
                } catch (IllegalArgumentException e1) {
                    System.out.println("유효하지 않은 입력 형식입니다. 다시 입력해주세요");
                }
            } catch (NullPointerException e2) {
                System.out.println("입력값이 NULL 입니다. 다시 입력해주세요");
            } catch (InputEmptyException | InputFormatException e3) {
                System.out.println(e3.getMessage());
            }
        }
    }
}
