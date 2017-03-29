package io.udacity.w9jds.contentproviderlesson.ui.adapters;

import android.content.Context;
import android.database.Cursor;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AlphabetIndexer;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import io.udacity.w9jds.contentproviderlesson.R;
import io.udacity.w9jds.contentproviderlesson.data.ContactsQuery;
import io.udacity.w9jds.contentproviderlesson.util.CircleTransform;


public class ContactsAdapter extends CursorAdapter implements SectionIndexer {

    private LayoutInflater layoutInflater;
    private AlphabetIndexer alphabetIndexer;

    public ContactsAdapter(Context context) {
        super(context, null, 0);

        layoutInflater = LayoutInflater.from(context);
        final String alphabet = context.getString(R.string.alphabet);

        alphabetIndexer = new AlphabetIndexer(null, ContactsQuery.SORT_KEY, alphabet);
    }

    @Override
    public Cursor swapCursor(Cursor newCursor) {
        alphabetIndexer.setCursor(newCursor);
        return super.swapCursor(newCursor);
    }

    @Override
    public int getCount() {
        if (getCursor() == null) {
            return 0;
        }
        return super.getCount();
    }

    @Override
    public Object[] getSections() {
        return alphabetIndexer.getSections();
    }

    @Override
    public int getPositionForSection(int i) {
        if (getCursor() == null) {
            return 0;
        }
        return alphabetIndexer.getPositionForSection(i);
    }

    @Override
    public int getSectionForPosition(int i) {
        if (getCursor() == null) {
            return 0;
        }
        return alphabetIndexer.getSectionForPosition(i);
    }

    private class ViewHolder {
        TextView primaryText;
        TextView secondaryText;
        ImageView icon;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {

        final View itemLayout = layoutInflater.inflate(R.layout.contact_list_item, viewGroup, false);
        final ViewHolder holder = new ViewHolder();

        holder.primaryText = (TextView) itemLayout.findViewById(R.id.primary_label);
        holder.secondaryText = (TextView) itemLayout.findViewById(R.id.secondary_label);
        holder.icon = (ImageView) itemLayout.findViewById(R.id.contact_icon);

        itemLayout.setTag(holder);
        return itemLayout;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        final ViewHolder holder = (ViewHolder) view.getTag();
        final String photoUri = cursor.getString(ContactsQuery.PHOTO_THUMBNAIL_DATA);
        final String displayName = cursor.getString(ContactsQuery.DISPLAY_NAME);
        final SpannableString highlightedName = new SpannableString(displayName);

        holder.primaryText.setText(highlightedName);
        holder.secondaryText.setVisibility(View.GONE);

        Glide.with(context)
            .load(photoUri)
            .transform(new CircleTransform(context))
            .into(holder.icon);
    }

}
