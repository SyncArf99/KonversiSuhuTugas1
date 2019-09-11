package com.example.konversi;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends Activity implements View.OnClickListener {

    private Spinner sp;
    private EditText edt_awal;
    private TextView tv_C;
    private TextView tv_R;
    private TextView tv_F;
    private TextView tv_K;
    final Context context = this;

    private EditText editText1;
    private String[] list={"C","R","F","K"};
    private Button btnAboutUs;
    Double awal, celcius, reamur, fahrenheit, kelvin;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Context context = this;
        //Button About Us
        btnAboutUs =(Button)findViewById(R.id.btn_about_us);
        btnAboutUs.setOnClickListener(this);

        sp=(Spinner) findViewById(R.id.spinner1);
        edt_awal=(EditText) findViewById(R.id.editText1);
        tv_C=(TextView) findViewById(R.id.tvCelcius);
        tv_R=(TextView) findViewById(R.id.tvReamur);
        tv_F=(TextView) findViewById(R.id.tvFahrenheit);
        tv_K=(TextView) findViewById(R.id.tvKelvin);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(adapter);
    }
    public void Konversi(View v){
        String satuan = String.valueOf(sp.getSelectedItemPosition());
        if(edt_awal.getText().toString().equals("")){
            //Toast.makeText(getBaseContext(), "Masukkan suhu awal, default suhu awal = 0 jika tidak dimasukkan suhu awal", Toast.LENGTH_LONG).show();
            awal = 0.0;
            final Dialog dialog = new Dialog(context);
            dialog.setContentView(R.layout.fragment_empty_fill);
            Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
            // if button is clicked, close the custom dialog
            dialogButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    //Toast.makeText(getApplicationContext(),"Dismissed..!!", Toast.LENGTH_SHORT).show();
                }
            });
            dialog.show();
        }else{
            awal = Double.parseDouble(edt_awal.getText().toString());
        }
        if(satuan.equals("0")){
            celcius = awal;
            reamur = 0.8 * awal;
            fahrenheit = (1.8 * awal) + 32;
            kelvin = awal + 273;

            tv_C.setText(String.valueOf(celcius));
            tv_R.setText(String.valueOf(reamur));
            tv_F.setText(String.valueOf(fahrenheit));
            tv_K.setText(String.valueOf(kelvin));
        }else if(satuan.equals("1")){
            celcius = 1.25 * awal;
            reamur = awal;
            fahrenheit = (2.25 * awal) + 32;
            kelvin = celcius + 273;

           tv_C.setText(String.valueOf(celcius));
            tv_R.setText(String.valueOf(reamur));
            tv_F.setText(String.valueOf(fahrenheit));
            tv_K.setText(String.valueOf(kelvin));
        }else if(satuan.equals("2")){
            celcius = 0.55555 *(awal - 32);
            reamur = 0.44444 * (awal-32);
            fahrenheit = awal;
            kelvin = celcius + 273;

            tv_C.setText(String.valueOf(celcius));
            tv_R.setText(String.valueOf(reamur));
            tv_F.setText(String.valueOf(fahrenheit));
            tv_K.setText(String.valueOf(kelvin));
        }else if(satuan.equals("3")){
            celcius = awal-273;
            reamur = 0.8 * (awal-273);
            fahrenheit = (1.8 * (awal-273)) + 32;
            kelvin = awal;

            tv_C.setText(String.valueOf(celcius));
            tv_R.setText(String.valueOf(reamur));
            tv_F.setText(String.valueOf(fahrenheit));
            tv_K.setText(String.valueOf(kelvin));
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_about_us:
                Intent aboutUs = new Intent(MainActivity.this,AboutUs.class);
                    startActivity(aboutUs);
                break;
        }
    }
}