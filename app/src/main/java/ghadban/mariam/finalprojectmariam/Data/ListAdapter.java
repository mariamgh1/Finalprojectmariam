package ghadban.mariam.finalprojectmariam.Data;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import ghadban.mariam.finalprojectmariam.MyUl.AddPlaceActivity;
import ghadban.mariam.finalprojectmariam.MyUl.MapActivity;
import ghadban.mariam.finalprojectmariam.MyUl.Pdetails;
import ghadban.mariam.finalprojectmariam.MyUl.SignIn;
import ghadban.mariam.finalprojectmariam.MyUl.SignUp;
import ghadban.mariam.finalprojectmariam.R;

// 1. new class             // 2. extends
public class ListAdapter extends ArrayAdapter<Place> {
    public ListAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }
    /**
     *
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        //building item view
        View vitem= LayoutInflater.from(getContext()).inflate(R.layout.place_item,parent,false);
        ImageView tvimg = vitem.findViewById(R.id.itmimg);
        TextView tvName = vitem.findViewById(R.id.itmname);
        ImageButton info = vitem.findViewById(R.id.infbtn);


        //getting data source
        final Place place = getItem(position);
        downloadImageUsingPicasso(place.getImage(), tvimg);
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), Pdetails.class);
                i.putExtra("place",place);
                getContext().startActivity(i);
            }
        });
        //connect item view to data source
        tvName.setText(place.getName());
        //todo doanload and view image
        //tvimg

       // tvimg.setText(place.getImage());
        

        return vitem;
    }

    private void downloadImageUsingPicasso(String imageUrL, ImageView toView)
    {
        Picasso.with(getContext())
                .load(imageUrL)
                .centerCrop()
                .error(R.drawable.common_full_open_on_phone)
                .resize(90,90)
                .into(toView);
    }
}



