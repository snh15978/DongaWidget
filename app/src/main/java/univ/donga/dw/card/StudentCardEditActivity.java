package univ.donga.dw.card;


import univ.donga.dw.R;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

public class StudentCardEditActivity extends Activity implements OnClickListener{
	
	private EditText name, major, studentNumber;
	private Button okBtn, cancelBtn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_student_card_edit);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		getWindow().setBackgroundDrawableResource(R.drawable.rounded_corner);
		
		name = (EditText)findViewById(R.id.profileNameEdit);
		major = (EditText)findViewById(R.id.profileMajorEdit);
		studentNumber = (EditText)findViewById(R.id.profileStudentNumberEdit);
		okBtn = (Button)findViewById(R.id.profileEditOKBtn);
		cancelBtn = (Button)findViewById(R.id.profileEditCancelBtn);
		okBtn.setOnClickListener(this);
		cancelBtn.setOnClickListener(this);
		
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.profileEditOKBtn:
			Intent intent = new Intent();
			intent.putExtra("name", name.getText().toString());
			intent.putExtra("major", major.getText().toString());
			intent.putExtra("studentNumber", studentNumber.getText().toString());
			setResult(RESULT_OK, intent);
			finish();
			break;
		case R.id.profileEditCancelBtn:
			setResult(RESULT_CANCELED);			
			finish();
			break;

		default:
			break;
		}
		
	}

}
