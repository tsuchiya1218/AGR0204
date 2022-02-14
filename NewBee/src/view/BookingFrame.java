/**
 * クラス名：	OrderInputFrame
 * 概要　　：	「注文／配達情報／顧客情報変更」画面
 * 作成者名：
 * 作成日　：
 * 修正者名：
 * 修正日　：
 */

package view;




import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import control.NewBeeController;
import model.BookingCheck;
import model.ControlUtility;


@SuppressWarnings("serial")
public class BookingFrame extends JFrame implements ActionListener {


	private JLabel lblName;
	private JTextField txtName;

	private JLabel lblTel;
	private JTextField txtTel;


	private JButton btnOrderRegister;
	private JButton btnReturn;
	private JTextField txtNum;
	private JLabel lblNum;
	private JButton btnOrderCancel;
	private JLabel lblOrderCode;
	private JTextField txtOrderCode;
	private JLabel lblType;
	private JTextField txtType;
	private JLabel lblTime;
	private JTextField txtTime;

	public BookingFrame(BookingCheck bookingCheck) {

		setTitle("【予約情報確認】 NewBee 業務システム");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);

		lblTel = new JLabel("電話番号");
		lblTel.setBounds(20, 20, 100, 20);
		add(lblTel);

		txtTel = new JTextField(bookingCheck.getTel());
		txtTel.setBounds(120, 20, 360, 20);
		txtTel.setEditable(false);
		add(txtTel);

		lblName = new JLabel("ユーザ名");
		lblName.setBounds(20, 50, 100, 20);
		add(lblName);

		txtName = new JTextField(bookingCheck.getCustName());
		txtName.setBounds(120, 50, 360, 20);
		txtName.setEditable(false);
		add(txtName);

		lblOrderCode = new JLabel("注文コード");
		lblOrderCode.setBounds(20, 80, 100, 20);
		add(lblOrderCode);

		txtOrderCode = new JTextField(bookingCheck.getOrderCode());
		txtOrderCode.setBounds(120, 80, 360, 20);
		txtOrderCode.setEditable(false);
		add(txtOrderCode);

		lblType = new JLabel("コース種類");
		lblType.setBounds(20, 110, 100, 20);
		add(lblType);

		txtType = new JTextField(bookingCheck.getType());
		txtType.setBounds(120, 110, 360, 20);
		txtType.setEditable(false);
		add(txtType);

		lblTime = new JLabel("注文日時");
		lblTime.setBounds(20, 140, 100, 20);
		add(lblTime);

		txtTime = new JTextField(bookingCheck.getOrderTime());
		txtTime.setBounds(120, 140, 360, 20);
		txtTime.setEditable(false);
		add(txtTime);
		
		lblNum = new JLabel("合計（税込）");
		lblNum.setBounds(20, 170, 100, 20);
		add(lblNum);

		txtNum = new JTextField(bookingCheck.getNum());
		txtNum.setBounds(120, 170, 360, 20);
		txtNum.setEditable(false);
		add(txtNum);

		btnOrderRegister = new JButton("注文登録");
		btnOrderRegister.setBounds(20, 450, 90, 30);
		btnOrderRegister.addActionListener(this);
		add(btnOrderRegister);

		btnOrderCancel = new JButton("注文取消");
		btnOrderCancel.setBounds(120, 450, 90, 30);
		btnOrderCancel.addActionListener(this);
		add(btnOrderCancel);
		
		btnReturn = new JButton("戻る");
		btnReturn.setBounds(220, 450, 90, 30);
		btnReturn.addActionListener(this);
		add(btnReturn);

		setVisible(true);
	}

	public void addNotify() {

		super.addNotify();

		Insets insets = getInsets();
		setSize(500 + insets.left + insets.right, 500 + insets.top + insets.bottom);
		setLocationRelativeTo(this);
	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btnOrderCancel) {
			try {
				//telを渡して、注文をキャンセルする。
				String[] data = {txtTel.getText(),txtOrderCode.getText()};
				String result = NewBeeController.executeCancel(data);
				JOptionPane.showMessageDialog(this, result, "【確認】", JOptionPane.INFORMATION_MESSAGE);

			} catch (Exception ex) {

				ControlUtility.systemErrorMessage(this, ex);
			}

		} else if (e.getSource() == btnOrderRegister) {
			
			try {
				String[] data = {txtTel.getText(),txtOrderCode.getText()};
				String result = NewBeeController.executeRegister(data);
				JOptionPane.showMessageDialog(this, result, "【確認】", JOptionPane.INFORMATION_MESSAGE);
			} catch (Exception ex) {

				ControlUtility.systemErrorMessage(this, ex);
			}

		} else if (e.getSource() == btnReturn) {

			setVisible(false);

			try {

				NewBeeController.bookingCheckDisplay();

			} catch (Exception ex) {

				ControlUtility.systemErrorMessage(this, ex);
			}
		}
	}
}
