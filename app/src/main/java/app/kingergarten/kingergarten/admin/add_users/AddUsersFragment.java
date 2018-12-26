package app.kingergarten.kingergarten.admin.add_users;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

import app.kingergarten.kingergarten.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AddUsersFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AddUsersFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddUsersFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private EditText civil_id, email, password;
    private Spinner spinner;
    private String mcivil_id, memail,mpassword,mtype;
    private AddUsers addUsers;
    private Button add;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private FirebaseUser firebaseUser;
    private String current_id;

    private  String type[] ={"Parent", "Teacher"};
    private ArrayAdapter<String> arrayAdapter;

    private OnFragmentInteractionListener mListener;

    public AddUsersFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddUsersFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddUsersFragment newInstance(String param1, String param2) {
        AddUsersFragment fragment = new AddUsersFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_add_users, container, false);

        spinner= view.findViewById(R.id.add_spinner_type);
        spinner.setAdapter(new ArrayAdapter<String>(getActivity().getApplicationContext(),
                android.R.layout.simple_list_item_1 , type));

        firebaseAuth=FirebaseAuth.getInstance();
        civil_id= view.findViewById(R.id.add_civil_id);
        email= view.findViewById(R.id.add_email);
        password= view.findViewById(R.id.add_pass);
        add= view.findViewById(R.id.add_button);

        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference= firebaseDatabase.getReference("users");

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mcivil_id=civil_id.getText().toString().trim();
                memail=email.getText().toString().trim();
                mpassword=password.getText().toString().trim();
                mtype=spinner.getSelectedItem().toString().trim();

                if (TextUtils.isEmpty(mcivil_id)) {
                    civil_id.setError("Please Enter your civil_id!!");
                    return;
                }

                if (TextUtils.isEmpty(memail)) {
                    email.setError("Please Enter your Email!!");
                    return;
                }
                if(!checkEmail(memail)){
                    Toast.makeText(getActivity(),"Email is not valid ",Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(mpassword)) {
                    password.setError("Please Enter your password");
                    return;
                }
                if(password.length()<6){
                    password.setError("the password must be more than 6 characters");
                    return;
                }

                firebaseAuth.createUserWithEmailAndPassword(memail,mpassword).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            sendDataToFirebase();
                            Toast.makeText(getActivity(),"Add user successfully: "+memail,Toast.LENGTH_LONG).show();
                            civil_id.setText("");
                            email.setText("");
                            password.setText("");
                            spinner.setSelection(0);

                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        e.printStackTrace();
                        Toast.makeText(getActivity(), "Authentication failed.", Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });






        return view;
    }

    private boolean checkEmail(String email) {
        return EMAIL_ADDRESS_PATTERN.matcher(email).matches();
    }

    public static final Pattern EMAIL_ADDRESS_PATTERN = Pattern.compile(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+"
    );


    private void sendDataToFirebase(){

        firebaseUser =FirebaseAuth.getInstance().getCurrentUser();
        current_id = firebaseUser.getUid();
        String cv= civil_id.getText().toString().trim();
        addUsers=new AddUsers();
        addUsers.setCurrent_id(current_id);
        addUsers.setCivil_id(civil_id.getText().toString().trim());
        addUsers.setEmail(email.getText().toString().trim());
        addUsers.setPassword(password.getText().toString().trim());
        addUsers.setType(spinner.getSelectedItem().toString());

        databaseReference.child(current_id).setValue(addUsers);


    }








    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
