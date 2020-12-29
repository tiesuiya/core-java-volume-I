package chapter06;

/**
 * @Author: tsy
 * @Date: 2020/12/29
 * @Description p161, 6-4 克隆
 */
public class CloneTest {
    public static void main(String[] args) throws CloneNotSupportedException {
        Manager boss = new Manager("zhangsan", 8000);
        boss.setBonus(19000);
        // 需要类自己实现
        // Manager boss1 = boss.clone();

        Employee employee = new Employee("lisi", 10000);
        Employee employee1 = employee.clone();
        employee1.setName("李四");// 不会改变原对象

        Employee[] employees = new Employee[2];
        employees[0] = boss;
        employees[1] = employee;
        for (Employee e : employees) {
            System.out.println(e);
        }

    }
}
