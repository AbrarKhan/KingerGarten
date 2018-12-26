package app.kingergarten.kingergarten.admin.add_best_student;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import app.kingergarten.kingergarten.R;
import de.hdodenhof.circleimageview.CircleImageView;


public class ArabicFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private TextView q_tv1,q_tv2,q_tv3,title;
    private CircleImageView q_ci1,q_ci2,q_ci3;
    private CircleImageView q_ci1_camera,q_ci2_camera,q_ci3_camera;
    private Button save_q;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private FirebaseStorage firebaseStorage;
    private StorageReference storageReference;
    private Quran quran, quran1,quran2,quran3;
    private final int CAMERA_REQUEST1=1;
    private final int CAMERA_REQUEST2=2;
    private final int CAMERA_REQUEST3=3;
    private final int RESULT_OK=-1;
    Bitmap bitmap1,bitmap2,bitmap3;
    private ArrayList<Quran> arrayList;
    private ArrayList<String> imgArrayList;
    private  ArrayList<Quran> headerArrayList;
    private BestStudentList bestStudentList;
    private ArrayList<BestStudentList> bestStudentListArrayList;
    private  Uri imgUrl;

    private OnFragmentInteractionListener mListener;

    public ArabicFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment QuranFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ArabicFragment newInstance(String param1, String param2) {
        ArabicFragment fragment = new ArabicFragment();
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
        View view= inflater.inflate(R.layout.quran_fragment, container, false);
        imgArrayList=new ArrayList<>();
        arrayList=new ArrayList<>();
        firebaseDatabase=FirebaseDatabase.getInstance();
        firebaseStorage= FirebaseStorage.getInstance();

        q_tv1=view.findViewById(R.id.std1_txt);
        q_tv2=view.findViewById(R.id.std2_txt);
        q_tv3=view.findViewById(R.id.std3_txt);
        title=view.findViewById(R.id.title_txt);


        q_ci1=view.findViewById(R.id.std1);
        q_ci2=view.findViewById(R.id.std2);
        q_ci3=view.findViewById(R.id.std3);

        q_ci1_camera=view.findViewById(R.id.camera_std1);
        q_ci2_camera=view.findViewById(R.id.camera_std2);
        q_ci3_camera=view.findViewById(R.id.camera_std3);

        save_q=view.findViewById(R.id.save_button);



        q_ci1_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  if (bitmap1 != null) {
                    Intent intent1 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent1, CAMERA_REQUEST1);
              //  } else {
              //      Toast.makeText(getActivity(), "Please capture previous first", Toast.LENGTH_LONG).show();
              //  }
            }
        });

        q_ci2_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if (bitmap2!=null) {
                    Intent intent2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent2, CAMERA_REQUEST2);
               // }
//                else{
//                    Toast.makeText(getActivity(),"Please capture previous first", Toast.LENGTH_LONG).show();
//                }
            }
        });

        q_ci3_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
           //     if (bitmap3!=null ){
                    Intent intent3=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent3,CAMERA_REQUEST3);
            //    }
            //    else{
            //        Toast.makeText(getActivity(),"Please capture previous first", Toast.LENGTH_LONG).show();
            //    }

            }
        });
                save_q.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                if (imgArrayList.size() != 0) {
                   bestStudentList =new BestStudentList();
                   bestStudentListArrayList =new ArrayList<>();

                    quran =new Quran();
                    DateFormat dateFormat=new SimpleDateFormat("yyyy/MM/dd");
                    Date date=new Date();
                    final String Current_date = dateFormat.format(date);

                    quran.setTitle(title.getText().toString().trim());
                    quran.setDate(Current_date);
                    arrayList.add(quran);

                    String first=imgArrayList.get(0);
                   quran1=new Quran();
                   quran1.setStd1_q(q_tv1.getText().toString().trim());
                   quran1.setSted1_img_q(first);
                   arrayList.add(quran1);

                   String second=imgArrayList.get(1);
                    quran2=new Quran();
                    quran2.setStd1_q(q_tv2.getText().toString().trim());
                    quran2.setSted1_img_q(second);
                    arrayList.add(quran2);

                   String third=imgArrayList.get(2);
                    quran3=new Quran();
                    quran3.setStd1_q(q_tv3.getText().toString().trim());
                    quran3.setSted1_img_q(third);
                    arrayList.add(quran3);
                    bestStudentList.setQuranArrayList(arrayList);
                    databaseReference=firebaseDatabase.getReference("Arabic");

                    databaseReference.child( databaseReference.push().getKey()).setValue(bestStudentList);

                    databaseReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                             for (DataSnapshot snap:dataSnapshot.getChildren()){
                                 BestStudentList bestStudentList =snap.getValue(BestStudentList.class);
                                 bestStudentListArrayList.add(bestStudentList);

                             }
                        }


                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }

            }
        });
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==1 && resultCode==RESULT_OK){

            Bundle bundle=data.getExtras();
            bitmap1=(Bitmap) bundle.get("data");
            q_ci1.setImageBitmap(bitmap1);
            int rand =(int) Math.floor(Math.random()*20);
            storageReference = firebaseStorage.getReference("Images_Arabic/"+rand);

            //step2 >> upload image
            ByteArrayOutputStream byteArrayOutputStream1=new ByteArrayOutputStream();
            bitmap1.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream1);
            byte[] data1= byteArrayOutputStream1.toByteArray();
            UploadTask uploadTask1= storageReference.putBytes(data1);
            uploadTask1.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if(!task.isSuccessful()){
                        throw task.getException();
                    }
                    else{
                        storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                imgArrayList.add(uri.toString());
                            }
                        });
                    }
                    return storageReference.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {

                }
            });

                }






//*****************************************************************************************************************
        else if (requestCode==2 && resultCode==RESULT_OK){

                Bundle bundle=data.getExtras();
                bitmap2=(Bitmap) bundle.get("data");
                q_ci2.setImageBitmap(bitmap2);
                int rand =(int) Math.floor(Math.random()*20);
                storageReference = firebaseStorage.getReference("Images_Arabic/"+rand);

                //step2 >> upload image
                ByteArrayOutputStream byteArrayOutputStream2=new ByteArrayOutputStream();
                bitmap2.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream2);
                byte[] data2= byteArrayOutputStream2.toByteArray();
                UploadTask uploadTask2= storageReference.putBytes(data2);
            uploadTask2.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if(!task.isSuccessful()){
                        throw task.getException();
                    }
                    else{
                        storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                imgArrayList.add(uri.toString());
                            }
                        });
                    }
                    return storageReference.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {

                }
            });

        }

//***************************************************************************************************************
        else if (requestCode==3 && resultCode==RESULT_OK) {

            Bundle bundle = data.getExtras();
            bitmap3 = (Bitmap) bundle.get("data");
            q_ci3.setImageBitmap(bitmap3);
            int rand = (int) Math.floor(Math.random() * 20);
            storageReference = firebaseStorage.getReference("Images_Arabic/" + rand);

            //step2 >> upload image
            ByteArrayOutputStream byteArrayOutputStream3 = new ByteArrayOutputStream();
            bitmap3.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream3);
            byte[] data3 = byteArrayOutputStream3.toByteArray();
            UploadTask uploadTask3 = storageReference.putBytes(data3);
            uploadTask3.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if(!task.isSuccessful()){
                        throw task.getException();
                    }
                    else{
                      storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                          @Override
                          public void onSuccess(Uri uri) {
                              imgArrayList.add(uri.toString());
                          }
                      });
                    }
                    return storageReference.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {

                }
            });

        }
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
