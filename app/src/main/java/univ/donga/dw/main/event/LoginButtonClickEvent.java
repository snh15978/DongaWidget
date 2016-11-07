package univ.donga.dw.main.event;

import univ.donga.dw.login.BookLoginActivity;
import univ.donga.dw.main.info.DataManager;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

public class LoginButtonClickEvent implements Button.OnClickListener{

	private Context context;
	public LoginButtonClickEvent(Context context)
	{
		this.context = context;
	}
	public void onClick(View v) {
		//�α����� ���� ��Ƽ��Ƽ�� ����ش�.
		((Activity)context).startActivityForResult(new Intent(context, BookLoginActivity.class), DataManager.LOGIN_RESULT);
	}
}
