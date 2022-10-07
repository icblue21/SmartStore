package customers;

import groups.Group;

import java.util.Objects;

public class Customer implements Comparable<Customer> {

    private String SerialNumber;
    private String name;
    private String id;
    private int useTime;
    private int totalPayment;
    private Group group;
    private static int customerCount = 0 ;

    public Customer(){
        SerialNumber = String.format("%04d",++customerCount);

    }

    public Customer(String name, String id, int useTime, int totalPayment){
        this.name = name;
        this.id = id;
        this.useTime = useTime;
        this.totalPayment = totalPayment;
        SerialNumber = String.format("%04d",++customerCount);
    }

    public String getSerialNumber() {
        return SerialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        SerialNumber = serialNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getUseTime() {
        return useTime;
    }

    public void setUseTime(int useTime) {
        this.useTime = useTime;
    }

    public int getTotalPayment() {
        return totalPayment;
    }

    public void setTotalPayment(int totalPayment) {
        this.totalPayment = totalPayment;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return useTime == customer.useTime && totalPayment == customer.totalPayment && SerialNumber.equals(customer.SerialNumber) && name.equals(customer.name) && id.equals(customer.id) && group.equals(customer.group);
    }

    @Override
    public int hashCode() {
        return Objects.hash(SerialNumber, name, id, useTime, totalPayment, group);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "SerialNumber='" + SerialNumber + '\'' +
                ", name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", useTime=" + useTime +
                ", totalPayment=" + totalPayment +
                ", group=" + group +
                '}';
    }

    @Override
    public int compareTo(Customer o) {
        if( this.name != null && o.name != null){ // 이름 순 정렬
            if( this.name.compareTo(o.name) < 0 ) return -1;
            else if( this.name.compareTo(o.name) == 0) return 0;
            else return 1;
        } else {
            return 0;
        }
    }
}
