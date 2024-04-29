
# Rapport

Added recyclerView to layout in activity_main.xml.
```
    <androidx.recyclerview.widget.RecyclerView
        android:id="@+id/recycler_view"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toTopOf="parent" />
```
Added a new java class "Mountain" with variables identical to the json file [Mountain.java]
```
public class Mountain {
    //Member variables
        private String ID;
        private String name;
        private String location;
        @SerializedName("cost")
        private int feet;
        @SerializedName("size")
        private int size;
        private Auxdata auxdata;
```
Added an array of mountains as a member variable in MainActivity.
```
ArrayList<Mountain> items = new ArrayList<>();
```
Added a recyclerview adapter as a member variable in MainActivity that makes a toast when clicked on.
```
RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, items, new RecyclerViewAdapter.OnClickListener() {
            @Override
            public void onClick(Mountain item) {
                Toast.makeText(MainActivity.this, item.toString(), Toast.LENGTH_SHORT).show();
            }
        });
```
Created layout for each mountain item with a new layout file.
There are three textView elements which will represent the name, location and size of each mountain.
[mountain_item.xml]

[Screenshot of layout](screenshots/screenshotrecyclerview.png)

Created a RecyclerView.Adapter class and RecyclerView.ViewHolder that connects the data in Mountain class to textView elements in mountain_item.xml.
[RecyclerViewAdapter.java]

```
 public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title;
        TextView location;
        TextView size;

        ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            title = itemView.findViewById(R.id.tv_title);
            location = itemView.findViewById(R.id.tv_location);
            size = itemView.findViewById(R.id.tv_size);

        }
```
Downloading json data in onPostExecute().
```
    @Override
    public void onPostExecute(String json) {
        Log.d("MainActivity", json);
        Type type = new TypeToken<List<Mountain>>() {}.getType();
        List<Mountain> listOfMountains = gson.fromJson(json, type);
        items.addAll(listOfMountains);
    }
```
adapter.notifyDataSetChanged() function used in MainActivity to update the arraylist.
```
        new JsonFile(this, this).execute(JSON_FILE);
        /*new JsonTask(this).execute(JSON_URL);*/
        adapter.notifyDataSetChanged();
```
Displaying the name, location and size of the mountain in the RecyclerView by connecting the viewholder to each getter in Mountain.java.
```
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.title.setText(items.get(position).getName());
        holder.location.setText(items.get(position).getLocation());
        holder.size.setText(items.get(position).getSize()+" m");
    }
```
