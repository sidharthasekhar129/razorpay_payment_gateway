package com.example.razorpay;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.razorpay.Checkout;
import com.razorpay.PaymentData;
import com.razorpay.PaymentResultListener;
import com.razorpay.PaymentResultWithDataListener;

import org.json.JSONObject;



public class MainActivity extends AppCompatActivity implements PaymentResultWithDataListener {
private EditText amount,name,phone;
private Button pay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Checkout.preload(getApplicationContext());

        amount=(EditText)findViewById(R.id.amount);
        name=(EditText)findViewById(R.id.name);
        phone=(EditText)findViewById(R.id.phone);

        pay=(Button)findViewById(R.id.pay);
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPayment();
            }
        });

    }

    public void startPayment() {



        String amount1=amount.getText().toString();
        String name1=name.getText().toString();
        String phone1=phone.getText().toString();


        if (amount1.isEmpty())
        {
            amount.setError("Enter Amount");
            amount.requestFocus();
            return;
        }
        if (name1.isEmpty())
        {
         name.setError("Enter Name");
         name.requestFocus();
         return;
        }
        if (phone1.isEmpty())
        {
            phone.setError("Enter Phone");
            phone.requestFocus();
            return;
        }
        int money=Integer.parseInt(amount1);

        int money1=money*100;

        Checkout checkout = new Checkout();


        checkout.setImage(R.drawable.ic_android_black_24dp);

        final Activity activity = this;

        try {
            JSONObject options = new JSONObject();

            options.put("name", "SIDHARTHA_CORP");
            options.put("description", "Reference no. #123456");
          // options.put("order_id", "0001");
            options.put("currency", "INR");
            options.put("amount", money1);



            checkout.open(activity, options);
        } catch (Exception e) {
            e.printStackTrace();


        }
    }
/*
    @Override
    public void onPaymentSuccess(String s) {

        Toast.makeText(MainActivity.this, "Payment Successfull", Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(getApplicationContext(),StatusActivity.class);
        startActivity(intent);

    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(MainActivity.this, "Payment Unsuccessfull", Toast.LENGTH_SHORT).show();

    }*/

    @Override
    public void onPaymentSuccess(String s, PaymentData paymentData) {
        Toast.makeText(MainActivity.this, "Payment Successfull", Toast.LENGTH_SHORT).show();
        String orderid=paymentData.getOrderId();
        String paymentid=paymentData.getPaymentId();
        String email=paymentData.getUserEmail();
        String contact=paymentData.getUserContact();

        Intent intent=new Intent(getApplicationContext(),StatusActivity.class);
        intent.putExtra("orderid",orderid);
        intent.putExtra("paymentid",paymentid);
        intent.putExtra("email",email);
        intent.putExtra("contact",contact);
        startActivity(intent);




    }

    @Override
    public void onPaymentError(int i, String s, PaymentData paymentData) {
        Toast.makeText(MainActivity.this, "Payment Unsuccessfull", Toast.LENGTH_SHORT).show();


    }
}
