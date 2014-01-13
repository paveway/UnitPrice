package info.paveway.unitprice;

import java.text.NumberFormat;

import android.app.Activity;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.ads.AdView;

public class MainActivity extends Activity {

    /** 数字アレー */
    private static final SparseArray<String> NUMBER_ARRAY;

    // 数字アレーを初期化する。
    static {
        NUMBER_ARRAY = new SparseArray<String>();
        NUMBER_ARRAY.put(R.id.oneButton,   "1");
        NUMBER_ARRAY.put(R.id.twoButton,   "2");
        NUMBER_ARRAY.put(R.id.threeButton, "3");
        NUMBER_ARRAY.put(R.id.fourButton,  "4");
        NUMBER_ARRAY.put(R.id.fiveButton,  "5");
        NUMBER_ARRAY.put(R.id.sixButton,   "6");
        NUMBER_ARRAY.put(R.id.sevenButton, "7");
        NUMBER_ARRAY.put(R.id.eightButton, "8");
        NUMBER_ARRAY.put(R.id.nineButton,  "9");
    }

    private AdView mAdView;
    private EditText mQuantity1Value;
    private EditText mQuantity2Value;
    private EditText mSetsNum1Value;
    private EditText mSetsNum2Value;
    private EditText mPrice1Value;
    private EditText mPrice2Value;
    private TextView mUnitPrice1Value;
    private TextView mUnitPrice2Value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        mQuantity1Value  = (EditText)findViewById(R.id.quantity1Value);
        mQuantity2Value  = (EditText)findViewById(R.id.quantity2Value);
        mSetsNum1Value   = (EditText)findViewById(R.id.setsNum1Value);
        mSetsNum2Value   = (EditText)findViewById(R.id.setsNum2Value);
        mPrice1Value     = (EditText)findViewById(R.id.price1Value);
        mPrice2Value     = (EditText)findViewById(R.id.price2Value);
        mUnitPrice1Value = (TextView)findViewById(R.id.unitPrice1Value);
        mUnitPrice2Value = (TextView)findViewById(R.id.unitPrice2Value);

        // キーパッドを非表示にする。
        mQuantity1Value.setInputType(0);
        mQuantity2Value.setInputType(0);
        mSetsNum1Value.setInputType(0);
        mSetsNum2Value.setInputType(0);
        mPrice1Value.setInputType(0);
        mPrice2Value.setInputType(0);

        mQuantity1Value.setOnClickListener( new OnClickListenerImpl());
        mQuantity2Value.setOnClickListener( new OnClickListenerImpl());
        mSetsNum1Value.setOnClickListener(  new OnClickListenerImpl());
        mSetsNum2Value.setOnClickListener(  new OnClickListenerImpl());
        mPrice1Value.setOnClickListener(    new OnClickListenerImpl());
        mPrice2Value.setOnClickListener(    new OnClickListenerImpl());

        ((TextView)findViewById(R.id.goods1Label)).setOnClickListener( new OnClickListenerImpl());
        ((TextView)findViewById(R.id.goods2Label)).setOnClickListener( new OnClickListenerImpl());

        ((Button)findViewById(R.id.calcButton)).setOnClickListener(    new OnClickListenerImpl());

        ((Button)findViewById(R.id.dotButton)).setOnClickListener(     new OnClickListenerImpl());
        ((Button)findViewById(R.id.bsButton)).setOnClickListener(      new OnClickListenerImpl());

