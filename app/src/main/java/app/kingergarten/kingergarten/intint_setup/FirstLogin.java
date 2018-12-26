package app.kingergarten.kingergarten.intint_setup;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.regex.Pattern;

import app.kingergarten.kingergarten.admin.add_users.AddUsers;
import app.kingergarten.kingergarten.users.Navigation.Navigation;
import app.kingergarten.kingergarten.R;
import app.kingergarten.kingergarten.admin.navigation_admin.Navigation_admin;

public class FirstLogin extends AppCompatActivity {
    private Button login,reset;
    private EditText email , password;
    private String mEmail, mPass;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private FirebaseUser firebaseUser;
    private AddUsers addUsers;
    private String current_id;

    public static final Pattern EMAIL_ADDRESS_PATTERN = Pattern.compile(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+"
    );
    private boolean checkEmail(String email) {
        return EMAIL_ADDRESS_PATTERN.matcher(email).matches();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_login);

        firebaseAuth=FirebaseAuth.getInstance();
        email = findViewById(R.id.l_email);
        password = findViewById(R.id.l_password);
        reset = findViewById(R.id.res_pass);
        login = findViewById(R.id.login_button);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(FirstLogin.this,Navigation.class);
                startActivity(intent);
//                mEmail=email.getText().toString().trim();
//                mPass=password.getText().toString().trim();
//
//                if (TextUtils.isEmpty(mEmail)) {
//                    email.setError("Please Enter your email!!");
//                    return;
//                }
//
//                if (TextUtils.isEmpty(mPass)) {
//                    password.setError("Please Enter yourpassword!!");
//                    return;
//                }
//                if(!checkEmail(mEmail)){
//                    Toast.makeText(FirstLogin.this,"Email is not valid ",Toast.LENGTH_LONG).show();
//                    return;
//                }
//
//
//                firebaseAuth.signInWithEmailAndPassword(mEmail,mPass).addOnCompleteListener(FirstLogin.this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if(task.isSuccessful()){
//
//                            if(mEmail.equals("admin@gmail.com") && mPass.equals("123456")){
//                                Toast.makeText(FirstLogin.this,"Successful Login",Toast.LENGTH_LONG).show();
//                                Intent intent=new Intent(FirstLogin.this,Navigation_admin.class);
//                                startActivity(intent);
//                            }
//                            else {
//                                firebaseUser=FirebaseAuth.getInstance().getCurrentUser();
//                                current_id=firebaseUser.getUid();
//
//                                firebaseDatabase =FirebaseDatabase.getInstance();
//                                databaseReference=firebaseDatabase.getReference("users");
//                                databaseReference.addValueEventListener(new ValueEventListener() {
//                                    @Override
//                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                                        for(DataSnapshot snap : dataSnapshot.getChildren()){
//                                            addUsers=snap.getValue(AddUsers.class);
//                                            if(addUsers.getCurrent_id().equals(current_id)){
//                                                if(addUsers.getType().equals("Teacher")){
//                                                    Intent intent=new Intent(FirstLogin.this,Navigation_admin.class);
//                                                    startActivity(intent);
//                                                }
//                                                else if(addUsers.getType().equals("Parent")){
//                                                    Intent intent1=new Intent(FirstLogin.this,Navigation.class);
//                                                    startActivity(intent1);
//
//                                                }
//                                            }
//
//                                        }
//                                    }
//
//                                    @Override
//                                    public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                                    }
//                                });
//
//
//                            }
//                            }
//                           else{
//                            Toast.makeText(FirstLogin.this,"UnAuthenticated user",Toast.LENGTH_LONG).show();
//                        }
//                    }
//                }).addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        e.printStackTrace();
//                    }
//                });
//
//
            }
        });

        reset.setOnClickListener(new View.OnClickListener(){

                   @Override
                   public void onClick(View v) {


                        mEmail=email.getText().toString().trim();
                        mPass=password.getText().toString().trim();

                        firebaseAuth.sendPasswordResetEmail(mEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(FirstLogin.this,"Reset Successfuly",Toast.LENGTH_LONG).show();
                                }
                                else {
                                    Toast.makeText(FirstLogin.this,"Reset UNSuccessfuly",Toast.LENGTH_LONG).show();
                                }
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                e.printStackTrace();
                            }
                        });
                 }
               });

        }

}
