package membership;

public class Test {
    int num1;

    // 기본 생성자
    public Test() {}

    // 매개변수 1개를 받는 생성자
    public Test(int num1) {
        this.num1 = num1;
    }
}

class A {
    public static void main(String[] args) {
        Test a1 = new Test();     // 기본 생성자 호출
        Test a2 = new Test(10);   // 매개변수 생성자 호출
    }
}
