package com.handsomezhou.mobileassistant.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.handsomezhou.mobileassistant.R;
import com.handsomezhou.mobileassistant.model.Contacts;

public class ContactsT9Adapter extends ArrayAdapter<Contacts> {
	private Context mContext;
	private int mTextViewResourceId;
	private List<Contacts> mContacts;
	
	public ContactsT9Adapter(Context context, int textViewResourceId,
			List<Contacts> contacts) {
		super(context, textViewResourceId, contacts);
		mContext=context;
		mTextViewResourceId=textViewResourceId;
		mContacts=contacts;
	}
	
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view=null;
		ViewHolder viewHolder;
		Contacts contacts=getItem(position);
		if(null==convertView){
			view=LayoutInflater.from(mContext).inflate(mTextViewResourceId, null);
			viewHolder=new ViewHolder();
			viewHolder.mNameTv=(TextView) view.findViewById(R.id.name_text_view);
			view.setTag(viewHolder);
		}else{
			view=convertView;
			viewHolder=(ViewHolder) view.getTag();
		}
		
		//viewHolder.mNameTv.setText(contacts.getName());
		viewHolder.mNameTv.setText(contacts.getName());
		
		return view;
	}


	private class ViewHolder{
		TextView mNameTv;
	}

}
