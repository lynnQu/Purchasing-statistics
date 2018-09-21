package au.edu.utas.yhuang3.assignment2;

import android.app.Service;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ItemAdapter extends ArrayAdapter<Item> {
    private int mLayoutResourceID;
    public ItemAdapter(Context context, int resource, List<Item> objects)
    {
        super(context, resource, objects);
        this.mLayoutResourceID = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater)getContext().getSystemService(Service.LAYOUT_INFLATER_SERVICE);
        View row = layoutInflater.inflate(mLayoutResourceID, parent, false);
        Item p = this.getItem(position);

        TextView lblName = row.findViewById(R.id.lblName);
        lblName.setText(p.getName());

        TextView lblQuantity = row.findViewById(R.id.lblQuantity);
        String sQuantity = (Integer.valueOf(p.getQuantity())).toString();
        lblQuantity.setText(sQuantity);

        TextView lblPrice = row.findViewById(R.id.lblPrice);
        String sPrice = (Double.valueOf(p.getPrice())).toString();
        lblPrice.setText(sPrice);



        return row;
    }
}