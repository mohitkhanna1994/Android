package wsa.my.com.android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class chat_Indiv extends AppCompatActivity {

    private Button btn_send_msg;
    private EditText input_msg;
    private TextView chat_conversation_right;
    private TextView chat_conversation_left;
    private String reciever;
    private String sender;
    private String path;
    private DatabaseReference root ;
    private String temp_key;
    private String chat_msg;
    private String msg_sender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_indiv);

        btn_send_msg = (Button) findViewById(R.id.btn_send);
        input_msg = (EditText) findViewById(R.id.msg_input);
        chat_conversation_right = (TextView) findViewById(R.id.chat_conversation_right);
        chat_conversation_left = (TextView) findViewById(R.id.chat_conversation_left);

        sender = FirebaseAuth.getInstance().getCurrentUser().getUid();
        reciever = getIntent().getExtras().get("reciever").toString();
        //reciever ="9RHNhnFWutbA35quG2tzWRLpMbm1";
        if (reciever.compareTo(sender) > 0) {
            path = sender + '-' + reciever;
        } else {
            path = reciever + '-' + sender;
        }
        setTitle(reciever);

        root = FirebaseDatabase.getInstance().getReference().child("Chats").child("IndividualChats").child(path);
        addNewUser();
        btn_send_msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Map<String, Object> map = new HashMap<>();
                    temp_key = root.push().getKey();
                    root.updateChildren(map);

                    DatabaseReference message_root = root.child(temp_key);
                    Map<String, Object> map2 = new HashMap<String, Object>();
                    map2.put("name", sender);
                    map2.put("msg", input_msg.getText().toString());

                    message_root.updateChildren(map2);
                    input_msg.setText("");
                }
        });
        root.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                append_chat_conversation(dataSnapshot);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                append_chat_conversation(dataSnapshot);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void addNewUser() {
        final DatabaseReference ref=FirebaseDatabase.getInstance().getReference().child("User").child(sender);
        final DatabaseReference ref2=FirebaseDatabase.getInstance().getReference().child("User").child(reciever);

        ref.addValueEventListener(new ValueEventListener() {
            String temp;
            boolean flag=true;
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterator i=dataSnapshot.getChildren().iterator();

                while(i.hasNext()){
                    temp=(((DataSnapshot)i.next()).getKey());
                    if(temp.equals(reciever))
                        flag=false;
                }
                if(flag){
                    Map<String,Object> map = new HashMap<String, Object>();
                    map.put(reciever,"");
                    ref.updateChildren(map);

                    Map<String,Object> map2 = new HashMap<String, Object>();
                    map2.put(sender,"");
                    ref2.updateChildren(map2);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void append_chat_conversation(DataSnapshot dataSnapshot) {

        Iterator i = dataSnapshot.getChildren().iterator();

        while (i.hasNext()) {

            chat_msg = (String) ((DataSnapshot) i.next()).getValue();
            msg_sender = (String) ((DataSnapshot) i.next()).getValue();



            if (msg_sender.equals(sender)) {
                chat_conversation_left.append(chat_msg + " \n");
                chat_conversation_right.append(" \n");
            } else {
                chat_conversation_right.append(chat_msg + " \n");
                chat_conversation_left.append(" \n");
            }
        }
    }
}
