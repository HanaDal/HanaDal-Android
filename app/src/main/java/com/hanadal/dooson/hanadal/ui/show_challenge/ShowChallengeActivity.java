package com.hanadal.dooson.hanadal.ui.show_challenge;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.hanadal.dooson.hanadal.R;
import com.hanadal.dooson.hanadal.connect.Connector;
import com.hanadal.dooson.hanadal.connect.Res;
import com.hanadal.dooson.hanadal.data.ChallengeDetail;
import com.hanadal.dooson.hanadal.data.Diary;
import com.hanadal.dooson.hanadal.util.UtilClass;

import java.util.ArrayList;

import br.tiagohm.markdownview.MarkdownView;
import br.tiagohm.markdownview.css.styles.Github;

// Todo("UI 기능 구현")
public class ShowChallengeActivity extends AppCompatActivity{

    private MarkdownView markdownView;
    private ArrayList<TextView> days = new ArrayList<>();
    private boolean isMine = false;
    private ArrayList<Diary> diaryList = new ArrayList<>();
    private int day;

    private Button btnWriteDiary;

    String token =
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjViOWFlNmJjMzY1ZjBlMTNhODVhMTQ0YSIsImlhdCI6MTUzODAyNTcwMSwiZXhwIjoxNTQwNjE3NzAxLCJpc3MiOiJoYW5hZGFsLXNlcnZlciJ9.9Ar-ElYJYpe_h9jet6TP3egDmr7vSpwuaz8mh-rr5Nc";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_challenge);

        setDays();

        btnWriteDiary = findViewById(R.id.btn_write_diary);
        btnWriteDiary.setVisibility(View.GONE);

        markdownView = findViewById(R.id.md_diary);
        markdownView.addStyleSheet(new Github());

        Intent intent = getIntent();
        Connector.api.showChallenge(token, intent.getExtras().getString("id"))
                .enqueue(new Res<ChallengeDetail>(getApplicationContext()) {
            @Override
            public void callback(int code, ChallengeDetail body) {
                if(code == 200){
                    days.get(body.day).setBackground(getResources().getDrawable(R.drawable.purple_circle, null));
                    days.get(body.day).setTextColor(Color.WHITE);
                    isMine = body.isMine;
                    day = body.day;
                    diaryList = body.diary;

                    if(isMine) btnWriteDiary.setVisibility(View.VISIBLE);

                    if(diaryList.size() > 0)
                        markdownView.loadMarkdown("# " + body.diary.get(body.day).title + "\n" + "\n" + body.diary.get(body.day).content);
                }
            }
        });
    }

    public void onDaysClick(View v){
        TextView tmp = (TextView)v;
        int chooseDay = Integer.parseInt(tmp.getText().toString())-1;

        if(chooseDay <= day) {
            for (TextView tv : days) {
                if (tv == tmp) {
                    tv.setBackground(getResources().getDrawable(R.drawable.purple_circle, null));
                    tv.setTextColor(Color.WHITE);

                    if (diaryList.size() > 0)
                        markdownView.loadMarkdown("# " + diaryList.get(day).title + "\n" + "\n" + diaryList.get(day).content);
                    else
                        markdownView.loadMarkdown("");

                    if (chooseDay == day) btnWriteDiary.setVisibility(View.VISIBLE);
                    else btnWriteDiary.setVisibility(View.GONE);
                } else {
                    tv.setBackground(getResources().getDrawable(R.drawable.white_circle, null));
                    tv.setTextColor(Color.argb(255, 80, 80, 80));
                }
            }
        }else{
            UtilClass.Toast(getApplicationContext(), "아직 진행되지 않은 Day 입니다.");
        }
    }

    @SuppressLint("FindViewByIdCast")
    private void setDays(){
        days.add(findViewById(R.id.day_1));
        days.add(findViewById(R.id.day_2));
        days.add(findViewById(R.id.day_3));
        days.add(findViewById(R.id.day_4));
        days.add(findViewById(R.id.day_5));
        days.add(findViewById(R.id.day_6));
        days.add(findViewById(R.id.day_7));
        days.add(findViewById(R.id.day_8));
        days.add(findViewById(R.id.day_9));
        days.add(findViewById(R.id.day_10));
        days.add(findViewById(R.id.day_11));
        days.add(findViewById(R.id.day_12));
        days.add(findViewById(R.id.day_13));
        days.add(findViewById(R.id.day_14));
        days.add(findViewById(R.id.day_15));
        days.add(findViewById(R.id.day_16));
        days.add(findViewById(R.id.day_17));
        days.add(findViewById(R.id.day_18));
        days.add(findViewById(R.id.day_19));
        days.add(findViewById(R.id.day_20));
        days.add(findViewById(R.id.day_21));
        days.add(findViewById(R.id.day_22));
        days.add(findViewById(R.id.day_23));
        days.add(findViewById(R.id.day_24));
        days.add(findViewById(R.id.day_25));
        days.add(findViewById(R.id.day_26));
        days.add(findViewById(R.id.day_27));
        days.add(findViewById(R.id.day_28));
        days.add(findViewById(R.id.day_29));
        days.add(findViewById(R.id.day_30));
    }
}

