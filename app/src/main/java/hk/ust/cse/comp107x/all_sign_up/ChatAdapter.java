package hk.ust.cse.comp107x.all_sign_up;

import android.view.LayoutInflater;
import android.widget.CompoundButton;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.systemlife.signup.DataModel.ChatModel;
import com.example.systemlife.signup.R;

import java.util.List;

/**
 * Created by System.Life on 9/12/2017.
 */

public class ChatAdapter extends ArrayAdapter<ChatModel>{

    Button addButton;
    TextView textView;
    CheckBox agreeCheck;

    public ChatAdapter(@NonNull Context context, @NonNull List<ChatModel> Objects) {
        super(context, 0, Objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ChatModel chatModel = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_row, parent, false);
        }

        textView = convertView.findViewById(R.id.textView);
        addButton = convertView.findViewById(R.id.button);
        agreeCheck = convertView.findViewById(R.id.checkBox2);
        agreeCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b=true){
                    addButton.setText("Done");
                }
            }
        });
        textView.setText(chatModel.getBody());
        return convertView;

    }



}



