package wsa.my.com.android;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.util.Calendar;

import model.Meta;
import model.Response;
import model.ResponseUser;
import model.UserReg;

public class Registration extends AppCompatActivity {

    UserReg user;
    Meta meta;
    String userJson,text="";
    BufferedReader reader;
    Button save;
    private Uri imageUri;
    TextView date,name,email;
    Calendar mCurrentDate;
    int day,month,year;
    ResponseUser result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        //camera access
        Button btn_camera = (Button) findViewById(R.id.button_camera);
        Button btn_gallery = (Button) findViewById(R.id.button_gallery);
        btn_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 0);
            }
        });

        //gallery access
        btn_gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });

        //date dialog model
        date = (TextView) findViewById(R.id.date);
        mCurrentDate = Calendar.getInstance();

        day = mCurrentDate.get(Calendar.DAY_OF_MONTH);
        month = mCurrentDate.get(Calendar.MONTH);
        year = mCurrentDate.get(Calendar.YEAR);
        month = month + 1;
        date.setText(day + "/" + month + "/" + year);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(Registration.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month = month + 1;
                        date.setText(dayOfMonth + "/" + month + "/" + year);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        //auto populating thr front end data through firebase
        name = (TextView) findViewById(R.id.name);
        email = (TextView) findViewById(R.id.email);
        name.setText(FirebaseAuth.getInstance().getCurrentUser().getDisplayName());
        email.setText(FirebaseAuth.getInstance().getCurrentUser().getEmail());


        //edit details
        String edit=getIntent().getExtras().get("EDIT").toString();
        if(edit.equals("edit_details")){
            populateDetails();
        }

        //save details POST web service call
        save = (Button) findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("in on click12", "on vlick");
                new JsonPostRequest().execute();
            }
        });
    }

    private void populateDetails() {
        Log.d("edit","yes");
        new JsonGetRequest().execute();
    }

    private class JsonGetRequest extends  AsyncTask<Void, Void, ResponseUser>{

        @Override
        protected ResponseUser doInBackground(Void... voids) {
            try {
                final String url = "http://106.51.100.150:7010/ws/userReg?regId=REG-USER-MOHIT-3110";
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                result = restTemplate.getForObject(url, ResponseUser.class);
                return result;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(ResponseUser result) {
            EditText address=findViewById(R.id.address);
            address.setText(result.getResult().getAddress());
            EditText phone=findViewById(R.id.phoneNumber);
            phone.setText(result.getResult().getPrimaryPhone());
            TextView date=findViewById(R.id.date);
            date.setText(result.getResult().getDob());
            EditText alterPhone=findViewById(R.id.alterPhone);
            alterPhone.setText(result.getResult().getAlterPhone());
            EditText organization=findViewById(R.id.organization);
            organization.setText(result.getResult().getCompany());
        }
    }
    private class JsonPostRequest extends AsyncTask<Void, Void, Response> {

        @Override
        protected Response doInBackground(Void... voids) {
            meta=new Meta();
            user=new UserReg();
        Spinner s1= (Spinner) findViewById(R.id.country_list);
        Spinner s2=(Spinner) findViewById(R.id.state_list);
        Spinner s3=(Spinner) findViewById(R.id.zipcode_list);
        Spinner s4=(Spinner) findViewById(R.id.maritalStatus);
        Spinner s5=(Spinner) findViewById(R.id.kids);
        Spinner s6=(Spinner) findViewById(R.id.residents);
        Spinner s7=(Spinner) findViewById(R.id.residenceType);
        Spinner s8=(Spinner) findViewById(R.id.occupationType);
        EditText address= findViewById(R.id.address);
        EditText alterPhone= findViewById(R.id.alterPhone);
        TextView name= findViewById(R.id.name);
        EditText phoneNumber=findViewById(R.id.phoneNumber);
        TextView email=findViewById(R.id.email);
        TextView dob=findViewById(R.id.date);
        EditText company=findViewById(R.id.organization);
        meta.setAction("add");
        meta.setUserChatId("dqwkvknqkjn");
        meta.setTableName("userreg");
        meta.setUserId("mohitkhanna1994@gmail.com");
        user.setAddress(address.getText().toString());
        user.setMeta(meta);
        user.setUserRegId("REG-USER-MOHIT-1111");
        user.setId("REG-USER-MOHIT-1111");
        user.setFullName(name.getText().toString());
        user.setPrimaryPhone(phoneNumber.getText().toString());
        user.setEmail(email.getText().toString());
        user.setDob(dob.getText().toString());
        user.setCompany(company.getText().toString());
        user.setAlterPhone(alterPhone.getText().toString());

        try{
        final String url="http://106.51.100.150:7010/ws/userReg";
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<?> httpEntity = new HttpEntity<Object>(user, requestHeaders);
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            Log.d("request",httpEntity.toString());
            Response response= restTemplate.postForObject(url,httpEntity,Response.class);
         return response;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
        }
    }
//    private class JsonPostRequest extends AsyncTask<Void, Void, String> {
//
//        @Override
//        protected String doInBackground(Void... voids) {
//            try {
//                String address = "http://106.51.100.150:7010/ws/userReg";
//
//                URL url = new URL(address);
//                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
//                urlConnection.setDoOutput(true);
//                urlConnection.setRequestProperty("Content-Type", "application/json");
//                OutputStream outputStream = new BufferedOutputStream(urlConnection.getOutputStream());
//                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, "utf-8"));
//                writer.write(userJson);
//                writer.flush();
//                writer.close();
//                outputStream.close();
//
//                InputStream inputStream;
//                // get stream
//                if (urlConnection.getResponseCode() < HttpURLConnection.HTTP_BAD_REQUEST) {
//                    inputStream = urlConnection.getInputStream();
//                } else {
//                    inputStream = urlConnection.getErrorStream();
//                }
//                // parse stream
//                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
//                String temp, response = "";
//                while ((temp = bufferedReader.readLine()) != null) {
//                    response += temp;
//                }
//                // put into JSONObject
//                JSONObject jsonObject = new JSONObject();
//                jsonObject.put("Content", response);
//                jsonObject.put("Message", urlConnection.getResponseMessage());
//                jsonObject.put("Length", urlConnection.getContentLength());
//                jsonObject.put("Type", urlConnection.getContentType());
//                return jsonObject.toString();
//            } catch (Exception e ) {
//                e.printStackTrace();
//                return e.toString();
//            }
//        }
//
//        @Override
//        protected void onPostExecute(String result) {
//            super.onPostExecute(result);
//            Log.i("", "POST RESPONSE: " + result);
//            //mTextView.setText(result);
//        }
//    }
//
//
//    private String createJSon() {
//        JSONObject meta=new JSONObject();
//        JSONObject jsonObj = new JSONObject();
//        Spinner s1= (Spinner) findViewById(R.id.country_list);
//        Spinner s2=(Spinner) findViewById(R.id.state_list);
//        Spinner s3=(Spinner) findViewById(R.id.zipcode_list);
//        Spinner s4=(Spinner) findViewById(R.id.maritalStatus);
//        Spinner s5=(Spinner) findViewById(R.id.kids);
//        Spinner s6=(Spinner) findViewById(R.id.residents);
//        Spinner s7=(Spinner) findViewById(R.id.residenceType);
//        Spinner s8=(Spinner) findViewById(R.id.occupationType);
//        EditText address= findViewById(R.id.address);
//        EditText alterPhone= findViewById(R.id.alterPhone);
//        TextView name= findViewById(R.id.name);
//        EditText phoneNumber=findViewById(R.id.phoneNumber);
//        TextView email=findViewById(R.id.email);
//        TextView dob=findViewById(R.id.date);
//        EditText company=findViewById(R.id.organization);
//        List<String> tasks=new ArrayList<>();
//        List<String> requests=new ArrayList<>();
//        CheckBox taskKids= findViewById(R.id.checkBox);
//        CheckBox taskBuy= findViewById(R.id.checkBox2);
//        CheckBox taskCar= findViewById(R.id.checkBox3);
//        CheckBox reqCar= findViewById(R.id.checkBox4);
//        CheckBox reqBuy= findViewById(R.id.checkBox5);
//        CheckBox reqKids= findViewById(R.id.checkBox6);
//        if(taskKids.isChecked())tasks.add("Kids Services");
//        if(taskBuy.isChecked())tasks.add("Buy Groceries");
//        if(taskCar.isChecked())tasks.add("Car Pooling");
//        if(reqKids.isChecked())requests.add("Kids Services");
//        if(reqBuy.isChecked())requests.add("Buy Groceries");
//        if(reqCar.isChecked())requests.add("Car Pooling");
//        String  preferredTask[]=new String[tasks.size()];
//        preferredTask=tasks.toArray(preferredTask);
//        String[] preferredRequets=new String[requests.size()];
//        preferredRequets=requests.toArray(preferredRequets);
//        try {
//            meta.put("action","add");
//            meta.put("userChatId","asfwfwefwef");
//            meta.put("userId","mohitkhanna1994@gmial.com");
//            meta.put("tableName","userreg");
//            jsonObj.put("address",address.getText().toString());
//            jsonObj.put("alterPhone",alterPhone.getText().toString());
//            jsonObj.put("fullName",name.getText().toString());
//            jsonObj.put("country",s1.getSelectedItem().toString());
//            jsonObj.put("state",s2.getSelectedItem().toString());
//            jsonObj.put("zipCode",s3.getSelectedItem().toString());
//            jsonObj.put("primaryPhone",phoneNumber.getText().toString());
//            jsonObj.put("email",email.getText().toString());
//          //  jsonObj.put("dob",date.getText().toString());
//            jsonObj.put("maritalStatus",s4.getSelectedItem().toString());
//            jsonObj.put("noOfKids",s5.getSelectedItem().toString());
//            jsonObj.put("noOfResidents",s6.getSelectedItem().toString());
//            jsonObj.put("company",company.getText().toString());
//            jsonObj.put("houseType",s7.getSelectedItem().toString());
//            jsonObj.put("occupation",s8.getSelectedItem().toString());
//            jsonObj.put("userRegId","REG-USER-MOHIT-4100");
//            jsonObj.put("id","REG-USER-MOHIT-4100");
//            jsonObj.put("meta",meta);
////            jsonObj.put("preferredTask",preferredTask);
////            jsonObj.put("preferredReq",preferredRequets);
//            return jsonObj.toString();
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    private void openGallery() {
        Log.d("open gallery-----","gal");
        Intent gallery=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, 100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==0) {
            try {
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");//bitmap has the image
                user.setGovtIdValue(bitmap.toString());
                Log.d("camera", bitmap.toString());
            }catch (Exception e){

            }
        }
        if(requestCode==100) {
            try {
                imageUri = data.getData();//imageUri has the gallery image
                user.setGovtIdValue(imageUri.toString());
                Log.d("gallery", imageUri.toString());
            }catch (Exception e){

            }
        }
    }
}
