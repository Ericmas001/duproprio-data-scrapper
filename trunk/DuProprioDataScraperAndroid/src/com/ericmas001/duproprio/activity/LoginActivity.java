package com.ericmas001.duproprio.activity;

import org.json.JSONArray;
import org.json.JSONException;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ericmas001.duproprio.R;
import com.ericmas001.duproprio.entities.HouseList;
import com.ericmas001.duproprio.entities.HouseSummary;
import com.ericmas001.duproprio.util.ContactWebservice;

public class LoginActivity extends Activity {

    public static final String PREFS_NAME = "LoginPrefsFile";
	
    private String mEmail;
	private String mPassword;

	private EditText mEmailView;
	private EditText mPasswordView;
	private View mLoginFormView;
	private View mLoginStatusView;
	private TextView mLoginStatusMessageView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_login);

		mEmailView = (EditText) findViewById(R.id.email);
		mEmailView.setText(mEmail);

		mPasswordView = (EditText) findViewById(R.id.password);
		mPasswordView
				.setOnEditorActionListener(new TextView.OnEditorActionListener() 
				{
					@Override
					public boolean onEditorAction(TextView textView, int id,
							KeyEvent keyEvent) 
					{
						if (id == R.id.login || id == EditorInfo.IME_NULL) {
							attemptLogin();
							return true;
						}
						return false;
					}
				});

		mLoginFormView = findViewById(R.id.login_form);
		mLoginStatusView = findViewById(R.id.login_status);
		mLoginStatusMessageView = (TextView) findViewById(R.id.login_status_message);

		findViewById(R.id.sign_in_button).setOnClickListener(
				new View.OnClickListener() 
				{
					@Override
					public void onClick(View view) 
					{
						attemptLogin();
					}
				});
		
       SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
       mEmailView.setText(settings.getString("savedUser", ""));
       mPasswordView.setText(settings.getString("savedPassword", ""));
	}

	public void attemptLogin() 
	{

		mEmailView.setError(null);
		mPasswordView.setError(null);

		mEmail = mEmailView.getText().toString();
		mPassword = mPasswordView.getText().toString();

		boolean cancel = false;
		View focusView = null;

		if (TextUtils.isEmpty(mPassword)) 
		{
			mPasswordView.setError(getString(R.string.error_field_required));
			focusView = mPasswordView;
			cancel = true;
		}

		if (TextUtils.isEmpty(mEmail)) 
		{
			mEmailView.setError(getString(R.string.error_field_required));
			focusView = mEmailView;
			cancel = true;
		}

		if (cancel) 
		{
			focusView.requestFocus();
		} 
		else 
		{
			InputMethodManager imm = (InputMethodManager)getSystemService(
				      Context.INPUT_METHOD_SERVICE);
				imm.hideSoftInputFromWindow(mLoginFormView.getWindowToken(), 0);
			mLoginStatusMessageView.setText(R.string.login_progress_signing_in);
			showProgress(true);
			ContactWebservice.CallWS(this, "onPostExecute", "http://ws.ericmas001.com/duproprio/user/favs/"+mEmail+"/"+mPassword);
		}
	}
    public void onPostExecute(String result, Exception exception)
    {			
        if (result != null && !result.isEmpty())
        {
            try
            {
                SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
                SharedPreferences.Editor editor = settings.edit();
                editor.putString("savedUser", mEmail);
                editor.putString("savedPassword", mPassword);
                editor.commit();
            	
            	JSONArray all = new JSONArray(result);
                for (int i = 0; i < all.length(); ++i)
                	HouseList.addItem(new HouseSummary(all.getJSONObject(i)));

                Intent intent = new Intent(this, HouseSummaryActivity.class);
            	startActivity(intent);  
            	showProgress(false); 
                finish();
            }
            catch (JSONException e)
            {
            	showProgress(false);
                Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
            }
        }
        else
        {
        	showProgress(false);
			mPasswordView.setError(getString(R.string.error_incorrect_password));
			mPasswordView.requestFocus();
			InputMethodManager imm = (InputMethodManager)getSystemService(
				      Context.INPUT_METHOD_SERVICE);
				imm.showSoftInput(mPasswordView,0);
        }
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
	private void showProgress(final boolean show) {

    	if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
			int shortAnimTime = getResources().getInteger(
					android.R.integer.config_shortAnimTime);

			mLoginStatusView.setVisibility(View.VISIBLE);
			mLoginStatusView.animate().setDuration(shortAnimTime)
					.alpha(show ? 1 : 0)
					.setListener(new AnimatorListenerAdapter() {
						@Override
						public void onAnimationEnd(Animator animation) {
							mLoginStatusView.setVisibility(show ? View.VISIBLE
									: View.GONE);
						}
					});

			mLoginFormView.setVisibility(View.VISIBLE);
			mLoginFormView.animate().setDuration(shortAnimTime)
					.alpha(show ? 0 : 1)
					.setListener(new AnimatorListenerAdapter() {
						@Override
						public void onAnimationEnd(Animator animation) {
							mLoginFormView.setVisibility(show ? View.GONE
									: View.VISIBLE);
						}
					});
		} else {
			mLoginStatusView.setVisibility(show ? View.VISIBLE : View.GONE);
			mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
		}
	}
}
