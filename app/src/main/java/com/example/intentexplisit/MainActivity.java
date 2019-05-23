package com.example.intentexplisit;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText phoneNumber;
    private EditText websiteUri;
    private EditText locationUri;
    private EditText textShare;

    Button buttonWebsite;
    Button buttonLocation;
    Button buttonText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        phoneNumber = findViewById(R.id.edtPhoneNumber);
        websiteUri = findViewById(R.id.edtWebsiteUri);
        locationUri = findViewById(R.id.edtLocationUri);
        textShare = findViewById(R.id.edtShareText);

        buttonWebsite = findViewById(R.id.btnWebsiteUri);
        buttonWebsite.setOnClickListener(this);

        buttonLocation = findViewById(R.id.btnLocationUri);
        buttonLocation.setOnClickListener(this);

        buttonText = findViewById(R.id.btnShareText);
        buttonText.setOnClickListener(this);

    }
    public void openDial(View view) {
        Intent dialPhone = new Intent(Intent.ACTION_VIEW,Uri.parse("tel:" + phoneNumber.getText().toString()));
        startActivity(dialPhone);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btnWebsiteUri:
                String strURL = websiteUri.getText().toString();
                if (!strURL.startsWith("http://") && !strURL.startsWith("http://") ){
                    strURL = "http://" + strURL;
                }
                Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse(strURL));
                startActivity(intent);
                break;

            case R.id.btnLocationUri:
                Intent openLocation = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=" + locationUri.getText().toString()));
                startActivity(openLocation);
                break;

            case R.id.btnShareText:
                ShareCompat.IntentBuilder
                        .from(this)
                        .setType("text/plan")
                        .setChooserTitle("Share this text with : " )
                        .setText(textShare.getText().toString())
                        .startChooser();
                break;
        }
    }


}
