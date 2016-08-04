package room.escape.android.com.Adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

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
            holder.imageViewIcon = (ImageView) v.findViewById(R.id.imageViewIcon);
            v.setTag(holder);
        }
        else {
            holder = (ViewHolder) v.getTag();
        }

        BlackSmithRequirements blackSmithRequirements = objects.get(position);

        if (blackSmithRequirements != null) {
            holder.imageViewIcon.setImageResource(getResIDByName(blackSmithRequirements.getName().replace(" ", "").toLowerCase()));
            holder.textViewName.setText(blackSmithRequirements.getName());
            if (blackSmithRequirements.getValue() == false){
                holder.textViewValue.setText("Missing Requirement");
                holder.textViewValue.setTextColor(Color.RED);
            }
            else{
                holder.textViewValue.setText("Gathered");
                holder.textViewValue.setTextColor(Color.GREEN);
            }
        }

        return v;

    }


    private int getResIDByName(String value){
        Resources resources = getContext().getResources();
        final int resourceId = resources.getIdentifier(value, "mipmap",
                getContext().getPackageName());
        return resourceId;
    }

    static class ViewHolder {
        ImageView imageViewIcon;
        TextView textViewName;
        TextView textViewValue;
    }
}
