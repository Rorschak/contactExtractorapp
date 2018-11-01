package com.domain.test.contactextractorapp;

import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    private Button mButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        mButton=findViewById(R.id.button);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                extractContacts();

            }
        });
    }

    private void extractContacts() {
        //query the provider here
        String URL = "content://com.domain.test.contactmalapplication.diffpack.ContactDetailProvider";
        Uri students = Uri.parse(URL);
        Cursor c = managedQuery(students, null, null, null, "name");

        if (c!=null && c.getCount()>0 && c.moveToFirst()) {
            do{
                Toast.makeText(this,
                        c.getString(c.getColumnIndex("_id")) +
                                ", " +  c.getString(c.getColumnIndex("name")) +
                                ", " + c.getString(c.getColumnIndex( "number")),
                        Toast.LENGTH_SHORT).show();
            } while (c.moveToNext());
        }
        else {
            Toast.makeText(this,"No contacts extracted yet",
                    Toast.LENGTH_SHORT).show();
        }
    }


}
