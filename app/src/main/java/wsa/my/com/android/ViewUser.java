package wsa.my.com.android;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import model.ResponseUser;
import model.UserReg;

public class ViewUser extends AppCompatActivity {

    ResponseUser result;
    UserReg user;

    public UserReg getUser() {
        return user;
    }

    public void setUser(UserReg user) {
        this.user = user;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_user);
        user = new UserReg();
        result = new ResponseUser();
        new HttpRequestTask().execute();

        FloatingActionButton editUser = (FloatingActionButton) findViewById(R.id.edit_details);
        editUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("onclick","clivk");
                editDetails();
            }
        });
    }

    private void editDetails() {
        Intent intent=new Intent(this,Registration.class);
        intent.putExtra("EDIT","edit_details");
        startActivity(intent);

    }

    private class HttpRequestTask extends AsyncTask<Void, Void, ResponseUser> {

        @Override
        protected ResponseUser doInBackground(Void... voids) {
            try {
                final String url = "http://106.51.100.150:7010/ws/userReg?regId=REG-USER-MOHIT-1111";
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                result = restTemplate.getForObject(url, ResponseUser.class);
                Log.d("cals fetched", "" + result);
                return result;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(ResponseUser result) {
            try {
                user = result.getResult();
                TextView name = findViewById(R.id.name);
                TextView address = findViewById(R.id.address);
                TextView phone = findViewById(R.id.phone);
                TextView email = findViewById(R.id.email);
                TextView houseType = findViewById(R.id.houseType);
                TextView maritalStatus = findViewById(R.id.maritalStatus);
                TextView kids = findViewById(R.id.kids);
                TextView residents = findViewById(R.id.residents);
                TextView requests = findViewById(R.id.requests);
                TextView tasks = findViewById(R.id.tasks);
                TextView occupation = findViewById(R.id.occupation);
                name.setText(user.getFullName());
                address.setText(user.getAddress());
                phone.setText(user.getPrimaryPhone());
                email.setText(user.getEmail());
                houseType.setText(user.getHouseType());
                maritalStatus.setText(user.getMaritalStatus());
                kids.setText(String.valueOf(user.getNoOfKids()));
                residents.setText(String.valueOf(user.getNoOfResidents()));
                String req[] = user.getPreferredReq();
                String preferredReq = "";
                for (String s : req)
                    preferredReq += s;
                requests.setText(preferredReq);
                String tsk[] = user.getPreferredTask();
                String preferredtask = "";
                for (String s : tsk)
                    preferredtask += s;
                tasks.setText(preferredtask);
                occupation.setText(user.getCompany());
            }catch (Exception e){

            }
        }

    }
}