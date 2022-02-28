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
import model.Customer;
import model.Item;
import model.Live;
import model.ControlUtility;
import model.Spot;

@SuppressWarnings("serial")
public class LiveDetailFrame extends JFrame implements ActionListener {

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
	private JLabel lblId;
	private JTextField txtId;
	private JLabel lblTime;
	private JTextField txtPrice;
	private JLabel lblPrice;

	public LiveDetailFrame(Live live) {

		setTitle("【観光地詳細】 NEWBEE TRAVEL 業務システム");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);

		lblId = new JLabel("ライブID");
		lblId.setBounds(60, 20, 180, 20);
		add(lblId);

		txtId = new JTextField(live.getLiveId());
		txtId.setBounds(200, 20, 320, 20);
		add(txtId);
		txtId.setEnabled(false);

		lblName = new JLabel("エリア");
		lblName.setBounds(60, 50, 180, 20);
		add(lblName);

		txtName = new JTextField(live.getArea());
		txtName.setBounds(200, 50, 320, 20);
		add(txtName);

		lblName = new JLabel("ライブ観光コース");
		lblName.setBounds(60, 80, 180, 20);
		add(lblName);

		txtName = new JTextField(live.getlName());
		txtName.setBounds(200, 80, 320, 20);
		add(txtName);

		lblName = new JLabel("配信者");
		lblName.setBounds(60, 110, 180, 20);
		add(lblName);

		txtName = new JTextField(live.getName());
		txtName.setBounds(200, 110, 320, 20);
		add(txtName);

		lblTime = new JLabel("開始日時");
		lblTime.setBounds(60, 140, 180, 20);
		add(lblTime);

		txtTime = new JTextField(live.getTime());
		txtTime.setBounds(200, 140, 320, 20);
		add(txtTime);

		lblComment = new JLabel("概要");
		lblComment.setBounds(60, 170, 180, 20);
		add(lblComment);

		txtComment = new JTextArea(live.getComment());
		txtComment.setLineWrap(true);
		txtComment.setWrapStyleWord(true);

		JScrollPane jsp = new JScrollPane(txtComment);
		jsp.setBounds(200, 170, 320, 90);
		jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		add(jsp);

		lblPrice = new JLabel("代金");
		lblPrice.setBounds(60, 280, 180, 20);
		add(lblPrice);

		txtPrice = new JTextField(live.getPrice());
		txtPrice.setBounds(200, 280, 320, 20);
		add(txtPrice);

		btnDelete = new JButton("入力消去");
		btnDelete.setBounds(390, 320, 90, 30);
		btnDelete.addActionListener(this);
		add(btnDelete);

		btnAdd = new JButton("更新");
		btnAdd.setBounds(490, 320, 90, 30);
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
			String name = txtName.getText();
			String time = txtTime.getText();
			String comment = txtComment.getText();
			String price = txtPrice.getText();
			// 入力値の半角スペースと全角スペースを取り除く
			id.replaceAll(" +", "");
			name.replaceAll(" +", "");
			time.replaceAll(" +", "");
			comment.replaceAll(" +", "");
			price.replaceAll(" +", "");

			String[] data = {id,name,time,comment};
			try {
				//変更する。
				String result = NewBeeController.liveUpdate(data);
				JOptionPane.showMessageDialog(this, result, "【確認】", JOptionPane.INFORMATION_MESSAGE);

		}catch (Exception ex) {

			ControlUtility.systemErrorMessage(this, ex);
		}
		}else if (e.getSource() == btnDelete) {
			txtName.setText("");
			txtTime.setText("");
			txtComment.setText("");
		} else if (e.getSource() == btnReturn) {

			setVisible(false);

			try {

				NewBeeController.liveUpdateDisplay();

			} catch (Exception ex) {

				ControlUtility.systemErrorMessage(this, ex);
			}
		}
	}
}
