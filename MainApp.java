import java.math.BigDecimal;
import java.util.Date;
import java.time.LocalDateTime;

public class MainApp {
    public static void main(String[] args) {
        // CREATE
            /* NOTES to self:
                Rental Info must exist before payment, customer drives and pays when they return.
                Constraint conflict will occur otherwise.
                Vice versa with deleting.

                Only exception is when database in completely empty.
             */

        /*
            Customers.insertCustomer(1, "Alice", "alice@email.com", 5551234, 5555678); // Insert Five values
            Marinas.insertMarina(01, "Bay Marina", "34758");
            Marinas.insertMarina(2, "Port Marina", "2347");
            Rentals.insertRental(1, 1, 14, 1,  java.sql.Date.valueOf("2025-10-12"));
            Payments.insertPayment(1, 1, LocalDateTime.of(2025, 10, 12, 14, 30, 0), BigDecimal.valueOf(49.00), "Debit Card", "Paid");
            */

        // READ
             System.out.println("\nRecords:");
             Customers.getAllCustomers();
             Marinas.getAllMarinas();
             Rentals.getAllRentals();

        // UPDATE emails

            //Customers.updateCustomers("1", "AliceMoon.1@boatmail.com" ); // Update on ID
            //Marinas.updateMarinaName(1, "Bay Lake Marina");
            //Rentals.updateRentalLocation(1, 2); // make sure the new location id actually exists before updating.
            //Payments.updatePaymentStatus(1,"Unpaid");


        // DELETE

            //Customers.deleteCustomer("1"); // Delete on ID
           //Marinas.deleteMarina(1);
           //Marinas.deleteMarina(2);
            //Rentals.deleteRental(1);
            //Payments.deletePayment(1);


        // READ again, make sure first read calls are uncommented.
            System.out.println("\nAfter Update/Delete:");
            Customers.getAllCustomers();
            Marinas.getAllMarinas();
            Rentals.getAllRentals();
    }
}
