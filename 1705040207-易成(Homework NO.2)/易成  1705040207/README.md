## �������߼��������
	���ο�������һ��С��App���Ҳ��õĿ�������ΪAndroid Studio, ���õı��������Java��
  
## ���л���
	������Android 4.0 �汾��Android�ֻ�����Androidģ����

## ��������
	�û���ͨ����App������ݡ��ֳ�λ��������Աÿ����Կ�����ָ���������ǵ�ƽ�������������ڵ�ʱ�Լ�����洢�����������Լ��洢���ļ۸񣬺�ʹ�洢��װ����������ĳɱ���

## Դ����˵��
### 1��activity_problem_description.xml������������ҳ����ʾ��������Ҫ��������TextView��ImageView��Button��EditText��������õ���LinearLayout���֡�
```activity_problem_description.xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/activity_problem_description"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:orientation="vertical"
    android:paddingLeft="@dimen/activity_horizontal_margin"
    android:paddingTop="@dimen/activity_vertical_margin"
    tools:context="com.example.a59124.thesolutionfromyicheng.ProblemDescription">

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:orientation="vertical"
        android:weightSum="1">

        <TextView
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:paddingTop="5dp"
            android:text="    According to historical data, the following assumptions can be made (where Y represents year, M represents storage capacity, PA and PB represent price) :"
            android:layout_alignParentLeft="true"
            android:layout_alignParentStart="true"
            android:layout_gravity="center"
            android:id="@+id/text1"
            android:layout_alignParentTop="true"
            android:lineSpacingMultiplier="1.2"/>

        <TextView
            android:id="@+id/text2"
            android:paddingTop="10dp"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_gravity="center"
            android:text="    1. The demand for computer storage capacity increases year by year according to the following formula:"
            android:layout_weight="0.05" />

        <ImageView
            android:id="@+id/pic1"
            android:paddingTop="5dp"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_gravity="center"
            android:src="@drawable/img_1"
            />

        <TextView
            android:id="@+id/text3"
            android:paddingTop="10dp"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_gravity="center"
            android:text="    2. The price of memory decreases year by year according to the following formula:"
            android:layout_weight="0.05"
            android:lineSpacingMultiplier="1.2"/>

        <ImageView
            android:id="@+id/pic2"
            android:paddingTop="5dp"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_gravity="center"
            android:src="@drawable/img_2"
            />

        <TextView
            android:id="@+id/text4"
            android:paddingTop="10dp"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_gravity="center"
            android:text="    If the computer word length is 16 bits, the trend of memory price decline is:"
            android:layout_weight="0.05"
            android:lineSpacingMultiplier="1.2"/>

        <ImageView
            android:id="@+id/pic3"
            android:paddingTop="5dp"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_gravity="center"
            android:src="@drawable/img_3"
            />

        <TextView
            android:id="@+id/text5"
            android:paddingTop="15dp"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_gravity="center"
            android:text="    If you enter the year(Year), the number of digits of memory word length(Bits), the number of instructions programmers can develop per day(Number), and their average salary(Wage), this program can help you calculate the demand and price of computer storage capacity, and the cost of filling up the program with memory in a given year."
            android:layout_weight="0.05"
            android:lineSpacingMultiplier="1.2"/>
    </LinearLayout>

    <LinearLayout
        android:orientation="horizontal"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:paddingTop="15dp">

        <TextView
            android:id="@+id/text6"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:paddingLeft="40dp"
            android:text="Year : "/>

        <EditText
            android:id="@+id/year"
            android:layout_width="81dp"
            android:layout_height="wrap_content"
            android:background="@android:color/darker_gray"
            android:hint=""
            android:selectAllOnFocus="false"
            tools:background="?android:attr/actionModeBackground" />

        <TextView
            android:id="@+id/text7"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:paddingLeft="51dp"
            android:text="Bits : "/>

        <EditText
            android:id="@+id/bits"
            android:layout_width="72dp"
            android:layout_height="wrap_content"
            android:background="@android:color/darker_gray"
            android:selectAllOnFocus="false"
            tools:background="?android:attr/actionModeBackground" />
    </LinearLayout>

    <LinearLayout
        android:orientation="horizontal"
        android:paddingTop="10dp"
        android:layout_width="match_parent"
        android:layout_height="match_parent">

        <TextView
            android:id="@+id/text8"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:paddingLeft="40dp"
            android:text="Number : "/>

        <EditText
            android:id="@+id/number"
            android:layout_width="60dp"
            android:layout_height="wrap_content"
            android:background="@android:color/darker_gray"
            android:hint=""
            android:selectAllOnFocus="false"
            tools:background="?android:attr/actionModeBackground" />

        <TextView
            android:id="@+id/text9"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:paddingLeft="50dp"
            android:text="Wage : "/>

        <EditText
            android:id="@+id/wage"
            android:layout_width="60dp"
            android:layout_height="wrap_content"
            android:background="@android:color/darker_gray"
            android:selectAllOnFocus="false"
            tools:background="?android:attr/actionModeBackground" />
    </LinearLayout>

    <LinearLayout
        android:layout_width="347dp"
        android:layout_height="wrap_content"
        android:paddingTop="25dp"
        android:orientation="horizontal">

        <Button
            android:id="@+id/calculate"
            android:layout_width="wrap_content"
            android:layout_height="36dp"
            android:layout_marginLeft="50dp"
            android:text="Calculate"
            android:textAllCaps="false"
            android:background="#00CD00"/>

        <Button
            android:id="@+id/clear"
            android:layout_width="wrap_content"
            android:layout_height="36dp"
            android:layout_marginLeft="70dp"
            android:text="Clear All"
            android:textAllCaps="false"
            android:background="#CD2626"/>
    </LinearLayout>
</LinearLayout>


```
### 2��ProblemDescription.Java, ��Ӧ��activity_problem_description.xml����ʾ��ҳ�棬������ʾ����������Լ���ȡ�û�������
``` ProblemDescription.Java
package com.example.a59124.thesolutionfromyicheng;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;


public class ProblemDescription extends AppCompatActivity {
    //ʵ�����������
	private EditText year;
    private EditText bits;
    private EditText number;
    private EditText wage;
    private Button calculate;
    private Button clear;
    private int year1;
    private int bits1;
    private Double number1;
    private Double wage1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problem_description);
		
        //ʹ�����Ķ����Ӧ��Ӧ���ı��༭��
		year = (EditText) findViewById(R.id.year);
        bits = (EditText) findViewById(R.id.bits);
        number =  (EditText) findViewById(R.id.number);
        wage = (EditText) findViewById(R.id.wage);

        calculate = (Button) findViewById(R.id.calculate);
        clear = (Button) findViewById(R.id.clear);
		
		//��ת��TheSolution���ȥ�����������ֵ
        intentTheSolution();

    }

    private void intentTheSolution(){
        calculate.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
			
			//��ȡ�û������룬������ת��ΪString���ͣ����ں�������
                String str1 = year.getText().toString();
                String str2 = bits.getText().toString();
                String str3 = number.getText().toString();
                String str4 = wage.getText().toString();
				
				// �ж������Ƿ�Ϊ�գ����Ϊ�գ�Ӧ��Toast��ʾ�û�
                if(str1.equals("")||str2.equals("")||str3.equals("")||str4.equals("")){
                    Toast.makeText(ProblemDescription.this, "Please enter all information to calculate! ", Toast.LENGTH_SHORT).show();
                    return;
                }else if(!(isNumber(str1.trim())&&isNumber(str2.trim())&&isNumber(str3.trim())&&isNumber(str4.trim()))){
                   // ȥ���û�������Ĺ����п�������Ŀո񣬷�ֹ�������
				   Toast.makeText(ProblemDescription.this, "Please enter a valid number! ", Toast.LENGTH_SHORT).show();
                    return;
                }else{
						// �ж���Ϻ��ڽ�String���͵�����ת��ΪInteger��Double����
                        year1 = Integer.parseInt(year.getText().toString().trim());
                        bits1 = Integer.parseInt(bits.getText().toString().trim());
                        number1 = Double.parseDouble(number.getText().toString().trim());
                        wage1 = Double.parseDouble(wage.getText().toString().trim());
                    }
				// ����Bundle��Ҫ���ݵ����ݽ��������ٴ��ݸ�Intent���䴫����һ�
                Intent intent = new Intent(ProblemDescription.this, TheSolution.class);
                Bundle bundle = new Bundle();
                bundle.putInt("year", year1);
                bundle.putInt("bits", bits1);
                bundle.putDouble("number", number1);
                bundle.putDouble("wage", wage1);
                intent.putExtras(bundle);
                startActivityForResult(intent, 1);
            }
        });
		// Clear All ��ť�Ĺ���ʵ�֣�������������������
        clear.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                year.setText("");
                bits.setText("");
                number.setText("");
                wage.setText("");
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
		
		// ����onActivityResult( )���������ôӵڶ������������һ���ʱ��������ݲ��ᶪʧ
        if(resultCode == RESULT_OK){
            Bundle bundle = data.getExtras();
            year1 = bundle.getInt("year");
            bits1 = bundle.getInt("bits");
            number1 = bundle.getDouble("number");
            wage1 = bundle.getDouble("wage");

            String anumber = number1.toString();
            String awage = wage1.toString();
            year.setText(year1);
            bits.setText(bits1);
            number.setText(anumber);
            wage.setText(awage);
        }
    }
	
	// �ж�����������Ƿ�Ϊ���֣���ֹ��Ƿ����뵼�³��ֱ���
    public boolean isNumber(String str){
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(str).matches();
    }
}
```

