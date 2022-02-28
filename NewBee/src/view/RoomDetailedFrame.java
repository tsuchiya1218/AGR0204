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
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import control.NewBeeController;
import model.ControlUtility;
import model.Customer;
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
	private JLabel lblSum;
	private JTextField txtSum;
	private JLabel lblTpye;
	private JTextField txtTpye;
	private JLabel lblType;
	private JTextField txtType;
	private JLabel lblRId;
	private JTextField txtRId;

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

		lblRId = new JLabel("部屋ID");
		lblRId.setBounds(20, 80, 100, 20);
		add(lblRId);

		txtRId = new JTextField(roomDetailed.getRoomId());
		txtRId.setBounds(120, 80, 360, 20);
		txtRId.setEditable(false);
		add(txtRId);

		lblSum = new JLabel("確保部屋数");
		lblSum.setBounds(20, 110, 100, 20);
		add(lblSum);

		txtSum = new JTextField(roomDetailed.getRoomNum());
		txtSum.setBounds(120, 110, 360, 20);
		add(txtSum);

		lblType = new JLabel("部屋タイプ");
		lblType.setBounds(20, 140, 100, 20);
		add(lblType);

		txtType = new JTextField(roomDetailed.getRoomType());
		txtType.setBounds(120, 140, 360, 20);
		txtType.setEditable(false);
		add(txtType);

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
			String hid = txtId.getText();
			String rId = txtRId.getText();
			String sum = txtSum.getText();
			String comment = txtComment.getText();
			String num = txtNum.getText();

			// 入力値の半角スペースと全角スペースを取り除く
			sum.replaceAll(" +", "");
			num.replaceAll(" +", "");
			rId.replaceAll(" +", "");
			comment.replaceAll(" +", "");
			hid.replaceAll(" +", "");

			String[] data = {sum,num,comment,hid,rId};
			try {
				//hotelIdとroomIdを渡して、部屋情報を変更する。
				String result = NewBeeController.roomUpdate(data);
				JOptionPane.showMessageDialog(this, result, "【確認】", JOptionPane.INFORMATION_MESSAGE);
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
}
