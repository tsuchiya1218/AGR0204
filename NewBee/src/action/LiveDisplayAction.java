/**
 * クラス名：	CustomerSearchDisplayAction
 * 概要　　：	顧客情報検索画面表示アクション
 * 作成者名：
 * 作成日　：
 * 修正者名：
 * 修正日　：
 */

package action;

import view.LiveAddFrame;
import view.LiveFrame;
import view.LiveUpdateFrame;

public class LiveDisplayAction {

	public void execute() {
		new LiveFrame();
	}
	
	public void executeAdd() {
		new LiveAddFrame();
	}
	
	public void executeUpdata() {
		new LiveUpdateFrame();
	}
}