### 3��activity_the_solution.xml���������Ϊ�򵥣����õ���Ȼ��LinearLayout���ַ�ʽ����Ҫ�ؼ�ΪTextView������ʵʱ��ʾ����������Button�������˳����򣩡�
```activity_the_solution.xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/activity_the_solution"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:paddingBottom="@dimen/activity_vertical_margin"
    android:paddingLeft="@dimen/activity_horizontal_margin"
    android:paddingRight="@dimen/activity_horizontal_margin"
    android:paddingTop="@dimen/activity_vertical_margin"
    tools:context="com.example.a59124.thesolutionfromyicheng.TheSolution">

    <TextView
        android:id="@+id/result"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:lineSpacingMultiplier="1.5"/>

    <LinearLayout
        android:layout_marginTop="480dp"
        android:layout_width="match_parent"
        android:layout_height="match_parent">

        <Button
            android:id="@+id/back"
            android:layout_width="200dp"
            android:layout_height="wrap_content"
            android:layout_marginLeft="85dp"
            android:text="Exit The Application"
            android:textAllCaps="false"
            android:background="#CD2626"/>

    </LinearLayout>

</LinearLayout>

```

### 4��TheSolution.java����Ӧactivity_the_solution.xml����ʾ�Ľ��棬���ڴ������ݲ�������Ľ�����ظ����棬������һ���ɹ��û�ֱ���˳�����İ�ť��
```TheSolution.java
package com.example.a59124.thesolutionfromyicheng;

import android.content.Intent;
import android.icu.text.DecimalFormat;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TheSolution extends AppCompatActivity {
    //ʵ�����������
	private TextView result;
    private Button back;
	//����һЩ���ڼ���ı���
    private int year;
    private int bits;
    private Double number;
    private Double wage;
    private Intent intent;
    private Bundle bundle;
    public Double price;
    public Double cost;
    public Double capacity;


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_solution);

        result = (TextView) findViewById(R.id.result);
        back = (Button) findViewById(R.id.back);
		// ��ȡ�ӵ�һ������ݹ���������
        intent = this.getIntent();
        bundle = intent.getExtras();
        year = bundle.getInt("year");
        bits = bundle.getInt("bits");
        number = bundle.getDouble("number");
        wage = bundle.getDouble("wage");

        calculateResult();//���ڴ�������
        disPlayAndBack();//���ڽ�����������û��Լ��˳�����
    }

    public void calculateResult() {//����ļ��㹫ʽ
        capacity = 4080 * Math.pow(Math.E, 0.28 * (year - 1960));
        if(bits == 16){
            price = 0.3 * Math.pow(0.72, year - 1974) * capacity;
        }else{
            price = 0.048 * bits * Math.pow(0.72, year - 1974) * capacity;
        }
        cost = wage * capacity / (number * 20);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)//���ݵͰ汾Android
    public void disPlayAndBack(){
        result.setText("    By calculation, In { " + year + " }, the demand for computer storage capacity was { " + formatting(capacity) + " } bytes. If the byte length was { "
                + bits + " }, the price of memory would be { $ " + formatting(price) + " }. " + "Assuming that a programmer could develop { " + number + " } instructions a day " +
                "and his average salary was { $ " + wage + " }, " + "the cost of filling a program with memory would be { $ " + formatting(cost) +" }.");

        back.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                setResult(RESULT_OK, intent);//�ӵڶ������������һ���ʱ,��������ݲ��ᶪʧ
                finish();// �ݻٻ���˳�����
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public String formatting(Double num){
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");//��ʽ���������
        String  num1 = decimalFormat.format(num);
        return num1;
    }
}
```