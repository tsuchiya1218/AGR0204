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
import java.util.Arrays;
import java.util.List;

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
import model.ControlUtility;
import model.Spot;

@SuppressWarnings("serial")
public class SpotDetailFrame extends JFrame implements ActionListener {

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
	private Object btnUpdate;
	private JLabel lblId;
	private JTextField txtId;
	private int defaultImg;
	private String addPath;

	public SpotDetailFrame(Spot spot) {


		setTitle("【観光地詳細】 NEWBEE TRAVEL 業務システム");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);

		lblId= new JLabel("観光地ID");
		lblId.setBounds(60, 20, 180, 20);
		add(lblId);

		txtId = new JTextField(String.valueOf(spot.getSpotId()));
		txtId.setBounds(200, 20, 320, 20);
		add(txtId);
		txtId.setEnabled(false);

		lblName= new JLabel("観光地名");
		lblName.setBounds(60, 60, 180, 20);
		add(lblName);

		txtName = new JTextField(spot.getsName());
		txtName.setBounds(200, 60, 320, 20);
		add(txtName);

		lblAddress = new JLabel("住所");
		lblAddress.setBounds(60, 100, 180, 20);
		add(lblAddress);

		txtAddress = new JTextField(spot.getAddress());
		txtAddress.setBounds(200, 100, 320, 20);
		add(txtAddress);

		lblAccess = new JLabel("アクセス");
		lblAccess.setBounds(60, 140, 180, 20);
		add(lblAccess);

		txtAccess = new JTextField(spot.getAccess());
		txtAccess.setBounds(200, 140, 320, 20);
		add(txtAccess);

		lbltime = new JLabel("営業時間");
		lbltime.setBounds(60, 180, 180, 20);
		add(lbltime);


		txtTime = new JTextField(spot.getTime());
		txtTime.setBounds(200, 180, 320, 20);
		add(txtTime);

		lblComment = new JLabel("概要");
		lblComment.setBounds(60, 220, 180, 20);
		add(lblComment);

		txtComment = new JTextArea(spot.getComment());
		txtComment.setLineWrap(true);
		txtComment.setWrapStyleWord(true);
		JScrollPane jsp = new JScrollPane(txtComment);
		jsp.setBounds(200, 220, 320, 90);
		jsp.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		add(jsp);

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

		lblImg = new JLabel("写真");
		lblImg.setBounds(60, 320, 180, 20);
		add(lblImg);

		ImageIcon icon = new ImageIcon("Y:\\卒業制作A\\Gr4\\卒業製作"+spot.getImgPath());
		System.out.println(icon);
		lblImg2 = new JLabel(icon);
		lblImg2.setBounds(60, 360, 480, 200);
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

		if (e.getSource() == btnAdd) {
			String name = txtName.getText();
			String address = txtAddress.getText();
			String access = txtAccess.getText();
			String time = txtTime.getText();
			String comment = txtComment.getText();
			String spotId = txtId.getText();

			// spotIdをdbに渡すメソッド

			String[] data = {name,address,access,time,comment,spotId};
			NewBeeController.spotUpdate(data);
			JOptionPane.showMessageDialog(this, "処理完了しました。", "【確認】", JOptionPane.INFORMATION_MESSAGE);

		}else if(e.getSource() == btnDelete) {
			txtName.setText("");
			txtAddress.setText("");
			txtAccess.setText("");
			txtTime.setText("");
			txtComment.setText("");

		}else if (e.getSource() == btnReturn) {

			setVisible(false);

			try {

				NewBeeController.spotUpdateDisplay();

			} catch (Exception ex) {

				ControlUtility.systemErrorMessage(this, ex);
			}
		}
		}
	}
