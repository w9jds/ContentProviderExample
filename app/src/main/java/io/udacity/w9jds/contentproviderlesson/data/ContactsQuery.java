package io.udacity.w9jds.contentproviderlesson.data;

import android.net.Uri;
import android.provider.ContactsContract;

public interface ContactsQuery {

    int QUERY_ID = 1;
    Uri CONTENT_URI = ContactsContract.Contacts.CONTENT_URI;
    Uri FILTER_URI = ContactsContract.Contacts.CONTENT_FILTER_URI;
    String SELECTION = ContactsContract.Contacts.DISPLAY_NAME_PRIMARY + "<>''" + " AND " + ContactsContract.Contacts.IN_VISIBLE_GROUP + "=1";
    String SORT_ORDER = ContactsContract.Contacts.SORT_KEY_PRIMARY;
    String[] PROJECTION = {
        ContactsContract.Contacts._ID,
        ContactsContract.Contacts.LOOKUP_KEY,
        ContactsContract.Contacts.DISPLAY_NAME_PRIMARY,
        ContactsContract.Contacts.PHOTO_THUMBNAIL_URI,
        SORT_ORDER,
    };

    int ID = 0;
    int LOOKUP_KEY = 1;
    int DISPLAY_NAME = 2;
    int PHOTO_THUMBNAIL_DATA = 3;
    int SORT_KEY = 4;
}