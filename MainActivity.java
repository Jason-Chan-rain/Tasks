package com.example.homework;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edt_year;
    private EditText edt_length;
    private EditText edt_salary;
    private EditText edt_order;
    private Button btn_range;
    private Button btn_price;
    private Button btn_cost;
    private TextView tv_range;
    private TextView tv_price;
    private TextView tv_cost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edt_year=(EditText)findViewById(R.id.edt_year);
        btn_range=(Button)findViewById(R.id.btn_range);
        tv_range=(TextView)findViewById(R.id.tv_range);
        edt_length=(EditText)findViewById(R.id.edt_length);
        btn_price=(Button)findViewById(R.id.btn_price);
        tv_price=(TextView)findViewById(R.id.tv_price);
        edt_salary=(EditText)findViewById(R.id.edt_salary);
        edt_order=(EditText)findViewById(R.id.edt_order);
        btn_cost=(Button)findViewById(R.id.btn_cost);
        tv_cost=(TextView)findViewById(R.id.tv_cost);

        btn_range.setOnClickListener(this);
        btn_price.setOnClickListener(this);
        btn_cost.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view==btn_range){
            int inputYear=Integer.parseInt(edt_year.getText().toString());
            int out=(int)(4080*Math.pow(Math.E,0.28*(inputYear-1960)));
            tv_range.setText(out+"");
        }
        else if(view==btn_price){
            int inputYear=Integer.parseInt(edt_year.getText().toString());
            int inputLength=Integer.parseInt(edt_length.getText().toString());
            int out=(int)(4080*Math.pow(Math.E,0.28*(inputYear-1960)));
            int out1=(int)(0.003*inputLength*out*Math.pow(0.72,(inputYear-1974)));
            tv_price.setText(out1+"");
        }else if(view==btn_cost){
            int year=Integer.parseInt(edt_year.getText().toString());
            double m=(4080*Math.pow(Math.E,0.28*(year-1960)));
            int order=Integer.parseInt(edt_order.getText().toString());
            int salary=Integer.parseInt(edt_salary.getText().toString());
            int cost=(int)((m/(order*20))*salary);
            tv_cost.setText(cost+"");
        }
    }
}
