package designMode.structuralMode.decorateMode;

public class Test {
    public static void main(String[] args) {
        Man man = new Man();
        AManDecorator aManDecorator = new AManDecorator();
        BManDecorator bManDecorator = new BManDecorator();
        aManDecorator.setPerson(man);
        bManDecorator.setPerson(man);

        man.eat();
        System.out.println("================");
        aManDecorator.eat();
        System.out.println("================");
        bManDecorator.eat();
    }
}
