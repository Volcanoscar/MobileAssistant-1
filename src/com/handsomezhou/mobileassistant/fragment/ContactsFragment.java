package com.handsomezhou.mobileassistant.fragment;

import android.content.Intent;
import android.net.Uri;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.handsomezhou.mobileassistant.R;
import com.handsomezhou.mobileassistant.helper.ContactsHelper;
import com.handsomezhou.mobileassistant.helper.ContactsHelper.OnContactsLoad;
import com.handsomezhou.mobileassistant.helper.ContactsIndexHelper;
import com.handsomezhou.mobileassistant.model.Contacts;
import com.handsomezhou.mobileassistant.util.ShareUtil;
import com.handsomezhou.mobileassistant.view.ContactsOperationView;
import com.handsomezhou.mobileassistant.view.ContactsOperationView.OnContactsOperationView;

public class ContactsFragment extends BaseFragment implements OnContactsLoad,OnContactsOperationView{
	private static final String TAG="ContactsFragment";
	private EditText mSearchEt;
	private ContactsOperationView mContactsOperationView;


	@Override
	protected void initData() {
		setContext(getActivity());
		ContactsHelper.getInstance().setOnContactsLoad(this);
	}

	@Override
	protected View initView(LayoutInflater inflater, ViewGroup container) {
		View view =inflater.inflate(R.layout.fragment_contacts, container, false);
		mSearchEt=(EditText)view.findViewById(R.id.search_edit_text);
		mContactsOperationView = (ContactsOperationView)view.findViewById(R.id.contacts_operation_layout);
		mContactsOperationView.setOnContactsOperationView(this);
		boolean startLoad = ContactsHelper.getInstance().startLoadContacts();
		if (true == startLoad) {
			mContactsOperationView.contactsLoading();
		}
		return view;
	}

	@Override
	protected void initListener() {
		mSearchEt.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				String curCharacter=s.toString().trim();
				
				if(TextUtils.isEmpty(curCharacter)){
					ContactsHelper.getInstance().parseQwertyInputSearchContacts(null);
				}else{
					ContactsHelper.getInstance().parseQwertyInputSearchContacts(curCharacter);
				}
				mContactsOperationView.updateContactsList(TextUtils.isEmpty(curCharacter));
				
			}
		});
	}
	

	/*start:OnContactsLoad*/
	@Override
	public void onContactsLoadSuccess() {
		ContactsHelper.getInstance().parseQwertyInputSearchContacts(null);
		mContactsOperationView.contactsLoadSuccess();
		
		//just background printing contacts information
		//ContactsHelper.getInstance().showContactsInfo();
		ContactsIndexHelper.getInstance().praseContacts(ContactsHelper.getInstance().getBaseContacts());
		//ContactsIndexHelper.getInstance().showContactsInfo();
	}

	@Override
	public void onContactsLoadFailed() {
		mContactsOperationView.contactsLoadFailed();
	}
	/*end:OnContactsLoad*/

	/*start:OnContactsOperationView*/
	@Override
	public void onListItemClick(Contacts contacts,int position){
		ContactsHelper.getInstance().parseQwertyInputSearchContacts(null);
		mContactsOperationView.updateContactsList(true);
	}

	@Override
	public void onAddContactsSelected(Contacts contacts) {
		if(null!=contacts){
			Log.i(TAG, "onAddContactsSelected name=["+contacts.getName()+"] phoneNumber=["+contacts.getPhoneNumber()+"]");
			Toast.makeText(getContext(),"Add ["+contacts.getName()+":"+contacts.getPhoneNumber()+"]", Toast.LENGTH_SHORT).show();
			ContactsHelper.getInstance().addSelectedContacts(contacts);
		}
	}


	@Override
	public void onRemoveContactsSelected(Contacts contacts) {
		if(null!=contacts){
			Log.i(TAG, "onRemoveContactsSelected name=["+contacts.getName()+"] phoneNumber=["+contacts.getPhoneNumber()+"]");
			Toast.makeText(getContext(),"Remove ["+contacts.getName()+":"+contacts.getPhoneNumber()+"]", Toast.LENGTH_SHORT).show();
			ContactsHelper.getInstance().removeSelectedContacts(contacts);
		}
	}
	
	@Override
	public void onContactsCall(Contacts contacts) {
		//Toast.makeText(mContext, "onContactsCall"+contacts.getPhoneNumber(), Toast.LENGTH_SHORT).show();
		if(null!=contacts){
			 Intent intent = new Intent(Intent.ACTION_CALL,Uri.parse("tel:"+contacts.getPhoneNumber()));
			 getContext().startActivity(intent);
		}
	}


	@Override
	public void onContactsSms(Contacts contacts) {
		//Toast.makeText(mContext, "onContactsSms"+contacts.getPhoneNumber(), Toast.LENGTH_SHORT).show();
		ShareUtil.shareTextBySms(getContext(), contacts.getPhoneNumber(), null);
	}
	/*end:OnContactsOperationView*/
}