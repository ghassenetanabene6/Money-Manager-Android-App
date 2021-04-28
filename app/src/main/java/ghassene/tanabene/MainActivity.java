package ghassene.tanabene;

//import android.Manifest;
//import android.content.DialogInterface;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
//import android.content.pm.PackageManager;
//import android.net.Uri;
//import android.support.v4.app.ActivityCompat;
//import android.support.v7.app.AlertDialog;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
//import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    ListView MyList;  //liste
    
   public static ArrayList<NvAchat> MyArrayList ; //base

    public static DBAdapter dbAdapter ;
    public static NvAchatAdapter AchatAdapter  ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyList = findViewById(R.id.liste);


        dbAdapter= new DBAdapter(this);
        MyArrayList = new ArrayList<NvAchat>();

       MyArrayList = dbAdapter.afficher();

       NvAchatAdapter AchatAdapter = new NvAchatAdapter(this, R.layout.activity_nv_achat,MyArrayList);


      MyList.setAdapter(AchatAdapter);
        MyList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, final View view, final int position, long id){
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Supprimer")
                        .setMessage("Voulez-vous supprimer cet élément de la liste ?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                dbAdapter.remove(MyArrayList.get(position).getId());
                                MyArrayList=dbAdapter.afficher();

                                NvAchatAdapter AchatAdapter =new NvAchatAdapter(MainActivity.this,R.layout.activity_nv_achat,MyArrayList);

                                MyList.setAdapter(AchatAdapter);
                                Toast.makeText(MainActivity.this, "élément supprimé.", Toast.LENGTH_SHORT).show(); }})
                        .setNegativeButton(android.R.string.no, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();


            }

        }     );
    }

    @Override
   public void onResume(){
        super.onResume();
       MyArrayList = dbAdapter.afficher();
      NvAchatAdapter AchatAdapter =new NvAchatAdapter(MainActivity.this,R.layout.activity_nv_achat,MyArrayList);
        MyList.setAdapter(AchatAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.ajout_achat_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        if (item.getItemId() == R.id.addAchat) {
            Intent intent = new Intent(MainActivity.this, AjoutAchat.class);
            startActivity(intent);
        }
        if( item.getItemId() == R.id.total)
        {
            Intent intent = new Intent(MainActivity.this, Total.class);
            startActivity(intent);
        }
        return true;
    }

    public static long getAppFirstInstallTime(Context context){
        PackageInfo packageInfo;
        try {
            if(Build.VERSION.SDK_INT>8/*Build.VERSION_CODES.FROYO*/ ){
                packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
                return packageInfo.firstInstallTime;
            }else{
                //firstinstalltime unsupported return last update time not first install time
                ApplicationInfo appInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 0);
                String sAppFile = appInfo.sourceDir;
                return new File(sAppFile).lastModified();
            }
        } catch (PackageManager.NameNotFoundException e) {
            //should never happen
            return 0;
        }
    }
    public static String getDate(long milliSeconds, String dateFormat) {
        // Create a DateFormatter object for displaying date in specified format.
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);

        // Create a calendar object that will convert the date and time value in milliseconds to date.
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return formatter.format(calendar.getTime());
    }

}








