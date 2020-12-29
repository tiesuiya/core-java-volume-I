package chapter06;

/**
 * @Author: tsy
 * @Date: 2020/12/29
 * @Description
 */
public class Manager extends Employee {

    private double bonus;

    public Manager(String name, double salary) {
        super(name, salary);
    }

    @Override
    public double getSalary() {
        return super.getSalary() + getBonus();
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    @Override
    public String toString() {
        return "Manager{" +
                super.toString()+
                "bonus=" + bonus +
                '}';
    }
}
