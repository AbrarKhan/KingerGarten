package app.kingergarten.kingergarten.users.display_best_student;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import app.kingergarten.kingergarten.R;
import app.kingergarten.kingergarten.admin.add_best_student.BestStudentList;



public class DisplayMathFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private ArrayList<BestStudentList> arrayList;
    private RecyclerView recyclerView;
    private BestStudentAdapter bestStudentAdapter;
    private BestStudentList quran;




    public DisplayMathFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_quran, container, false);
        recyclerView = view.findViewById(R.id.rec_view_std_q);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("Math");

        arrayList=new ArrayList<>();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snap : dataSnapshot.getChildren()) {
                    quran= snap.getValue(BestStudentList.class);

                    arrayList.add(quran);

                }
                LinearLayoutManager manager=new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
                recyclerView.setLayoutManager(manager);
                bestStudentAdapter =new BestStudentAdapter(arrayList,getActivity());

                recyclerView.setAdapter(bestStudentAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        return view;
    }


    // TODO: Rename method, update argument and hook method into UI event

}
