package hk.ust.cse.comp107x.all_sign_up;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.systemlife.signup.Adapter.ChatAdapter;
import com.example.systemlife.signup.DataModel.ChatModel;

import java.util.ArrayList;

import static android.R.id.button2;
import static com.example.systemlife.signup.R.id.button2;
public class Main3Activity extends AppCompatActivity implements View.OnClickListener {

    ListView listView;
    TextView infoTxt;
    EditText noteText;
    Button button;
    ArrayList<ChatModel> items;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        listView=(ListView) findViewById(R.id.listView);
        items=new ArrayList<>();

        for (int i = 0; i <5 ; i++) {
            // items.add(new ChatModel(noteText.getText().toString()));
            items.add(new ChatModel("Dina"));
        }

        ChatAdapter chat=new ChatAdapter(this,items);
        listView.setAdapter(chat);

//data on infoTxt by intent
        Intent intent=getIntent();
        String text=intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        infoTxt =(TextView) findViewById(R.id.textView3);
        infoTxt.setText(text);

//rewrite button
        button=(Button) findViewById(R.id.button2);
        button.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        int id= view.getId();

        switch (id) {
            case button2:
                String s=noteText.getText().toString();
                items.add(new ChatModel(s));
                noteText.setText("");
    }
}}
