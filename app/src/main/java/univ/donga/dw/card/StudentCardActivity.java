package univ.donga.dw.card;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.krysalis.barcode4j.impl.code39.Code39Bean;
import org.krysalis.barcode4j.tools.UnitConv;

import univ.donga.dw.R;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class StudentCardActivity extends Activity implements OnClickListener{
	
	private static final String prefName = "SAVEDATA";
	
	private ImageView barCodeImage;
	private TextView name;
	private TextView studentNumber, major;
	private Button editBtn;
	
	private SharedPreferences sharedPref;
	private SharedPreferences.Editor editor;
	
	private static final int REQUEST_IMAGE_CAPTURE = 1;
	private static final int REQUEST_IMAGE_ALBUM = 2;
	private static final int REQUEST_IMAGE_CROP = 3;
	private ImageView mImageView;
	private String mCurrentPhotoPath;
	private Uri contentUri;
	
	
    /** Called when the activity is first created. */
    @Override 
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);   
        setContentView(R.layout.activity_student_card);        
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                
        // 화면의 뷰 초기화
        barCodeImage = (ImageView)findViewById(R.id.barCodeImage);
        name = (TextView)findViewById(R.id.profileNameText);
        studentNumber = (TextView)findViewById(R.id.profileStudentNumberText);
        major = (TextView)findViewById(R.id.profileMajorText);
        editBtn = (Button)findViewById(R.id.profileEditBtn);
        editBtn.setOnClickListener(this);
        
        //prefrence 초기화
        sharedPref = getSharedPreferences(prefName, MODE_PRIVATE);
        editor = sharedPref.edit();
        
        loadProfile();
        
        mCurrentPhotoPath = null;		
		mImageView = (ImageView)findViewById(R.id.profile_icon);
		File path = Environment.getExternalStoragePublicDirectory(
        Environment.DIRECTORY_PICTURES);		
		
		if(!path.exists()) {
			path.mkdirs();	
		}
		
		mImageView.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
				startActivityForResult(intent, REQUEST_IMAGE_ALBUM);
			}
		});
        
		loadProfileImage();
        
    }
    
    protected void displayBarcode(Bitmap bm)
	{
    	barCodeImage.setImageBitmap(bm);
	}
	protected void createBarcodeWithBarcode4j(String s)
	{
		try {
            //Create the barcode bean
            Code39Bean bean = new Code39Bean();
            
            final int dpi = 120;
            
            //Configure the barcode generator
            bean.setModuleWidth(UnitConv.in2mm(1.0f / dpi)); //makes the narrow bar 
                                                             //width exactly one pixel
            bean.setWideFactor(3);
            bean.doQuietZone(false);
            
            //Open output file
            File outputFile = new File(Environment.getExternalStorageDirectory() + "/barcode4j.png");
            OutputStream out = new FileOutputStream(outputFile);
            try {   
                MyBarcodeCanvasProvider canvas = new MyBarcodeCanvasProvider(out, 0);
                
                //Generate the barcode
                bean.generateBarcode(canvas, s);
                displayBarcode(canvas.getBarcodeBitmap());
                
                canvas.finish();
            } finally {
                out.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.profileEditBtn:
			Intent intent = new Intent(this, StudentCardEditActivity.class);
			startActivityForResult(intent, 100);
			
			
			
			break;

		default:
			break;
		}
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode==100 && resultCode == RESULT_OK){
			name.setText(data.getStringExtra("name"));
			major.setText(data.getStringExtra("major"));
			studentNumber.setText(data.getStringExtra("studentNumber"));
			createBarcodeWithBarcode4j(data.getStringExtra("studentNumber"));
			saveProfile();
		}
		else if(resultCode == RESULT_OK) {
			switch(requestCode) {
				case REQUEST_IMAGE_ALBUM:
					contentUri = data.getData();
				case REQUEST_IMAGE_CAPTURE:
					rotatePhoto();					
					cropImage(contentUri);
					break;
				case REQUEST_IMAGE_CROP:
					Bundle extras = data.getExtras();
					if(extras != null) {
						Bitmap bitmap = (Bitmap)extras.get("data");						
						mImageView.setImageBitmap(bitmap);
						saveProfileImage(bitmap);
						//임시로 저장한 파일 삭제.
						if(mCurrentPhotoPath != null) {
							File f = new File(mCurrentPhotoPath);
							if(f.exists()) {
								f.delete();
							}
							mCurrentPhotoPath = null;
						}
					}
					break;
			}
		}	    
		
	}
	
	private void loadProfile(){
		name.setText(sharedPref.getString("name", "이름"));
		major.setText(sharedPref.getString("major", "학과"));
		studentNumber.setText(sharedPref.getString("studentNumber", "학번"));		
		createBarcodeWithBarcode4j(sharedPref.getString("studentNumber", "0000001"));
	}
	private void saveProfile(){
		editor.putString("name", name.getText().toString());
		editor.putString("major", major.getText().toString());
		editor.putString("studentNumber", studentNumber.getText().toString());
		editor.commit();
	}
	
	private void loadProfileImage(){
		
		File file = new File(Environment.getExternalStorageDirectory().getPath()+"/profile_icon.png");
		if(file.exists()){	
			Bitmap bitmap = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory().getPath()+"/profile_icon.png");
			mImageView.setImageBitmap(bitmap);
		} else{
			mImageView.setImageLevel(R.drawable.profile_icon);
		}
	}
	
	private void saveProfileImage(Bitmap bitmap){
		//Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.id.profile_icon);
		FileOutputStream out = null;

		try{
			out=new FileOutputStream(Environment.getExternalStorageDirectory().getPath()+"/profile_icon.png");
			bitmap.compress(Bitmap.CompressFormat.PNG, 50, out);
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
	}
	
	private void dispatchTakePictureIntent() {
		Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
    // Ensure that there's a camera activity to handle the intent
    if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
        // Create the File where the photo should go
        File photoFile = null;
        try {
            photoFile = createImageFile();
        } catch (IOException ex) {
            // Error occurred while creating the File
//            ...
        }
        // Continue only if the File was successfully created
        if (photoFile != null) {
        		contentUri = Uri.fromFile(photoFile);        	
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                    Uri.fromFile(photoFile));
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }
	}

	private File createImageFile() throws IOException {
    // Create an image file name
    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
    String imageFileName = "JPEG_" + timeStamp + "_";
    File storageDir = Environment.getExternalStoragePublicDirectory(
            Environment.DIRECTORY_PICTURES);
    File image = File.createTempFile(
        imageFileName,  /* prefix */
        ".jpg",         /* suffix */
        storageDir      /* directory */
    );
    
    mCurrentPhotoPath = image.getAbsolutePath(); //나중에 Rotate하기 위한 파일 경로.
    
    return image;	
	}	
	
	private void galleryAddPic() {
    Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
    File f = new File(mCurrentPhotoPath);
    Uri contentUri = Uri.fromFile(f);
    mediaScanIntent.setData(contentUri);
    this.sendBroadcast(mediaScanIntent);
	}
	
	private void cropImage(Uri contentUri) {
		Intent cropIntent = new Intent("com.android.camera.action.CROP");
	  //indicate image type and Uri of image
	  cropIntent.setDataAndType(contentUri, "image/*");
	  //set crop properties
	  cropIntent.putExtra("crop", "true");
	  //indicate aspect of desired crop
	  cropIntent.putExtra("aspectX", 1);
	  cropIntent.putExtra("aspectY", 1);
	  //indicate output X and Y
	  cropIntent.putExtra("outputX", 256);
	  cropIntent.putExtra("outputY", 256);
	  //retrieve data on return
	  cropIntent.putExtra("return-data", true);
	  startActivityForResult(cropIntent, REQUEST_IMAGE_CROP);		
	}
	
	public Bitmap getBitmap() {
    BitmapFactory.Options options = new BitmapFactory.Options();
    options.inInputShareable = true;
    options.inDither=false;
    options.inTempStorage=new byte[32 * 1024];
    options.inPurgeable = true;
    options.inJustDecodeBounds = false;
  	
  	File f = new File(mCurrentPhotoPath);
    
    FileInputStream fs=null;
    try {
        fs = new FileInputStream(f);
    } catch (FileNotFoundException e) {
        //TODO do something intelligent
        e.printStackTrace();
    }
    
    Bitmap bm = null;

    try {
        if(fs!=null) bm=BitmapFactory.decodeFileDescriptor(fs.getFD(), null, options);
    } catch (IOException e) {
        //TODO do something intelligent
        e.printStackTrace();
    } finally{ 
        if(fs!=null) {
            try {
                fs.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }		
    return bm;
	}
	
  public void rotatePhoto() {
		ExifInterface exif;
		try {
			if(mCurrentPhotoPath == null) {
				mCurrentPhotoPath = contentUri.getPath();
			}
			exif = new ExifInterface(mCurrentPhotoPath);
	    int exifOrientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);	    
	    int exifDegree = exifOrientationToDegrees(exifOrientation);
	    if(exifDegree != 0) {
	    	Bitmap bitmap = getBitmap();			    	
		    Bitmap rotatePhoto = rotate(bitmap, exifDegree);
		    saveBitmap(rotatePhoto);			    			    
	    }	    					
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}				

  }
  
	public int exifOrientationToDegrees(int exifOrientation)
	{
	  if(exifOrientation == ExifInterface.ORIENTATION_ROTATE_90)
	  {
	    return 90;
	  }
	  else if(exifOrientation == ExifInterface.ORIENTATION_ROTATE_180)
	  {
	    return 180;
	  }
	  else if(exifOrientation == ExifInterface.ORIENTATION_ROTATE_270)
	  {
	    return 270;
	  }
	  return 0;
	}
	
	public static Bitmap rotate(Bitmap image, int degrees)
	{
		if(degrees != 0 && image != null)
		{
			Matrix m = new Matrix();
			m.setRotate(degrees, (float)image.getWidth(), (float)image.getHeight());

			try
			{
				Bitmap b = Bitmap.createBitmap(image, 0, 0, image.getWidth(), image.getHeight(), m, true);
				
				if(image != b)
				{
					image.recycle();
					image = b;
				}
					
				image = b;
			} 
			catch(OutOfMemoryError ex)
			{
				ex.printStackTrace();
			}
		}
		return image;
	}
	
	public void saveBitmap(Bitmap bitmap) {
  	File file = new File(mCurrentPhotoPath);
  	OutputStream out = null;
		try {
			out = new FileOutputStream(file);
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		bitmap.compress(CompressFormat.JPEG, 100, out) ;
		try {
			out.close();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	
		
		
	
}