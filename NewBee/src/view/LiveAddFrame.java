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
	private JLabel lblId;
	private JTextField txtId;
	String path = null;
	private JLabel lblName;
	private JTextField txtName;
	private JLabel lblTime;
	private JLabel lblPrice;
	private JTextField txtPrice;

	public LiveAddFrame() {

		setTitle("【ライブ観光データ追加】 NEWBEE TRAVEL 業務システム");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);

		lblId = new JLabel("観光地ID");
		lblId.setBounds(60, 20, 180, 20);
		add(lblId);

		txtId = new JTextField();
		txtId.setBounds(200, 20, 320, 20);
		add(txtId);

		lblName = new JLabel("配信者");
		lblName.setBounds(60, 50, 180, 20);
		add(lblName);


		txtName = new JTextField();
		txtName.setBounds(200, 50, 320, 20);
		add(txtName);

		lblTime = new JLabel("開始日時");
		lblTime.setBounds(60, 80, 180, 20);
		add(lblTime);

		txtTime = new JTextField();
		txtTime.setBounds(200, 80, 320, 20);
		add(txtTime);


		lblComment = new JLabel("概要");
		lblComment.setBounds(60, 110, 180, 20);
		add(lblComment);

		txtComment = new JTextArea();
		txtComment.setLineWrap(true);
		txtComment.setWrapStyleWord(true);

		JScrollPane jsp = new JScrollPane(txtComment);
		jsp.setBounds(200, 110, 320, 80);
		jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		add(jsp);

		lblPrice = new JLabel("代金");
		lblPrice.setBounds(60, 210, 180, 20);
		add(lblPrice);

		txtPrice = new JTextField();
		txtPrice.setBounds(200, 210, 320, 20);
		add(txtPrice);

		btnDelete = new JButton("入力消去");
		btnDelete.setBounds(390, 240, 90, 30);
		btnDelete.addActionListener(this);
		add(btnDelete);

		btnAdd = new JButton("新規追加");
		btnAdd.setBounds(490, 240, 90, 30);
		btnAdd.addActionListener(this);
		add(btnAdd);

		btnReturn = new JButton("戻る");
		btnReturn.setBounds(30, 550, 90, 30);
		btnReturn.addActionListener(this);
		add(btnReturn);

		lblImg = new JLabel("写真");
		lblImg.setBounds(60, 240, 180, 20);
		add(lblImg);

		btnImg = new JButton("写真選択");
		btnImg.setBounds(200, 240, 90, 30);
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


		if (e.getSource() == btnDelete) {

			txtId.setText("");
			txtName.setText("");
			txtComment.setText("");
			txtTime.setText("");
			txtPrice.setText("");

		} else if (e.getSource() == btnImg) {

			path = open();
			String[] str = path.split(":");
			path = str[1];

		} else if (e.getSource() == btnAdd) {

			String id = txtId.getText();
			String name = txtName.getText();
			String comment = txtComment.getText();
			String time = txtTime.getText();
			String price = txtPrice.getText();

			// 入力値の半角スペースと全角スペースを取り除く
			id.replaceAll(" +", "");
			name.replaceAll(" +", "");
			comment.replaceAll(" +", "");
			time.replaceAll(" +", "");
			price.replaceAll(" +", "");
			String[] data = { name, comment, time, path , id , price};

			try {
				if (path != null && name != null && id != null && comment != null && time != null && price != null) {
					String result = NewBeeController.liveAdd(data);

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

				NewBeeController.liveDisplay();

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
