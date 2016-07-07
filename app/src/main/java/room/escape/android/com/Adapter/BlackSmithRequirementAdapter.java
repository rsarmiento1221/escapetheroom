package room.escape.android.com.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import room.escape.android.com.Model.BlackSmithRequirements;
import room.escape.android.com.R;

/**
 * Created by Janwel Ocampo on 7/5/2016.
 */
public class BlackSmithRequirementAdapter extends ArrayAdapter<BlackSmithRequirements> {

    private ArrayList<BlackSmithRequirements> objects;

    public BlackSmithRequirementAdapter(Context context, int textViewResourceId, ArrayList<BlackSmithRequirements> objects) {
        super(context, textViewResourceId, objects);
        this.objects = objects;
    }

    public View getView(int position, View convertView, ViewGroup parent){

        View v = convertView;
        ViewHolder holder;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.list_blacksmith_requirement, null);
            holder = new ViewHolder();
            holder.textViewName = (TextView) v.findViewById(R.id.textViewName);
            holder.textViewValue = (TextView) v.findViewById(R.id.textViewValue);
            v.setTag(holder);
        }
        else {
            holder = (ViewHolder) v.getTag();
        }

        BlackSmithRequirements blackSmithRequirements = objects.get(position);

        if (blackSmithRequirements != null) {
            holder.textViewName.setText(blackSmithRequirements.getName());
            if (blackSmithRequirements.getValue() == false){
                holder.textViewValue.setText("NOT SCANNED YET");
                holder.textViewValue.setTextColor(Color.RED);
            }
            else{
                holder.textViewValue.setText("FOUND ALREADY");
                holder.textViewValue.setTextColor(Color.GREEN);
            }
        }

        return v;

    }

    static class ViewHolder {
        TextView textViewName;
        TextView textViewValue;
    }
}
