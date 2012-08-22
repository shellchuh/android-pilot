package com.demo.android.sc;

import java.text.DecimalFormat;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CompoundButton;
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

	private Double salaryBefore;
	private Double publicRate;
	private Double personalRate;
	private Double salaryAfterFundandInsurance;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sc);
		findViews();
		setListener();
	}

	private void findViews() {
		button_calc = (Button) findViewById(R.id.calculate);
		public_rate = (TextView) findViewById(R.id.social_security);
		personal_rate = (TextView) findViewById(R.id.public_fund);
		salary_before = (EditText) findViewById(R.id.salary_input);
		salary_before.setOnKeyListener(new EditText.OnKeyListener() {
			@Override
			public boolean onKey(View arg, int arg1, KeyEvent arg2) {
				// Integer salary = Integer.parseInt(salary_before.getText()
				// .toString());
				// if (salary > 11688) {
				// public_rate.setText(salary_before.getText());
				// } else {
				// public_rate.setText("11688");
				// }
				public_rate.setText(salary_before.getText());
				personal_rate.setText(salary_before.getText());
				return false;
			}

		});
		salary_after = (EditText) findViewById(R.id.salary_after);
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

	private void setListener() {
		button_calc.setOnClickListener(calcSc);
	}

	private OnClickListener calcSc = new Button.OnClickListener() {
		public void onClick(View v) {
			try {
				Sc.this.hideSoftInput();
				Sc.this.getSalaryBefore();
				Sc.this.getPublicRate();
				Sc.this.getPersonalRate();
				Sc.this.calSalaryAfterFundandInsurance();
				Sc.this.calSalaryAfter();
				Sc.this.generateCompanyPaidTotal();
				Sc.this.generatePersonalPaidTotal();
				Sc.this.generatePublicPaidDetails();
				Sc.this.generatePersonalPaidDetails();
			} catch (Exception e) {
				e.printStackTrace();
				Toast.makeText(Sc.this, R.string.input_error,
						Toast.LENGTH_SHORT).show();
			}
		}

	};

	private void hideSoftInput() {
		InputMethodManager inputMethodManager = (InputMethodManager) Sc.this
				.getApplicationContext().getSystemService(
						Context.INPUT_METHOD_SERVICE);
		inputMethodManager.hideSoftInputFromWindow(
				button_calc.getWindowToken(), 0);
	}

	private Double calTax() {
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
		}
		DecimalFormat nf = new DecimalFormat("0.00");
		tax_result.setText(nf.format(tax));
		return tax;
	}

	private void calSalaryAfterFundandInsurance() {
		salaryAfterFundandInsurance = salaryBefore
				- (Double) (personalRate * 0.18);
	}

	private void getPublicRate() {
		DecimalFormat intNf = new DecimalFormat("0");
		publicRate = (double) Double.parseDouble(public_rate.getText()
				.toString());
		if (publicRate > 11688) {
			publicRate = (double) 11688;
		} else if (publicRate < 1280) {
			publicRate = (double) 1280;
		}
		public_rate.setText(intNf.format(publicRate));

	}

	private void getPersonalRate() {
		DecimalFormat intNf = new DecimalFormat("0");
		personalRate = (double) Double.parseDouble(personal_rate.getText()
				.toString());
		if (personalRate > 11688) {
			personalRate = (double) 11688;
		}else if (personalRate < 1280) {
			personalRate = (double) 1280;
		}
		personal_rate.setText(intNf.format(personalRate));

	}

	private void generatePublicPaidDetails() {
		DecimalFormat intNf = new DecimalFormat("0");
		Double pensionCompany = publicRate * 0.22;
		Double medicalInsuranceCompany = publicRate * 0.12;
		Double unemploymentInsuranceCompany = publicRate * 0.02;
		Double industrialInjuryCompany = publicRate * 0.005;
		Double maternityCompany = publicRate * 0.005;
		Double publicFundInsCompany = publicRate * 0.07;

		pension_company.setText(intNf.format(pensionCompany));
		medical_insurance_company
				.setText(intNf.format(medicalInsuranceCompany));
		unemployment_insurance_company.setText(intNf
				.format(unemploymentInsuranceCompany));
		industrial_injury_company
				.setText(intNf.format(industrialInjuryCompany));
		maternity_company.setText(intNf.format(maternityCompany));
		public_fund_ins_company.setText(intNf.format(publicFundInsCompany));

	}

	private void generatePersonalPaidDetails() {
		DecimalFormat intNf = new DecimalFormat("0");
		Double personalPersonal = personalRate * 0.08;
		Double medicalInsurancePersonal = personalRate * 0.02;
		Double unemploymentInsurancePersonal = personalRate * 0.01;
		Double industrialInjuryPersonal = (double) 0;
		Double maternityPersonal = (double) 0;
		Double publicFundInsPersonal = personalRate * 0.07;

		personal_personal.setText(intNf.format(personalPersonal));
		medical_insurance_personal.setText(intNf
				.format(medicalInsurancePersonal));
		unemployment_insurance_personal.setText(intNf
				.format(unemploymentInsurancePersonal));
		industrial_injury_personal.setText(intNf
				.format(industrialInjuryPersonal));
		maternity_personal.setText(intNf.format(maternityPersonal));
		public_fund_ins_personal.setText(intNf.format(publicFundInsPersonal));
	}

	private void generateCompanyPaidTotal() {
		DecimalFormat intNf = new DecimalFormat("0");
		Double companyPaid = publicRate * 0.44;
		company_paid_result.setText(intNf.format(companyPaid));
	}

	private void generatePersonalPaidTotal() {
		DecimalFormat intNf = new DecimalFormat("0");
		Double personalPaid = personalRate * 0.18;
		personal_paid_result.setText(intNf.format(personalPaid));
	}

	private void calSalaryAfter() {
		DecimalFormat nf = new DecimalFormat("0.00");
		Double salaryAfter = salaryAfterFundandInsurance - Sc.this.calTax();
		salary_after.setText(nf.format(salaryAfter));
	}

	private void getSalaryBefore() {
		salaryBefore = Double.parseDouble(salary_before.getText().toString());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_sc, menu);
		return true;
	}

}
