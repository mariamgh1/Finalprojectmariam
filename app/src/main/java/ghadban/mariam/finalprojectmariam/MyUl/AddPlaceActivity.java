package ghadban.mariam.finalprojectmariam.MyUl;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.UUID;

import ghadban.mariam.finalprojectmariam.Data.Place;
import ghadban.mariam.finalprojectmariam.R;


public class AddPlaceActivity extends AppCompatActivity {
    private static final int PERMISSION_CODE = 100;
    private static final int IMAGE_PICK_CODE = 101;


    private ImageView Addimg;
    private EditText StoreName, Addlocation, AddCategory, Evaluation;
    private Button ttsave;
    private Uri toUploadimageUri;//local address
    private Uri downladuri;//firebase/cloude address
    private Place place;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        getSupportActionBar().setTitle("Add a store");

        Addimg = findViewById(R.id.Addimg);
        StoreName = findViewById(R.id.StoreName);
        Addlocation = findViewById(R.id.Addlocation);
        AddCategory = findViewById(R.id.Addcategory);
        Evaluation = findViewById(R.id.evlauation);
        ttsave = findViewById(R.id.ttsave);

        ttsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkValidateForm();
            }
        });
        Addimg.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View view){
                //check runtime permission
                Toast.makeText(AddPlaceActivity.this, "image", Toast.LENGTH_SHORT).show();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                        //permission not granted, request it.
                        String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
                        //show popup for runtime permission
                        requestPermissions(permissions, PERMISSION_CODE);
                    } else {
                        //permission already granted
                        pickImageFromGallery();
                    }

                }
            }
        });
    }

    public void checkValidateForm() {
        String sname = StoreName.getText().toString();
        String location = Addlocation.getText().toString();
        String category = AddCategory.getText().toString();
        double evlu = Double.parseDouble(Evaluation.getText().toString());
        boolean isok = true;
        if (sname.length() < 2) {
            isok = false;
            StoreName.setError("at least two char");
        }
        if (location.length() < 3) {
            isok = false;
            Addlocation.setError("at least 3 char");
        }
        if (category.length() < 3) {
            isok = false;
            AddCategory.setError("at least one word");
        }
        if (evlu < 2) {
            isok = false;
            Evaluation.setError("maximum 5 points");
        }
        if(toUploadimageUri == null){
            isok = false;
            Toast.makeText(this, "choose image", Toast.LENGTH_SHORT).show();
        }
        if(isok){
            place=new Place();
            place.setName(sname);
            place.setLocation(location);
            place.setCategory(category);
            place.setEvaluation(evlu);
            uploadImage(toUploadimageUri);
    }



}

    private void savePlace(Place place) {
        //1.
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        //2.
        DatabaseReference reference = database.getReference();
        //3. user id
        FirebaseAuth auth=FirebaseAuth.getInstance();
        String uid = auth.getCurrentUser().getUid();
        //4. My Object Key
        String key = reference.child("Places").push().getKey();
        //5. update your Object
        place.setOwner(uid);
        place.setKey(key);
        //6. actual stroring
        reference.child("Places").child(uid).child(key).setValue(place).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()) {
                    Toast.makeText(AddPlaceActivity.this, "add successful", Toast.LENGTH_SHORT).show();
                    finish();
                }
                else {
                    Toast.makeText(AddPlaceActivity.this, "add failed"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    task.getException().printStackTrace();
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
             toUploadimageUri = data.getData();
            Addimg.setImageURI(toUploadimageUri);
        }
    }
    //upload: 5
    private void uploadImage(Uri filePath) {

        if(filePath != null)
        {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();
            FirebaseStorage storage= FirebaseStorage.getInstance();
            StorageReference storageReference = storage.getReference();
            final StorageReference ref = storageReference.child("images/"+ UUID.randomUUID().toString());
            ref.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            ref.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                                @Override
                                public void onComplete(@NonNull Task<Uri> task) {
                                    downladuri = task.getResult();
                                    place.setImage(downladuri.toString());
                                    savePlace(place);

                                }
                            });

                            Toast.makeText(getApplicationContext(), "Uploaded", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot
                                    .getTotalByteCount());
                            progressDialog.setMessage("Uploaded "+(int)progress+"%");
                        }
                    });
        }
    }


}
