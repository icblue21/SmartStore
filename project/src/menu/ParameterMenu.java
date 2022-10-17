package menu;

import exception.InputEmptyException;
import exception.InputFormatException;
import exception.InputRangeException;
import group.Group;
import group.GroupType;
import group.Groups;
import group.Parameter;

import java.util.InputMismatchException;

public class ParameterMenu extends Menu{
    public static Groups groups = new Groups();

    public ParameterMenu(){}

    public static void manageParameter(){
        while(true){
            int choice = displayParameterMenu();
            if( choice == 1){
                setParameter();
            }else if( choice == 2){
                viewParameter();
            }else if ( choice == 3){
                updateParameter();
            }else {
                if( choice == 4 ){
                    return;
                }
                System.out.println("잘못된 입력입니다. 다시 입력해주세요");
            }
        }
    }

    public static int displayParameterMenu(){ // 작업 선택 화면
        while(true){
            try{
            System.out.println();
            System.out.println("==============================");
            System.out.println(" 1. 분류기준 설정");
            System.out.println(" 2. 분류기준 보기");
            System.out.println(" 3. 분류기준 수정");
            System.out.println(" 4. 이전으로");
            System.out.println("==============================");
            System.out.print("원하시는 작업을 선택하세요 : ");
            int choice = Integer.parseInt(Menu.scanner.next());
            if (choice >= 1 && choice <= 4) {
                return choice;
            }

            throw new InputRangeException();

            }catch (NumberFormatException e1){
                System.out.println("입력값이 숫자가 아닙니다. 다시 입력해주세요");
            }catch (InputRangeException e2){
                System.out.println(e2.getMessage());
            }
        }
    }

    public static String displayChooseGroup(){ // 그룹 선택 화면
        while (true) {
            try {
                System.out.println();
                System.out.println("** 종료하고 싶으시면 'end'를 입력하세요 **");
                System.out.print("그룹을 선택하세요 (GENERAL, VIP, VVIP) : ");
                String choice = Menu.scanner.next().toUpperCase();
                if (choice == null) {
                    throw new NullPointerException();
                }
                if (choice == "") {
                    throw new InputEmptyException();
                }
                if (choice.equals("END")) {
                    return choice;
                }
                for (int i = 0; i < GroupType.values().length; i++) {
                    if (choice.equals(GroupType.values()[i].toString())) {
                        return choice;
                    }
                }
                throw new InputFormatException();
            } catch (NullPointerException e1) {
                System.out.println("입력값이 NULL 입니다. 다시 입력해주세요");
            } catch (InputEmptyException | InputFormatException e2) {
                System.out.println(e2.getMessage());
            }
        }
    }

    public static void setParameter(){
        while(true){
            String chooseGroup = displayChooseGroup();
            if(chooseGroup.equals("END")){
                return;
            }

            GroupType groupType;
            try{
                groupType = GroupType.valueOf(chooseGroup);
            } catch (IllegalArgumentException e){
                System.out.println("잘못된 입력입니다. 다시 입력해주세요");
                continue;
            }

            Group group = groups.find(groupType);
            if( group != null && group.getParam() != null){
                System.out.println("\n" + chooseGroup +"그룹이 이미 존재합니다.");
                System.out.println("\n" + group);
            } else {

                Parameter param = new Parameter();

                while(true){

                    try{
                        int paramChoice = setParameterMenu();

                        if(paramChoice == 1){
                            setParamMinimumSpentTime(param);
                        }else if(paramChoice == 2){
                            setParamMinimumTotalPayment(param);
                        }else{
                            if(paramChoice == 3){
                                break;
                            }

                            System.out.println("입력값이 유효하지 않습니다. 다시 입력해주세요");
                        }
                    }catch(InputMismatchException e){
                        System.out.println("잘못된 입력입니다. 숫자를 입력해주세요");
                    }
                }

                groups.add(new Group(groupType, param));
                CustomerMenu.customers.refresh(groups);
            }

        }

    }

    public static int setParameterMenu(){

        while(true){
            try{
                System.out.println();
                System.out.println("==============================");
                System.out.println(" 1. 최소 총 이용 시간 설정");
                System.out.println(" 2. 최소 총 결제 금액 설정");
                System.out.println(" 3. 이전으로");
                System.out.println("==============================");
                System.out.print("원하시는 작업을 선택하세요 : ");
                int choice = Integer.parseInt(Menu.scanner.next());
                if (choice >= 1 && choice <= 3) {
                    return choice;
                }
                throw new InputRangeException();
            }catch(NumberFormatException | InputRangeException e1){
                System.out.println(e1.getMessage());
            }
        }
    }

    public static void setParamMinimumSpentTime(Parameter param){
        while(true){
            try{
                System.out.println();
                System.out.print("최소 총 이용 시간을 입력하세요 : ");
                int minimumSpentTime = Integer.parseInt(Menu.scanner.next());
                if( minimumSpentTime < 0 ){
                    throw new InputRangeException();
                }
                param.setMinimumUseTime(minimumSpentTime);
                return;
            }catch(NumberFormatException | InputRangeException e1){
                System.out.println(e1.getMessage());
            }
        }
    }

    public static void setParamMinimumTotalPayment(Parameter param){
        while(true){
            try{
                System.out.println();
                System.out.print("최소 총 결제 금액을 입력하세요 : ");
                int minimumTotalPayment = Integer.parseInt(Menu.scanner.next());
                if( minimumTotalPayment < 0 ){
                    throw new InputRangeException();
                }
                param.setMinimumTotalPayment(minimumTotalPayment);
                return;
            }catch(NumberFormatException | InputRangeException e1){
                System.out.println(e1.getMessage());
            }
        }
    }

    public static void viewParameter(){
        while(true){
            String chooseGroup = displayChooseGroup();
            if(chooseGroup.equals("END")){
                return;
            }

            GroupType groupType;
            try{
                groupType = GroupType.valueOf(chooseGroup);
            } catch (IllegalArgumentException e){
                System.out.println("잘못된 입력입니다. 다시 입력해주세요");
                continue;
            }

            Group group = groups.find(groupType);
            System.out.println();
            System.out.println(group);

        }

    }

    public static void updateParameter(){
        while(true){
            String chooseGroup = displayChooseGroup();
            if(chooseGroup.equals("END")){
                return;
            }

            GroupType groupType;
            try{
                groupType = GroupType.valueOf(chooseGroup);
            } catch (IllegalArgumentException e){
                System.out.println("잘못된 입력입니다. 다시 입력해주세요");
                continue;
            }

            Group group = groups.find(groupType);

            if( group.getParam() == null ){
                System.out.println("분류 기준이 없습니다. 먼저 분류 기준을 설정해주세요 ");
            } else {

                Parameter param = group.getParam();

                while(true){

                    try{
                        int paramChoice = setParameterMenu();

                        if(paramChoice == 1){
                            setParamMinimumSpentTime(param);
                        }else if(paramChoice == 2){
                            setParamMinimumTotalPayment(param);
                        }else{
                            if(paramChoice == 3){
                                break;
                            }

                            System.out.println("입력값이 유효하지 않습니다. 다시 입력해주세요");
                        }
                    }catch(InputMismatchException e){
                        System.out.println("잘못된 입력입니다. 숫자를 입력해주세요");
                    }
                }
                CustomerMenu.customers.refresh(groups);
                System.out.println("\n" + group);
            }
        }
    }
}
