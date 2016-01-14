package cs.b07.lab8;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        // get the Intent that launched me
        Intent intent = getIntent();

        // get the String that was put into the Intent with key dataKey
        String data = (String) intent.getSerializableExtra("dataKey");

        // find the TextView object for TextView with id display_id
        TextView newlySubmitted = (TextView) findViewById(R.id.display_data);

        // specify text to be displayed in the TextView
        newlySubmitted.setText(data);
    }
}
