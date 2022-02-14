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

@SuppressWarnings("serial")
public class RoomDetailedFrame extends JFrame implements ActionListener {

	private JLabel lblId;
	private JTextField txtId;

	private JLabel lblName;
	private JTextField txtName;

	private JLabel lblTel;
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

	public RoomDetailedFrame(RoomDetailed roomDetailed) {

		setTitle("【部屋詳細】 NewBee 業務システム");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);

		lblId = new JLabel("ホテルID");
		lblId.setBounds(20, 20, 100, 20);
		add(lblId);

		txtId = new JTextField(roomDetailed.getHotelId());
		txtId.setBounds(120, 20, 360, 20);
		txtId.setEditable(false);
		add(txtId);

		lblName = new JLabel("ホテル名");
		lblName.setBounds(20, 50, 100, 20);
		add(lblName);

		txtName = new JTextField(roomDetailed.getHotelName());
		txtName.setBounds(120, 50, 360, 20);
		txtName.setEditable(false);
		add(txtName);

		lblKana = new JLabel("部屋ID");
		lblKana.setBounds(20, 80, 100, 20);
		add(lblKana);

		txtKana = new JTextField(roomDetailed.getRoomId());
		txtKana.setBounds(120, 80, 360, 20);
		txtKana.setEditable(false);
		add(txtKana);

		lblTel = new JLabel("確保部屋数");
		lblTel.setBounds(20, 110, 100, 20);
		add(lblTel);

		txtTel = new JTextField(roomDetailed.getRoomNum());
		txtTel.setBounds(120, 110, 360, 20);
		add(txtTel);

		lblAddress = new JLabel("部屋タイプ");
		lblAddress.setBounds(20, 140, 100, 20);
		add(lblAddress);

		txtAddress = new JTextField(roomDetailed.getRoomType());
		txtAddress.setBounds(120, 140, 360, 20);
		txtAddress.setEditable(false);
		add(txtAddress);
		
		lblAddress = new JLabel("概要");
		lblAddress.setBounds(20, 170, 100, 20);
		add(lblAddress);

		txtComment = new JTextArea(roomDetailed.getComment());
		txtComment.setBounds(120, 170, 360, 80);
		txtComment.setLineWrap(true);
		txtComment.setWrapStyleWord(true);
		add(txtComment);
		
		
		lblNum = new JLabel("残り部屋数");
		lblNum.setBounds(20, 260, 100, 20);
		add(lblNum);

		txtNum = new JTextField(roomDetailed.getNum());
		txtNum.setBounds(120, 260, 360, 20);
		add(txtNum);

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

			try {
				//hotelIdとroomIdを渡して、部屋情報を変更する。
				//String[][] tableData = NewBeeController.itemMenuDisplay();

			} catch (Exception ex) {

				ControlUtility.systemErrorMessage(this, ex);
			}

		} else if (e.getSource() == btnReturn) {

			setVisible(false);

			try {

				NewBeeController.roomUpdateDisplay();

			} catch (Exception ex) {

				ControlUtility.systemErrorMessage(this, ex);
			}
		}
	}

	private class ConfirmDialog extends JDialog implements ActionListener {

		private JLabel lblIdCap;
		private JLabel lblId;

		private JLabel lblNameCap;
		private JLabel lblName;

		private JLabel lblTelCap;
		private JLabel lblTel;

		private JLabel lblKanaCap;
		private JLabel lblKana;

		private JLabel lblAddressCap;
		private JLabel lblAddress;

		private JLabel lblMessage;

		private JButton btnDecide;
		private JButton btnReturn;

		public ConfirmDialog() {

			setTitle("【顧客情報変更確認】");
			setModalityType(JDialog.DEFAULT_MODALITY_TYPE);
			setLayout(null);

			lblIdCap = new JLabel("ID");
			lblIdCap.setBounds(20, 20, 100, 20);
			add(lblIdCap);

			lblId = new JLabel("：　" + txtId.getText());
			lblId.setBounds(120, 20, 360, 20);
			add(lblId);

			lblNameCap = new JLabel("氏名");
			lblNameCap.setBounds(20, 40, 100, 20);
			add(lblNameCap);

			lblName = new JLabel("：　" + txtName.getText());
			lblName.setBounds(120, 40, 360, 20);
			add(lblName);

			lblKanaCap = new JLabel("カナ");
			lblKanaCap.setBounds(20, 60, 100, 20);
			add(lblKanaCap);

			lblKana = new JLabel("：　" + txtKana.getText());
			lblKana.setBounds(120, 60, 360, 20);
			add(lblKana);

			lblTelCap = new JLabel("電話番号");
			lblTelCap.setBounds(20, 80, 100, 20);
			add(lblTelCap);

			lblTel = new JLabel("：　" + txtTel.getText());
			lblTel.setBounds(120, 80, 360, 20);
			add(lblTel);

			lblAddressCap = new JLabel("住所");
			lblAddressCap.setBounds(20, 100, 100, 20);
			add(lblAddressCap);

			lblAddress = new JLabel("：　" + txtAddress.getText());
			lblAddress.setBounds(120, 100, 360, 20);
			add(lblAddress);

			lblMessage = new JLabel("※この内容でよろしければ、[確定]ボタンを押してください。");
			lblMessage.setBounds(20, 130, 460, 20);
			add(lblMessage);

			btnDecide = new JButton("確定");
			btnDecide.setBounds(20, 160, 90, 30);
			btnDecide.addActionListener(this);
			add(btnDecide);

			btnReturn = new JButton("戻る");
			btnReturn.setBounds(120, 160, 90, 30);
			btnReturn.addActionListener(this);
			add(btnReturn);
		}

		public void addNotify() {

			super.addNotify();

			Insets insets = getInsets();
			setSize(500 + insets.left + insets.right, 210 + insets.top + insets.bottom);
			setLocationRelativeTo(this);
		}

		public void actionPerformed(ActionEvent e) {

			setVisible(false);

			if (e.getSource() == btnDecide) {

				try {

					String custId = txtId.getText();
					int intCustId = Integer.parseInt(custId);
					String custName = txtName.getText();
					String kana = txtKana.getText();
					String tel = txtTel.getText();
					String address = txtAddress.getText();

					Customer customer = new Customer(intCustId, custName, kana, tel, address);

					int count = NewBeeController.customerModify(customer);

					if (count != 0) {

						JOptionPane.showMessageDialog(this, "顧客情報を更新しました。", "【顧客情報変更完了】", JOptionPane.INFORMATION_MESSAGE);

					} else {

						throw new Exception("顧客情報更新処理に失敗しました！");
					}

				} catch (Exception ex) {

					ControlUtility.systemErrorMessage(RoomDetailedFrame.this, ex);
				}
			}
		}
	}
}
