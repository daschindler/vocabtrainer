package at.davidomi.vocabtrainer.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import at.davidomi.vocabtrainer.R;
import at.davidomi.vocabtrainer.activities.WordsActivity;
import at.davidomi.vocabtrainer.entity.Dict;


public class DictListAdapter extends RecyclerView.Adapter<DictListAdapter.DictViewHolder> {

    class DictViewHolder extends RecyclerView.ViewHolder {
        private final TextView wordItemView;

        private DictViewHolder(View itemView) {
            super(itemView);
            wordItemView = itemView.findViewById(R.id.textView);
        }
    }

    private final LayoutInflater mInflater;
    private List<Dict> mDicts; // Cached copy of words

    public DictListAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    @Override
    public DictViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new DictViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final DictViewHolder holder, int position) {
        if (mDicts != null) {
            final Dict current = mDicts.get(position);
            holder.wordItemView.setText(current.getType());

            holder.wordItemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(holder.wordItemView.getContext(), WordsActivity.class);
                    intent.putExtra("dict_type", current.getType());
                    holder.wordItemView.getContext().startActivity(intent);
                }
            });
        } else {
            // Covers the case of data not being ready yet.
            holder.wordItemView.setText("No Dict");
        }
    }

    public void setDicts(List<Dict> dicts){
        mDicts = dicts;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mDicts has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mDicts != null)
            return mDicts.size();
        else return 0;
    }
}
