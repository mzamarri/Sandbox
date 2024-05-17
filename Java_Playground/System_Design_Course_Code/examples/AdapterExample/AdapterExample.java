public class AdapterExample {
    public static void main(String[] args) {
        OldCoffeeMachine oldMachine = new OldCoffeeMachine();
        CoffeeTouchscreenAdapter adapter = new CoffeeTouchscreenAdapter(oldMachine);
        ModernCoffeeMachine newMachine = new ModernCoffeeMachine(adapter);

        try {
            System.out.println("Option Chosen: " + args[0]);
            if (args[0].equals("1")) {
                System.out.println("Choosing First Selection (A):");
                newMachine.chooseFirstSelection();
            } else if (args[0].equals("2")) {
                System.out.println("Selecting B:");
                newMachine.chooseSecondSelection();
            } else {
                System.out.println("Please provide an option to choose from: 1 or 2");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Please provide an option to choose from: 1 or 2");
            return;
        }
    }
}

interface CoffeeMachineInterface {
    public void chooseFirstSelection();
    public void chooseSecondSelection();
}

class OldCoffeeMachine {
        public void selectA() {
            System.out.println("A selected");
        }
    
        public void selectB() {
            System.out.println("B selected");
        }
}

class CoffeeTouchscreenAdapter implements CoffeeMachineInterface {
    private OldCoffeeMachine oldMachine;

    public CoffeeTouchscreenAdapter(OldCoffeeMachine oldMachine) {
        this.oldMachine = oldMachine;
    }

    public void chooseFirstSelection() {
        oldMachine.selectA();
    }

    public void chooseSecondSelection() {
        oldMachine.selectB();
    }
}

class ModernCoffeeMachine {
    private CoffeeMachineInterface newMachine;

    public ModernCoffeeMachine(CoffeeMachineInterface newMachine) {
        this.newMachine = newMachine;
    }

    public void chooseFirstSelection() {
        newMachine.chooseFirstSelection();
    }
    public void chooseSecondSelection() {
        newMachine.chooseSecondSelection();
    }
}