        ((Button)findViewById(R.id.zeroButton)).setOnClickListener(    new OnClickListenerImpl());
        ((Button)findViewById(R.id.oneButton)).setOnClickListener(     new OnClickListenerImpl());
        ((Button)findViewById(R.id.twoButton)).setOnClickListener(     new OnClickListenerImpl());
        ((Button)findViewById(R.id.threeButton)).setOnClickListener(   new OnClickListenerImpl());
        ((Button)findViewById(R.id.fourButton)).setOnClickListener(    new OnClickListenerImpl());
        ((Button)findViewById(R.id.fiveButton)).setOnClickListener(    new OnClickListenerImpl());
        ((Button)findViewById(R.id.sixButton)).setOnClickListener(     new OnClickListenerImpl());
        ((Button)findViewById(R.id.sevenButton)).setOnClickListener(   new OnClickListenerImpl());
        ((Button)findViewById(R.id.eightButton)).setOnClickListener(   new OnClickListenerImpl());
        ((Button)findViewById(R.id.nineButton)).setOnClickListener(    new OnClickListenerImpl());

    }

    /**
     * 終了する時に呼び出される。
     */
    @Override
    public void onDestroy() {
        // ADViewが有効な場合
        if (null != mAdView) {
            // ADViewの終了処理を行う。
            mAdView.destroy();
        }

        // スーパークラスのメソッドを呼び出す。
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    /**
     * ボタンクリックリスナークラス
     *
     */
    private class OnClickListenerImpl implements OnClickListener {

        /**
         * ボタンがクリックされた時に呼び出される。
         *
         * @param v クリックされたボタン
         */
        @Override
        public void onClick(View v) {
            // ボタンのIDを取得する。
            int id = v.getId();

            // ボタンにより処理を判別する。
            switch (id) {
            case R.id.goods1Label:
                mQuantity1Value.setText("");
                mSetsNum1Value.setText("");
                mPrice1Value.setText("");
                mUnitPrice1Value.setText("");
                break;

            case R.id.goods2Label:
                mQuantity2Value.setText("");
                mSetsNum2Value.setText("");
                mPrice2Value.setText("");
                mUnitPrice2Value.setText("");
                break;

            case R.id.quantity1Value:
                mQuantity1Value.setText("");
                mUnitPrice1Value.setText("");
                break;

            case R.id.quantity2Value:
                mQuantity2Value.setText("");
                mUnitPrice2Value.setText("");
                break;

            case R.id.setsNum1Value:
                mSetsNum1Value.setText("");
                mUnitPrice1Value.setText("");
                break;

            case R.id.setsNum2Value:
                mSetsNum2Value.setText("");
                mUnitPrice2Value.setText("");
                break;

            case R.id.price1Value:
                mPrice1Value.setText("");
                mUnitPrice1Value.setText("");
                break;

            case R.id.price2Value:
                mPrice2Value.setText("");
                mUnitPrice2Value.setText("");
                break;

            // 計算ボタンの場合
            case R.id.calcButton:
                if (mQuantity1Value.isFocused()) {
                    if (isNotNullOrEmpty(mQuantity1Value.getText().toString())) {
                        mSetsNum1Value.requestFocus();
                    }

                } else if (mSetsNum1Value.isFocused()) {
                    if (isNotNullOrEmpty(mSetsNum1Value.getText().toString())) {
                        mPrice1Value.requestFocus();
                    }

                } else if (mPrice1Value.isFocused()) {
                    if (isNotNullOrEmpty(mPrice1Value.getText().toString())) {
                        mQuantity2Value.requestFocus();
                    }

                } else if (mQuantity2Value.isFocused()) {
                    if (isNotNullOrEmpty(mQuantity2Value.getText().toString())) {
                        mSetsNum2Value.requestFocus();
                    }

                } else if (mSetsNum2Value.isFocused()) {
                    if (isNotNullOrEmpty(mSetsNum2Value.getText().toString())) {
                        mPrice2Value.requestFocus();
                    }

                } else if (mPrice2Value.isFocused()) {
                    if (isNotNullOrEmpty(mPrice2Value.getText().toString())) {
                        mQuantity1Value.requestFocus();
                    }
                }

                String quantity1Value = mQuantity1Value.getText().toString();
                String setsNum1Value  = mSetsNum1Value.getText().toString();
                String price1Value    = mPrice1Value.getText().toString();
                setUnitPrice(quantity1Value, setsNum1Value, price1Value, mUnitPrice1Value);

                String quantity2Value = mQuantity2Value.getText().toString();
                String setsNum2Value  = mSetsNum2Value.getText().toString();
                String price2Value    = mPrice2Value.getText().toString();
                setUnitPrice(quantity2Value, setsNum2Value, price2Value, mUnitPrice2Value);
                break;

            // BSボタンの場合
            case R.id.bsButton:
                doBsButton();
                if (isNullOrEmpty(mQuantity1Value.getText().toString()) ||
                    isNullOrEmpty(mSetsNum1Value.getText().toString()) ||
                    isNullOrEmpty(mPrice1Value.getText().toString())) {
                    mUnitPrice1Value.setText("");
                }

                if (isNullOrEmpty(mQuantity2Value.getText().toString()) ||
                    isNullOrEmpty(mSetsNum2Value.getText().toString()) ||
                    isNullOrEmpty(mPrice2Value.getText().toString())) {
                    mUnitPrice2Value.setText("");
                }
                break;

            // .ボタンの場合
            case R.id.dotButton:
                doDotButton();
                break;

            // 0ボタンの場合
            case R.id.zeroButton:
                doZeroButton();
                break;

            // 1～9ボタンの場合
            case R.id.oneButton:
            case R.id.twoButton:
            case R.id.threeButton:
            case R.id.fourButton:
            case R.id.fiveButton:
            case R.id.sixButton:
            case R.id.sevenButton:
            case R.id.eightButton:
            case R.id.nineButton:
                doNumberButton(id);
                break;
            }
        }

        /**
         * フォーカスされた入力ウィジットを取得する。
         *
         * @return フォーカスされた入力ウィジット
         */
        private EditText getTarget() {
            if (mQuantity1Value.isFocused()) {
                return mQuantity1Value;

            } else if (mQuantity2Value.isFocused()) {
                return mQuantity2Value;

            } else if (mSetsNum1Value.isFocused()) {
                return mSetsNum1Value;

            } else if (mSetsNum2Value.isFocused()) {
                return mSetsNum2Value;

            } else if (mPrice1Value.isFocused()) {
                return mPrice1Value;

            } else if (mPrice2Value.isFocused()) {
                return mPrice2Value;
            }

            return null;
        }

        /**
         * BSボタンの処理を行う。
         */
        private void doBsButton() {
            // 入力対象を取得する。
            EditText target = getTarget();

            // 入力対象が取得できた場合
            if (null != target) {
                // 入力済みの値を取得する。
                String value = target.getText().toString();

                // 入力済みの値がある場合
                if (!"".equals(value)) {
                    // 最右端の値を削除する。
                    int length = value.length();
                    String newValue = value.substring(0, length - 1);
                    if (!"".equals(newValue)) {
                        double num = Double.parseDouble(newValue.replaceAll(",", ""));
                        NumberFormat format = NumberFormat.getNumberInstance();
                        target.setText(format.format(num));

                    } else {
                        target.setText("");
                    }
                }
            }
        }

        /**
         * "."ボタンの処理を行う。
         */
        private void doDotButton() {
            // 入力対象を取得する。
            EditText target = getTarget();

            // 入力対象が取得できた場合
            if (null != target) {
                // 入力済みの値を取得する。
                   String value = target.getText().toString();

                   // 入力済みかつ"."が未入力の場合
                   if (!"".equals(value) && (-1 == value.indexOf("."))) {
                       // "."を追加する。
                       target.setText(value + ".");
                   }
            }
        }

        /**
         * 0ボタンの処理を行う。
         */
        private void doZeroButton() {
            // 入力対象を取得する。
            EditText target = getTarget();

            // 入力対象が取得できた場合
            if (null != target) {
                // 入力済みの値を取得する。
                String value = target.getText().toString();

                   // "0"のみ入力されていない場合
                if (!"0".equals(value)) {
                    // "0"を追加する。
                    String newValue = target.getText().toString() + "0";
                    double num = Double.parseDouble(newValue.replaceAll(",", ""));
                    NumberFormat format = NumberFormat.getNumberInstance();
                    target.setText(format.format(num));
                }
            }
        }

        /**
         * 数字ボタンの処理を行う。
         *
         * @param id ボタンID
         */
        private void doNumberButton(int id) {
               // 入力対象を取得する。
            EditText target = getTarget();

            // 入力対象が取得できた場合
            if (null != target) {
                // 入力済みの値を取得する。
                String value = target.getText().toString();

                // "0"のみ入力されていない場合
                if (!"0".equals(value)) {
                    // 数字を追加する。
                    String newValue = target.getText().toString() + NUMBER_ARRAY.get(id);
                    double num = Double.parseDouble(newValue.replaceAll(",", ""));
                    NumberFormat format = NumberFormat.getNumberInstance();
                    target.setText(format.format(num));
                }
            }
        }

        /**
         * 単価を設定する。
         *
         * @param quantityValue 数量
         * @param setsNumValue セット数
         * @param priceValue 価格
         * @param unitPriceValue セットする単価TextView
         */
        private void setUnitPrice(String quantityValue, String setsNumValue, String priceValue, TextView unitPriceValue) {
            if (isNotNullOrEmpty(quantityValue) && isNotNullOrEmpty(setsNumValue) && isNotNullOrEmpty(priceValue)) {
                double quantity  = Double.parseDouble(quantityValue.replaceAll(",", ""));
                double setsNum   = Double.parseDouble(setsNumValue.replaceAll(",", ""));
                double price     = Double.parseDouble(priceValue.replaceAll(",", ""));
                double unitPrice = price / (double)(quantity * setsNum);
                unitPriceValue.setText(String.valueOf(unitPrice));
            }
        }
    }

    /**
     * nullか空文字列ではないかチェックする。
     *
     * @param src チェックする文字列
     * @return true:nullまたは空文字列ではない / false:nullまたは空文字列
     */
    private boolean isNotNullOrEmpty(String src) {
        return ((null != src) && !"".equals(src));
    }

    private boolean isNullOrEmpty(String src) {
        return !isNotNullOrEmpty(src);
    }
}
