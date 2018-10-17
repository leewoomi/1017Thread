package com.example.a503_08.a1017thread;


import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Handler3Activity extends AppCompatActivity {

    //진행율을 표시하기 위한 대화상자
    ProgressDialog progressDialog;
    //값을 나타낼 변수
    int value;

    Button download;

     Handler handler = new Handler(){
        public void handleMessage(Message msg){
            value = value +1;
            try {
                Thread.sleep(2000);
                if(value<100){
                    progressDialog.setProgress(value);
                    handler.sendEmptyMessage(0);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };

    public void download() {
        try {
            Thread.sleep(1000);
            Toast.makeText(this, "다운로드 완료", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler3);



        download = (Button) findViewById(R.id.download);

        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(Handler3Activity.this).setTitle("다운로드")
                        .setMessage("다운로드 하시겠습니까?")
                        .setPositiveButton("네", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        value = 0;
                        progressDialog = new ProgressDialog(Handler3Activity.this);
                        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                        progressDialog.setTitle("다운로드");
                        progressDialog.setMessage("기다리세요....");
                        //뒤로 버튼으로 대화상자를 닫을 수 없게 설정
                        progressDialog.setCancelable(false);
                        progressDialog.show();
                        handler.sendEmptyMessage(0);
                    }
                }).setNegativeButton("아니오", null).show();

            }
        });


    }
}
