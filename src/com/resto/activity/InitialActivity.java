package com.resto.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.resto.R;
import android.app.Dialog;

public class InitialActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String rest_name = dbHelper.getRestaurantName();
        if (rest_name == null) {
            setContentView(R.layout.initial);
            findViewById(R.id.rest_id_submit).setOnClickListener(onClickListener);
        } else {
            startActivity(new Intent(InitialActivity.this, MainActivity.class));
        }
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
    public void onClick(View v) {
        EditText e = (EditText) findViewById(R.id.rest_id_input);
        String restaurantId = e.getText().toString();
        if(restaurantId.length() == 0){
            e.setError("Please input Valid text");
            return;
        }
        try{
            dataService.initializeDatabase(restaurantId);
            startActivity(new Intent(InitialActivity.this, MainActivity.class));
        }catch(Exception e1){
            handleException(e1);
        }
      }
    };

    public void handleException(Exception e){
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.error);
        dialog.setTitle("ERROR");
        TextView text = (TextView) dialog.findViewById(R.id.errorText);
        text.setText(e.getLocalizedMessage());
        dialog.findViewById(R.id.dialogButtonOK).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();

    }
}
