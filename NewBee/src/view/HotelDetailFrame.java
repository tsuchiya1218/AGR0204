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
import java.io.File;
import java.util.ArrayList;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import control.NewBeeController;
import dao.Hotel;
import model.Customer;
import model.Item;
import model.Live;
import model.ControlUtility;
import model.Spot;

@SuppressWarnings("serial")
public class HotelDetailFrame extends JFrame implements ActionListener {
	private JLabel lblComment;
	private JTextArea txtComment;

	private JTextField txtTime;

	private JButton btnReturn;
	private JButton btnDelete;
	private JButton btnAdd;
	private JLabel lblImg;
	private JButton btnImg;

	private JLabel lblImg2;
	private JLabel lblName;
	private JTextField txtName;
	private JLabel lblId;
	private JTextField txtId;
	private JLabel lblTime;
	private JLabel lblAddress;
	private JTextField txtAddress;
	private JLabel lblAccess;
	private JTextField txtAccess;
	private JLabel lblCheckIn;
	private JTextField txtCheckIn;
	private JLabel lblCheckOut;
	private JTextField txtCheckOut;


	public HotelDetailFrame(Hotel hotel) {


		setTitle("【観光地詳細】 NEWBEE TRAVEL 業務システム");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);

		lblId= new JLabel("ホテルID");
		lblId.setBounds(60, 20, 180, 20);
		add(lblId);

		txtId = new JTextField(String.valueOf(hotel.getHotelid()));
		txtId.setBounds(200, 20, 320, 20);
		add(txtId);
		txtId.setEnabled(false);

		lblName= new JLabel("ホテル名");
		lblName.setBounds(60, 60, 180, 20);
		lblName.setEnabled(false);
		add(lblName);

		txtName = new JTextField(hotel.getName());
		txtName.setBounds(200, 60, 320, 20);
		add(txtName);

		lblAddress = new JLabel("所在地");
		lblAddress.setBounds(60, 100, 180, 20);
		add(lblAddress);

		txtAddress = new JTextField(hotel.getAddress());
		txtAddress.setBounds(200, 100, 320, 20);
		add(txtAddress);

		lblAccess = new JLabel("アクセス");
		lblAccess.setBounds(60, 140, 180, 20);
		add(lblAccess);

		txtAccess = new JTextField(hotel.getAccess());
		txtAccess.setBounds(200, 140, 320, 20);
		add(txtAccess);

		lblCheckIn = new JLabel("チェックイン");
		lblCheckIn.setBounds(60, 180, 180, 20);
		add(lblCheckIn);

		txtCheckIn = new JTextField(hotel.getCheckin());
		txtCheckIn.setBounds(200, 180, 320, 20);
		add(txtCheckIn);

		lblCheckOut = new JLabel("チェックアウト");
		lblCheckOut.setBounds(60, 220, 180, 20);
		add(lblCheckOut);

		txtCheckOut = new JTextField(hotel.getCheckout());
		txtCheckOut.setBounds(200, 220, 320, 20);
		add(txtCheckOut);

		lblComment = new JLabel("概要");
		lblComment.setBounds(60, 260, 180, 20);
		add(lblComment);

		txtComment = new JTextArea(hotel.getComment());
		txtComment.setLineWrap(true);
		txtComment.setWrapStyleWord(true);
		JScrollPane jsp = new JScrollPane(txtComment);
		jsp.setBounds(200, 260, 320, 90);
		jsp.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		add(jsp);

		btnDelete = new JButton("入力消去");
		btnDelete.setBounds(390, 370, 90, 30);
		btnDelete.addActionListener(this);
		add(btnDelete);

		btnAdd = new JButton("更新");
		btnAdd.setBounds(490, 370, 90, 30);
		btnAdd.addActionListener(this);
		add(btnAdd);

		btnReturn = new JButton("戻る");
		btnReturn.setBounds(30, 600, 90, 30);
		btnReturn.addActionListener(this);
		add(btnReturn);

		setVisible(true);
	}






	public void addNotify() {

		super.addNotify();

		Insets insets = getInsets();
		setSize(600 + insets.left + insets.right, 650 + insets.top + insets.bottom);
		setLocationRelativeTo(this);
	}




	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btnAdd) {

			String id = txtId.getText();
			String address = txtAddress.getText();
			String access = txtAccess.getText();
			String CheckIn = txtCheckIn.getText();
			String CheckOut = txtCheckOut.getText();
			String comment = txtComment.getText();
			// 入力値の半角スペースと全角スペースを取り除く
			id.replaceAll(" +", "");
			address.replaceAll(" +", "");
			access.replaceAll(" +", "");
			CheckIn.replaceAll(" +", "");
			CheckOut.replaceAll(" +", "");
			comment.replaceAll(" +", "");

			String[] data = {id,address,comment,access,CheckIn,CheckOut};
			try {
				//変更する。
				String result = NewBeeController.hotelUpdate(data);
				JOptionPane.showMessageDialog(this, result, "【確認】", JOptionPane.INFORMATION_MESSAGE);

		}catch (Exception ex) {

			ControlUtility.systemErrorMessage(this, ex);
		}
		}else if(e.getSource() == btnDelete) {
			txtAddress.setText("");
			txtAccess.setText("");
			txtCheckIn.setText("");
			txtCheckOut.setText("");
			txtComment.setText("");
		}else if (e.getSource() == btnReturn) {

			setVisible(false);

			try {

				NewBeeController.hotelUpdateDisplay();

			} catch (Exception ex) {

				ControlUtility.systemErrorMessage(this, ex);
			}
		}
		}
	}
