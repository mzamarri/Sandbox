public class TemplateExample {
    public static void main(String[] args) {
        ChickenAlfredoPenne chickenAlfredo = new ChickenAlfredoPenne();
        SpaghettiMeatballPasta spaghetti = new SpaghettiMeatballPasta();

        chickenAlfredo.makeRecipe();
        spaghetti.makeRecipe();
    }
}

abstract class PastaDish {
    public final void makeRecipe() {
        System.out.println("Preparing pasta...");
        boilWater();
        addPasta();
        cookPasta();
        drainPastaWater();
        addProtien();
        addSauce();
        addGarnish();
        System.out.println(getPastaName() + " Ready!\n");
    }

    private void boilWater() {
        System.out.println("Heating Water...");
        System.out.println("Water is boiling");
    } 

    private void cookPasta() {
        System.out.println("Cooking pasta...");
        System.out.println("Finished cooking pasta");
    }

    private void drainPastaWater() {
        System.out.println("Draining water...");
        System.out.println("Finished draining water");
    }

    protected abstract void addPasta();
    protected abstract void addProtien();
    protected abstract void addSauce();
    protected abstract void addGarnish();
    public abstract String getPastaName();
}

class ChickenAlfredoPenne extends PastaDish {
    private String pastaName = "Chicken Alfredo";

    protected void addPasta() {
        System.out.println("Adding Penne Pasta");
    }

    protected void addProtien() {
        System.out.println("Adding some damm chicken");
    }

    protected void addSauce() {
        System.out.println("Adding Alfredo sauce");
    }

    protected void addGarnish() {
        System.out.println("Adding parsley");
    }

    public String getPastaName() {
        return pastaName;
    }
}

class SpaghettiMeatballPasta extends PastaDish {
    private String pastaName = "Spaghetti and Meatball";

    protected void addPasta() {
        System.out.println("Adding Spaghetti");
    }

    protected void addProtien() {
        System.out.println("Adding Meatballs");
    }

    protected void addSauce() {
        System.out.println("Adding Marinara Sauce");
    }

    protected void addGarnish() {
        System.out.println("Adding parmesan");
    }

    public String getPastaName() {
        return pastaName;
    }
}