package univ.donga.dw.carte;

import univ.donga.dw.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

public class CarteDialogActivity extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_carte_dialog);
		getWindow().setBackgroundDrawableResource(R.drawable.transparent);
		
		Intent intent = getIntent();
		String location = intent.getStringExtra("location");
		String menu = intent.getStringExtra("menu");
		
		TextView locationText = (TextView)findViewById(R.id.carteDialogLocation);
		TextView menuText = (TextView)findViewById(R.id.carteDialogMenu);
		
		locationText.setText(location);
		menuText.setText(menu);
		
		
	}

}
