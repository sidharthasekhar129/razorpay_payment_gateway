package com.example.razorpay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class StatusActivity extends AppCompatActivity {
    private Button home,history;
    private TextView info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);

        home=(Button)findViewById(R.id.home);
        history=(Button)findViewById(R.id.history);
        info=(TextView)findViewById(R.id.info);


        String orderid=getIntent().getStringExtra("orderid");
        String paymentid=getIntent().getStringExtra("paymentid");
        String email=getIntent().getStringExtra("email");
        String contact=getIntent().getStringExtra("contact");
        info.setText("Orderid:"+orderid+"Paymentid:"+paymentid+"email:"+email+"Contact:"+contact);


        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
