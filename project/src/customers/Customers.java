package customers;

import java.util.Arrays;
import java.util.Objects;

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
        return (Customer[])Arrays.copyOf(this.customers, customerCount);
    }

    public void setCustomers(Customer[] customers) {
        this.customers = customers;
    }

    public void add(Customer customer){
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

    public void edit (int index, Customer customer){
        if( index >= 0 && index < this.SIZE){
            this.customers[index] = customer;
        }
    }

    public boolean isEmpty() {
        return this.count == 0;
    }

    public boolean delete(int index){

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


    @Override
    public String toString() {
        return "Customers{" +
                "count=" + count +
                ", customers=" + Arrays.toString(customers) +
                '}';
    }
}
