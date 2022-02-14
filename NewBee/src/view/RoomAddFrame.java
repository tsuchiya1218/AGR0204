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
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.ControlUtility;
import control.NewBeeController;


public class RoomAddFrame extends JFrame implements ActionListener {

	private JTextField txtAddress;

	private JTextField txtAccess;

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
	private JLabel lblRoomNum;
	private JTextField txtName;
	private JTextField txtRoomNum;
	private JLabel lblType;
	private JTextField txtType;

	public RoomAddFrame() {

		setTitle("【部屋数追加】 NEWBEE TRAVEL 業務システム");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);

		lblName = new JLabel("ホテル名");
		lblName.setBounds(60, 20, 180, 20);
		add(lblName);


		txtName = new JTextField();
		txtName.setBounds(200, 20, 320, 20);
		add(txtName);

		lblRoomNum = new JLabel("確保部屋数");
		lblRoomNum.setBounds(60, 50, 180, 20);
		add(lblRoomNum);

		txtRoomNum = new JTextField();
		txtRoomNum.setBounds(200, 50, 320, 20);
		add(txtRoomNum);

		lblType = new JLabel("部屋タイプ");
		lblType.setBounds(60, 80, 180, 20);
		add(lblType);

		txtType = new JTextField();
		txtType.setBounds(200, 80, 320, 20);
		add(txtType);

		lblComment = new JLabel("概要");
		lblComment.setBounds(60, 110, 180, 20);
		add(lblComment);

		txtComment = new JTextArea();
		txtComment.setBounds(200, 110, 320, 80);
		add(txtComment);

		btnDelete = new JButton("入力消去");
		btnDelete.setBounds(390, 200, 90, 30);
		btnDelete.addActionListener(this);
		add(btnDelete);

		btnAdd = new JButton("新規追加");
		btnAdd.setBounds(490, 200, 90, 30);
		btnAdd.addActionListener(this);
		add(btnAdd);

		btnReturn = new JButton("戻る");
		btnReturn.setBounds(30, 550, 90, 30);
		btnReturn.addActionListener(this);
		add(btnReturn);

		lblImg = new JLabel("写真");
		lblImg.setBounds(60, 200, 180, 20);
		add(lblImg);

		btnImg = new JButton("写真選択");
		btnImg.setBounds(200, 200, 90, 30);
		btnImg.addActionListener(this);
		add(btnImg);

		lblImg2 = new JLabel();
		lblImg2.setBounds(60, 240, 480, 250);
		add(lblImg2);

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
			txtRoomNum.setText("");
			txtComment.setText("");
			txtType.setText("");

		} else if (e.getSource() == btnImg) {

			path = open();

		} else if (e.getSource() == btnAdd) {

			String name = txtName.getText();
			String num = txtRoomNum.getText();
			String comment = txtComment.getText();
			String type = txtType.getText();

			// 入力値の半角スペースと全角スペースを取り除く
			name.replaceAll(" +", "");
			num.replaceAll(" +", "");
			comment.replaceAll(" +", "");
			type.replaceAll(" +", "");
			String[] data = { name, num, comment, type, path };

			try {
				if (path != null && name != null && num != null && comment != null && type != null) {
					String result = NewBeeController.roomAdd(data);

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

				NewBeeController.roomDisplay();

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
