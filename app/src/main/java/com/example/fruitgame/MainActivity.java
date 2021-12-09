package com.example.fruitgame;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvMain;
    private Button btnEN, btnCN;
    private Boolean flag = true;
    Bundle bundle = new Bundle();
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvMain = findViewById(R.id.rv_main);
        btnCN = findViewById(R.id.btn_cn);
        btnEN = findViewById(R.id.btn_en);
        mSharedPreferences = getSharedPreferences("flag", MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();
        flag = mSharedPreferences.getBoolean("flag", flag);
        flag();
        setListener();
    }


    private void setListener(){
        Onclick onclick = new Onclick();
        btnEN.setOnClickListener(onclick);
        btnCN.setOnClickListener(onclick);
    }


    private class Onclick implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btn_en:
                    flag = true;
                    break;
                case R.id.btn_cn:
                    flag = false;
                    break;
            }
            flag();
        }
    }
    public void flag(){
        if(flag){
            btnCN.setText("Chinese");
            btnEN.setText("English");
            //布局管理
            rvMain.setLayoutManager(new LinearLayoutManager(MainActivity.this));
            //分割线
            rvMain.addItemDecoration(new MyDecoration());
            //适配器
            rvMain.setAdapter(new LinearAdapterEN(MainActivity.this, new LinearAdapterEN.OnItemClickListener() {
                @Override
                public void onClick(int pos) {
                    Intent intent = null;
                    switch(pos){
                        case 0:
                            intent = new Intent(MainActivity.this, OrangeActivity.class);
                            break;
                        case 1:
                            intent = new Intent(MainActivity.this, AppleActivity.class);
                            break;
                        case 2:
                            intent = new Intent(MainActivity.this, PearActivity.class);
                            break;
                        case 3:
                            intent = new Intent(MainActivity.this, StrawberryActivity.class);
                            break;

                    }
                    mEditor.putBoolean("flag", flag);
                    mEditor.apply();
                    startActivity(intent);
                }
            }));
        }
        else{
            btnCN.setText("中文");
            btnEN.setText("英文");
            //布局管理
            rvMain.setLayoutManager(new LinearLayoutManager(MainActivity.this));
            //分割线
            rvMain.addItemDecoration(new MyDecoration());
            //适配器
            rvMain.setAdapter(new LinearAdapterCN(MainActivity.this, new LinearAdapterCN.OnItemClickListener() {
                @Override
                public void onClick(int pos) {
                    Intent intent = null;
                    switch(pos){
                        case 0:
                            intent = new Intent(MainActivity.this, OrangeActivity.class);
                            break;
                        case 1:
                            intent = new Intent(MainActivity.this, AppleActivity.class);
                            break;
                        case 2:
                            intent = new Intent(MainActivity.this, PearActivity.class);
                            break;
                        case 3:
                            intent = new Intent(MainActivity.this, StrawberryActivity.class);
                            break;

                    }
                    mEditor.putBoolean("flag", flag);
                    mEditor.apply();
                    startActivity(intent);
                }
            }));
        }
    }

    class MyDecoration extends RecyclerView.ItemDecoration{
        //划线
        @Override
        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            outRect.set(0,0,0,getResources().getDimensionPixelOffset(R.dimen.dividerHeight));
        }
    }
}