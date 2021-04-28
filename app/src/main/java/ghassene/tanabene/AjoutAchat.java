package ghassene.tanabene;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class AjoutAchat extends AppCompatActivity {

    EditText e1,e2;
    Button b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_achat);
        e1=findViewById(R.id.editAchat);
        e2=findViewById(R.id.editPrix);
        b=findViewById(R.id.sumbitBouton);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String achat=e1.getText().toString() ;
                String prix=e2.getText().toString() ;



//                    Float prix =Float.valueOf(e2.getText().toString());
                NvAchat acha=new NvAchat(achat,prix);

                DBAdapter dbAdapter=new DBAdapter(AjoutAchat.this);
                dbAdapter.ajoutAchat(acha);

                Toast.makeText(AjoutAchat.this, " Ajout avec succés ", Toast.LENGTH_SHORT).show();

                    Intent intent=new Intent(AjoutAchat.this,MainActivity.class);
                    startActivity(intent);




            }
        });
    }
}