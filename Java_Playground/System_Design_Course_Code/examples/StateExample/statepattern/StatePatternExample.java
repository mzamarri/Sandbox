import java.util.HashMap;

/* This java application show an example of a Vending Machine object
 */

public class StatePatternExample {
    public static void main(String[] args) {
        Item xbox = new Item("a-01", "Xbox", 500.00);
        Item ps = new Item("b-01", "Play Station", 400.00);

        VendingMachine machine = new VendingMachine();
        machine.addStock("a1", xbox, 3);
        machine.addStock("a2", ps, 2);
        machine.addMoney(1400);
        machine.dispense("a1");
        machine.dispense("a2");
        machine.dispense("a1");
        machine.dispense("a1");
        machine.addMoney(800);
        machine.dispense("a1");
        machine.dispense("a2");
        machine.dispense("a2");
        machine.dispense("a1");
    } 
}
