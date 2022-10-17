package group;

public enum GroupType {
    NONE("비회원"),
    GENERAL("일반회원"),
    VIP("우수고객"),
    VVIP("최우수고객");

    String groupType = "";


    GroupType(String groupType) {
        this.groupType = groupType;
    }

    public String getGroupType() {
        return groupType;
    }

    public void setGroupType(String groupType) {
        this.groupType = groupType;
    }
}
