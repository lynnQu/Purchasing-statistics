package au.edu.utas.yhuang3.assignment2;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class ItemTable {
    public static Item createFromCursor(Cursor c) {
        if (c == null || c.isAfterLast() || c.isBeforeFirst()) {
            return null;
        } else {
            Item p = new Item();
            p.setItemID(c.getInt(c.getColumnIndex(KEY_ITEM_ID)));
            p.setName(c.getString(c.getColumnIndex(KEY_NAME)));
            p.setQuantity(c.getInt(c.getColumnIndex(KEY_QUANTITY)));
            p.setPrice(c.getDouble(c.getColumnIndex(KEY_PRICE)));
 //           p.setCheck(c.getInt(c.getColumnIndex(KEY_CHECK)));
            return p;
        }
    }

    public static final String TABLE_NAME = "item";
    public static final String KEY_ITEM_ID = "item_id";
    public static final String KEY_NAME = "name";
    public static final String KEY_PRICE = "price";
    public static final String KEY_QUANTITY = "quantity";
 //   public static final String KEY_CHECK = "check";

    public static final String CREATE_STATEMENT = "CREATE TABLE "
            + TABLE_NAME
            + " (" + KEY_ITEM_ID + " integer primary key autoincrement, "
            + KEY_NAME + " string not null, "
            + KEY_PRICE + " double not null, "
            + KEY_QUANTITY + " string not null "
  //          + KEY_CHECK + " string not null "
            + ");";


    public static void insert(SQLiteDatabase db, Item p) {
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, p.getName());
        values.put(KEY_PRICE, p.getPrice());
        values.put(KEY_QUANTITY, p.getQuantity());
//        values.put(KEY_CHECK, p.getCheck());
        db.insert(TABLE_NAME, null, values);
    }

    public static ArrayList<Item> selectAll(SQLiteDatabase db) {
        ArrayList<Item> results = new ArrayList<Item>();

        Cursor c = db.query(TABLE_NAME, null, null, null, null, null, null);
        //check for error
        if (c != null) {
            //make sure the cursor is at the start of the list
            c.moveToFirst();
            //loop through until we are at the end of the list
            while (!c.isAfterLast()) {
                Item p = createFromCursor(c);
                results.add(p);
                //increment the cursor
                c.moveToNext();
            }
        }
        return results;
    }

    public static void update(SQLiteDatabase db, Item p) {
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, p.getName());
        values.put(KEY_PRICE, p.getPrice());
        values.put(KEY_QUANTITY, p.getQuantity());
 //       values.put(KEY_CHECK, p.getCheck());
        db.update(TABLE_NAME, values, KEY_ITEM_ID + "= ?", new String[]{"" + p.getItemID()});
    }


/*
    public static double getTotalUncheck(SQLiteDatabase db)
    {
        Cursor c = db.query(TABLE_NAME, null, KEY_CHECK + "= 0", null, null,
                null, null);

        double d = 0;

        if (c != null) {
            c.moveToFirst();
            while (!c.isAfterLast()) {
                Item p = createFromCursor(c);
                d = d + p.getPrice();
                c.moveToNext();
            }
        }
        return d;
    }
*/


}