/*
        markdownView.loadMarkdown("# MarkdownView\n" +
                "\n" +
                "Android library to display markdown text.\n" +
                "\n" +
                "It uses [Flexmark](https://github.com/vsch/flexmark-java) and some of its extensions.\n" +
                "\n" +
                "## Setup\n" +
                "\n" +
                "Add it in your root build.gradle at the end of repositories:\n" +
                "```gradle\n" +
                "allprojects {\n" +
                "\t\trepositories {\n" +
                "\t\t\t...\n" +
                "\t\t\tmaven { url 'https://jitpack.io' }\n" +
                "\t\t}\n" +
                "\t}\n" +
                "```\n" +
                "Add the dependency:\n" +
                "```gradle\n" +
                "implementation 'com.github.tiagohm.MarkdownView:library:LATEST-VERSION'\n" +
                "```\n" +
                "\n" +
                "LATEST-VERSION is [![](https://jitpack.io/v/tiagohm/MarkdownView.svg)](https://jitpack.io/#tiagohm/MarkdownView)\n" +
                "\n" +
                "## Usage\n" +
                "\n" +
                "```xml\n" +
                "<br.tiagohm.markdownview.MarkdownView\n" +
                " android:id=\"@+id/markdown_view\"\n" +
                " app:escapeHtml=\"false\"\n" +
                " android:layout_width=\"match_parent\"\n" +
                " android:layout_height=\"match_parent\"/>\n" +
                "```\n" +
                "```java\n" +
                "mMarkdownView = (MarkdownView)findViewById(R.id.markdown_view);\n" +
                "mMarkdownView.addStyleSheet(new Github());\n" +
                "mMarkdownView.loadMarkdown(\"**MarkdownView**\");\n" +
                "mMarkdownView.loadMarkdownFromAsset(\"markdown1.md\");\n" +
                "mMarkdownView.loadMarkdownFromFile(new File());\n" +
                "mMarkdownView.loadMarkdownFromUrl(\"url\");\n" +
                "```\n" +
                "\n" +
                "## Using Emojies\n" +
                "##### Without internet\n" +
                "Add the dependency:\n" +
                "```gradle\n" +
                "implementation 'com.github.tiagohm.MarkdownView:emoji:LATEST-VERSION'\n" +
                "```\n" +
                "\n" +
                "## Custom CSS\n" +
                "\n" +
                "```java\n" +
                "//InternalStyleSheet css = new InternalStyleSheet();\n" +
                "InternalStyleSheet css = new Github();\n" +
                "css.addFontFace(\"MyFont\", \"condensed\", \"italic\", \"bold\", \"url('myfont.ttf')\");\n" +
                "css.addMedia(\"screen and (min-width: 1281px)\");\n" +
                "css.addRule(\"h1\", \"color: orange\");\n" +
                "css.endMedia();\n" +
                "css.addRule(\"h1\", \"color: green\", \"font-family: MyFont\");\n" +
                "mMarkdownView.addStyleSheet(css);\n" +
                "\n" +
                "mMarkdownView.addStyleSheet(ExternalStyleSheet.fromAsset(\"github.css\", null);\n" +
                "mMarkdownView.addStyleSheet(ExternalStyleSheet.fromAsset(\"github2.css\", \"screen and (min-width: 1281px)\");\n" +
                "```\n" +
                "\n" +
                "## JavaScript\n" +
                "```java\n" +
                "JavaScript js = new ExternalJavaScript(url, async, defer);\n" +
                "mMarkdownView.addJavascript(js);\n" +
                "```\n" +
                "\n" +
                "## Twitter\n" +
                "\n" +
                "#### Embed a Single Tweet\n" +
                "From *https://twitter.com/RealGrumpyCat/status/845101936550469634*\n" +
                "* `#[tweet](845101936550469634)`\n" +
                "* `#[tweet-hide-cards](845101936550469634)`\n" +
                "\n" +
                "#### Follow Button\n" +
                "From *https://twitter.com/tiag0hm*\n" +
                "* `#[follow](tiag0hm)`\n" +
                "\n" +
                "## Themes\n" +
                "* GitHub\n" +
                "\n" +
                "## Support\n" +
                "\n" +
                "- [x] Bold `**Text**` or `__Text__`\n" +
                "- [x] Italic `*Text*` or `_Text_`\n" +
                "- [x] Strikethrough `~~Text~~`\n" +
                "- [x] Horizontal Rules `---`\n" +
                "- [x] Headings `#`\n" +
                "- [x] Links `[alt](url)`\n" +
                "- [x] Images `![alt](url)`\n" +
                "- [x] Code\n" +
                "- [x] Blockquote\n" +
                "- [x] Nested Blockquote\n" +
                "- [x] Lists\n" +
                "- [x] Tables\n" +
                "- [x] TaskList\n" +
                "- [x] AutoLink\n" +
                "- [x] Abbreviation\n" +
                "- [x] Mark `==Text==`\n" +
                "- [x] Subscript `H~2~O`\n" +
                "- [x] Superscript `10^-10^`\n" +
                "- [x] Keystroke `@ctrl+alt+del@`\n" +
                "- [x] MathJax Inline `$x = {-b \\pm \\sqrt{b^2-4ac} \\over 2a}$`\n" +
                "- [x] MathJax `$$x = {-b \\pm \\sqrt{b^2-4ac} \\over 2a}$$`\n" +
                "- [x] Footnotes\n" +
                "- [x] Image Resizing `![alt](url@100px|auto)`\n" +
                "- [x] Syntax Highlighting (using [Highlight.js](https://highlightjs.org/))\n" +
                "- [x] Emoji ([EmojiOne v2](http://emojione.com)) `:smile:`\n" +
                "- [x] Custom CSS\n" +
                "- [x] Youtube `@[youtube](fM-J08X6l_Q)`\n" +
                "- [x] Twitter\n" +
                "- [x] JavaScripts\n" +
                "- [x] Label `--DEFAULT--` `---SUCCESS---` `----WARNING----` `-----DANGER-----`\n" +
                "- [x] Bean `{{fieldName}}`\n" +
                "- [x] Custom Attributes `{ #id .class name=value name='value'}`\n" +
                "- [x] Text Align, Text Size and Text Colors using Custom Attributes\n" +
                "\n" +
                "<img width='380' src='https://raw.githubusercontent.com/tiagohm/MarkdownView/master/1.png'/>\n" +
                "<img width='380' src='https://raw.githubusercontent.com/tiagohm/MarkdownView/master/2.png'/>\n" +
                "<img width='380' src='https://raw.githubusercontent.com/tiagohm/MarkdownView/master/3.png'/>\n" +
                "<img width='380' src='https://raw.githubusercontent.com/tiagohm/MarkdownView/master/4.png'/>\n" +
                "<img width='380' src='https://raw.githubusercontent.com/tiagohm/MarkdownView/master/5.png'/>\n" +
                "<img width='380' src='https://raw.githubusercontent.com/tiagohm/MarkdownView/master/6.png'/>\n" +
                "<img width='380' src='https://raw.githubusercontent.com/tiagohm/MarkdownView/master/7.png'/>\n" +
                "<img width='380' src='https://raw.githubusercontent.com/tiagohm/MarkdownView/master/8.png'/>\n" +
                "\n" +
                "## LICENSE\n" +
                "```\n" +
                "Copyright 2017-2018 tiagohm\n" +
                "\n" +
                "Licensed under the Apache License, Version 2.0 (the \"License\");\n" +
                "you may not use this file except in compliance with the License.\n" +
                "You may obtain a copy of the License at\n" +
                "\n" +
                "   http://www.apache.org/licenses/LICENSE-2.0\n" +
                "\n" +
                "Unless required by applicable law or agreed to in writing, software\n" +
                "distributed under the License is distributed on an \"AS IS\" BASIS,\n" +
                "WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\n" +
                "See the License for the specific language governing permissions and\n" +
                "limitations under the License.\n" +
                "```\n");*/
