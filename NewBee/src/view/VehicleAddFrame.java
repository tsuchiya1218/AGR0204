/**
 * クラス名：	CustomerSearchFrame
 * 概要　　：	「顧客情報検索」画面
 * 作成者名：
 * 作成日　：
 * 修正者名：
 * 修正日　：
 */

package view;

import java.awt.Component;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import model.Customer;
import model.OrderControlUtility;
import control.NewBeeController;

@SuppressWarnings("serial")
public class VehicleAddFrame extends JFrame implements ActionListener {


	private JButton btnReturn;
	private JButton btnDelete;
	private JButton btnAdd;

	private JLabel lblId;
	private JTextField txtId;
	private JLabel lblType;
	private JTextField txtType;
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

	public VehicleAddFrame() {

		setTitle("【移動手段データ追加】 NEWBEE TRAVEL 業務システム");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);

		lblType = new JLabel("種類");
		lblType.setBounds(60, 20, 180, 20);
		add(lblType);

		txtType = new JTextField();
		txtType.setBounds(200, 20, 320, 20);
		add(txtType);

		lblName = new JLabel("便名");
		lblName.setBounds(60, 60, 180, 20);
		add(lblName);

		txtName = new JTextField();
		txtName.setBounds(200, 60, 320, 20);
		add(txtName);

		lblStart = new JLabel("出発駅");
		lblStart.setBounds(60, 100, 180, 20);
		add(lblStart);

		txtStart = new JTextField();
		txtStart.setBounds(200, 100, 320, 20);
		add(txtStart);

		lblEnd = new JLabel("到着駅");
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

		btnDelete = new JButton("入力消去");
		btnDelete.setBounds(390, 260, 90, 30);
		btnDelete.addActionListener(this);
		add(btnDelete);

		btnAdd = new JButton("新規追加");
		btnAdd.setBounds(490, 260, 90, 30);
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

			txtId.setText("");
			txtName.setText("");
			txtType.setText("");
			txtStart.setText("");
			txtEnd.setText("");
			txtSTime.setText("");
			txtETime.setText("");

		} else if (e.getSource() == btnAdd) {

			String id = txtId.getText();
			String name = txtName.getText();
			String type = txtType.getText();
			String start = txtStart.getText();
			String end = txtEnd.getText();
			String stime = txtSTime.getText();
			String etime = txtETime.getText();

			// 入力値の半角スペースと全角スペースを取り除く
			id.replaceAll(" +", "");
			name.replaceAll(" +", "");
			type.replaceAll(" +", "");
			start.replaceAll(" +", "");
			end.replaceAll(" +", "");
			stime.replaceAll(" +", "");
			etime.replaceAll(" +", "");
			String[] data = {id, name, type, start, end, stime, etime};

			try {
				if (id != null && name != null && type != null && start != null && end != null && stime != null && etime != null) {
					String result = NewBeeController.spotAdd(data);

					JOptionPane.showMessageDialog(this, result, "【確認】", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(this, "未入力項目があります。" + "\n" + "ご確認ください。", "【確認】", JOptionPane.WARNING_MESSAGE);
				}
			} catch (Exception ex) {

				OrderControlUtility.systemErrorMessage(this, ex);
			}

		} else if (e.getSource() == btnReturn) {
			setVisible(false);
			try {

				NewBeeController.vehicleDisplay();

			} catch (Exception ex) {

				OrderControlUtility.systemErrorMessage(this, ex);
			}
		}
	}

}
