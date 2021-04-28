package ghassene.tanabene;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static ghassene.tanabene.MainActivity.dbAdapter;
import static ghassene.tanabene.MainActivity.getAppFirstInstallTime;
import static ghassene.tanabene.MainActivity.getDate;

public class Total extends AppCompatActivity {
    Long date ;
    ImageView b;
    TextView t,dateview ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_total);

        t =findViewById(R.id.total);
        t.setText(Float.toString(dbAdapter.total()));
        date= getAppFirstInstallTime(this);
        String d = getDate(date, "dd/MM/yyyy");
        dateview=findViewById(R.id.date);
        dateview.setText(d);
        b=findViewById(R.id.retour);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Total.this, "Ouups! Tu m'as frappé.", Toast.LENGTH_SHORT).show();

                Intent intent=new Intent(Total.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }


    @Override
    public void onResume(){
        super.onResume();
        // put your code here...
        t.setText(Float.toString(dbAdapter.total()));
    }
}
