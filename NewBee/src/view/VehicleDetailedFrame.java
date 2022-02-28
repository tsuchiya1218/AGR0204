/**
 * クラス名：	OrderInputFrame
 * 概要　　：	「注文／配達情報／顧客情報変更」画面
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
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import control.NewBeeController;
import model.BookingCheck;
import model.Customer;
import model.Item;
import model.OrderChange;
import model.ControlUtility;
import model.Reviews;
import model.RoomDetailed;
import model.VehicleDetailed;

@SuppressWarnings("serial")
public class VehicleDetailedFrame extends JFrame implements ActionListener {

	private JLabel lblId;
	private JTextField txtId;

	private JLabel lblName;
	private JTextField txtName;

	private JLabel lblttypeid;
	private JTextField txtTel;

	private JLabel lblKana;
	private JTextField txtKana;

	private JLabel lblAddress;
	private JTextField txtAddress;

	private JButton btnOrder;
	private JButton btnDelivery;
	private JButton btnModify;

	private JTable table;

	private JButton btnOrderRegister;
	private JButton btnReturn;
	private JTextField txtNum;
	private JLabel lblNum;
	private JButton btnOrderCancel;
	private JButton btnRoomRegister;
	private JTextArea txtComment;
	private JLabel lblSum;
	private JTextField txtSum;
	private JTextField txtttypeid;
	private JLabel lbldstation;
	private JTextField txtdstation;
	private JLabel lblastation;
	private JTextField txtastation;
	private JLabel lbldtime;
	private JTextField txtdtime;
	private JLabel lblatime;
	private JTextField txtatime;
	private JLabel lblPrice;
	private JTextField txtPrice;

	public VehicleDetailedFrame(VehicleDetailed vehicle) {

		setTitle("【交通手段詳細】 NewBee 業務システム");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);

		lblName = new JLabel("便名");
		lblName.setBounds(20, 20, 100, 20);
		add(lblName);

		txtName = new JTextField(vehicle.getName());
		txtName.setBounds(120, 20, 360, 20);
		txtName.setEditable(false);
		add(txtName);

		lbldstation = new JLabel("出発駅");
		lbldstation.setBounds(20, 50, 100, 20);
		add(lbldstation);

		txtdstation = new JTextField(vehicle.getDstation());
		txtdstation.setBounds(120, 50, 360, 20);
		txtdstation.setEditable(false);
		add(txtdstation);

		lblastation = new JLabel("到着駅");
		lblastation.setBounds(20, 80, 100, 20);
		add(lblastation);

		txtastation = new JTextField(vehicle.getAstation());
		txtastation.setBounds(120, 80, 360, 20);
		txtastation.setEditable(false);
		add(txtastation);

		lbldtime = new JLabel("出発日時");
		lbldtime.setBounds(20, 110, 100, 20);
		add(lbldtime);

		txtdtime = new JTextField(vehicle.getDtime());
		txtdtime.setBounds(120, 110, 360, 20);
		add(txtdtime);

		lblatime = new JLabel("到着日時");
		lblatime.setBounds(20, 140, 100, 20);
		add(lblatime);

		txtatime = new JTextField(vehicle.getAtime());
		txtatime.setBounds(120, 140, 360, 20);
		add(txtatime);

		lblPrice = new JLabel("価格");
		lblPrice.setBounds(20, 170, 100, 20);
		add(lblPrice);

		txtPrice = new JTextField(vehicle.getPrice());
		txtPrice.setBounds(120, 170, 360, 20);
		add(txtPrice);

		lblttypeid = new JLabel("種類ID");
		lblttypeid.setBounds(20, 210, 100, 20);
		add(lblttypeid);

		txtttypeid = new JTextField(vehicle.getTtypeid());
		txtttypeid.setBounds(120, 210, 360, 20);
		txtttypeid.setEditable(false);
		add(txtttypeid);

		btnRoomRegister = new JButton("情報変更");
		btnRoomRegister.setBounds(20, 450, 90, 30);
		btnRoomRegister.addActionListener(this);
		add(btnRoomRegister);

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

		if (e.getSource() == btnRoomRegister) {
			String dstation = txtdstation.getText();
			String astation = txtastation.getText();
			String dtime = txtdtime.getText();
			String atime = txtatime.getText();
			String price = txtPrice.getText();
			// 入力値の半角スペースと全角スペースを取り除く
			dstation.replaceAll(" +", "");
			astation.replaceAll(" +", "");
			dtime.replaceAll(" +", "");
			atime.replaceAll(" +", "");
			price.replaceAll(" +", "");

			String[] data = {dstation,astation,dtime,atime,price};
			try {
				//変更する。
				String result = NewBeeController.vehicleUpdate(data);
				JOptionPane.showMessageDialog(this, result, "【確認】", JOptionPane.INFORMATION_MESSAGE);
			} catch (Exception ex) {

				ControlUtility.systemErrorMessage(this, ex);
			}

		} else if (e.getSource() == btnReturn) {

			setVisible(false);

			try {

				NewBeeController.vehicleUpdateDisplay();

			} catch (Exception ex) {

				ControlUtility.systemErrorMessage(this, ex);
			}
		}
	}
}
