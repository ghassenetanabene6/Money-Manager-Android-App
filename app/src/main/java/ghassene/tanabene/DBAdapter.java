package ghassene.tanabene;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBAdapter extends SQLiteOpenHelper {

    public DBAdapter (Context context)
    {super(context,"formalab",null,1);}
    @Override

    public void onCreate(SQLiteDatabase db) {
        String createTable="create table panier(id integer primary key,achat text,prix text, date text )";
        db.execSQL(createTable);
    }
    @Override

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String deleteTable=("drop table if exists panier");
        db.execSQL(deleteTable);
        onCreate(db);
    }

    public void ajoutAchat(NvAchat ach)
    {
        SQLiteDatabase db=getWritableDatabase();
        ContentValues contentValues=new ContentValues();

        contentValues.put("achat",ach.getAchat());
        contentValues.put("prix",ach.getPrix());
        contentValues.put("date",ach.getDate());

        db.insert("panier",null,contentValues);
    }

    public ArrayList<NvAchat> afficher(){
        SQLiteDatabase db=getReadableDatabase();
        String selectall="SELECT * FROM panier;";

        Cursor cursor= db.rawQuery(selectall,null);
        ArrayList<NvAchat> achats=new ArrayList<>();

        if (cursor.moveToFirst()){
            do{
                NvAchat ach=new NvAchat(cursor.getString(1),cursor.getString(2));

                ach.setId(cursor.getInt(0));
//                ach.setId(cursor.getInt(0));
                ach.setDate(cursor.getString(3));
                achats.add(ach);
            }
            while(cursor.moveToNext());
        }
        return achats;
    }



    public void remove(int id){
        SQLiteDatabase db=getWritableDatabase();
        db.delete("panier","id="+Integer.toString(id),null);}

    public Float total()
    {   Float s =0f ;

        SQLiteDatabase db=getReadableDatabase();
        String selectall="SELECT * FROM panier";
        Cursor cursor= db.rawQuery(selectall,null);
        ArrayList<NvAchat> achats=new ArrayList<>();
        if (cursor.moveToFirst()){
            do{
                s+=cursor.getFloat(2);
            }
            while(cursor.moveToNext());
        }

        return s ;}
}
