package com.xsc.lottery.util;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.swing.text.MutableAttributeSet;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.HTMLEditorKit.ParserCallback;

@SuppressWarnings("serial")
public class FetchlDataUtil
{
    public FetchlDataUtil()
    {
    }

    public List<String> parserHtml(String url, HTML.Tag htmlTag) throws Exception
    {
        String context;
        try {
            context = NetWorkUtil.requestNet(url, "gbk", 10);
            System.out.println(context);
        } 
        catch (Exception e1) {
            throw new Exception(url + "路径资源抓取失效");
        }
        SnatchResult snatchResult = new SnatchResult();
        FetchParser fetchParser = new FetchParser();
        try {
            snatchResult.getHtmlParser().parse(new StringReader(context),
                    fetchParser, true);
            return fetchParser.getKeyList();
        }
        catch (IOException e) {
            throw new Exception(url + "解析程序出错");
        }

    }

    class SnatchResult extends HTMLEditorKit
    {
        public SnatchResult()
        {
        }

        public HTMLEditorKit.Parser getHtmlParser()
        {
            return super.getParser();
        }
    }

    class FetchParser extends ParserCallback
    {
        private Map<Integer, String> resultMap = new Hashtable<Integer, String>();
        private List<String> keyList = new ArrayList<String>();
        private boolean isResult = false;
        private HTML.Tag htmlTag = HTML.Tag.TR;

        public void handleComment(char[] data, int pos)
        {
        }

        public void handleEndTag(HTML.Tag t, int pos)
        {
            if (t == htmlTag) {
                isResult = false;
            }
        }

        public void handleError(String errorMsg, int pos)
        {
        }

        public void handleSimpleTag(HTML.Tag t, MutableAttributeSet a, int pos)
        {
            handleStartTag(t, a, pos);
        }

        public void handleStartTag(HTML.Tag t, MutableAttributeSet a, int pos)
        {
            if (t == htmlTag) {
                isResult = true;
            }
        }

        public void handleText(char[] data, int pos)
        {
            if (isResult) {
                keyList.add(String.valueOf(data));
                resultMap.put(Integer.valueOf(pos), String.valueOf(data));
            }
        }

        public Map<Integer, String> getResultMap()
        {
            return resultMap;
        }

        public List<String> getKeyList()
        {
            return keyList;
        }

        public void setHtmlTag(HTML.Tag htmlTag)
        {
            this.htmlTag = htmlTag;
        }

    }

}
