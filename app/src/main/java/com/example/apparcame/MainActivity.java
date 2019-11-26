package com.example.apparcame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {
    private RequestQueue mQueue;
    private TextView textview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textview=findViewById(R.id.textView);
        Button responseButton= findViewById(R.id.response);
        mQueue= Volley.newRequestQueue(this);

        responseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jsonParse();

            }
        });




        //getDatosVolley();
        Toast.makeText(this, "OnCreate", Toast.LENGTH_SHORT).show();

    }

private void jsonParse(){
    String url="https://apparcame.000webhostapp.com/apparcamebd/apparcameClientesDatos.php";

    JsonObjectRequest request= new JsonObjectRequest(Request.Method.GET, url, null,
            new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("clientes");

                    for(int i= 0; i < jsonArray.length();i++){
                        JSONObject cliente = jsonArray.getJSONObject(i);
                        //String firstName = cliente.getString("firstaname");
                        String dniCliente = cliente.getString("dniCliente");
                        String nombreCliente = cliente.getString("nombreCliente");
                        String contrasennia=cliente.getString("contrasennia");
                        int telefonoCliente= cliente.getInt("telefonoCliente");
                        String direccion=cliente.getString("direccion");
                        String municipio=cliente.getString("municipio");
                        textview.append(dniCliente +", "+nombreCliente+", "+contrasennia+", "+String.valueOf(telefonoCliente)+", "+direccion+", "+municipio+"\n\n");
                    }
                }catch (JSONException e){
                    e.printStackTrace();
                }

                }
            }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            error.printStackTrace();
        }
    });
mQueue.add(request);
}
    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "OnResume", Toast.LENGTH_SHORT).show();


        // La actividad se ha vuelto visible (ahora se "reanuda").
    }
    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "OnPause", Toast.LENGTH_SHORT).show();
        // Enfocarse en otra actividad  (esta actividad está a punto de ser "detenida").
    }
    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "OnStop", Toast.LENGTH_SHORT).show();
        // La actividad ya no es visible (ahora está "detenida")
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "OnDestroy", Toast.LENGTH_SHORT).show();
        // La actividad está a punto de ser destruida.
    }
}