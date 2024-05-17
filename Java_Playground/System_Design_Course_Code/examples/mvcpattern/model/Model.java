package model;

// import java.util.ArrayList;
import view.*;

/* The model package will implement the process of purchasing different gaming objects and the handling of data associtated to it. 
 *  - Model should keep an order list and members archive. NOTE: members archive will be implemented later
    - Model should allow a customer to add and remove items from an order list
    - Model can allow admins to add or remove members from archive. NOTE: Admin level access will be implemented later
    - Model will allow customers to see order list when they are at the checkout phase, and allow the following:
      - purchasing of items
      - editing number of items
      - Canceling order option

    - Checkout will be used when at checkout the items
    - ProductCatalog will be used when looking through the selection of items
*/

public class Model {
    private double totalCredit;
    private View view;

    public Model(double startingCredit) {
        this.totalCredit = startingCredit;
    }

    public double getTotalCredit() {
        return this.totalCredit;
    }

    public void addCredit(double credit) {
        this.totalCredit += credit;
    }

    public void resetCredit() {
        this.totalCredit = 0.00;
    }

    public void refundCredit() {
        System.out.println("Refunding Amount: " + Double.toString(totalCredit));
        resetCredit();
    }
}   