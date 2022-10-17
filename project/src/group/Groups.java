package group;

import customer.Customer;

public class Groups {
    private int count;
    private Group[] groups;

    public Groups(){
        this.groups = new Group[GroupType.values().length];
    }
    public Groups(Group[] groups){
        this.groups = groups;
    }
    public int length(){
        return this.groups.length;
    }

    public Groups(int size){
        this.groups = new Group[size];
    }

    public int getCount() {
        return count;
    }

    public boolean isEmpty(){
        if( count == 0){
            return false;
        }
        return  true;
    }

    public void Init(){

        GroupType[] groupTypes = GroupType.values();
        int groupTypesLength = groupTypes.length;

        for(int i =0; i< groupTypesLength; i++){
            GroupType groupType = groupTypes[i];
            this.groups[i] = new Group(groupType,null);
        }
    }

    public void add(Group group){
        Group grp = this.find(group.getType());

        if( grp != null ){ // 이미 groups에 해당 그룹이 존재하는 경우
            this.edit(group); // 해당 그룹의 분류조건을 새로운 분류조건으로 변경
        } else {
            this.groups[this.count] = group;
            this.count++;
        }
    }

    public Group get(int index){
        return this.groups[index];
    }

    public Group find(GroupType groupType){
        Group[] grp = this.groups;

        for(int i=0; i< grp.length; i++){
            if( grp[i].getType() == groupType ){
                return grp[i];
            }
        }
        return null;
    }

    public void edit(Group group){
        Group grp = this.find(group.getType());
        if( grp != null ){
            grp.setParam(group.getParam());
        }
    }

    public Group findGroupFor(Customer customer){
        if( this.groups != null && customer != null){

            for(int i = groups.length-1; i >= 0; i--){
                Parameter param = this.groups[i].getParam();
                if( param != null && customer.getUseTime() >= param.getMinimumUseTime() && customer.getTotalPayment() >= param.getMinimumTotalPayment()){
                    return this.groups[i];
                }
            }

            return null;
        }
        return null;
    }



}
