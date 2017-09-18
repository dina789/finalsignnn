package hk.ust.cse.comp107x.all_sign_up;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.photo)
    Button takePhoto;
    @BindView(R.id.gallery)
    Button fromGallery;
    @BindView(R.id.send)
    Button sendMail;
    @BindView(R.id.call)
    Button callNumber;
    @BindView(R.id.share)
    Button shareWhatsApp;
    @BindView(R.id.signup)
    Button sign;
    @BindView(R.id.firstname)
    EditText first;
    @BindView(R.id.lastname)
    EditText last;
    @BindView(R.id.email)
    EditText mail;
    @BindView(R.id.phone)
    EditText phoneNumber;
    @BindView(R.id.msg)
    EditText message;
    @BindView(R.id.imageView)
    ImageView image;
    @BindView(R.id.pik)
    Button btnPikContact;
    @BindView(R.id.showNumber)
    TextView showText;
    @BindView(R.id.male)
    RadioButton maleRadio;
    @BindView(R.id.female)
    RadioButton femaleRadio;
    @BindView(R.id.sign2)
    Button btnSign2;

    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int REQUEST_PICK_IMAGE = 55;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        takePhoto.setOnClickListener(this);
        fromGallery.setOnClickListener(this);
        shareWhatsApp.setOnClickListener(this);
        sendMail.setOnClickListener(this);
        callNumber.setOnClickListener(this);
        sign.setOnClickListener(this);
        btnPikContact.setOnClickListener(this);
        maleRadio.setOnClickListener(this);
        femaleRadio.setOnClickListener(this);
        btnSign2.setOnClickListener(this);
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            image.setImageBitmap(imageBitmap);
        } else if (requestCode == REQUEST_PICK_IMAGE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            if (extras != null) {
                //Get image
                Bitmap newProfilePic = extras.getParcelable("data");
                image.setImageBitmap(newProfilePic);
            }
            else {
                Toast.makeText(this,"you didn't pick a picture",Toast.LENGTH_LONG).show();
            }
        }
    }

    public final static boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }

    private boolean isValidMobile(String phone) {
        boolean check = false;
        if (!Pattern.matches("[a-zA-Z]+", phone)) {
            if (phone.length() < 6 || phone.length() > 13) {
                // if(phone.length() != 10) {
                check = false;
                phoneNumber.setError("Not Valid Number");
            } else {
                check = true;
            }
        } else {
            check = false;
        }
        return check;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.photo://finished
                dispatchTakePictureIntent();
                break;

            case R.id.gallery://finished
                Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                gallery.setType("image/*");
                startActivityForResult(gallery,55);
                break;

            case R.id.send://finished
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                // The intent does not have a URI, so declare the "text/plain" MIME type
                emailIntent.setType("text/plain");
                if (mail.getText().toString().equals(null)) {
                    mail.setError("you should write mail");
                } else if (isValidEmail(mail.getText().toString())) {
                    emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{mail.getText().toString()}); // recipients
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, first.getText().toString() + " " +
                            last.getText().toString());
                    emailIntent.putExtra(Intent.EXTRA_TEXT, "Hellow\n" + "i am " + first.getText().toString() +
                            " " + last.getText().toString() + "\n and my mail is " + mail.getText().toString() +
                            "\n you can call me on " + phoneNumber.getText().toString() +
                            "\n and i agree to take the course\n" + message.getText().toString());
                    emailIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse("content://path/to/email/attachment"));
                    // You can also attach multiple items by passing an ArrayList of Uris
                    startActivity(emailIntent);
                } else mail.setError("the E-mail is not valid");

                break;

            case R.id.call://finished
                if (isValidMobile(phoneNumber.getText().toString())) {
                    Uri number = Uri.parse("tel:" + phoneNumber.getText().toString());
                    Intent callIntent = new Intent(Intent.ACTION_DIAL, number);
                    startActivity(callIntent);
                }
                break;

            case R.id.pik://finished
//                Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
//                startActivityForResult(intent,PICK_CONTACT);
                Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                startActivityForResult(intent, 7);
                break;

            case R.id.share://finished
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT,
                        "hey i am " + first.getText().toString()
                                + " " + last.getText().toString()
                                + ", i am using WhatsApp, you can text me on it");
                sendIntent.setType("text/plain");
                // sendIntent.setData(Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.com.WhatsApp"));
                //startActivity(Intent.createChooser(sendIntent, ""));
                sendIntent.setPackage("com.whatsapp");
                startActivity(sendIntent);
                break;

            case R.id.signup://finished
                intent = new Intent(this, Main2Activity.class);
                String msg2 = "Hellow\n" + "i am " + first.getText().toString() +
                        " " + last.getText().toString() + "\n and my mail is " + mail.getText().toString() +
                        "\n you can call me on " + phoneNumber.getText().toString() +
                        "\n and i agree to take the course\n" + message.getText().toString();
                intent.putExtra(EXTRA_MESSAGE, msg2);
                startActivity(intent);
                break;

            case R.id.sign2://finished
                Intent i = new Intent(this, Main3Activity.class);
                String text = "Hellow\n" + "i am " + first.getText().toString() +
                        " " + last.getText().toString() + "\n and my notes are: ";
                i.putExtra(EXTRA_MESSAGE, text);
                startActivity(i);
                break;

        }

        // change visibilty of ListView to gone and GridView to visible and vice versa
    }
}




