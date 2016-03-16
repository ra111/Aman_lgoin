package com.push.ra1.login3;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    String url = "http://172.16.27.32/Login/login.php";
    String un,pd;
    Button bt;
    EditText username,password;
    ProgressDialog PDlogin;
    String iddisplay;
    String fundsent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    void initViews() {
        username = (EditText) findViewById(R.id.un);
        password =(EditText)findViewById(R.id.pd);
        PDlogin = new ProgressDialog(this);
        PDlogin.setMessage("Loading.....");
        bt=(Button)findViewById(R.id.b1);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             Intent obj=new Intent(getApplicationContext(),Register.class);
            startActivity(obj);
            }
        });

    }


    public void logincheck(View v) {

        PDlogin.show();
        un = username.getText().toString();
        pd= password.getText().toString();
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        String msg = response;
                        JSONObject jsonObject = null;
                        int b = 0;

                        try {
                            jsonObject = new JSONObject(response);
                            b = jsonObject.getInt("success");
                            iddisplay = String.valueOf(b);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        if (b >0) {
                          PDlogin.setMessage("Logged in");
                            Intent intent = new Intent(getApplicationContext(), Register.class);
                            startActivity(intent);
                        } else {
                          PDlogin.setMessage("Invalid Username or password");
                        }
                        username.setText("");
                        password.setText("");
                        Toast.makeText(getApplicationContext(),
                                "Connected successfully",
                                Toast.LENGTH_SHORT).show();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                PDlogin.dismiss();
                Toast.makeText(getApplicationContext(),
                        "Failed to connect", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("username", un);
                params.put("password", pd);
                return params;
            }
        };

        // Adding request to request queue
        MyApplication.getInstance().addToReqQueue(postRequest);
    }


}



















/*
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    public static final String LOGIN_URL = "http://172.16.27.32/Login/log.php";

    public static final String KEY_USERNAME="username";
    public static final String KEY_PASSWORD="password";

    private EditText editTextUsername;
    private EditText editTextPassword;
    private Button buttonLogin,bt;

    private String username;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextUsername = (EditText) findViewById(R.id.un);
        editTextPassword = (EditText) findViewById(R.id.pd);

        buttonLogin = (Button) findViewById(R.id.b2);
        bt = (Button) findViewById(R.id.b1);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent obj=new Intent(getApplicationContext(),Register.class);
                startActivity(obj);
            }
        });
        buttonLogin.setOnClickListener(this);
    }


    private void userLogin() {
        username = editTextUsername.getText().toString().trim();
        password = editTextPassword.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, LOGIN_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.trim().equals("success")){
                            openProfile();
                        }else  {
                            Toast.makeText(MainActivity.this,"Not Login",Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this,error.toString(),Toast.LENGTH_LONG ).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<String,String>();
                map.put(KEY_USERNAME,username);
                map.put(KEY_PASSWORD,password);
                return map;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void openProfile(){
        Intent intent = new Intent(this, Register.class);
      //  intent.putExtra(KEY_USERNAME, username);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        userLogin();
    }
}
*/