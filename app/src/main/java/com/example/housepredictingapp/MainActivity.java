package com.example.housepredictingapp;

import  androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.housepredictingapp.ml.CropPredictionModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText noOfBedroom;
    EditText noOfBathroom;
    EditText area;
    EditText noOfStories;
    EditText mainRoad;
    EditText guestRoom;
    EditText basement;
    EditText hotWater;
    EditText airConditioning;
    EditText parking;
    EditText prefArea;
    EditText furnishingStatus;
    TextView price;
    Button submitButton;
    String url = "https://predict-price-of-house.onrender.com/predict";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        area = findViewById(R.id.house_area);
        noOfBedroom = findViewById(R.id.bedroom);
        noOfBathroom = findViewById(R.id.bathroom);
        noOfStories = findViewById(R.id.stories);
        mainRoad = findViewById(R.id.mainroad);
        guestRoom = findViewById(R.id.guestroom);
        basement = findViewById(R.id.basement);
        hotWater = findViewById(R.id.water);
        airConditioning = findViewById(R.id.ac);
        parking = findViewById(R.id.parking);
        prefArea = findViewById(R.id.prefarea);
        furnishingStatus = findViewById(R.id.status);
        price = findViewById(R.id.price);
        submitButton = findViewById(R.id.btn_submit);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject jsonObject= new JSONObject(response);
                                    String ans = jsonObject.getString("price");
                                    price.setText(ans);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this,error.getMessage(),Toast.LENGTH_SHORT);
                    }
                }){
                    @Override
                    protected Map<String,String> getParams(){
                        Map<String,String> params = new HashMap<String,String>();
                        params.put("area",area.getText().toString());
                        params.put("noOfBedroom",noOfBedroom.getText().toString());
                        params.put("noOfBathroom",noOfBathroom.getText().toString());
                        params.put("noOfStories",noOfStories.getText().toString());
                        params.put("mainRoad",mainRoad.getText().toString());
                        params.put("guestRoom",guestRoom.getText().toString());
                        params.put("basement",basement.getText().toString());
                        params.put("hotWaterHeating",hotWater.getText().toString());
                        params.put("airConditioning",airConditioning.getText().toString());
                        params.put("parking",parking.getText().toString());
                        params.put("prefarea",prefArea.getText().toString());
                        params.put("furnishingstatus",furnishingStatus.getText().toString());
                        return params;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
                requestQueue.add(stringRequest);
            }
        });
    }


}