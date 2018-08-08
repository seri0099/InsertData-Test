package com.androidjson.insertdata_androidjsoncom;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    String ServerURL = "http://netcom.ge/apps/testfolder/get_data.php" ;
    EditText device, pon, splitter, box_id, description	 ;
    Button button;
    String TempDevice, TempPon, TempSplitter, TempBox_id, TempDescription ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        device = (EditText)findViewById(R.id.editText2);
        pon = (EditText)findViewById(R.id.editText3);
        splitter = (EditText)findViewById(R.id.editText4);
        box_id = (EditText)findViewById(R.id.editText5);
        description = (EditText)findViewById(R.id.editText6);
        button = (Button)findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                GetData();

                InsertData(TempDevice, TempPon, TempSplitter, TempBox_id, TempDescription );

            }
        });
    }

   public void GetData(){

       TempDevice = device.getText().toString();

       TempPon = pon.getText().toString();

       TempSplitter = splitter.getText().toString();

       TempBox_id = box_id.getText().toString();

       TempDescription = description.getText().toString();

    }

    public void InsertData(final String device, final String pon, final String splitter, final String box_id, final String description ){

        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {

                String DeviceHolder = device ;
                String PonHolder = pon ;
                String SplitterHolder = splitter ;
                String Box_idHolder = box_id ;
                String DescriptionHolder = description ;

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

                nameValuePairs.add(new BasicNameValuePair("device", DeviceHolder));
                nameValuePairs.add(new BasicNameValuePair("pon", PonHolder));
                nameValuePairs.add(new BasicNameValuePair("splitter", SplitterHolder));
                nameValuePairs.add(new BasicNameValuePair("box_id", Box_idHolder));
                nameValuePairs.add(new BasicNameValuePair("description", DescriptionHolder));

                try {
                    HttpClient httpClient = new DefaultHttpClient();

                    HttpPost httpPost = new HttpPost(ServerURL);

                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                    HttpResponse httpResponse = httpClient.execute(httpPost);

                    HttpEntity httpEntity = httpResponse.getEntity();


                } catch (ClientProtocolException e) {

                } catch (IOException e) {

                }
                return "Data Inserted Successfully";
            }

            @Override
            protected void onPostExecute(String result) {

                super.onPostExecute(result);

                Toast.makeText(MainActivity.this, "Data Submit Successfully", Toast.LENGTH_LONG).show();

            }
        }

        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();

        sendPostReqAsyncTask.execute(device, pon, splitter, box_id, description);
    }

}
