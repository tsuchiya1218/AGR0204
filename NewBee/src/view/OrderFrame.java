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
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import control.NewBeeController;
import model.Customer;
import model.OrderChange;
import model.ControlUtility;

@SuppressWarnings("serial")
public class OrderFrame extends JFrame implements ActionListener {

	private JTextField txtId;

	private JLabel lblName;
	private JTextField txtName;

	private JLabel lblTel;


	private JButton btnOrderRegister;
	private JButton btnReturn;
	private JLabel lblEndTime;
	private JTextField txtEndTime;
	private JLabel lblNum;
	private JTextField txtNum;
	private JLabel lblOrderCode;
	private JTextField txtOrderCode;
	private JLabel lblType;
	private JTextArea txtType;
	private JTextField txtStartTime;
	private JLabel lblStartTime;

	private JTextField txtTel;

	public OrderFrame(OrderChange orderchange) {

		setTitle("【注文内容変更】 NewBee 業務システム");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);

		lblTel = new JLabel("電話番号");
		lblTel.setBounds(20, 20, 100, 20);
		add(lblTel);

		txtTel = new JTextField(orderchange.getTel());
		txtTel.setBounds(120, 20, 360, 20);
		txtTel.setEditable(false);
		add(txtTel);

		lblName = new JLabel("ユーザ名");
		lblName.setBounds(20, 50, 100, 20);
		add(lblName);

		txtName = new JTextField(orderchange.getCustName());
		txtName.setBounds(120, 50, 360, 20);
		txtName.setEditable(false);
		add(txtName);

		lblOrderCode = new JLabel("注文コード");
		lblOrderCode.setBounds(20, 80, 100, 20);
		add(lblOrderCode);

		txtOrderCode = new JTextField(orderchange.getOrderCode());
		txtOrderCode.setBounds(120, 80, 360, 20);
		txtOrderCode.setEditable(false);
		add(txtOrderCode);

		lblType = new JLabel("コース種類");
		lblType.setBounds(20, 110, 100, 20);
		add(lblType);

		txtType = new JTextArea(orderchange.getCourse());
		txtType.setBounds(120, 110, 360, 60);
		txtType.setLineWrap(true);
		txtType.setWrapStyleWord(true);
		txtType.setEditable(false);
		add(txtType);

		lblStartTime = new JLabel("開始日");
		lblStartTime.setBounds(20, 180, 100, 20);
		add(lblStartTime);

		txtStartTime = new JTextField(orderchange.getStartTime());
		txtStartTime.setBounds(120, 180, 360, 20);
		add(txtStartTime);

		lblEndTime = new JLabel("終了日");
		lblEndTime.setBounds(20, 210, 100, 20);
		add(lblEndTime);

		txtEndTime = new JTextField(orderchange.getEndTime());
		txtEndTime.setBounds(120, 210, 360, 20);
		add(txtEndTime);
		
		lblNum = new JLabel("合計（税込）");
		lblNum.setBounds(20, 240, 100, 20);

		txtNum = new JTextField(orderchange.getNum());
		txtNum.setBounds(120, 240, 360, 20);
		add(txtNum);

		btnOrderRegister = new JButton("注文変更");
		btnOrderRegister.setBounds(20, 450, 90, 30);
		btnOrderRegister.addActionListener(this);
		add(btnOrderRegister);

		btnReturn = new JButton("戻る");
		btnReturn.setBounds(120, 450, 90, 30);
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
		if (e.getSource() == btnOrderRegister) {
			String startTime = txtStartTime.getText();
			String endTime = txtEndTime.getText();
			String num = txtNum.getText();
			String tel = txtTel.getText();
			String orderCode = txtOrderCode.getText();

			// 入力値の半角スペースと全角スペースを取り除く
			startTime.replaceAll(" +", "");
			endTime.replaceAll(" +", "");
			num.replaceAll(" +", "");
			tel.replaceAll(" +", "");
			orderCode.replaceAll(" +", "");
			String[] data = { startTime, endTime, num, tel, orderCode};

			try {
				
					String result = NewBeeController.orderChange(data);
					JOptionPane.showMessageDialog(this, result, "【確認】", JOptionPane.INFORMATION_MESSAGE);
			} catch (Exception ex) {

				ControlUtility.systemErrorMessage(this, ex);
			}

		} else if (e.getSource() == btnReturn) {

			setVisible(false);

			try {

				NewBeeController.orderChangeDisplay();

			} catch (Exception ex) {

				ControlUtility.systemErrorMessage(this, ex);
			}
		}
	}
}
