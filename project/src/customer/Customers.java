package customer;

import group.Group;
import group.GroupType;
import group.Groups;

import java.util.Arrays;

public class Customers {

    public static int SIZE = 10;
    private int count;
    private Customer[] customers;

    public Customers(){
        customers = new Customer[SIZE];
    }

    public int getCount() {
        return count;
    }

    public Customer[] getCustomers() {
        int customerCount = 0;

        for(int i =0; i<this.customers.length; i++){
            if( this.customers[i] == null ) break;
            customerCount++;
        }
        return (Customer[]) Arrays.copyOf(this.customers, customerCount);
    }

    public void setCustomers(Customer[] customers) {
        this.customers = customers;
    }

    public void add(Customer customer){ // 고객 정보 추가
        if( this.count < SIZE ){
            this.customers[count] = customer;
            this.count++;
        } else {
            this.extend(customer);
        }

    }

    public void extend(Customer customer){

        Customer[] temp = (Customer[])Arrays.copyOf(this.customers, this.customers.length);
        SIZE *= 2;

        customers = new Customer[SIZE];

        for(int i=0; i< temp.length; i++){
            customers[i] = temp[i];
        }
        this.count = temp.length;
        this.add(customer);
    }

    public void edit (int index, Customer customer){ // 고객 정보 설정
        if( index >= 0 && index < SIZE){
            this.customers[index] = customer;
        }
    }

    public boolean isEmpty() {
        return this.count == 0;
    }

    public boolean delete(int index){ // 고객 정보 삭제

        if (this.count == 0) {
            return false;
        } else if ( index >= 0 && index < SIZE ){

            customers[index] = null;
            for(int i = index+1; i < this.count; i++){
                customers[i-1] = customers[i];
            }
            this.count--;
            return true;
        } else {
            return false;
        }
    }

    public Customer get(int index){
        if( index >= 0 && index < this.count ){
            return this.customers[index];
        }
        else
            return null;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for(int i =0; i< this.count; i++){
            sb.append(this.customers[i].toString());
            sb.append("\n");
        }

        return sb.toString();
    }

    public Customers findAllCustomers(GroupType type){ // 해당 그룹에 속한 모든 고객들을 반환
        Customers customs = new Customers();

        for(int i = 0; i < this.count; i++){
            Customer cus = this.get(i);
            if( cus != null ){
                Group group = cus.getGroup();
                if( type.equals(GroupType.NONE)){ // 비회원인 경우
                    if(group == null || group.getType() == null || group.getType().equals(GroupType.NONE)){
                        customs.add(cus);
                    }
                } else if ( group != null && group.getType().equals(type)){
                    customs.add(cus);
                }
            }
        }
        return customs;
    }

    public Customers findAllCustomers(Group grp) {
        if (grp != null) {
            if (grp.getType() != null) {
                return this.findAllCustomers(grp.getType());
            } else {
                System.out.println("Customers.findAllCustomers Error : No group type.");
                return null;
            }
        } else {
            System.out.println("Customers.findAllCustomers Error : No group.");
            return null;
        }
    }

    public void refresh(Groups groups){
        if( groups != null ){

            for(int i =0; i<this.count; i++){
                Customer customer = this.customers[i];
                customer.setGroup(groups.findGroupFor(customer));
            }
        }
    }
}
