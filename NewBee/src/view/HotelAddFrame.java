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
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.ControlUtility;
import control.NewBeeController;

public class HotelAddFrame extends JFrame implements ActionListener {

	private JLabel lblAddress;
	private JTextField txtAddress;

	private JLabel lblAccess;
	private JTextField txtAccess;

	private JTextArea txtComment;


	private JButton btnReturn;
	private JButton btnDelete;
	private JButton btnAdd;
	private JLabel lblImg;
	private JButton btnImg;

	private JLabel lblImg2;
	private JLabel lblComment;
	private JLabel lblName;
	private JTextField txtName;
	private JLabel lblCheckIn;
	private JTextField txtCheckIn;
	private JLabel lblCheckOut;
	private JTextField txtCheckOut;

	String path = null;
	private JLabel lblSpotId;
	private JTextField txtSpotId;

	public HotelAddFrame() {

		setTitle("【ホテルデータ追加】 NEWBEE TRAVEL 業務システム");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);

		lblName = new JLabel("ホテル名");
		lblName.setBounds(60, 20, 180, 20);
		add(lblName);

		txtName = new JTextField();
		txtName.setBounds(200, 20, 320, 20);
		add(txtName);

		lblAddress = new JLabel("所在地");
		lblAddress.setBounds(60, 60, 180, 20);
		add(lblAddress);

		txtAddress = new JTextField();
		txtAddress.setBounds(200, 60, 320, 20);
		add(txtAddress);

		lblAccess = new JLabel("アクセス");
		lblAccess.setBounds(60, 100, 180, 20);
		add(lblAccess);

		txtAccess = new JTextField();
		txtAccess.setBounds(200, 100, 320, 20);
		add(txtAccess);

		lblCheckIn = new JLabel("チェックイン");
		lblCheckIn.setBounds(60, 140, 180, 20);
		add(lblCheckIn);

		txtCheckIn = new JTextField();
		txtCheckIn.setBounds(200, 140, 320, 20);
		add(txtCheckIn);

		lblCheckOut = new JLabel("チェックアウト");
		lblCheckOut.setBounds(60, 180, 180, 20);
		add(lblCheckOut);


		txtCheckOut = new JTextField();
		txtCheckOut.setBounds(200, 180, 320, 20);
		add(txtCheckOut);

		lblComment = new JLabel("概要");
		lblComment.setBounds(60, 220, 180, 20);
		add(lblComment);

		txtComment = new JTextArea();
		txtComment.setLineWrap(true);
		txtComment.setWrapStyleWord(true);

		JScrollPane jsp = new JScrollPane(txtComment);
		jsp.setBounds(200, 220, 320, 80);
		jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		add(jsp);

		lblSpotId= new JLabel("エリアID");
		lblSpotId.setBounds(60, 320, 180, 20);
		add(lblSpotId);

		txtSpotId = new JTextField();
		txtSpotId.setBounds(200,320,320,20);
		add(txtSpotId);

		btnDelete = new JButton("入力消去");
		btnDelete.setBounds(390, 360, 90, 30);
		btnDelete.addActionListener(this);
		add(btnDelete);

		btnAdd = new JButton("新規追加");
		btnAdd.setBounds(490, 360, 90, 30);
		btnAdd.addActionListener(this);
		add(btnAdd);

		btnReturn = new JButton("戻る");
		btnReturn.setBounds(30, 700, 90, 30);
		btnReturn.addActionListener(this);
		add(btnReturn);

		lblImg = new JLabel("写真");
		lblImg.setBounds(60, 360, 320, 100);
		add(lblImg);

		btnImg = new JButton("写真選択");
		btnImg.setBounds(200, 360, 90, 30);
		btnImg.addActionListener(this);
		add(btnImg);

		lblImg2 = new JLabel();
		lblImg2.setBounds(60,450, 300, 250);
		add(lblImg2);

		setVisible(true);
	}

	public void addNotify() {

		super.addNotify();

		Insets insets = getInsets();
		setSize(600 + insets.left + insets.right, 750 + insets.top + insets.bottom);
		setLocationRelativeTo(this);
	}

	public void actionPerformed(ActionEvent e) {


		if (e.getSource() == btnDelete) {

			txtName.setText("");
			txtAddress.setText("");
			txtAccess.setText("");
			txtCheckIn.setText("");
			txtCheckOut.setText("");
			txtComment.setText("");

		} else if (e.getSource() == btnImg) {

			path = open();
			String[] str = path.split(":");
			path = str[1];

		} else if (e.getSource() == btnAdd) {

			String name = txtName.getText();
			String address = txtAddress.getText();
			String access = txtAccess.getText();
			String checkIn = txtCheckIn.getText();
			String checkOut = txtCheckOut.getText();
			String comment = txtComment.getText();
			String spotId = txtSpotId.getText();

			// 入力値の半角スペースと全角スペースを取り除く
			name.replaceAll(" +", "");
			address.replaceAll(" +", "");
			access.replaceAll(" +", "");
			checkIn.replaceAll(" +", "");
			checkOut.replaceAll(" +", "");
			comment.replaceAll(" +", "");
			spotId.replaceAll(" +", "");
			String[] data = { name, address ,comment , access, checkIn,checkOut, path ,spotId};

			try {
				if (name != null && address != null && access != null && checkIn != null && checkOut != null && comment != null && path != null) {
					String result = NewBeeController.hotelAdd(data);

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

				NewBeeController.hotelDisplay();

			} catch (Exception ex) {

				ControlUtility.systemErrorMessage(this, ex);
			}
		}
	}

	private String open() {
		File f = null;
		JFileChooser fc = new JFileChooser();
		// 画像ファイルの拡張子を設定
		fc.setFileFilter(new FileNameExtensionFilter("画像ファイル", "png", "jpg", "Jpeg", "GIF", "bmp"));
		// ファイル選択ダイアログを表示、戻り値がAPPROVE_OPTIONの場合、画像ファイルを開く
		if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			f = fc.getSelectedFile();
			// アイコンをラベルに設定
			ImageIcon icon = new ImageIcon(f.getPath());
			lblImg2.setIcon(icon);
		}
		String path = String.valueOf(f);
		return path;

	}
}
