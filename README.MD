# ParabolaEmojiRain
抛物线表情雨效果

## How to use

### xml
```
<me.next.library.EmojiFrameLayout
    android:id="@+id/emoji_frame_layout"
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <!-- Your layout. -->

</me.next.library.EmojiFrameLayout>
```

### code
```
EmojiFrameLayout emojiFrameLayout = (EmojiFrameLayout) findViewById(R.id.emoji_frame_layout);
emojiFrameLayout.addEmoji(R.drawable.d_doge);
emojiFrameLayout.addEmoji(R.drawable.d_miao);
emojiFrameLayout.startEmojiRain();
```
## Preview
![preview](http://ww1.sinaimg.cn/mw690/6db4aff6gy1fjfqq95guhg20a80i80yn.gif)

## Thanks To
[https://github.com/Luolc/EmojiRain](https://github.com/Luolc/EmojiRain)
[https://github.com/xandone/ParabolaBlast](https://github.com/xandone/ParabolaBlast)

## License

    Copyright 2017 NeXT

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.