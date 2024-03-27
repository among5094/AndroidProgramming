package com.example.miniproject04_04;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView text1,text2;
    CheckBox chkAgree;
    RadioGroup Rgroup1;
    RadioButton rdoDog, rdoCat, rdoRabbit;
    Button btnOK;
    ImageView imgPet;

    Button endBtn,resetBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("애완동물 사진 보기");

        text1=(TextView) findViewById(R.id.Text1);
        chkAgree=(CheckBox) findViewById(R.id.chkAgree);

        text2=(TextView) findViewById(R.id.Text2);
        Rgroup1 = (RadioGroup) findViewById(R.id.Rgroup1);
        rdoDog=(RadioButton) findViewById(R.id.rdoDog);
        rdoCat=(RadioButton) findViewById(R.id.rdoCat);
        rdoRabbit=(RadioButton) findViewById(R.id.rdoRabbit);

        btnOK=(Button) findViewById(R.id.btnOK);
        imgPet=(ImageView) findViewById(R.id.imgPet);

        endBtn=(Button) findViewById(R.id.endBtn);
        resetBtn=(Button) findViewById(R.id.resetBtn);


        // 동의함에 체크했을 때
        chkAgree.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){

                if(chkAgree.isChecked()==true){
                    text2.setVisibility(android.view.View.VISIBLE);
                    Rgroup1.setVisibility(android.view.View.VISIBLE);
                    btnOK.setVisibility(android.view.View.VISIBLE);
                    imgPet.setVisibility(android.view.View.VISIBLE);

                }else{
                    text2.setVisibility(android.view.View.INVISIBLE);
                    Rgroup1.setVisibility(android.view.View.INVISIBLE);
                    btnOK.setVisibility(android.view.View.INVISIBLE);
                    imgPet.setVisibility(android.view.View.INVISIBLE);
                }//else문 닫는괄호

            }//onCheckedChanged() 닫는괄호


        }); //익명 클래스로 setOnCheckedChangeListener설정
        
        // OK버튼을 눌렀을 때
        btnOK.setOnClickListener(new View.OnClickListener(){
            public void onClick(View arg0){
                int checkedRadio=Rgroup1.getCheckedRadioButtonId();

                if(checkedRadio==R.id.rdoDog)
                    imgPet.setImageResource(R.drawable.dog);
                else if(checkedRadio==R.id.rdoCat)
                    imgPet.setImageResource(R.drawable.cat);
                else if(checkedRadio==R.id.rdoRabbit)
                    imgPet.setImageResource(R.drawable.rabbit);
                else //전부 체크 안되면?
                    Toast.makeText(getApplicationContext(), "동물 먼저 선택하세요", Toast.LENGTH_SHORT).show();
            }
        });

        // 종료버튼 (End Button)  누르면 응용프로그램이 완전히 종료되게
        endBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // 처음으로 (Reset Button) 누르면 다시 초기화되고 처음 화면이 나오게
        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Reset the CheckBox and clear its selection
                chkAgree.setChecked(false);

                // Clear the RadioGroup selection
                Rgroup1.clearCheck();

                // Hide the components that were made visible upon agreeing to the terms
                text2.setVisibility(View.INVISIBLE);
                Rgroup1.setVisibility(View.INVISIBLE);
                btnOK.setVisibility(View.INVISIBLE);
                imgPet.setVisibility(View.INVISIBLE);

                // Remove the image from the ImageView
                imgPet.setImageDrawable(null);
            }
        });






        //처음으로 버튼 눌렀을 때

    }
}