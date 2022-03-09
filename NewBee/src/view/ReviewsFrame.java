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
import javax.swing.JScrollPane;
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


	private JTextArea txtComment;

	public ReviewsFrame(Reviews reviewsCheck) {

		setTitle("【レビュー•評価詳細】 NewBee 業務システム");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);

		lblUserId = new JLabel("ユーザーID");
		lblUserId.setBounds(20, 20, 100, 20);
		add(lblUserId);

		txtUserId = new JTextField(reviewsCheck.getCustomerId());
		txtUserId.setBounds(120, 20, 360, 20);
		txtUserId.setEditable(false);
		add(txtUserId);

		lblName = new JLabel("ユーザ名");
		lblName.setBounds(20, 50, 100, 20);
		add(lblName);

		txtName = new JTextField(reviewsCheck.getCustomerName());
		txtName.setBounds(120, 50, 360, 20);
		txtName.setEditable(false);
		add(txtName);

		lblItemCode = new JLabel("注文コード");
		lblItemCode.setBounds(20, 80, 100, 20);
		add(lblItemCode);

		txtItemCode = new JTextField(reviewsCheck.getOrderId());
		txtItemCode.setBounds(120, 80, 360, 20);
		txtItemCode.setEditable(false);
		add(txtItemCode);

		lblReviews = new JLabel("レビュー•評価");
		lblReviews.setBounds(20, 110, 100, 20);
		add(lblReviews);

		txtComment = new JTextArea(reviewsCheck.getReview());
		txtComment.setLineWrap(true);
		txtComment.setWrapStyleWord(true);

		JScrollPane jsp = new JScrollPane(txtComment);
		jsp.setBounds(120, 110, 360, 120);
		jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		add(jsp);

		lblDate = new JLabel("日付");
		lblDate.setBounds(20, 250, 100, 20);
		add(lblDate);

		txtNum = new JTextField(reviewsCheck.getDate());
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
				String customerID = txtUserId.getText();
				String code = txtItemCode.getText();


				customerID.replaceAll(" +", "");
				code.replaceAll(" +", "");

				//userIdとitemCodeを渡して、レビューをキャンセルする。
				String[] data = {customerID,code};
				String result = NewBeeController.reviewsCancel(data);
				JOptionPane.showMessageDialog(this, result, "【確認】", JOptionPane.INFORMATION_MESSAGE);
			} catch (Exception ex) {

				ControlUtility.systemErrorMessage(this, ex);
			}
		} else if (e.getSource() == btnReviewsOk) {

			try {
				String customerID = txtUserId.getText();
				String code = txtItemCode.getText();


				customerID.replaceAll(" +", "");
				code.replaceAll(" +", "");

				//userIdとitemCodeを渡して、レビューをキャンセルする。
				String[] data = {customerID,code};

				String result = NewBeeController.reviewsOk(data);
				JOptionPane.showMessageDialog(this, result, "【確認】", JOptionPane.INFORMATION_MESSAGE);
			} catch (Exception ex) {

				ControlUtility.systemErrorMessage(this, ex);
			}

		} else if (e.getSource() == btnReturn) {

			setVisible(false);

			try {

				NewBeeController.reviewsCheckDisplay();

			} catch (Exception ex) {

				ControlUtility.systemErrorMessage(this, ex);
			}
		}
	}
}
