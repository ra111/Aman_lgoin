package com.push.ra1.login3;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;





public class Register  extends AppCompatActivity {
    static EditText name,username,password1,password2;
    Button b;
    String s,s1,s2,s3;
    public static String URL = "http://172.16.27.32/Login/message1.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        name = (EditText) findViewById(R.id.n);
        username = (EditText) findViewById(R.id.u);
        password1 = (EditText) findViewById(R.id.p1);
       password2 = (EditText) findViewById(R.id.p2);
        b = (Button) findViewById(R.id.button);
     /*   DateEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTruitonTimePickerDialog(v);
                showTruitonDatePickerDialog(v);

            }
        });
        ;*/

        b.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                s = name.getText().toString();
                s1 = username.getText().toString();
                s2 = password1.getText().toString();

                // TODO Auto-generated method stub
           if( name.getText().toString().equals("")&&username.getText().toString().equals("")&&password1.getText().toString().equals(""))
           {
               Toast.makeText(getApplicationContext(), "Enter Your Name,Username and Password", Toast.LENGTH_LONG).show();
           }
               else if(password1.getText().toString().equals(password2.getText().toString())) {


                    cancel();
                }
else{

                    Toast.makeText(getApplicationContext(), "Enter Your Password Again", Toast.LENGTH_LONG).show();
                }

            }
        });

    }


    public void cancel()
    {

        class sa extends AsyncTask<Void,Void,String>
        {
            ProgressDialog loading;



            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Register.this, "Adding...", "Wait...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(Register.this, "Account Registered", Toast.LENGTH_LONG).show();
                Intent obj=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(obj);
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put("name",s.trim());
                params.put("username",s1.trim());
                params.put("password",s2.trim());
               // params.put("message",s.trim());

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest("http://172.16.27.32/Login/message1.php", params);

                return res;
            }

        }

        sa ae =new sa();
        ae.execute();
    }
    public class RequestHandler {

        //Method to send httpPostRequest
        //This method is taking two arguments
        //First argument is the URL of the script to which we will send the request
        //Other is an HashMap with name value pairs containing the data to be send with the request
        public String sendPostRequest(String requestURL,
                                      HashMap<String, String> postDataParams) {
            //Creating a URL
            java.net.URL url;

            //StringBuilder object to store the message retrieved from the server
            StringBuilder sb = new StringBuilder();
            try {
                //Initializing Url
                url = new java.net.URL(requestURL);

                //Creating an httmlurl connection
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                //Configuring connection properties
                conn.setReadTimeout(15000);
                conn.setConnectTimeout(15000);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);

                //Creating an output stream
                OutputStream os = conn.getOutputStream();

                //Writing parameters to the request
                //We are using a method getPostDataString which is defined below
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                writer.write(getPostDataString(postDataParams));

                writer.flush();
                writer.close();
                os.close();
                int responseCode = conn.getResponseCode();

                if (responseCode == HttpsURLConnection.HTTP_OK) {

                    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    sb = new StringBuilder();
                    String response;
                    //Reading server response
                    while ((response = br.readLine()) != null){
                        sb.append(response);
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return sb.toString();
        }


        private String getPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException {
            StringBuilder result = new StringBuilder();
            boolean first = true;
            for (Map.Entry<String, String> entry : params.entrySet()) {
                if (first)
                    first = false;
                else
                    result.append("&");

                result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
                result.append("=");
                result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
            }

            return result.toString();
        }
    }
}


