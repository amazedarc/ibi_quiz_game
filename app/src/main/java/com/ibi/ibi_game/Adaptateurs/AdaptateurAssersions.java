package com.ibi.ibi_game.Adaptateurs;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.ibi.ibi_game.Main3Activity;
import com.ibi.ibi_game.R;
import com.ibi.ibi_game.classes.Asserssion2;

import java.util.List;

import static androidx.appcompat.content.res.AppCompatResources.getDrawable;

public  class AdaptateurAssersions extends RecyclerView.Adapter<AdaptateurAssersions.ElementoViewHolder> {

         //les valeurs de comparaisons
         String sta;
        int idQuestion;
        int idQuestionAnswer;
        static  int index=0;
        TextView incorrect;

        //ce context on va l'utuliser pour etirere le layout
        private Context mCtx;

        //we are storing all the products in a list
        private List<Asserssion2> listeDasserssions;

        //retourner le context et lelement de la liste par le constructeur
        public AdaptateurAssersions(Context mCtx, List<Asserssion2> listeDasserssions) {
            this.mCtx = mCtx;
            this.listeDasserssions = listeDasserssions;
        }

        @Override
        public ElementoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            //retourner view holder
            LayoutInflater inflater = LayoutInflater.from(mCtx);
            View view = inflater.inflate(R.layout.assersionshow, null);
            return new ElementoViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ElementoViewHolder holder, int position){

            //retourner lelment sur une positiuon precise
            final Asserssion2 Ass = listeDasserssions.get(position);
            final  int answ_id2=Ass.getAnsw_id2();
            final  int answ_quest=Ass.getAnsw_quest();
            final String answers_name2=Ass.getAnswers_name2();

            //construire les donnees par viewholder views
            holder.textAss.setText(answers_name2);
            holder.textAss.setEnabled(true);


            holder.textAss.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(answ_id2!=answ_quest) {

                        sta = "false";
                        holder.textAss.setEnabled(false);
                        holder.textAss.setTextColor(ContextCompat.getColor(mCtx, R.color.white));
                        holder.textAss.setBackground(getDrawable(mCtx, R.drawable.incorrect_selection));
                        Intent intent = new Intent(mCtx, Main3Activity.class);
                        //intent.putExtra("indexe", index);
                        intent.putExtra("etat", sta);
                        intent.putExtra("fermer","no");
                        mCtx.startActivity(intent);

                    }
                    else if(answ_id2==answ_quest) {
                        // index++; sta="true";
                        sta = "true";
                        holder.textAss.setEnabled(false);
                        holder.textAss.setTextColor(ContextCompat.getColor(mCtx, R.color.white));
                        holder.textAss.setBackground(getDrawable(mCtx, R.drawable.correct_selection));
                        Intent intent = new Intent(mCtx, Main3Activity.class);
                        // intent.putExtra("indexe",index);
                        intent.putExtra("etat", sta);
                        intent.putExtra("fermer","no");
                        mCtx.startActivity(intent);
                    }

                }
            });
        }

        @Override
        public int getItemCount(){
            return listeDasserssions.size();
        }

        class ElementoViewHolder extends RecyclerView.ViewHolder{

            TextView textAss;

            public ElementoViewHolder(View itemView) {
                super(itemView);

                textAss=itemView.findViewById(R.id.textAss);
            }
        }
}

