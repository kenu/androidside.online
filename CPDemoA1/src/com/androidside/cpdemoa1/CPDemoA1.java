package com.androidside.cpdemoa1;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.TextView;

public class CPDemoA1 extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        TextView text = (TextView) findViewById(R.id.text);
        text.setText(getContacts());
    }    

    public String getContacts() {
        //�ּҷ� ������ ���� ���ڿ� ��ü
        StringBuffer sb = new StringBuffer();
        
        //getContentResolver() �޼ҵ�� ContentResolver�� ���� �Ŀ� 
        //query �޼ҵ带 ����ؼ� �ּҷ� ������ ��û�Ѵ�.
        //�̷��� ���� �ּҷ� ��� ������ Cursor�� ���ؼ� �����ϰ� ������ �� �ִ�.
        Cursor contacts = getContentResolver().query(
                ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        
        //Cursor�� moveToNext() �޼ҵ带 ����ؼ� �ּҷ��� �˻��Ѵ�.
        while (contacts.moveToNext()) {
            
            //����� �̸��� ���� ���Ѵ�.
            String displayName = contacts.getString(contacts
                    .getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            sb.append("display_name: " + displayName).append("\n");

            //�ּҷ� ���̵� ���Ѵ�.
            //�� ���̵�� ��ȭ��ȣ ��ϰ� �̸��� ����� ã�� �� ����Ѵ�.
            String contactId = contacts.getString(contacts
                    .getColumnIndex(ContactsContract.Contacts._ID));
            
            //��ȭ��ȣ ���� ���θ� ���Ѵ�.
            //����Ǿ� �ִٸ� "1"�� ��ȯ�Ǹ� �׷��� �ʴٸ� "0"�� ��ȯ�ȴ�.
            String hasPhoneNumber = contacts
                    .getString(contacts
                            .getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));
            
            sb.append("hasPhoneNumber: " + hasPhoneNumber).append("\n");

            //��ȭ��ȣ�� ����Ǿ� �ִٸ�
            if (hasPhoneNumber.equals("1")) {

                //��ȭ��ȣ Cursor�� �����Ѵ�.
                //�̶� contactId�� ����ؼ� ��ȭ��ȣ ����� ���Ѵ�.
                Cursor phones = getContentResolver().query(
                        ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                        null,
                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID
                                + " = " + contactId, null, null);
                
                //��ȭ��ȣ�� ���� ������ ��� �ݺ��ϸ鼭 ��ȭ��ȣ�� ���Ѵ�.
                while (phones.moveToNext()) {
                    String phoneNumber = phones
                            .getString(phones
                                    .getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

                    sb.append("phoneNumber: " + phoneNumber).append("\n");
                }
                //��ȭ��ȣ Cursor�� �ݴ´�.
                phones.close();
            }

            //�̸��� ����� ���Ѵ�.
            //�̶� contactId�� ����ؼ� �̸��� ����� ���Ѵ�.
            Cursor emails = getContentResolver().query(
                    ContactsContract.CommonDataKinds.Email.CONTENT_URI,
                    null,
                    ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = "
                            + contactId, null, null);
            
            //�̸����� ���� ������ ��� �ݺ��ϸ鼭 �̸����� ���Ѵ�.
            while (emails.moveToNext()) {
                // This would allow you get several email addresses
                String emailAddress = emails
                        .getString(emails
                                .getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));
                
                sb.append("emailAddress: " + emailAddress).append("\n");
            }
            //�̸��� Cursor�� �ݴ´�.
            emails.close();
        }

        //�ּҷ� Cursor�� �ݴ´�.
        contacts.close();
        
        return sb.toString();
    }    
}