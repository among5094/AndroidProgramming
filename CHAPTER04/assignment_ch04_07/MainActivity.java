package com.example.assignment_ch04_07;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    CheckBox Checkbox_enable, Checkbox_clickable, Checkbox_rotation;
    Button Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Checkbox_enable = findViewById(R.id.Checkbox_enable);
        Checkbox_clickable = findViewById(R.id.Checkbox_clickable);
        Checkbox_rotation = findViewById(R.id.Checkbox_rotation);

        Button = findViewById(R.id.Button);


        Checkbox_enable.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (Checkbox_enable.isChecked() == true){
                    Button.setEnabled(true);
                } else {
                    Button.setEnabled(false);
                }
            }
        });

        Checkbox_clickable.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (Checkbox_clickable.isChecked() == true){
                    Button.setClickable(true);
                } else {
                    Button.setClickable(false);
                }
            }
        });

        Checkbox_rotation.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (Checkbox_rotation.isChecked() == true){
                    Button.setRotation(45);
                } else {
                    Button.setRotation(0);
                }
            }
        });
    }
}