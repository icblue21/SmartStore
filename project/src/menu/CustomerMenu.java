package menu;

import customer.Customer;
import customer.Customers;
import exception.InputEmptyException;
import exception.InputFormatException;
import exception.InputRangeException;
import group.Group;

import java.util.regex.Pattern;

public class CustomerMenu extends Menu{

    public static Customers customers = new Customers();

    public CustomerMenu(){}

    public static void manageCustomerData() {
        while (true) {
            try{
                int choice = displayCustomerMenu();
                if (choice == 1) {
                    int size = getSizeToInput();
                    addCustomerData(size);
                } else if (choice == 2) {
                    viewCustomerData();
                } else if (choice == 3) {
                    updateCustomerData();
                } else if (choice == 4) {
                    deleteCustomerData();
                } else {
                    if (choice == 5) {
                        return;
                    }
                    throw new InputRangeException();
                }
            }catch (InputRangeException e){
                System.out.println(e.getMessage());
            }catch (NumberFormatException e2){
                System.out.println("잘못된 입력입니다. 숫자를 입력해주세요");
            }
        }
    }

    public static int displayCustomerMenu(){

        while(true) {
            try {
                System.out.println();
                System.out.println("==============================");
                System.out.println(" 1. 고객 정보 추가");
                System.out.println(" 2. 고객 정보 보기");
                System.out.println(" 3. 고객 정보 업데이트");
                System.out.println(" 4. 고객 정보 삭제");
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

    public static void addCustomerData(int size){

        if( size == 0 ){
            return;
        }

        for( int i=0; i<size; i++){
            Customer customer = new Customer();
            System.out.println("\n==== Customer"+(i+1)+" Info ====");

            while(true){
                while(true){
                    try{
                        int choice = setCustomerDataMenu();
                        if( choice == 1){
                            setCustomerName(customer);
                            break;
                        } else if ( choice == 2){
                            setCustomerId(customer);
                            break;
                        } else if ( choice == 3){
                            setCustomerUseTime(customer);
                            break;
                        } else if( choice == 4) {
                            setCustomerTotalPayment(customer);
                            break;
                        } else {
                            if( choice == 5){
                                customer.setGroup(ParameterMenu.groups.findGroupFor(customer));
                                customers.add(customer);
                                System.out.println();
                                return;
                            }

                            throw new InputRangeException();
                        }
                    }catch (InputRangeException e){
                        System.out.println(e.getMessage());
                    }
                }

                Group grp = ParameterMenu.groups.findGroupFor(customer);
                customer.setGroup(grp);
            }
        }
    }

    public static int getSizeToInput(){

        while(true){
            try{
                System.out.println();
                System.out.println("** 종료하고 싶으시면 '0'을 입력해주세요 **");
                System.out.print("몇 개의 고객정보를 추가하시겠습니까? : ");
                int size = Integer.parseInt(scanner.next());

                if( size < 0){
                    throw new InputRangeException();
                }
                return size;

            }catch(InputRangeException | InputFormatException e){
                System.out.println(e.getMessage());
            }
        }
    }

    public static int setCustomerDataMenu(){

        while(true){
            try{
                System.out.println();
                System.out.println("==============================");
                System.out.println(" 1. 이름 설정");
                System.out.println(" 2. 아이디 설정");
                System.out.println(" 3. 총 이용 시간 설정");
                System.out.println(" 4. 총 결제 금액 설정");
                System.out.println(" 5. 이전으로");
                System.out.println("==============================");
                System.out.print("원하시는 작업을 선택하세요 : ");
                int choice = Integer.parseInt(Menu.scanner.next());
                if (choice >= 1 && choice <= 5) {
                    return choice;
                }
                throw new InputRangeException();
            }catch (InputRangeException | InputFormatException e){
                System.out.println(e.getMessage());
            }
        }
    }

    public static void setCustomerName(Customer customer){

        while(true){
            try{
                System.out.println();
                System.out.print("이름을 입력하세요 : ");
                String name = scanner.next();
                String REGEX ="^[a-zA-z]{3,}$";

                if(name != null && !name.equals("")){
                    if(Pattern.matches(REGEX, name)){
                        customer.setName(name);
                        return;
                    }
                    throw new InputFormatException();
                }
                throw new InputEmptyException();
            }catch (InputFormatException e1){
                System.out.println("이름 형식이 유효하지 않습니다. 영문자로 3글자 이상 입력하세요");
            }catch (InputEmptyException e2){
                System.out.println(e2.getMessage());
            }
        }
    }

    public static void setCustomerId(Customer customer){
        while(true){
            try {
                System.out.println();
                System.out.print("아이디를 입력하세요 : ");
                String id = scanner.next();
                String REGEX = "^[a-zA-z]{1}[a-zA-Z0-9]{4,11}$";

                if (id != null && !id.equals("")) {
                    if (Pattern.matches(REGEX, id)) {
                        customer.setId(id);
                        return;
                    }
                    throw new InputFormatException();
                }
                throw new InputEmptyException();
            }catch (InputFormatException e1){
                System.out.println("아이디 형식이 유효하지 않습니다. 영문자1개 + 영문자 혹은 숫자 4~11개로 입력해주세요 ");
            }catch (InputEmptyException e2){
                System.out.println(e2.getMessage());
            }
        }
    }

    public static void setCustomerUseTime(Customer customer){
        while(true){
            try{
                System.out.println();
                System.out.print("총 이용 시간을 입력하세요 : ");
                int useTime = Integer.parseInt(scanner.next());

                if(useTime < 0){
                    throw new InputRangeException();
                }
                customer.setUseTime(useTime);
                return;
            }catch (NumberFormatException e1 ){
                System.out.println("입력값이 숫자가 아닙니다. 숫자를 입력해주세요 ");
            }catch (InputRangeException e2){
                System.out.println(e2.getMessage());
            }
        }
    }

    public static void setCustomerTotalPayment(Customer customer){
        while(true){
            try{
                System.out.println();
                System.out.print("총 결제 금액을 입력하세요 : ");
                int totalPayment = Integer.parseInt(scanner.next());

                if( totalPayment < 0){
                    throw new InputRangeException();
                }
                customer.setTotalPayment(totalPayment);
                return;
            }catch ( NumberFormatException e1){
                System.out.println("입력값이 숫자가 아닙니다. 숫자를 입력해주세요");
            }catch ( InputRangeException e2){
                System.out.println(e2.getMessage());
            }
        }
    }

    public static void viewCustomerData(){

        int count = customers.getCount();
        if( count == 0){
            System.out.println("고객 정보가 없습니다. 먼저 고객을 추가해주세요");
            return;
        }
        System.out.println("\n==== Customer Info ====");
        for(int i =0 ; i< customers.getCount(); i++){
            Customer cust = customers.get(i);
            if( cust != null ){
                System.out.println("No."+(i+1)+" -> "+ cust);
            }
        }
    }

    public static void updateCustomerData(){

        int customCount = customers.getCount();
        if( customCount == 0 ){
            System.out.println("등록된 고객이 없습니다. 먼저 고객을 추가해주세요 ");
            return;
        }
        viewCustomerData();
        int target = findCustomer(customCount);
        if( target > 0 && target <= customCount){

            Customer customer = customers.get(target-1);
            while(true){
                while(true){
                    try{
                        int choice = setCustomerDataMenu();
                        if( choice == 1){
                            setCustomerName(customer);
                            break;
                        } else if ( choice == 2){
                            setCustomerId(customer);
                            break;
                        } else if ( choice == 3){
                            setCustomerUseTime(customer);
                            break;
                        } else if( choice == 4) {
                            setCustomerTotalPayment(customer);
                            break;
                        } else {
                            if( choice == 5) return;
                            throw new InputRangeException();
                        }
                    }catch ( InputFormatException | InputRangeException e){
                        System.out.println(e.getMessage());
                    }
                }
                Group grp = ParameterMenu.groups.findGroupFor(customer);
                customer.setGroup(grp);
            }
        } else {
            System.out.println("고객 번호가 잘못 입력되었습니다.");
        }
    }

    public static int findCustomer(int customCount){

        while(true){
            try{
                System.out.println();
                System.out.print("몇 번째 고객의 정보를 업데이트 하시겠습니까? ( 1 ~ "+customCount+" )");
                int targetIndex = Integer.parseInt(scanner.next());
                return targetIndex;
            }catch( NumberFormatException e1){
                System.out.println("입력값이 숫자가 아닙니다. 숫자를 입력해주세요");
            }
        }
    }

    public static void deleteCustomerData(){
        int customCount = customers.getCount();
        if( customCount == 0 ){
            System.out.println("등록된 고객이 없습니다. 먼저 고객을 추가해주세요 ");
            return;
        }
        viewCustomerData();
        int target = findCustomer(customCount);
        if( target > 0 && target <= customCount ){

            Customer customer = customers.get(target-1);
            boolean res = customers.delete(target-1);
            if( res ){
                System.out.println("고객의 데이터를 성공적으로 삭제했습니다.");
            }else {
                System.out.println("고객의 데이터를 삭제할 수 없습니다.");
            }
            viewCustomerData();
        } else {
            System.out.println("고객 번호가 잘못 입력되었습니다.");
        }
    }
}
