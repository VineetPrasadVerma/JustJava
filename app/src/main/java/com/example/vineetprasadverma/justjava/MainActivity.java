

package com.example.vineetprasadverma.justjava;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {

        EditText nameOfCustomer = (EditText) findViewById(R.id.name_view);
        String name = nameOfCustomer.getText().toString();

        CheckBox whippedCreamCheckBox= (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        Boolean hasWhippedCream = whippedCreamCheckBox.isChecked();

        CheckBox chocolateCheckBox= (CheckBox) findViewById(R.id.chocolate_checkbox);
        Boolean hasChocolate = chocolateCheckBox.isChecked();

        int price =  calculatePrice(hasWhippedCream, hasChocolate);
        String orderSummary = createOrderSummary(name,price,hasWhippedCream,hasChocolate);
        displayMessage(orderSummary);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT,"Coffee Order from Darvin's Cafe for "+name );
        intent.putExtra(Intent.EXTRA_TEXT, orderSummary);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    /**
     * Calculates the price of the order.
     * @param addChocolate whether user wants chocolate or not
     * @param addWhippedCream whether user wants whippedcream or not
     */
    private int calculatePrice(boolean addWhippedCream, boolean addChocolate) {

        int basePrice = 10;
        if(addWhippedCream){
            basePrice = basePrice + 5 ;
        }
        if(addChocolate){
            basePrice = basePrice + 7;
        }
        return quantity * basePrice;
    }

    /**
     * create summary order
     * @param price of the order
     * @param hasWhippedCream is whether or not the user wants topping or not
     * @param hasChocolate is check whether user wants chocolate or not
     * @return text summary
     */
    private String createOrderSummary(String name, int price, boolean hasWhippedCream, boolean hasChocolate){
        return  name+"\nAdd Whipped Cream ? "+hasWhippedCream+"\nAdd Chocolate ? "+hasChocolate+"\nQuantity : "+quantity+"\nTotal : ₹"+price+"\nThank you!";
    }

    public void increment(View view) {

        if(quantity < 100) {
            quantity = quantity + 1;
        }else{
            Toast.makeText(this, "You can't order more than 100 Coffee",Toast.LENGTH_LONG).show();
        }
        displayQuantity(quantity);
    }

    public void decrement(View view) {
        if(quantity > 1) {
            quantity = quantity - 1;
        }else{
            Toast.makeText(this, "You can't order less than 1 Coffee",Toast.LENGTH_LONG).show();
        }
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