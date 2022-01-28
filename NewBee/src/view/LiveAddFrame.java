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

import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.OrderControlUtility;
import control.NewBeeController;


public class LiveAddFrame extends JFrame implements ActionListener {

	private JLabel lblAddress;
	private JTextField txtAddress;

	private JLabel lblAccess;
	private JTextField txtAccess;

	private JLabel lblComment;
	private JTextArea txtComment;

	private JLabel lbltime;
	private JTextField txtTime;

	private JButton btnReturn;
	private JButton btnDelete;
	private JButton btnAdd;
	private JLabel lblImg;
	private JButton btnImg;

	private JLabel lblImg2;
	private JLabel lbltimeText;

	public LiveAddFrame() {

		setTitle("【ライブ観光データ追加】 NEWBEE TRAVEL 業務システム");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);

		lblAddress = new JLabel("配信者");
		lblAddress.setBounds(60, 20, 180, 20);
		add(lblAddress);


		txtAddress = new JTextField();
		txtAddress.setBounds(200, 20, 320, 20);
		add(txtAddress);

		lblAccess = new JLabel("開始日時");
		lblAccess.setBounds(60, 60, 180, 20);
		add(lblAccess);

		txtAccess = new JTextField();
		txtAccess.setBounds(200, 60, 320, 20);
		add(txtAccess);


		lblComment = new JLabel("紹介");
		lblComment.setBounds(60, 100, 180, 20);
		add(lblComment);

		txtComment = new JTextArea();
		txtComment.setBounds(200, 100, 320, 80);
		add(txtComment);

		btnDelete = new JButton("入力消去");
		btnDelete.setBounds(390, 210, 90, 30);
		btnDelete.addActionListener(this);
		add(btnDelete);

		btnAdd = new JButton("新規追加");
		btnAdd.setBounds(490, 210, 90, 30);
		btnAdd.addActionListener(this);
		add(btnAdd);

		btnReturn = new JButton("戻る");
		btnReturn.setBounds(30, 550, 90, 30);
		btnReturn.addActionListener(this);
		add(btnReturn);

		lblImg = new JLabel("写真");
		lblImg.setBounds(60, 210, 180, 20);
		add(lblImg);

		btnImg = new JButton("写真選択");
		btnImg.setBounds(200, 210, 90, 30);
		btnImg.addActionListener(this);
		add(btnImg);

		lblImg2 = new JLabel();
		lblImg2.setBounds(60, 290, 480, 250);
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

			txtAddress.setText("");
			txtAccess.setText("");
			txtComment.setText("");
			txtTime.setText("");

		} else if (e.getSource() == btnImg) {

			path = open();

		} else if (e.getSource() == btnAdd) {

			String address = txtAddress.getText();
			String access = txtAccess.getText();
			String comment = txtComment.getText();
			String time = txtTime.getText();

			// 入力値の半角スペースと全角スペースを取り除く
			address.replaceAll(" +", "");
			access.replaceAll(" +", "");
			comment.replaceAll(" +", "");
			time.replaceAll(" +", "");
			String[] data = { address, access, comment, time, path };

			try {
				if (path != null && address != null && access != null && comment != null && time != null) {
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

				NewBeeController.liveDisplay();

			} catch (Exception ex) {

				OrderControlUtility.systemErrorMessage(this, ex);
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
