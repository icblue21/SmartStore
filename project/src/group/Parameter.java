package group;

import java.util.Objects;

public class Parameter {

    private int minimumUseTime;
    private int minimumTotalPayment;

    public Parameter(){}

    public Parameter(int minimumUseTime, int minimumTotalPayment) {
        this.minimumUseTime = minimumUseTime;
        this.minimumTotalPayment = minimumTotalPayment;
    }

    public int getMinimumUseTime() {
        return minimumUseTime;
    }

    public void setMinimumUseTime(int minimumUseTime) {
        this.minimumUseTime = minimumUseTime;
    }

    public int getMinimumTotalPayment() {
        return minimumTotalPayment;
    }

    public void setMinimumTotalPayment(int minimumTotalPayment) {
        this.minimumTotalPayment = minimumTotalPayment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Parameter parameter = (Parameter) o;
        return minimumUseTime == parameter.minimumUseTime && minimumTotalPayment == parameter.minimumTotalPayment;
    }

    @Override
    public int hashCode() {
        return Objects.hash(minimumUseTime, minimumTotalPayment);
    }

    @Override
    public String toString() {
        return "Parameter{" +
                "minimumUseTime=" + minimumUseTime +
                ", minimumTotalPayment=" + minimumTotalPayment +
                '}';
    }
}
