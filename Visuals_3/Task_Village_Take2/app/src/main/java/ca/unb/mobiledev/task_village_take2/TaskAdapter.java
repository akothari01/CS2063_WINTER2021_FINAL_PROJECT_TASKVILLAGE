package ca.unb.mobiledev.task_village_take2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;

public class TaskAdapter extends ArrayAdapter<Task> {
    private TaskViewModel taskViewModel;
    private List<Task> list;
    private int villageId;
    public TaskAdapter(Context context, List<Task> tasks, TaskViewModel taskViewModel, int villageId) {
        super(context, 0, tasks);
        this.list = tasks;
        this.taskViewModel = taskViewModel;
        this.villageId = villageId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Task task = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.card_layout, parent, false);
        }

        // Lookup view for data population
        TextView tvTitle = convertView.findViewById(R.id.textView12);
        TextView tvDeadline = convertView.findViewById(R.id.textView14);
        TextView tvDesc = convertView.findViewById(R.id.textView15);
        Button doneButton = convertView.findViewById(R.id.button9);

        tvTitle.setText(task.getTitle());
        tvDeadline.setText(task.getDeadline().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)));
        String desc = "Priority: " + task.getPriority() + " Type: " + task.getType() +
                "\n" + task.getDesc();
        tvDesc.setText(desc);

        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                taskViewModel.completeTask(list.get(position).getId());
                taskViewModel.increaseNoBuildings(villageId, 1);
                //list.remove(position);

            }
        });
        // Return the completed view to render on screen
        return convertView;
    }
}

