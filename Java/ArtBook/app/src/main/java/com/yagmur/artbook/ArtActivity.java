package com.yagmur.artbook;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.yagmur.artbook.databinding.ActivityArtBinding;

import java.io.ByteArrayOutputStream;

public class ArtActivity extends AppCompatActivity {

    private ActivityArtBinding binding;
    ActivityResultLauncher<Intent> activityResultLauncher;
    ActivityResultLauncher<String> permissionLauncher;
    Bitmap selectedImage;
    SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityArtBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        registerLauncher();
        database = this.openOrCreateDatabase("Arts", MODE_PRIVATE, null);

        Intent intent = getIntent();
        String info = intent.getStringExtra("info");
        if(info.equals("new")){
            binding.artText.setText("");
            binding.artistText.setText("");
            binding.yearText.setText("");
            binding.button.setVisibility(View.VISIBLE);
            binding.imageView.setImageResource(R.drawable.selectimage);
        }else{
            int artId = intent.getIntExtra("artId", 1);
            binding.button.setVisibility(View.INVISIBLE);
            try{
                Cursor cursor = database.rawQuery("SELECT * FROM arts WHERE id = ?", new String[] {String.valueOf(artId)});
                int artNameIndex = cursor.getColumnIndex("artname");
                int artistIndex = cursor.getColumnIndex("artist");
                int yearIndex = cursor.getColumnIndex("year");
                int imageIndex = cursor.getColumnIndex("image");

                while(cursor.moveToNext()){
                    binding.artText.setText(cursor.getString(artNameIndex));
                    binding.artistText.setText(cursor.getString(artistIndex));
                    binding.yearText.setText(cursor.getString(yearIndex));
                    byte[] bytes = cursor.getBlob(imageIndex);
                    Bitmap bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
                    binding.imageView.setImageBitmap(bitmap);
                }
                cursor.close();
            }catch (Exception e){
                e.printStackTrace();

            }
        }
    }

    public void save(View view){
        String name = binding.artText.getText().toString();
        String artist = binding.artistText.getText().toString();
        String year = binding.yearText.getText().toString();

        Bitmap smaller = makeSmallerImage(selectedImage, 300);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        smaller.compress(Bitmap.CompressFormat.PNG, 50, outputStream);
        byte[] byteArr = outputStream.toByteArray();

        try{
            database.execSQL("CREATE TABLE IF NOT EXISTS arts (id INTEGER PRIMARY KEY, artname VARCHAR, artist VARCHAR, year VARCHAR, image BLOB)");
            String sql = "INSERT INTO arts (artname, artist, year, image) VALUES (?,?,?,?)";
            SQLiteStatement statement = database.compileStatement(sql);
            statement.bindString(1,name);
            statement.bindString(2,artist);
            statement.bindString(3,year);
            statement.bindBlob(4,byteArr);
            statement.execute();


        }catch (Exception e){
            e.printStackTrace();
        }

        //finish();
        Intent intent = new Intent(ArtActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public Bitmap makeSmallerImage(Bitmap image, int maxSize){
        int width = image.getWidth();
        int height = image.getHeight();
        float ratio = (float)width/(float)height;

        if(ratio>1){
            width = maxSize;
            height = (int) (width/ratio);
        }else{
            height = maxSize;
            width = (int) (height * ratio);
        }

        return image.createScaledBitmap(image, width, height, true);
    }

    public void select(View view){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            //need to request permission
            if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)){
                Snackbar.make(view, "Permission needed for gallery", Snackbar.LENGTH_INDEFINITE).setAction("Give Permission", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //request perm
                        permissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE);
                    }
                }).show(); //until any button is pressed
            }else{
                //request permission
                permissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE);
            }
        }else{
            //go gallery directly
            Intent intentToGallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            activityResultLauncher.launch(intentToGallery);
        }
    }

    private void registerLauncher(){
        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if(result.getResultCode() == RESULT_OK){
                    Intent intentFromResult = result.getData();
                    if(intentFromResult != null){
                        Uri imageData = intentFromResult.getData(); //görselin nerede kayıtlı olduğu
                        //binding.imageView.setImageURI(imageData);
                        try{
                            if(Build.VERSION.SDK_INT >=28) {
                                ImageDecoder.Source source = ImageDecoder.createSource(getContentResolver(), imageData);
                                selectedImage = ImageDecoder.decodeBitmap(source);
                                binding.imageView.setImageBitmap(selectedImage);
                            }else{
                                selectedImage = MediaStore.Images.Media.getBitmap(getContentResolver(), imageData);
                                binding.imageView.setImageBitmap(selectedImage);
                            }
                        }catch(Exception e){
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        permissionLauncher = registerForActivityResult(new ActivityResultContracts.RequestPermission(), new ActivityResultCallback<Boolean>() {
            @Override
            public void onActivityResult(Boolean result) {
                if(result){
                    //permission granted
                    Intent intentToGallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    activityResultLauncher.launch(intentToGallery);
                }else{
                    //permission denied
                    Toast.makeText(ArtActivity.this, "Permission denied!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}