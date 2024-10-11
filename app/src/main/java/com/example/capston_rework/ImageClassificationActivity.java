package com.example.capston_rework;



import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.capston_rework.ml.Model;

import org.tensorflow.lite.DataType;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class ImageClassificationActivity extends AppCompatActivity {

    Button camera, gallery, displayInUnityButton;
    ImageView imageView;
    TextView result;


    ProgressBar progressBar;
    int imageSize = 224;
    private String currentModelName = ""; // This field will store the last recognized model name

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_classification);

        VehicleModelDatabase database = VehicleModelDatabase.getVehicleModelDatabase(getApplicationContext());
        VehicleArmorCostDao vehicleArmorCostDao = database.vehicleArmorCostDao();


        VehicleArmorCost fordRangerPickUp = new VehicleArmorCost(
                "Ford Ranger",
                2400000, 180000, 600000, 140000, 15000, 30000, 30000, 80000, 18000, 15000, 3500, 150000, 15000, 60000,
                2600000, 650000, 800000, 140000, 15000, 30000, 30000, 80000, 18000, 15000, 3500, 150000, 15000, 80000
        );

        VehicleArmorCost fordEverestSUV = new VehicleArmorCost(
                "Ford Everest",
                2400000, 252000, 600000, 140000, 15000, 45000, 65000, 80000, 18000, 15000, 3500, 250000, 15000, 60000,
                2800000, 628000, 900000, 140000, 15000, 45000, 65000, 80000, 18000, 15000, 3500, 250000, 15000, 80000
        );

        VehicleArmorCost toyotaLandCruiserSUV = new VehicleArmorCost(
                "Toyota LandCruiser",
                2400000, 252000, 600000, 140000, 15000, 45000, 65000, 80000, 18000, 15000, 3500, 250000, 15000, 60000,
                2800000, 628000, 900000, 140000, 15000, 45000, 65000, 80000, 18000, 15000, 3500, 250000, 15000, 80000
        );

        VehicleArmorCost hiluxConquestPickUp = new VehicleArmorCost(
                "Hilux Conquest",
                2400000, 180000, 600000, 140000, 15000, 30000, 30000, 80000, 18000, 15000, 3500, 150000, 15000, 60000,
                2600000, 650000, 800000, 140000, 15000, 30000, 30000, 80000, 18000, 15000, 3500, 150000, 15000, 80000
        );

        VehicleArmorCost fordExpeditionSUV = new VehicleArmorCost(
                "Ford Expedition",
                2400000, 252000, 600000, 140000, 15000, 45000, 65000, 80000, 18000, 15000, 3500, 250000, 15000, 60000,
                2800000, 628000, 900000, 140000, 15000, 45000, 65000, 80000, 18000, 15000, 3500, 250000, 15000, 80000
        );

        VehicleArmorCost toyotaFortuner = new VehicleArmorCost(
                "Toyota Fortuner",
                2400000, 252000, 600000, 140000, 15000, 45000, 65000, 80000, 18000, 15000, 3500, 250000, 15000, 60000,
                2800000, 628000, 900000, 140000, 15000, 45000, 65000, 80000, 18000, 15000, 3500, 250000, 15000, 80000
        );


        new Thread(() -> {
            if(vehicleArmorCostDao.isDatabaseEmpty() != "Ford Ranger"){
                vehicleArmorCostDao.insert(fordRangerPickUp);
                vehicleArmorCostDao.insert(fordEverestSUV);
                vehicleArmorCostDao.insert(toyotaLandCruiserSUV);
                vehicleArmorCostDao.insert(hiluxConquestPickUp);
                vehicleArmorCostDao.insert(fordExpeditionSUV);
                vehicleArmorCostDao.insert(toyotaFortuner);
            }
        }).start();


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });

        camera = findViewById(R.id.picture);
        gallery = findViewById(R.id.button2);
        result = findViewById(R.id.tv_result);
        imageView = findViewById(R.id.iv_car_image_level6);

        //when camera button clicked it will request a permission
        camera.setOnClickListener(view -> {
            if (checkSelfPermission(android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                startActivityForResult(new Intent(MediaStore.ACTION_IMAGE_CAPTURE), 3);
            } else {
                requestPermissions(new String[]{Manifest.permission.CAMERA}, 100);
            }
        });
        // this is for button gallery so the user can take a choose a photo from gallery
        gallery.setOnClickListener(view -> startActivityForResult(new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI), 1));




    }


    public void classifyImage(Bitmap image) {
        try {
            Model model = Model.newInstance(getApplicationContext());
            TensorBuffer inputFeature0 = TensorBuffer.createFixedSize(new int[]{1, 224, 224, 3}, DataType.FLOAT32);
            ByteBuffer byteBuffer = ByteBuffer.allocateDirect(4 * imageSize * imageSize * 3);
            byteBuffer.order(ByteOrder.nativeOrder());

            int[] intValues = new int[imageSize * imageSize];
            image.getPixels(intValues, 0, image.getWidth(), 0, 0, image.getWidth(), image.getHeight());
            int pixel = 0;
            for (int i = 0; i < imageSize; i++) {
                for (int j = 0; j < imageSize; j++) {
                    int val = intValues[pixel++]; // RGB
                    byteBuffer.putFloat(((val >> 16) & 0xFF) * (1.f / 255));
                    byteBuffer.putFloat(((val >> 8) & 0xFF) * (1.f / 255));
                    byteBuffer.putFloat((val & 0xFF) * (1.f / 255));
                }
            }

            inputFeature0.loadBuffer(byteBuffer);
            Model.Outputs outputs = model.process(inputFeature0);
            TensorBuffer outputFeature0 = outputs.getOutputFeature0AsTensorBuffer();
            float[] confidences = outputFeature0.getFloatArray();
            float maxConfidence = 0;
            int maxPos = 0;
            for (int i = 0; i < confidences.length; i++) {
                if (confidences[i] > maxConfidence) {
                    maxConfidence = confidences[i];
                    maxPos = i;
                }
            }

            float confidenceThreshold = 0.8f; // Confidence threshold
            if (maxConfidence >= confidenceThreshold) {
                // Save the model name and image to SharedPreferences
                currentModelName = getResources().getStringArray(R.array.car_models)[maxPos];
                SharedPreferences sharedPref = getSharedPreferences("AppData", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("CarModelName", currentModelName);
                editor.putString("CarImage", bitmapToString(image));
                editor.apply();
                // progressBar.setProgress((int) (maxConfidence * 100));
                result.setText(String.format("%s (%.2f%% confidence)", currentModelName, maxConfidence * 100));
                //displayInUnityButton.setVisibility(View.VISIBLE);

                // Open MainActivity2
                Intent intent = new Intent(ImageClassificationActivity.this, PaymentOptionsActivity.class);
                startActivity(intent);
//displayInUnityButton.setText("Display " + currentModelName + " in Unity");
            } else {
                result.setText(String.format("Try again. Confidence level (%.2f%%) is too low.", maxConfidence * 100));
                //displayInUnityButton.setVisibility(View.GONE);
                Toast.makeText(this, "Low confidence. Please try another image.", Toast.LENGTH_SHORT).show();

            }
        } catch (IOException e) {
            e.printStackTrace();
            result.setText("Failed to process the image.");
        }

    }
    private String bitmapToString(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(byteArray, Base64.DEFAULT);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            Bitmap image = null;
            if (requestCode == 3 && data.getExtras() != null) {
                image = (Bitmap) data.getExtras().get("data");
            } else if (requestCode == 1) {
                Uri dat = data.getData();
                try {
                    image = MediaStore.Images.Media.getBitmap(this.getContentResolver(), dat);
                    image = rotateImageIfRequired(image, dat); // Rotate the image if required
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (image != null) {
                // Automatically detect the orientation and fill the ImageView
                imageView.setImageBitmap(image); // Set image in ImageView
                image = Bitmap.createScaledBitmap(image, imageSize, imageSize, false); // Resize the image for model classification
                classifyImage(image);
            }
        }
    }

    private Bitmap rotateImageIfRequired(Bitmap img, Uri selectedImage) throws IOException {
        InputStream input = getContentResolver().openInputStream(selectedImage);
        ExifInterface ei = new ExifInterface(input);

        int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_UNDEFINED);

        switch (orientation) {
            case ExifInterface.ORIENTATION_ROTATE_90:
                return rotateImage(img, 90);
            case ExifInterface.ORIENTATION_ROTATE_180:
                return rotateImage(img, 180);
            case ExifInterface.ORIENTATION_ROTATE_270:
                return rotateImage(img, 270);
            default:
                return img;
        }
    }

    private Bitmap rotateImage(Bitmap source, float angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix, true);
    }



}