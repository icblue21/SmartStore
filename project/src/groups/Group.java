package groups;

import java.util.Objects;

public class Group {

    private GroupType type;
    private Parameter param;

    public Group(){

    }

    public Group(GroupType type, Parameter param) {
        this.type = type;
        this.param = param;
    }

    public GroupType getType() {
        return type;
    }

    public void setType(GroupType type) {
        this.type = type;
    }

    public Parameter getParam() {
        return param;
    }

    public void setParam(Parameter param) {
        this.param = param;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return type == group.type && Objects.equals(param, group.param);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, param);
    }

    @Override
    public String toString() {
        return "Group{" +
                "type=" + type +
                ", param=" + param +
                '}';
    }
}
