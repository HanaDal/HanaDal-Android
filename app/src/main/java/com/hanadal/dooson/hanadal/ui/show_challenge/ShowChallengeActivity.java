package com.hanadal.dooson.hanadal.ui.show_challenge;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.hanadal.dooson.hanadal.R;

import java.io.File;

import br.tiagohm.markdownview.MarkdownView;
import br.tiagohm.markdownview.css.styles.Github;

// Todo("UI 기능 구현")
public class ShowChallengeActivity extends AppCompatActivity {

    MarkdownView markdownView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_challenge);

        markdownView = findViewById(R.id.md_diary);
        markdownView.addStyleSheet(new Github());
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
                "```\n");
    }
}
