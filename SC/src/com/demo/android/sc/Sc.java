package com.demo.android.sc;

import java.text.DecimalFormat;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Sc extends Activity {

	private Button button_calc;
	private EditText salary_before;
	private EditText salary_after;
	private TextView tax_result;
	private TextView company_paid_result;
	private TextView personal_paid_result;
	private TextView public_rate;
	private TextView personal_rate;
	private EditText pension_company;
	private EditText medical_insurance_company;
	private EditText unemployment_insurance_company;
	private EditText industrial_injury_company;
	private EditText maternity_company;
	private EditText public_fund_ins_company;
	private EditText personal_personal;
	private EditText medical_insurance_personal;
	private EditText unemployment_insurance_personal;
	private EditText industrial_injury_personal;
	private EditText maternity_personal;
	private EditText public_fund_ins_personal;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sc);
		findViews();
		setListener();
	}

	private void setListener() {
		button_calc.setOnClickListener(calcSc);
	}

	private void findViews() {
		button_calc = (Button) findViewById(R.id.calculate);
		salary_before = (EditText) findViewById(R.id.salary_input);
		salary_after = (EditText) findViewById(R.id.salary_after);
		public_rate = (TextView) findViewById(R.id.social_security);
		personal_rate = (TextView) findViewById(R.id.public_fund);
		tax_result = (TextView) findViewById(R.id.tax);

		company_paid_result = (TextView) findViewById(R.id.company_paid);
		personal_paid_result = (TextView) findViewById(R.id.personal_paid);

		pension_company = (EditText) findViewById(R.id.pension_company);
		medical_insurance_company = (EditText) findViewById(R.id.medical_insurance_company);
		unemployment_insurance_company = (EditText) findViewById(R.id.unemployment_insurance_company);
		industrial_injury_company = (EditText) findViewById(R.id.industrial_injury_company);
		maternity_company = (EditText) findViewById(R.id.maternity_company);
		public_fund_ins_company = (EditText) findViewById(R.id.public_fund_ins_company);

		personal_personal = (EditText) findViewById(R.id.personal_personal);
		medical_insurance_personal = (EditText) findViewById(R.id.medical_insurance_personal);
		unemployment_insurance_personal = (EditText) findViewById(R.id.unemployment_insurance_personal);
		industrial_injury_personal = (EditText) findViewById(R.id.industrial_injury_personal);
		maternity_personal = (EditText) findViewById(R.id.maternity_personal);
		public_fund_ins_personal = (EditText) findViewById(R.id.public_fund_ins_personal);

	}

	private OnClickListener calcSc = new Button.OnClickListener() {
		public void onClick(View v) {
			try {
				InputMethodManager inputMethodManager = (InputMethodManager) Sc.this
						.getApplicationContext().getSystemService(
								Context.INPUT_METHOD_SERVICE);
				inputMethodManager.hideSoftInputFromWindow(
						button_calc.getWindowToken(), 0);

				DecimalFormat nf = new DecimalFormat("0.00");
				DecimalFormat intNf = new DecimalFormat("0");
				Double salaryBefore = Double.parseDouble(salary_before
						.getText().toString());
				Double publicRate;
				Double personalRate;
				if (public_rate.getText().toString().equals("")) {
					publicRate = salaryBefore;
					public_rate.setText(intNf.format(publicRate));
				} else {
					publicRate = (double) Double.parseDouble(public_rate
							.getText().toString());
				}
				if (personal_rate.getText().toString().equals("")) {
					personalRate = salaryBefore;
					personal_rate.setText(intNf.format(personalRate));
				} else {
					personalRate = (double) Double.parseDouble(personal_rate
							.getText().toString());
				}

				Double salaryAfterFundandInsurance = salaryBefore
						- (Double) (personalRate * 0.18);
				Double salaryAfter = salaryAfterFundandInsurance;
				Double tax = (double) 0;
				if (salaryAfterFundandInsurance > 3500) {
					Double salaryExtra = salaryAfterFundandInsurance - 3500;
					if (salaryExtra <= 1500) {
						tax = salaryExtra * 0.03 - 0;
					} else if (salaryExtra > 1500 && salaryExtra <= 4500) {
						tax = salaryExtra * 0.1 - 105;
					} else if (salaryExtra > 4500 && salaryExtra <= 9000) {
						tax = salaryExtra * 0.2 - 555;
					} else if (salaryExtra > 9000 && salaryExtra <= 35000) {
						tax = salaryExtra * 0.25 - 1005;
					} else if (salaryExtra > 35000 && salaryExtra <= 55000) {
						tax = salaryExtra * 0.3 - 2755;
					} else if (salaryExtra > 55000 && salaryExtra <= 80000) {
						tax = salaryExtra * 0.35 - 5505;
					} else if (salaryExtra > 80000) {
						tax = salaryExtra * 0.45 - 13505;
					}
					salaryAfter = salaryAfterFundandInsurance - tax;
				}
				Double companyPaid = publicRate * 0.44;
				Double personalPaid = personalRate * 0.18;

				Double pensionCompany = publicRate * 0.22;
				Double medicalInsuranceCompany = publicRate * 0.12;
				Double unemploymentInsuranceCompany = publicRate * 0.02;
				Double industrialInjuryCompany = publicRate * 0.005;
				Double maternityCompany = publicRate * 0.005;
				Double publicFundInsCompany = publicRate * 0.07;

				Double personalPersonal = personalRate * 0.08;
				Double medicalInsurancePersonal = personalRate * 0.02;
				Double unemploymentInsurancePersonal = personalRate * 0.01;
				Double industrialInjuryPersonal = (double) 0;
				Double maternityPersonal = (double) 0;
				Double publicFundInsPersonal = personalRate * 0.07;

				salary_after.setText(nf.format(salaryAfter));
				tax_result.setText(nf.format(tax));
				company_paid_result.setText(intNf.format(companyPaid));
				personal_paid_result.setText(intNf.format(personalPaid));

				pension_company.setText(intNf.format(pensionCompany));
				medical_insurance_company.setText(intNf
						.format(medicalInsuranceCompany));
				unemployment_insurance_company.setText(intNf
						.format(unemploymentInsuranceCompany));
				industrial_injury_company.setText(intNf
						.format(industrialInjuryCompany));
				maternity_company.setText(intNf.format(maternityCompany));
				public_fund_ins_company.setText(intNf
						.format(publicFundInsCompany));

				personal_personal.setText(intNf.format(personalPersonal));
				medical_insurance_personal.setText(intNf
						.format(medicalInsurancePersonal));
				unemployment_insurance_personal.setText(intNf
						.format(unemploymentInsurancePersonal));
				industrial_injury_personal.setText(intNf
						.format(industrialInjuryPersonal));
				maternity_personal.setText(intNf.format(maternityPersonal));
				public_fund_ins_personal.setText(intNf
						.format(publicFundInsPersonal));
			} catch (Exception e) {
				e.printStackTrace();
				Toast.makeText(Sc.this, R.string.input_error,
						Toast.LENGTH_SHORT).show();
			}
		}

	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_sc, menu);
		return true;
	}

}
