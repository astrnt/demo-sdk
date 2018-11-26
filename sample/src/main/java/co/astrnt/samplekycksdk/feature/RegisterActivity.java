package co.astrnt.samplekycksdk.feature;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import co.astrnt.demosdk.core.MyObserver;
import co.astrnt.demosdk.dao.RegisterApiDao;
import co.astrnt.demosdk.repository.CandidateRepository;
import co.astrnt.samplekycksdk.R;
import co.astrnt.samplekycksdk.base.BaseActivity;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class RegisterActivity extends BaseActivity implements View.OnClickListener {

    private CandidateRepository repository;
    private AppCompatEditText inpEmail, inpName;
    private Button btnSubmit;
    private ProgressDialog progressDialog;

    public static void start(Context context) {
        Intent intent = new Intent(context, RegisterActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        context = this;

        inpName = findViewById(R.id.inp_name);
        inpEmail = findViewById(R.id.inp_email);
        btnSubmit = findViewById(R.id.btn_submit);

        repository = new CandidateRepository(getApi());

        btnSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_submit:
                validateInput();
                break;
        }
    }

    private void validateInput() {

        String name = inpName.getText().toString();
        String email = inpEmail.getText().toString();

        if (TextUtils.isEmpty(name)) {
            inpName.setError("Name still empty");
            inpName.setFocusable(true);
            return;
        }

        if (TextUtils.isEmpty(email)) {
            inpEmail.setError("Email still empty");
            inpEmail.setFocusable(true);
            return;
        }
        doLogin(name, email);
    }

    private void doLogin(final String name, final String email) {
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        repository.registerCandidate(name, email)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver<RegisterApiDao>() {

                    @Override
                    public void onApiResultCompleted() {
                        progressDialog.dismiss();
                    }

                    @Override
                    public void onApiResultError(String message, String code) {
                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onApiResultOk(RegisterApiDao registerApiDao) {
                        if (registerApiDao.getStatus().equals("SUCCESS")) {
                            Toast.makeText(context, "Candidate Id" + registerApiDao.getCandidateIdentifier(), Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(context, registerApiDao.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

}
