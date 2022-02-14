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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingConstants;

import model.ControlUtility;
import control.NewBeeController;

@SuppressWarnings("serial")
public class VehicleFrame extends JFrame implements ActionListener {

	private JButton btnAdd;
	private JButton btnUpdate;
	private JButton btnReturn;

	public VehicleFrame() {

		setTitle("【移動手段データ】 NEWBEE TRAVEL 業務システム");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);

		Insets insets = new Insets(10, 80, 10, 0);

		btnAdd = new JButton("移動手段データ追加");
		btnAdd.setBounds(100, 60, 300, 30);
		btnAdd.setHorizontalAlignment(SwingConstants.LEFT);
		btnAdd.setMargin(insets);
		btnAdd.addActionListener(this);
		add(btnAdd);

		btnUpdate = new JButton("移動手段データ更新");
		btnUpdate.setBounds(100, 120, 300, 30);
		btnUpdate.setHorizontalAlignment(SwingConstants.LEFT);
		btnUpdate.setMargin(insets);
		btnUpdate.addActionListener(this);
		add(btnUpdate);

		btnReturn = new JButton("戻る");
		btnReturn.setBounds(20, 450, 90, 30);
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
		if (e.getSource() == btnAdd) {
			NewBeeController.vehicleAddDisplay();
		} else if (e.getSource() == btnUpdate) {
			NewBeeController.vehicleUpdateDisplay();
		} else if (e.getSource() == btnReturn) {

			try {

				NewBeeController.mainMenuDisplay();

			} catch (Exception ex) {

				ControlUtility.systemErrorMessage(this, ex);
			}
		}
		setVisible(false);

	}

}
