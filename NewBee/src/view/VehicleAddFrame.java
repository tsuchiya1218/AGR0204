/**
 * クラス名：	CustomerSearchFrame
 * 概要　　：	「顧客情報検索」画面
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

import model.ControlUtility;
import control.NewBeeController;

@SuppressWarnings("serial")
public class VehicleAddFrame extends JFrame implements ActionListener {


	private JButton btnReturn;
	private JButton btnDelete;
	private JButton btnAdd;

	private JLabel lblTypeId;
	private JTextField txtTypeId;
	private JLabel lblStart;
	private JTextField txtStart;
	private JLabel lblEnd;
	private JTextField txtEnd;
	private JLabel lblStime;
	private JTextField txtSTime;
	private JLabel lblEtime;


	private JTextField txtETime;


	private JLabel lblName;


	private JTextField txtName;
	private JLabel lblPrice;
	private JTextField txtPrice;

	public VehicleAddFrame() {

		setTitle("【移動手段データ追加】 NEWBEE TRAVEL 業務システム");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);

		lblTypeId = new JLabel("種類ID");
		lblTypeId.setBounds(60, 20, 180, 20);
		add(lblTypeId);

		txtTypeId = new JTextField();
		txtTypeId.setBounds(200, 20, 320, 20);
		add(txtTypeId);

		lblName = new JLabel("便名");
		lblName.setBounds(60, 60, 180, 20);
		add(lblName);

		txtName = new JTextField();
		txtName.setBounds(200, 60, 320, 20);
		add(txtName);

		lblStart = new JLabel("出発");
		lblStart.setBounds(60, 100, 180, 20);
		add(lblStart);

		txtStart = new JTextField();
		txtStart.setBounds(200, 100, 320, 20);
		add(txtStart);

		lblEnd = new JLabel("到着");
		lblEnd.setBounds(60, 140, 180, 20);
		add(lblEnd);

		txtEnd = new JTextField();
		txtEnd.setBounds(200, 140, 320, 20);
		add(txtEnd);


		lblStime = new JLabel("出発日時");
		lblStime.setBounds(60, 180, 180, 20);
		add(lblStime);


		txtSTime = new JTextField();
		txtSTime.setBounds(200, 180, 320, 20);
		add(txtSTime);

		lblEtime = new JLabel("到着日時");
		lblEtime.setBounds(60, 220, 180, 20);
		add(lblEtime);

		txtETime = new JTextField();
		txtETime.setBounds(200, 220, 320, 20);
		add(txtETime);

		lblPrice = new JLabel("単価");
		lblPrice.setBounds(60, 260, 180, 20);
		add(lblPrice);

		txtPrice = new JTextField();
		txtPrice.setBounds(200, 260, 320, 20);
		add(txtPrice);

		btnDelete = new JButton("入力消去");
		btnDelete.setBounds(60, 300, 90, 30);
		btnDelete.addActionListener(this);
		add(btnDelete);

		btnAdd = new JButton("新規追加");
		btnAdd.setBounds(170, 300, 90, 30);
		btnAdd.addActionListener(this);
		add(btnAdd);

		btnReturn = new JButton("戻る");
		btnReturn.setBounds(30, 550, 90, 30);
		btnReturn.addActionListener(this);
		add(btnReturn);


		setVisible(true);
	}

	public void addNotify() {

		super.addNotify();

		Insets insets = getInsets();
		setSize(600 + insets.left + insets.right, 600 + insets.top + insets.bottom);
		setLocationRelativeTo(this);
	}

	@SuppressWarnings("unused")
	public void actionPerformed(ActionEvent e) {
		String path = null;

		if (e.getSource() == btnDelete) {

			txtName.setText("");
			txtTypeId.setText("");
			txtStart.setText("");
			txtEnd.setText("");
			txtSTime.setText("");
			txtETime.setText("");
			txtPrice.setText("");

		} else if (e.getSource() == btnAdd) {

			String name = txtName.getText();
			String typeId = txtTypeId.getText();
			String start = txtStart.getText();
			String end = txtEnd.getText();
			String stime = txtSTime.getText();
			String etime = txtETime.getText();
			String price = txtPrice.getText();

			// 入力値の半角スペースと全角スペースを取り除く
			name.replaceAll(" +", "");
			typeId.replaceAll(" +", "");
			start.replaceAll(" +", "");
			end.replaceAll(" +", "");
			stime.replaceAll(" +", "");
			etime.replaceAll(" +", "");
			price.replaceAll(" +", "");

			String[] data = { name, start, end, stime, etime, price,typeId};

			try {
				if (name != null && typeId != null && start != null && end != null && stime != null && etime != null) {
					//DBに追加メソッド
					String result = NewBeeController.vehicleAdd(data);

					JOptionPane.showMessageDialog(this, result, "【確認】", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(this, "未入力項目があります。" + "\n" + "ご確認ください。", "【確認】", JOptionPane.WARNING_MESSAGE);
				}
			} catch (Exception ex) {

				ControlUtility.systemErrorMessage(this, ex);
			}

		} else if (e.getSource() == btnReturn) {
			setVisible(false);
			try {

				NewBeeController.vehicleDisplay();

			} catch (Exception ex) {

				ControlUtility.systemErrorMessage(this, ex);
			}
		}
	}

}
