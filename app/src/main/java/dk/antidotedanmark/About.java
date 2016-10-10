package dk.antidotedanmark;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class About extends AppCompatActivity {

    private final String mUri = "http://www.antidotedanmark.dk";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
    }

    public void launchSite(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(mUri));
        startActivity(intent);
    }
}
