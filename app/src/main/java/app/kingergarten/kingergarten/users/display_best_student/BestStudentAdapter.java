package app.kingergarten.kingergarten.users.display_best_student;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import app.kingergarten.kingergarten.R;
import app.kingergarten.kingergarten.admin.add_best_student.HelloBestStudent;
import app.kingergarten.kingergarten.admin.add_best_student.Quran;
import app.kingergarten.kingergarten.admin.add_best_student.BestStudentList;
import de.hdodenhof.circleimageview.CircleImageView;


public class BestStudentAdapter extends RecyclerView.Adapter<BestStudentAdapter.MyViewHolder> {
    private ArrayList<BestStudentList> quranArrayList;

    private HelloBestStudent hello;
    private View view;
   private LinearLayout linearLayout;

   private RelativeLayout relativeLayout;
    private final int WIDTH = 150;
   // private GridLayout gridLayout;
   // private ArrayList<PhoneObject> ph_items;
    private Context context;


    public BestStudentAdapter(ArrayList<BestStudentList> quranArrayList, Context context) {
        //this.items = items;
        //this.sources=sources;
        this.quranArrayList=quranArrayList;
       // this.ph_items=ph_items;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view= layoutInflater.inflate(R.layout.best_student_adapter,parent,false);
        //step 5 defind the card view using findviewid();
         cardView=view.findViewById(R.id.card_view_stds);
            linearLayout=view.findViewById(R.id.r1);



        return new MyViewHolder (view); }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {


        BestStudentList quran= quranArrayList.get(position);

        ArrayList<Quran> quranArrayList1=quran.getQuranArrayList();
        hello=new HelloBestStudent();
        for (Quran q:quranArrayList1){

            TextView textView=new TextView(context);
            TextView header=new TextView(context);
            TextView date_txt=new TextView(context);
            CircleImageView circle=new CircleImageView(context);

            textView.setTextColor(Color.BLUE);
            textView.setTextSize(20);
            //textView.setBackgroundColor(0x7C5B77);

            header.setTextColor(Color.BLACK);
            header.setTextSize(22);
           // header.setBackgroundColor(0x7C5B77);

            date_txt.setTextColor(Color.GRAY);
            date_txt.setTextSize(20);
           // date_txt.setBackgroundColor(0x7C5B77);


            LinearLayout.LayoutParams ci=new LinearLayout.LayoutParams(200,200);
            ci.leftMargin= 30;
            ci.topMargin = -150;
            circle.setLayoutParams(ci);
            Picasso.with(context).load(q.getSted1_img_q()).into(circle);


            LinearLayout.LayoutParams tv=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
            tv.topMargin = -50;
            tv.leftMargin = 250;
            textView.setLayoutParams(tv);
            textView.setText(q.getStd1_q());

            LinearLayout.LayoutParams h=new LinearLayout.LayoutParams(1050,ViewGroup.LayoutParams.WRAP_CONTENT);
            h.topMargin=30;
            h.leftMargin = 20;
            h.gravity=Gravity.LEFT;
            header.setLayoutParams(h);
            header.setText(q.getTitle());

            LinearLayout.LayoutParams d=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
            d.topMargin=-30;
            d.rightMargin = 20;
            d.gravity=Gravity.RIGHT;
            date_txt.setLayoutParams(d);
            date_txt.setText(q.getDate());



            linearLayout.addView(header);
            linearLayout.addView(date_txt);
            linearLayout.addView(circle);
            linearLayout.addView(textView);
         }



    }

    @Override
    public int getItemCount() {
        return quranArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textHeader,textDate;

        public MyViewHolder(View itemView) {
            super(itemView);

           // textHeader=itemView.findViewById(R.id.header);
        //    textDate=itemView.findViewById(R.id.date_txt);
//            q_tv3=itemView.findViewById(R.id.std3_q);
//            q_ci1=itemView.findViewById(R.id.std1_img_q);
//            q_ci2=itemView.findViewById(R.id.std2_img_q);
//            q_ci3=itemView.findViewById(R.id.std3_img_q);


        }
    }
    CardView cardView;
    private BestStudentAdapter.adapterItemClick on_click;

    //step 1 defind the interface
    public interface adapterItemClick {
        void clickItem(int position);
    }

    //step 4 create method with argument
    public void clickMethod(BestStudentAdapter.adapterItemClick on_click){
        this.on_click=on_click;
    }
}


