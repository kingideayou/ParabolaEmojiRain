package me.next.parabolaemojirain;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import me.next.library.EmojiFrameLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EmojiFrameLayout emojiFrameLayout = (EmojiFrameLayout) findViewById(R.id.emoji_frame_layout);
        emojiFrameLayout.addEmoji(R.drawable.d_doge);
        emojiFrameLayout.addEmoji(R.drawable.d_miao);
        emojiFrameLayout.addEmoji(R.drawable.d_doge);
        emojiFrameLayout.addEmoji(R.drawable.d_miao);

        final EditText editText = (EditText) findViewById(R.id.et_comment);

        findViewById(R.id.bt_send).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ("doge".equals(editText.getText().toString())) {
                    emojiFrameLayout.generateEmojis();
                }
                editText.setText("");
            }
        });
    }
}
