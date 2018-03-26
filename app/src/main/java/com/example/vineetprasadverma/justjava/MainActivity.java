

package com.example.vineetprasadverma.justjava;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        int price =  calculatePrice();
        displayMessage(createOrderSummary(price));
    }

    /**
     * Calculates the price of the order.
     */
    private int calculatePrice() {
        return quantity * 5;
    }

    /**
     * create summary order
     * @price of the order
     * @return text summary
     */
    private String createOrderSummary(int price){
        return  "Name : Vineet Verma\nQuantity : "+quantity+"\nTotal : ₹"+price+"\nThank you!";
    }

    public void increment(View view) {
        quantity = quantity+1;
        displayQuantity(quantity);
    }

    public void decrement(View view) {
        quantity = quantity-1;
        displayQuantity(quantity);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int numberOfCoffees) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + numberOfCoffees);
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderTextView.setText(message);
    }
}