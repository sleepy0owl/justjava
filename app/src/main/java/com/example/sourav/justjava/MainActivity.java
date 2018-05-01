package com.example.sourav.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    int quantity = 0;
    /**
     * the quantity variable
     */
    int price = 5; /** price as variable */
    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {

        //for edit text field
        EditText nameField = (EditText) findViewById(R.id.name_field);
        String nameValue = nameField.getText().toString();

        //for whipped cream checkbox
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whippedCream_check_box);
        boolean addWhippedCream = whippedCreamCheckBox.isChecked();

        //for chocolate
        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_check_box);
        boolean addChocolate = chocolateCheckBox.isChecked();

        int priceDisplay = calculatePrice(addWhippedCream, addChocolate);
        String priceMessage = orderSummary(priceDisplay, addWhippedCream, addChocolate, nameValue);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT, "JustJava coffee orders");
        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }


    }

    /**
     * This method is called when the increment button is clicked.
     */
    public void increment(View view) {
        if (quantity == 10) {
            Toast.makeText(this, "You can only have maximum 10 cups of coffee", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity + 1;
        display(quantity);
    }

    /**
     * This method is called when the decrement button is clicked.
     */
    public void decrement(View view) {
        if (quantity == 1) {
            Toast.makeText(this, "You can only have minimum 1 cup of coffee", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity - 1;
        display(quantity);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }


    /**
     * calculatePrice method
     */
    private int calculatePrice(boolean whippedCream, boolean chocolate) {
        int basePrice = 5;

        if (whippedCream) {
            basePrice += 1;
        }

        if (chocolate) {
            basePrice += 2;
        }
        return quantity * basePrice;
    }

    /**
     * summary method
     */
    private String orderSummary(int price, boolean whippedCream, boolean chocolate, String name) {
        String priceMessage = "Name: " + name;
        priceMessage += "\nAdd Whipped Cream?" + whippedCream;
        priceMessage += "\nAdd Chocolate?" + chocolate;
        priceMessage += "\nQuantity: " + quantity;
        priceMessage += "\nPrice: " + price;
        priceMessage += "\nThank You!";
        return priceMessage;
    }

}