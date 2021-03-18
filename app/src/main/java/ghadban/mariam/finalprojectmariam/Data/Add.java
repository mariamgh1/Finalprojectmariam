package ghadban.mariam.finalprojectmariam.Data;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import ghadban.mariam.finalprojectmariam.R;

public class Add extends AppCompatActivity {
    private ImageView Addimg;
    private EditText StoreName, Addlocation, AddCategory, Evaluation;
    private Button ttsave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        getSupportActionBar().setTitle("Add a store");

        Addimg = findViewById(R.id.Addimg);
        StoreName = findViewById(R.id.StoreName);
        Addlocation = findViewById(R.id.Addlocation);
        AddCategory = findViewById(R.id.AddCategory);
        Evaluation = findViewById(R.id.Evaluation);
        ttsave = findViewById(R.id.ttsave);

        Addimg.setOnClickListener(new View.OnClickListener()
    {
            @Override
            public void onClick(View view) {
                //check runtime permission
                if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M) {
                    if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                        //permission not granted, request it.
                        String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
                        //show popup for runtime permission
                        requestPermissions(permissions, PERMISSION_CODE);
                    }
                    else{
                        //permission already granted
                        pickImageFromGallery();
                    }

                }
            }
        });
    }

    private void pickImageFromGallery(){
        //intent to pick image
        Intent intent=new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent,IMAGE_PICK_CODE);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults){
        switch (requestCode){
            case PERMISSION_CODE:{
                if(grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED){
                    //permission was granted
                    pickImageFromGallery();
                }
                else {
                    //permission was denied
                    Toast.makeText(this,"Permission denied...!",Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    //handle result of picked images
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if (resultCode==RESULT_OK && requestCode== IMAGE_PICK_CODE){
            //set image to image view
            Addimg.setImageURI(data.getData());
        }
    }

}
