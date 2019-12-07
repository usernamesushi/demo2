package day01;

public class Test {

    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Test() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Test(String name, int age) {
        super();
        this.name = name;
        this.age = age;
    }


    private void play() {
        System.out.println("打篮球");
    }

    private String play2() {
        return "打麻将";
    }

}
