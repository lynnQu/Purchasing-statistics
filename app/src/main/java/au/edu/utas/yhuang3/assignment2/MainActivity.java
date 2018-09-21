package au.edu.utas.yhuang3.assignment2;

import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Open the database, so that we can read and write
        ItemDatabase databaseConnection = new ItemDatabase(this);
        final SQLiteDatabase db = databaseConnection.open();

        Item item1 = new Item();
        item1.setName("Watermelon");
        item1.setQuantity(1);
        item1.setPrice(5.5);
    //  item1.setCheck(0);
        Item item2 = new Item();
        item2.setName("Apple");
        item2.setQuantity(5);
        item2.setPrice(12.00);
   //     item2.setCheck(0);
        ItemTable.insert(db, item1);
        ItemTable.insert(db, item2);

        final ArrayList<Item> items = ItemTable.selectAll(db);

        final ItemAdapter myItemAdapter = new ItemAdapter(getApplicationContext(), R.layout.shopping_list_item, items);

        ListView mainList = findViewById(R.id.mainList);
        mainList.setAdapter(myItemAdapter);

        mainList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                final Item p = items.get(i);
                builder.setTitle("Update Item Name");
                final EditText input = new EditText(MainActivity.this);
                input.setInputType(InputType.TYPE_CLASS_TEXT);
                input.setText(p.getName());
                builder.setView(input);

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        p.setName(input.getText().toString());
                        ItemTable.update(db, p);
                        myItemAdapter.notifyDataSetChanged();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
/*
        TextView lblUncheckTotal = findViewById(R.id.lblUncheckTotal);
        double d = ItemTable.getTotalUncheck(db);
        String sUncheck = (Double.valueOf(d)).toString();
        lblUncheckTotal.setText(sUncheck);
*/




        System.out.println("SIZE is "+ items.size());

    }
}