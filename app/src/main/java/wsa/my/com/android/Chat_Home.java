package wsa.my.com.android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Chat_Home extends AppCompatActivity {


    private DatabaseReference temp_key;
    private ListView listView;
    private DatabaseReference root = FirebaseDatabase.getInstance().getReference().child("User").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
    private ArrayList<String> list_of_chats = new ArrayList<>();
    private ArrayAdapter<String> arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_home);
        setTitle("Chats");
        listView = (ListView) findViewById(R.id.listView);

        arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, list_of_chats);

        listView.setAdapter(arrayAdapter);

        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Set<String> set = new HashSet<>();
                Iterator i = dataSnapshot.getChildren().iterator();

               while(i.hasNext())
                   set.add(((DataSnapshot)i.next()).getKey());

                list_of_chats.clear();
                list_of_chats.addAll(set);

                arrayAdapter.notifyDataSetChanged();
                /*if(list_of_chats==null){
                    Toast.makeText(Chat_Home.this, "No chats",
                            Toast.LENGTH_SHORT).show();
                }*/
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getApplicationContext(),chat_Indiv.class);
                intent.putExtra("reciever",((TextView)view).getText().toString());
                startActivity(intent);
            }
        });
    }
}
