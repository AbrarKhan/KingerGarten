package app.kingergarten.kingergarten.admin.home_admin;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import app.kingergarten.kingergarten.R;
import app.kingergarten.kingergarten.admin.add_best_student.AddStudentBestFragment;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomeAdminFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HomeAdminFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeAdminFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    FragmentTransaction fragmentTransaction;
    private Button gallery,best_std,sugg,events;
    private ViewPager viewPager;
    private  SectionPageAdapter sectionPagerAdapter;
    private static int[] pager_image=
            {R.drawable.kindergarten,R.drawable.kindergarten,R.drawable.kindergarten,R.drawable.kindergarten,};
    private Runnable runnable;
    private Handler handler;
    private int mPageNumber;



    private OnFragmentInteractionListener mListener;

    public HomeAdminFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Home.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeAdminFragment newInstance(String param1, String param2) {
        HomeAdminFragment fragment = new HomeAdminFragment();
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
        View view= inflater.inflate(R.layout.fragment_home_admin, container, false);
        viewPager =view.findViewById(R.id.view_pager);
        sectionPagerAdapter=new SectionPageAdapter(getChildFragmentManager());
        viewPager.setAdapter(sectionPagerAdapter);
        //indicator.setViewPager(viewPager);
        best_std=view.findViewById(R.id.best_std);
        best_std.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentTransaction=getActivity().getSupportFragmentManager().beginTransaction();
                AddStudentBestFragment studentBestFragment =AddStudentBestFragment.newInstance(null,null);
                fragmentTransaction.replace(R.id.main_container,studentBestFragment);
                fragmentTransaction.commit();
            }
        });



        // ------ code for pager --------
        handler =new Handler();
       runnable =new Runnable() {
            @Override
            public void run() {
                if(sectionPagerAdapter.getCount()== mPageNumber){
                    mPageNumber =0;
                }else{
                    mPageNumber ++;
                }
                viewPager.setCurrentItem(mPageNumber, true);
                handler.postDelayed(this,3000);
            }
        };

        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
    }

    @Override
    public void onResume() {
        super.onResume();
        handler.postDelayed(runnable,3000);
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


    // for pager ....
    public class SectionPageAdapter extends FragmentPagerAdapter {

        public SectionPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return PlaceholderFragment.newInstance(position+1);
        }

        @Override
        public int getCount() {
            return pager_image.length;
        }

    }
    public static class PlaceholderFragment extends  Fragment{
        private  static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() { }
        public static PlaceholderFragment newInstance( int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            View rootview= inflater.inflate(R.layout.home_image_view, container, false);
            int pageNum = getArguments().getInt(ARG_SECTION_NUMBER);
            ImageView tutorialImage = (ImageView)rootview.findViewById(R.id.img_view_id);
            tutorialImage.setImageResource(pager_image[pageNum - 1]);
            return rootview;
        }

    }
 //----------------end pager cod ---------------
}




