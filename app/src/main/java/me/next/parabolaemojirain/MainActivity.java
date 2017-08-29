package me.next.parabolaemojirain;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import me.next.library.EmojiFrameLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EmojiFrameLayout emojiFrameLayout = (EmojiFrameLayout) findViewById(R.id.emoji_frame_layout);
        emojiFrameLayout.addEmoji(R.mipmap.ic_launcher);
        emojiFrameLayout.addEmoji(R.mipmap.ic_launcher_round);
        emojiFrameLayout.addEmoji(R.mipmap.ic_launcher);
        emojiFrameLayout.addEmoji(R.mipmap.ic_launcher_round);
        emojiFrameLayout.generateEmojis();
    }
}
