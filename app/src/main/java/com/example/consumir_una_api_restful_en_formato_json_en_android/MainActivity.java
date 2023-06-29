package com.example.consumir_una_api_restful_en_formato_json_en_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import WebService.Asynchtask;
import WebService.WebService;

public class MainActivity extends AppCompatActivity implements Asynchtask {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bundle bundle = this.getIntent().getExtras();
        Map<String, String> datos = new HashMap<String, String>();
        WebService ws= new WebService(
                "https://jsonplaceholder.typicode.com/users",
                datos, MainActivity.this, MainActivity.this);
        ws.execute("GET");

    }

    @Override
    public void processFinish(String result) throws JSONException {
        String information="";
        TextView txtBienvenido =(TextView)findViewById(R.id.txt2Resultado);
        JSONArray JSONlista = new JSONArray(result);

            for(int i=0; i< JSONlista.length();i++){
                JSONObject usuario= JSONlista.getJSONObject(i);

                information+=("["+usuario.getString("id")+", "+usuario.getString("name")+", "+usuario.getString("email")+", "+usuario.getString("phone")+", "+usuario.getJSONObject("address").getString("street")+"]\n");
            }

        txtBienvenido.setText(information);

    }
}