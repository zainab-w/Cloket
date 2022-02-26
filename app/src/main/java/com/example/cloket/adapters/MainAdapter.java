package com.example.cloket;

public class MainAdapter
        //extends RecyclerView.Adapter<MainAdapter.ViewHolder>
{
//    //Initialize variable
//    Activity act;
//    ArrayList<String> arrayList;
//    //create constructor
//    public MainAdapter(Activity activity,ArrayList<String> arrayList)
//    {
//        this.act = activity;
//        this.arrayList = arrayList;
//
//    }
//    @NonNull
//    @Override
//    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
//    {
//        //Initialize variable
//
//
//        //return holder view
//        return new ViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
//    {
//        //set text on text view
//        holder.textView.setText(arrayList.get(position));
//
//        holder.textView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //get clicked item postion
//                int position = holder.getAdapterPosition();
//                //check condition
//                switch (position) {
//                    case 0:
//                        //when position = 0
//                        //redirect to home page
//                        act.startActivity(new Intent(act, HomePage.class)
//                                .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
//                        break;
//                    case 1:
//                        //when position = 1
//                        //redirect to Dashboard page
//                        act.startActivity(new Intent(act, Profile.class)
//                                .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
//                        break;
//                    case 2:
//                        //when position = 1
//                        //redirect to Dashboard page
//                        act.startActivity(new Intent(act, Settings.class)
//                                .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
//                        break;
//                    case 3:
//                        //when position = 2
//                        //redirect to About page
//                        act.startActivity(new Intent(act, HelpNdFeedback.class)
//                                .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
//                        break;
//                    case 4:
//                        //when position = 3
//                        //Initialize alert dialogue
//                        AlertDialog.Builder builder = new AlertDialog.Builder(act);
//                        //set title
//                        builder.setTitle("Logout");
//                        //set message
//                        builder.setMessage("Are you sure you want to logout?");
//                        //if yes
//                        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                //finish all activity
//                                act.finishAffinity();
//                                //Exit app
//                                System.exit(0);
//                            }
//                        });
//                        //if no
//                        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                //dismiss Dialog
//                                dialog.dismiss();
//                            }
//                        });
//                        builder.show();
//                        break;
//                }
//            }
//        });
//    }
//
//    @Override
//    public int getItemCount()
//    {
//        //return array list size
//        return arrayList.size();
//    }
//
//    public class ViewHolder extends RecyclerView.ViewHolder
//    {
//        //initialize variable
//        TextView textView;
//        public ViewHolder(@NonNull View itemView)
//        {
//            super(itemView);
//            //Assign Variable
//            textView = itemView.findViewById(R.id.text_view);
//        }
//    }
}
