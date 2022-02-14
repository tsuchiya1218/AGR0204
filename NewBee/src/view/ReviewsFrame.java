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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import control.NewBeeController;
import model.ControlUtility;
import model.Reviews;

@SuppressWarnings("serial")
public class ReviewsFrame extends JFrame implements ActionListener {

	private JLabel lblReviews;


	private JLabel lblName;
	private JTextField txtName;
	private JTextField txtItemCode;

	private JButton btnReturn;
	private JTextField txtNum;
	private JLabel lblUserId;
	private JTextField txtUserId;
	private JLabel lblItemCode;
	private JLabel lblCourse;
	private JTextArea txtCourse;
	private JTextArea txtReviews;
	private JLabel lblDate;
	private JButton btnReviewsOk;
	private JButton btnReviewsCancel;

	public ReviewsFrame(Reviews reviewsCheck) {

		setTitle("【レビュー•評価詳細】 NewBee 業務システム");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);

		lblUserId = new JLabel("ユーザーID");
		lblUserId.setBounds(20, 20, 100, 20);
		add(lblUserId);

		txtUserId = new JTextField(reviewsCheck.getUserId());
		txtUserId.setBounds(120, 20, 360, 20);
		txtUserId.setEditable(false);
		add(txtUserId);

		lblName = new JLabel("ユーザ名");
		lblName.setBounds(20, 50, 100, 20);
		add(lblName);

		txtName = new JTextField(reviewsCheck.getCustName());
		txtName.setBounds(120, 50, 360, 20);
		txtName.setEditable(false);
		add(txtName);

		lblItemCode = new JLabel("項目コード");
		lblItemCode.setBounds(20, 80, 100, 20);
		add(lblItemCode);

		txtItemCode = new JTextField(reviewsCheck.getItemCode());
		txtItemCode.setBounds(120, 80, 360, 20);
		txtItemCode.setEditable(false);
		add(txtItemCode);

		lblCourse = new JLabel("旅行コース");
		lblCourse.setBounds(20, 110, 100, 20);
		add(lblCourse);

		txtCourse = new JTextArea(reviewsCheck.getCourse());
		txtCourse.setBounds(120, 110, 360, 40);
		txtCourse.setLineWrap(true);
		txtCourse.setEditable(false);
		txtCourse.setWrapStyleWord(true);
		add(txtCourse);

		lblReviews = new JLabel("レビュー•評価");
		lblReviews.setBounds(20, 160, 100, 20);
		add(lblReviews);

		txtReviews = new JTextArea(reviewsCheck.getComment());
		txtReviews.setBounds(120, 160, 360, 80);
		txtReviews.setLineWrap(true);
		txtReviews.setEditable(false);
		txtReviews.setWrapStyleWord(true);
		add(txtReviews);
		
		lblDate = new JLabel("日付");
		lblDate.setBounds(20, 250, 100, 20);
		add(lblDate);

		txtNum = new JTextField(reviewsCheck.getTime());
		txtNum.setBounds(120, 250, 360, 20);
		txtNum.setEditable(false);
		add(txtNum);

		btnReviewsOk = new JButton("許可");
		btnReviewsOk.setBounds(20, 450, 90, 30);
		btnReviewsOk.addActionListener(this);
		add(btnReviewsOk);

		btnReviewsCancel = new JButton("取消");
		btnReviewsCancel.setBounds(120, 450, 90, 30);
		btnReviewsCancel.addActionListener(this);
		add(btnReviewsCancel);
		
		btnReturn = new JButton("戻る");
		btnReturn.setBounds(220, 450, 90, 30);
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

	@SuppressWarnings("unused")
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btnReviewsCancel) {

			try {
				//userIdとitemCodeを渡して、レビューをキャンセルする。
				String[] data = {txtUserId.getText(),txtItemCode.getText()};
				String result = NewBeeController.reviewsCancel(data);
				JOptionPane.showMessageDialog(this, result, "【確認】", JOptionPane.INFORMATION_MESSAGE);
			} catch (Exception ex) {

				ControlUtility.systemErrorMessage(this, ex);
			}
		} else if (e.getSource() == btnReviewsOk) {

			try {
			//userIdとitemCodeを渡して、レビューを許可する。
				String[] data = {txtUserId.getText(),txtItemCode.getText()};
				String result = NewBeeController.reviewsOk(data);
				JOptionPane.showMessageDialog(this, result, "【確認】", JOptionPane.INFORMATION_MESSAGE);
			} catch (Exception ex) {

				ControlUtility.systemErrorMessage(this, ex);
			}

		} else if (e.getSource() == btnReturn) {

			setVisible(false);

			try {

				NewBeeController.bookingCheckDisplay();

			} catch (Exception ex) {

				ControlUtility.systemErrorMessage(this, ex);
			}
		}
	}
}
