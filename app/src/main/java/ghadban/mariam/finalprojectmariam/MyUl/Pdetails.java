package ghadban.mariam.finalprojectmariam.MyUl;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import ghadban.mariam.finalprojectmariam.Data.Place;
import ghadban.mariam.finalprojectmariam.R;

public class Pdetails extends AppCompatActivity {

    private ImageView infoImg;
    private TextView infoName, infoLocation, infoCategory, infoEvaluation;
    private Button go;
    private Place p ;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdetails);


        infoImg = findViewById(R.id.infimg);
        infoName=findViewById(R.id.infnm);
        infoName = findViewById(R.id.infnm);
        infoLocation = findViewById(R.id.infloc);
        infoCategory = findViewById(R.id.infcat);
        infoEvaluation = findViewById(R.id.infeva);
        go = findViewById(R.id.gobtn);

        go.setOnClickListener(v -> { // from button to place location on google map
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("geo:"+p.getLat()+","+p.getLng()));
            Intent chooser = Intent.createChooser(intent,"Lauch Maps");
            startActivity(chooser);
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        if(intent!= null){
             p = (Place) intent.getExtras().get("place");
            infoName.setText("Name : "+ p.getName());
            infoLocation.setText("Location : "+ p.getLocation());
            infoCategory.setText("Category : "+ p.getCategory());
            infoEvaluation.setText("Evaluation (1-5) : "+ p.getEvaluation());
            downloadImageUsingPicasso(p.getImage(), infoImg);

        }


    }
    private void downloadImageUsingPicasso(String imageUrL, ImageView toView)
    {
        Picasso.with(this)
                .load(imageUrL)
                .centerCrop()
                .error(R.drawable.common_full_open_on_phone)
                .resize(90,90)
                .into(toView);
    }
}
