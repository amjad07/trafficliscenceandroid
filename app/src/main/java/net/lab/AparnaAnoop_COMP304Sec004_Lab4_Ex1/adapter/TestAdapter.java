package net.lab.AparnaAnoop_COMP304Sec004_Lab4_Ex1.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import net.lab.AparnaAnoop_COMP304Sec004_Lab4_Ex1.R;
import net.lab.AparnaAnoop_COMP304Sec004_Lab4_Ex1.setters.TestTrafficSetters;

public class TestAdapter extends ListAdapter<TestTrafficSetters, TestAdapter.NoteHolder> {
    private OnItemClickListener listener;
    public TestAdapter() {
        super(DIFF_CALLBACK);
    }
    private static final DiffUtil.ItemCallback<TestTrafficSetters> DIFF_CALLBACK = new DiffUtil.ItemCallback<TestTrafficSetters>() {
        @Override
        public boolean areItemsTheSame(TestTrafficSetters oldItem, TestTrafficSetters newItem) {
            return oldItem.getId() == newItem.getId();
        }
        @Override
        public boolean areContentsTheSame(TestTrafficSetters oldItem, TestTrafficSetters newItem) {
            return oldItem.getTestResult().equals(newItem.getTestResult()) &&
                    oldItem.getTestDate().equals(newItem.getTestDate()) &&
                    oldItem.getTestRoute().equals(newItem.getTestRoute()) ;
        }
    };
    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_item, parent, false);
        return new NoteHolder(itemView);
    }
    @Override
    public void onBindViewHolder(@NonNull NoteHolder holder, int position) {
        TestTrafficSetters currentNote = getItem(position);
        holder.result.setText(currentNote.getTestResult());
        holder.date.setText(currentNote.getTestDate());
        holder.route.setText(String.valueOf(currentNote.getTestRoute()));
         holder.testType.setText(String.valueOf(currentNote.getTest_type()));
    }
    public TestTrafficSetters getNoteAt(int position) {
        return getItem(position);
    }
    class NoteHolder extends RecyclerView.ViewHolder {
        private TextView result;
        private TextView date;
        private TextView route;
         private TextView testType;

        public NoteHolder(View itemView) {
            super(itemView);
            result = itemView.findViewById(R.id.first_name);
            date = itemView.findViewById(R.id.text_view_lastname);
            route = itemView.findViewById(R.id.test_route_);
            testType = itemView.findViewById(R.id.test_type_);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(getItem(position));
                    }
                }
            });
        }
    }
    public interface OnItemClickListener {
        void onItemClick(TestTrafficSetters note);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}