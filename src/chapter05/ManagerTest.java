package chapter05;

/**
 * @Author: tsy
 * @Date: 2020/12/29
 * @Description p161, 5-1 多态演示
 * 一个对象变量，可以指示多种实际类型的现象称为多态。在运行时能够自动的选择适当的方法，称为动态绑定。
 */
public class ManagerTest {
    public static void main(String[] args) {
        Employee[] employees = new Employee[2];

        Manager boss = new Manager("zhangsan", 8000);
        boss.setBonus(19000);

        Employee employee = new Employee("lisi", 10000);

        employees[0] = boss;
        employees[1] = employee;

        for (Employee e : employees) {
            System.out.println(e.getName() + " " + e.getSalary());
        }
    }
}
