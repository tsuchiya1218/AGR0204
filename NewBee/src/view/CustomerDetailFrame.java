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
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import control.NewBeeController;
import model.ControlUtility;
import model.Customer;
import model.Item;

@SuppressWarnings("serial")
public class CustomerDetailFrame extends JFrame implements ActionListener {

	private JLabel lblId;
	private JTextField txtId;

	private JLabel lblName;
	private JTextField txtName;

	private JLabel lblTel;
	private JTextField txtTel;

	private JLabel lblAddress;
	private JTextField txtAddress;

	private JButton btnReturn;
	private JButton btnFreeze;
	private JLabel lblEmail;
	private JTextField txtEmail;

	public CustomerDetailFrame(Customer customer) {

		setTitle("【会員情報管理】 NewBee 業務システム");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);

		lblId = new JLabel("ID");
		lblId.setBounds(20, 20, 100, 20);
		add(lblId);

		txtId = new JTextField(customer.getCustomerid());
		txtId.setBounds(120, 20, 360, 20);
		txtId.setEnabled(false);
		add(txtId);

		lblName = new JLabel("ユーザー名");
		lblName.setBounds(20, 50, 100, 20);
		add(lblName);

		txtName = new JTextField(customer.getName());
		txtName.setBounds(120, 50, 360, 20);
		txtName.setEnabled(false);
		add(txtName);

		lblEmail = new JLabel("メールアドレス");
		lblEmail.setBounds(20, 80, 100, 20);
		add(lblEmail);

		txtEmail = new JTextField(customer.getEmail());
		txtEmail.setBounds(120, 80, 360, 20);
		txtEmail.setEnabled(false);
		add(txtEmail);

		lblTel = new JLabel("電話番号");
		lblTel.setBounds(20, 110, 100, 20);
		add(lblTel);

		txtTel = new JTextField(customer.getTel());
		txtTel.setBounds(120, 110, 360, 20);
		txtTel.setEnabled(false);
		add(txtTel);

		lblAddress = new JLabel("住所");
		lblAddress.setBounds(20, 140, 100, 20);
		add(lblAddress);

		txtAddress = new JTextField(customer.getTel());
		txtAddress.setBounds(120, 140, 360, 20);
		txtAddress.setEnabled(false);
		add(txtAddress);


		btnFreeze = new JButton("禁止");
		btnFreeze.setBounds(20, 450, 90, 30);
		btnFreeze.addActionListener(this);
		add(btnFreeze);

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

		if (e.getSource() == btnFreeze) {


			// cusidをdbに渡すメソッド
			String data = txtId.getText();

			NewBeeController.customerUpdate(data);
			JOptionPane.showMessageDialog(this, "処理完了しました。", "【確認】", JOptionPane.INFORMATION_MESSAGE);

		} else if (e.getSource() == btnReturn) {

			setVisible(false);

			try {

				NewBeeController.customerSearchDisplay();

			} catch (Exception ex) {

				ControlUtility.systemErrorMessage(this, ex);
			}
		}
	}
}
