/**
 * クラス名：	MainMenuFrame
 * 概要　　：	「メインメニュー」画面
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

import control.NewBeeController;

public class MainMenuFrame extends JFrame implements ActionListener {

	public MainMenuFrame() {

		setTitle("NEWBEE TRAVEL 業務システム");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);
		Insets insets = new Insets(10, 80, 10, 0);

		JButton customerControl = new JButton("O_UW01会員情報管理");
		customerControl.setBounds(100, 20, 300, 30);
		customerControl.setActionCommand("customer");
		customerControl.setHorizontalAlignment(SwingConstants.LEFT);
		customerControl.setMargin(insets);
		customerControl.addActionListener(this);
		add(customerControl);

		JButton SpotControl = new JButton("O_UW02観光地データ追加");
		SpotControl.setBounds(100, 60, 300, 30);
		SpotControl.setActionCommand("tourist");
		SpotControl.setHorizontalAlignment(SwingConstants.LEFT);
		SpotControl.setMargin(insets);
		SpotControl.addActionListener(this);
		add(SpotControl);

		JButton liveControl = new JButton("O_UW03ライブ観光データ追加");
		liveControl.setBounds(100, 100, 300, 30);
		liveControl.setActionCommand("live");
		liveControl.setHorizontalAlignment(SwingConstants.LEFT);
		liveControl.setMargin(insets);
		liveControl.addActionListener(this);
		add(liveControl);

		JButton hotelControl = new JButton("O_UW04宿泊地データ追加");
		hotelControl.setBounds(100, 140, 300, 30);
		hotelControl.setActionCommand("hotel");
		hotelControl.setHorizontalAlignment(SwingConstants.LEFT);
		hotelControl.setMargin(insets);
		hotelControl.addActionListener(this);
		add(hotelControl);

		JButton vehicleControl = new JButton("O_UW05移動手段データ追加");
		vehicleControl.setBounds(100, 180, 300, 30);
		vehicleControl.setActionCommand("vehicle");
		vehicleControl.setHorizontalAlignment(SwingConstants.LEFT);
		vehicleControl.setMargin(insets);
		vehicleControl.addActionListener(this);
		add(vehicleControl);

		JButton orderControl = new JButton("O_UW06注文情報変更");
		orderControl.setBounds(100, 220, 300, 30);
		orderControl.setActionCommand("order");
		orderControl.setHorizontalAlignment(SwingConstants.LEFT);
		orderControl.setMargin(insets);
		orderControl.addActionListener(this);
		add(orderControl);

		JButton booKingControl = new JButton("O_UW07予約状況確認");
		booKingControl.setBounds(100, 260, 300, 30);
		booKingControl.setActionCommand("booking");
		booKingControl.setMargin(insets);
		booKingControl.setHorizontalAlignment(SwingConstants.LEFT);
		booKingControl.addActionListener(this);
		add(booKingControl);

		JButton reviewsControl = new JButton("O_UW08レビュー・評価確認");
		reviewsControl.setBounds(100, 300, 300, 30);
		reviewsControl.setMargin(insets);
		reviewsControl.setHorizontalAlignment(SwingConstants.LEFT);
		reviewsControl.setActionCommand("reviews");
		reviewsControl.addActionListener(this);
		add(reviewsControl);

		JButton emptyControl = new JButton("O_UW09空き状況確認");
		emptyControl.setBounds(100, 340, 300, 30);
		emptyControl.setMargin(insets);
		emptyControl.setHorizontalAlignment(SwingConstants.LEFT);
		emptyControl.setActionCommand("empty");
		emptyControl.addActionListener(this);
		add(emptyControl);

		setVisible(true);
	}

	public void addNotify() {

		super.addNotify();
		setSize(500, 450);
		setLocationRelativeTo(this);
	}

	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if (cmd.equals("customer")) {
			NewBeeController.customerSearchDisplay();

		} else if (cmd.equals("spot")) {
			NewBeeController.spotDisplay();

		} else if (cmd.equals("live")) {
			NewBeeController.liveDisplay();

		} else if (cmd.equals("hotel")) {
			NewBeeController.hotelDisplay();

		} else if (cmd.equals("vehicle")) {
			NewBeeController.vehicleDisplay();

		} else if (cmd.equals("order")) {
			NewBeeController.orderChangeDisplay();

		} else if (cmd.equals("booking")) {
			NewBeeController.bookingCheckDisplay();

		} else if (cmd.equals("reviews")) {
			NewBeeController.reviewsCheckDisplay();

		} else if (cmd.equals("empty")) {
			NewBeeController.emptyCheckDisplay();

		}

		setVisible(false);

	}
}