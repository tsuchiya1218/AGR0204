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
import java.io.IOException;

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

@SuppressWarnings("serial")
public class SpotAddFrame extends JFrame implements ActionListener {

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
	private JLabel lblName;
	private JTextField txtName;
	private JLabel lblAreaId;
	private JTextField txtAreaId;
	String img = null;

	public SpotAddFrame() {


		setTitle("【観光地データ追加】 NEWBEE TRAVEL 業務システム");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);

		lblAreaId= new JLabel("エリアID");
		lblAreaId.setBounds(60, 20, 180, 20);
		add(lblAreaId);

		txtAreaId = new JTextField();
		txtAreaId.setBounds(200, 20, 320, 20);
		add(txtAreaId);

		lblName= new JLabel("観光地名");
		lblName.setBounds(60, 50, 180, 20);
		add(lblName);

		txtName = new JTextField();
		txtName.setBounds(200, 50, 320, 20);
		add(txtName);

		lblAddress = new JLabel("住所");
		lblAddress.setBounds(60, 80, 180, 20);
		add(lblAddress);

		txtAddress = new JTextField();
		txtAddress.setBounds(200, 80, 320, 20);
		add(txtAddress);

		lblAccess = new JLabel("アクセス	");
		lblAccess.setBounds(60, 110, 180, 20);
		add(lblAccess);

		txtAccess = new JTextField();
		txtAccess.setBounds(200, 110, 320, 20);
		add(txtAccess);

		lbltime = new JLabel("利用時間");
		lbltime.setBounds(60, 140, 180, 20);
		add(lbltime);


		txtTime = new JTextField();
		txtTime.setBounds(200, 140, 320, 20);
		add(txtTime);

		lblComment = new JLabel("概要");
		lblComment.setBounds(60, 170, 180, 20);
		add(lblComment);

		txtComment = new JTextArea();
		txtComment.setLineWrap(true);
		txtComment.setWrapStyleWord(true);

		JScrollPane jsp = new JScrollPane(txtComment);
		jsp.setBounds(200, 170, 320, 80);
		jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		add(jsp);

		btnDelete = new JButton("入力消去");
		btnDelete.setBounds(390, 260, 90, 30);
		btnDelete.addActionListener(this);
		add(btnDelete);

		btnAdd = new JButton("新規追加");
		btnAdd.setBounds(490, 260, 90, 30);
		btnAdd.addActionListener(this);
		add(btnAdd);

		btnReturn = new JButton("戻る");
		btnReturn.setBounds(30, 600, 90, 30);
		btnReturn.addActionListener(this);
		add(btnReturn);

		lblImg = new JLabel("写真");
		lblImg.setBounds(60, 260, 180, 20);
		add(lblImg);

		btnImg = new JButton("写真選択");
		btnImg.setBounds(200, 260, 90, 30);
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
		setSize(600 + insets.left + insets.right, 650 + insets.top + insets.bottom);
		setLocationRelativeTo(this);
	}

	@SuppressWarnings("unused")
	public void actionPerformed(ActionEvent e) {


		if (e.getSource() == btnDelete) {

			txtAreaId.setText("");
			txtName.setText("");
			txtAddress.setText("");
			txtAccess.setText("");
			txtTime.setText("");
			txtComment.setText("");

		} else if (e.getSource() == btnImg) {

			img = open();
			String[] str = img.split(":");
			img = str[1];

		} else if (e.getSource() == btnAdd) {

			String areaId = txtAreaId.getText();
			String name = txtName.getText();
			String address = txtAddress.getText();
			String access = txtAccess.getText();
			String time = txtTime.getText();
			String comment = txtComment.getText();

			// 入力値の半角スペースと全角スペースを取り除く
			areaId.replaceAll(" +", "");
			name.replaceAll(" +", "");
			address.replaceAll(" +", "");
			access.replaceAll(" +", "");
			comment.replaceAll(" +", "");
			time.replaceAll(" +", "");

			String[] data = {name, img , address , access , time , comment , areaId};

			try {
				if (areaId != null && name != null && address != null && access != null && comment != null && time != null && img != null) {

					String result = NewBeeController.spotAdd(data);

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

				NewBeeController.spotDisplay();

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
			ImageIcon icon;
			try {
				icon = new ImageIcon(f.getCanonicalPath());
				lblImg2.setIcon(icon);
			} catch (IOException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}

		}
		String path = String.valueOf(f);
		return path;

	}
}
