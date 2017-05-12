package com.chris.mystudy.myself;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.chris.mystudy.DetailActivity;
import com.chris.mystudy.R;
import com.chris.mystudy.adapater.RecyclerAdapterForNews;
import com.chris.mystudy.base.BaseFragment;

import butterknife.BindView;

/**
 * Created on 17/3/10.
 * Author : chris
 * Email  : mengqi@analysys.com.cn
 * Detail :
 */

public class MySelfFragment extends BaseFragment implements RecyclerAdapterForNews.OnItemClickLitener{


    @BindView(R.id.pick_contact)Button  mPickContact;

    public static MySelfFragment newInstance(String title) {

        Bundle args = new Bundle();
        args.putString(FRAGMENT_TITLE,title);
        MySelfFragment fragment = new MySelfFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.myself_fragment_layout;
    }

    public static final int PHONE_REQUEST_CODE = 0;
    @Override
    protected void initView() {
       mPickContact.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Uri uri = Uri.parse("content://contacts");
               Intent intent = new Intent(Intent.ACTION_PICK,uri);
               intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
               startActivityForResult(intent,PHONE_REQUEST_CODE);
           }
       });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PHONE_REQUEST_CODE){
            if (resultCode == Activity.RESULT_OK){
                Uri contactUri = data.getData();
                String[] projection = {ContactsContract.CommonDataKinds.Phone.NUMBER};
                Cursor cursor = getActivity().getContentResolver().query(contactUri,projection,null,null,null);
                cursor.moveToFirst();
                int column = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                String phoneNumber = cursor.getString(column);
                Toast.makeText(getContext(),phoneNumber,Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void destoryView() {

    }

    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(mContext, DetailActivity.class);
        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.enter_animation,R.anim.exit_animation);
    }

    @Override
    public void onItemLongClick(View view, int position) {

    }
}
