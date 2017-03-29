package io.udacity.w9jds.contentproviderlesson.ui;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import io.udacity.w9jds.contentproviderlesson.R;
import io.udacity.w9jds.contentproviderlesson.data.ContactsQuery;
import io.udacity.w9jds.contentproviderlesson.ui.adapters.ContactsAdapter;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private ContactsAdapter contactsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contactsAdapter = new ContactsAdapter(this);
        ListView contactsList = (ListView) findViewById(R.id.contact_list);

        contactsList.setAdapter(contactsAdapter);
        contactsList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        getLoaderManager().initLoader(ContactsQuery.QUERY_ID, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {

        if (id == ContactsQuery.QUERY_ID) {
            return new CursorLoader(this, ContactsQuery.CONTENT_URI, ContactsQuery.PROJECTION,
                    ContactsQuery.SELECTION, null, ContactsQuery.SORT_ORDER);
        }

        return null;
    }

    @Override
    public void onLoadFinished(android.content.Loader<Cursor> loader, Cursor data) {
        if (loader.getId() == ContactsQuery.QUERY_ID) {
            contactsAdapter.swapCursor(data);
        }
    }

    @Override
    public void onLoaderReset(android.content.Loader<Cursor> loader) {
        if (loader.getId() == ContactsQuery.QUERY_ID) {
            contactsAdapter.swapCursor(null);
        }
    }
}
