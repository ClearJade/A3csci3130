package com.acme.a3csci3130;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class DetailViewActivity extends Activity {

    private EditText nameField, businessNumberField,  primaryBusinessField, addressField,provinceField;
    Contact receivedPersonInfo;

    private MyApplicationData appState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);

        appState = ((MyApplicationData) getApplicationContext());

        receivedPersonInfo = (Contact)getIntent().getSerializableExtra("Contact");

        businessNumberField = (EditText) findViewById(R.id.businessNumber);
        nameField = (EditText) findViewById(R.id.name);
        primaryBusinessField = (EditText) findViewById(R.id.primaryBusiness);
        addressField = (EditText) findViewById(R.id.address);
        provinceField = (EditText) findViewById(R.id.province);

        if(receivedPersonInfo != null){
            businessNumberField.setText(receivedPersonInfo.businessNumber);
            nameField.setText(receivedPersonInfo.name);
            primaryBusinessField.setText(receivedPersonInfo.primaryBusiness);
            addressField.setText(receivedPersonInfo.address);
            provinceField.setText(receivedPersonInfo.provinceTerritory);
        }
    }

    public void updateContact(View v){
        String businessID = receivedPersonInfo.getUid();
        String businessNumber = businessNumberField.getText().toString();
        String name = nameField.getText().toString();
        String primaryBusiness = primaryBusinessField.getText().toString();
        String address = addressField.getText().toString();
        String province = provinceField.getText().toString();


        Contact business = new Contact(businessID, businessNumber, name, primaryBusiness, address, province);

        appState.firebaseReference.child(businessID).setValue(business);

        finish();
    }

    public void eraseContact(View v){
        String businessID = receivedPersonInfo.getUid();
        appState.firebaseReference.child(businessID).removeValue();
        finish();
    }
}
